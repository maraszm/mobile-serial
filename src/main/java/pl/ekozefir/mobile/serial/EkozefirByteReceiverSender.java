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

import java.util.Optional;
import org.apache.log4j.Logger;
import pl.ekozefir.mobile.serial.connection.SerialConnection;
import static pl.ekozefir.mobile.serial.connection.SerialConnectionHelper.toStringFromUnsigned;

/**
 *
 * @author Michal Marasz
 */
public final class EkozefirByteReceiverSender {

    private static final Logger log = Logger.getLogger(EkozefirByteReceiverSender.class);
    private static final int preambuleReadTries = 5;
    private static final int preambuleTimeout = 100;
    private static final int preambleLength = 1;
    private static final int messageLength = 50;
    private final SerialConnection connection;

    public EkozefirByteReceiverSender(final SerialConnection serialConnection) {
        this.connection = serialConnection;
    }

    public Optional<byte[]> receivePreambule(final byte preambule) {
        for (int loop = 0; loop < preambuleReadTries; loop++) {
            Optional<byte[]> maybePreambule = tryToReceivePreambule(preambule);
            if (maybePreambule.isPresent()) {
                return maybePreambule;
            }
            try {
                Thread.sleep(preambuleTimeout);
            } catch (InterruptedException ex) {
                log.error("Interrputed waiting for preambule", ex);
            }
        }
        log.debug("Preambule not received " + toStringFromUnsigned(preambule));
        return Optional.empty();
    }

    public Optional<byte[]> receiveMessage() {
        log.debug("Receiving message");
        Optional<byte[]> maybeMessage = connection.receiveBytes(messageLength);
        if (maybeMessage.isPresent()) {
            return maybeMessage;
        }
        return Optional.empty();
    }

    public void sendMessage(final byte[] command) {
        connection.sendBytes(command);
    }

    public void connect() {
        connection.connect();
    }

    public void disconnect() {
        connection.disconnect();
    }

    private Optional<byte[]> tryToReceivePreambule(byte preambule) {
        Optional<byte[]> maybePreambule = connection.receiveBytes(preambleLength);
        if (maybePreambule.isPresent()) {
            byte[] preambuleArray = maybePreambule.get();
            if (preambuleArray.length == preambleLength) {
                byte receivedPreambule = preambuleArray[0];
                if (preambule == receivedPreambule) {
                    log.debug("Received preambule " + toStringFromUnsigned(preambule));
                    return maybePreambule;
                }
            }
        }
        return Optional.empty();
    }
}
