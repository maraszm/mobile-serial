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

import com.google.common.collect.Lists;
import pl.ekozefir.mobile.serial.centralstate.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.log4j.Logger;
import pl.ekozefir.mobile.serial.centralcommand.MobileCommand;
import pl.ekozefir.mobile.serial.connection.RawSerialConnection;
import pl.ekozefir.mobile.serial.connection.RawSerialConnectionWithTries;

/**
 *
 * @author Michal Marasz
 */
public final class EkozefirCentralReceiverSender {

    private static final Logger log = Logger.getLogger(EkozefirCentralReceiverSender.class);
    private static final int timeout = 10;
    private final EkozefirMessageReceiverSender receiverSender;
    private ExecutorService service;

    public EkozefirCentralReceiverSender(final int baud, final String device) {
        this.receiverSender = new EkozefirMessageReceiverSender(
                new EkozefirSerialConnection(
                        new EkozefirByteReceiverSender(
                                new RawSerialConnectionWithTries(
                                        new RawSerialConnection(baud, device)))));
    }

    public Optional<Response> sendAndReceiveMessage(MobileCommand command) {
        Future<Optional<Response>> message = service.submit(() -> {
            return receiverSender.sendAndReceiveMessage(command);
        });
        try {
            return message.get(timeout, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException ex) {
            log.error("Error while sending message", ex);
            return Optional.empty();
        }
    }

    public void sendMessage(MobileCommand command) {
        service.submit(() -> receiverSender.sendMessage(command));
    }

    public List<Response> receiveCentralList() {
        Future<List<Response>> message = service.submit(() -> {
            Optional<Response> maybeStandardDigital = receiverSender.getStandardDigitalCentralIfAvailable();
            if (maybeStandardDigital.isPresent()) {
                return Arrays.asList(maybeStandardDigital.get());
            } else {
                return receiverSender.getEkotouchCentrals();
            }
        });
        try {
            return message.get(timeout, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException ex) {
            log.error("Error while sending message", ex);
            return Lists.newArrayList();
        }
    }

    public void stop() {
        service.shutdownNow();
        receiverSender.stop();
    }

    public void start() {
        service = Executors.newSingleThreadExecutor();
        receiverSender.start();
    }
}
