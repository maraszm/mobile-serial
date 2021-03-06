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

/**
 *
 * @author Michal Marasz  
 */
public interface SerialConnection {

    void connect();

    void disconnect();

    Optional<byte[]> receiveBytes(final int bytesNumber);

    void sendBytes(final byte[] message);
}
