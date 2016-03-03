/* 
 * Copyright (c) 2016 Michal Marasz.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Michal Marasz - initial API and implementation and/or initial documentation
 */
package pl.ekozefir.mobile.serial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.log4j.Logger;
import pl.ekozefir.mobile.serial.centralcommand.MobileCommand;
import pl.ekozefir.mobile.serial.centralcommand.value.ChangeCentralCreator;
import pl.ekozefir.mobile.serial.centralcommand.value.ParameterRequestCreator;
import pl.ekozefir.mobile.serial.centralstate.Response;

/**
 *
 * @author Michal Marasz
 */
public final class EkozefirMessageReceiverSender {

    private static final Logger log = Logger.getLogger(EkozefirMessageReceiverSender.class);
    private static final List<Character> ekotouchCentralNames = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H');
    private static final char digitalStandardCentralName = 'X';
    private static final byte firstByteOfParameterRequest = (byte) 0xAA;
    private static final byte secondByteOfParameterRequest = (byte) 0x55;
    private final EkozefirSerialConnection serial;

    public EkozefirMessageReceiverSender(final EkozefirSerialConnection serial) {
        this.serial = serial;
    }

    public Optional<Response> sendAndReceiveMessage(final MobileCommand command) {
        log.debug("Try to send and receive message");
        log.debug("Send message for change central");
        serial.sendMessage(new ChangeCentralCreator().create(null, command.getCentralId()));
        log.debug("Sending message");
        serial.sendMessage(command);
        byte[] commandBytes = command.getCommand();
        if (commandBytes[0] == firstByteOfParameterRequest && commandBytes[1] == secondByteOfParameterRequest) {
            log.debug("Waiting for parameters");
            Optional<byte[]> maybeBytes = serial.receiveBytes();
            if (maybeBytes.isPresent()) {
                try {
                    Response message = new Response(maybeBytes.get());
                    return Optional.of(message);
                } catch (Exception ex) {
                    log.error("Message from central is wrong! Closing application");
                }
            } else {
                log.error("Message from central is not comming!");
            }
        }
        return Optional.empty();
    }

    public void sendMessage(final MobileCommand command) {
        serial.sendMessage(command);
    }

    public void start() {
        serial.connect();
    }

    public void stop() {
        serial.disconnect();
    }

    public List<Response> getEkotouchCentrals() {
        return ekotouchCentralNames.stream().
                map((centralName) -> sendAndReceiveMessage(new ParameterRequestCreator().create(null, centralName))).
                filter(optional -> optional.isPresent()).
                map(optional -> optional.get()).
                collect(Collectors.toCollection(ArrayList::new));
    }

    public Optional<Response> getStandardDigitalCentralIfAvailable() {
        return sendAndReceiveMessage(new ParameterRequestCreator().create(null, digitalStandardCentralName));
    }
}
