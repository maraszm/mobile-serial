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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import org.apache.log4j.Logger;
import pl.ekozefir.mobile.serial.centralcommand.MobileCommand;

/**
 *
 * @author Michal Marasz
 */
public final class EkozefirSerialConnection {

    private static final Logger log = Logger.getLogger(EkozefirSerialConnection.class);
    private final EkozefirByteReceiverSender ekozefirConnection;
    private final byte preambuleByte = (byte) 0xAA;

    public EkozefirSerialConnection(final EkozefirByteReceiverSender connection) {
        this.ekozefirConnection = connection;
    }

    public Optional<byte[]> receiveBytes() {
        Optional<byte[]> maybePreambuleByte = ekozefirConnection.receivePreambule(preambuleByte);
        if (maybePreambuleByte.isPresent()) {
            log.debug("Bytes receivied");
            byte[] preambule = maybePreambuleByte.get();
            if (preambule[0] == preambuleByte) {
                log.debug("Preambule byte is ok");
                Optional<byte[]> maybeBytes = ekozefirConnection.receiveMessage();
                if (maybeBytes.isPresent()) {
                    byte[] message = maybeBytes.get();
                    log.debug("Message bytes received");
                    ByteArrayOutputStream allBytes = new ByteArrayOutputStream(preambule.length + message.length);
                    try {
                        allBytes.write(preambule);
                        allBytes.write(message);
                        return Optional.ofNullable(allBytes.toByteArray());
                    } catch (IOException ex) {
                        log.error("While collecting bytes", ex);
                    }

                }
            }
        }
        return Optional.empty();
    }

    public void sendMessage(final MobileCommand command) {
        ekozefirConnection.sendMessage(command.getCommand());
    }

    public void connect() {
        ekozefirConnection.connect();
    }

    public void disconnect() {
        ekozefirConnection.disconnect();
    }
}
