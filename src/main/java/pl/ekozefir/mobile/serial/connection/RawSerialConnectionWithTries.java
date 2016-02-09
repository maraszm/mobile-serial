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
package pl.ekozefir.mobile.serial.connection;

import java.util.Optional;
import org.apache.log4j.Logger;

/**
 *
 * @author Michal Marasz
 */
public final class RawSerialConnectionWithTries implements SerialConnection {

    private static final Logger log = Logger.getLogger(RawSerialConnectionWithTries.class);
    private static final int tries = 5;
    private static final int waitingTime = 100;
    private final RawSerialConnection connection;

    public RawSerialConnectionWithTries(RawSerialConnection connection) {
        this.connection = connection;
    }

    @Override
    public void connect() {
        connection.connect();
    }

    @Override
    public void disconnect() {
        connection.disconnect();
    }

    @Override
    public Optional<byte[]> receiveBytes(final int bytesNumber) {
        for (int loop = 0; loop < tries; loop++) {
            Optional<byte[]> maybeBytes = connection.receiveBytes(bytesNumber);
            if (maybeBytes.isPresent()) {
                log.debug("Receive bytes:" + SerialConnectionHelper.toStringFromUnsigned(maybeBytes.get()));
                return maybeBytes;
            } else {
                try {
                    log.debug("Wait a while");
                    Thread.sleep(waitingTime);
                } catch (InterruptedException ex) {
                    log.debug("Interputed waiting time");
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void sendBytes(final byte[] message) {
        log.debug("Send bytes: " + SerialConnectionHelper.toStringFromUnsigned(message));
        connection.sendBytes(message);
    }
}
