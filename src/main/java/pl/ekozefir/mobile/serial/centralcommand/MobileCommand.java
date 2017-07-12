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
package pl.ekozefir.mobile.serial.centralcommand;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author Michal Marasz
 */
public class MobileCommand {

    private static final int byteNumber = 5;
    private final byte[] command;
    private final char centralId;

    public MobileCommand(byte[] command, char centralId) {
        Objects.requireNonNull(command);
        if (command.length != byteNumber) {
            throw new IllegalStateException("Wrong number of bytes");
        }
        this.command = Arrays.copyOf(command, command.length);
        this.centralId = centralId;
    }

    public byte[] getCommand() {
        return Arrays.copyOf(command, command.length);
    }

    public char getCentralId() {
        return centralId;
    }

    @Override
    public String toString() {
        return "MobileCommand{" + "command=" + command + ", centralId=" + centralId + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Arrays.hashCode(this.command);
        hash = 67 * hash + this.centralId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MobileCommand other = (MobileCommand) obj;
        if (!Arrays.equals(this.command, other.command)) {
            return false;
        }
        if (this.centralId != other.centralId) {
            return false;
        }
        return true;
    }
    
    

}
