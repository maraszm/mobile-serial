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
package pl.ekozefir.mobile.serial.centralstate.value;

import pl.ekozefir.mobile.serial.centralstate.Response;
import pl.ekozefir.mobile.serial.centralstate.MobileParser;
import pl.ekozefir.mobile.serial.centralstate.value.OperatingModeParser.OperatingMode;

/**
 *
 * @author Michal Marasz
 */
public class OperatingModeParser implements MobileParser<OperatingMode> {

    public enum OperatingMode {
        MANUAL(0), AUTO(1);

        private final int parameter;

        private OperatingMode(int parameter) {
            this.parameter = parameter;
        }

        private static OperatingMode parse(int value) {
            for (OperatingMode state : values()) {
                if (state.parameter == value) {
                    return state;
                }
            }
            throw new IllegalStateException("Could not find value");
        }

    }
    private static final int byteNumber = 40;
    private static final int bitShift = 3;
    private static final int bitMask = 1;

    @Override
    public OperatingMode parse(Response response) {
        return OperatingMode.parse(response.convertByteOfNumberToInt(byteNumber, bitShift, bitMask));
    }

}
