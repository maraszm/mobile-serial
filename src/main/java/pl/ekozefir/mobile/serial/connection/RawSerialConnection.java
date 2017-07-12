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

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;
import org.apache.log4j.Logger;
import static pl.ekozefir.mobile.serial.connection.SerialConnectionHelper.toStringFromUnsigned;
import pl.ekozefir.mobile.serial.exception.SerialConnectionError;

/**
 *
 * @author Michal Marasz
 */
public final class RawSerialConnection implements SerialConnection {

    private static final Logger log = Logger.getLogger(RawSerialConnection.class);
    private final int baud;
    private final String portName;
    private SerialPort serialPort = null;
    private InputStream in = null;
    private OutputStream out = null;

    public RawSerialConnection(final int baud, final String portName) {
        this.baud = baud;
        this.portName = portName;
    }

    @Override
    public void connect() {
        CommPortIdentifier portIdentifier;
        try {
            log.debug("Connecting to port: " + portName);
            portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
            CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);
            serialPort = (SerialPort) commPort;
            serialPort.setSerialPortParams(baud, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            in = serialPort.getInputStream();
            out = serialPort.getOutputStream();
            log.debug("Connection rs232 established");
        } catch (NoSuchPortException | UnsupportedCommOperationException | PortInUseException | IOException ex) {
            log.error("Error while connecting rs232 driver", ex);
            throw new SerialConnectionError(ex);
        }
    }

    @Override
    public void disconnect() {
        log.debug("Disconnecting driver");
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (serialPort != null) {
                serialPort.close();
            }
            serialPort = null;
            in = null;
            log.debug("Disconnected from driver");
        } catch (IOException ex) {
            log.error("Could not disconnect driver", ex);
            throw new SerialConnectionError(ex);
        }
        log.debug("Disconnected driver");
    }

    @Override
    public Optional<byte[]> receiveBytes(final int bytesNumber) {
        log.debug("Receive bytes");
        try {
            log.debug("Try to read bytes");
            if (in.available() >= bytesNumber) {
                log.debug("Bytes available: " + bytesNumber);
                byte[] readedBytes = new byte[bytesNumber];
                in.read(readedBytes, 0, bytesNumber);
                log.debug("Readed bytes: " + toStringFromUnsigned(readedBytes));
                return Optional.of(readedBytes);
            }
            log.debug("Bytes not available");
            return Optional.empty();
        } catch (IOException ex) {
            log.error("Error while receiving bytes", ex);
            throw new SerialConnectionError(ex);
//            return Optional.empty();
        }
    }

    @Override
    public void sendBytes(final byte[] message) {
        log.debug("Executing additional service task");
        log.debug("Sending bytes to driver: " + toStringFromUnsigned(message));
        try {
            log.debug("Wait a while");
            Thread.sleep(250);
            out.write(message);
            out.flush();
        } catch (InterruptedException | IOException ex) {
            log.error("Error while sending bytes to driver", ex);
            throw new SerialConnectionError(ex);
        }
    }
}
