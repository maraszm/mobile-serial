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

import java.util.Objects;

/**
 *
 * @author Michal Marasz  
 */
public final class SerialConnectionHelper {

    private SerialConnectionHelper() {
    }

    public static String toStringFromUnsigned(final byte[] data) {
        Objects.requireNonNull(data);
        StringBuilder build = new StringBuilder();
        for (byte byteValue : data) {
            build.append(" ").append(0xFF & byteValue);
        }
        return build.toString();
    }

    public static String toStringFromUnsigned(final byte data) {
        StringBuilder build = new StringBuilder();
        build.append(" ").append(0xFF & data);
        return build.toString();
    }
}
