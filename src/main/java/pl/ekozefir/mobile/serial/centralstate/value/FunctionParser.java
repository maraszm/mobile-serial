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

import pl.ekozefir.mobile.serial.centralstate.MobileParser;
import pl.ekozefir.mobile.serial.centralstate.Response;
import pl.ekozefir.mobile.serial.centralstate.value.FunctionParser.Function;

/**
 *
 * @author Michal Marasz
 */
public class FunctionParser implements MobileParser<Function> {

    public enum Function {
        COOLING(0x01), HEATING(0x00), RECOVERY(0x02), AUTO(0x08);

        private final int parameter;

        private Function(int parameter) {
            this.parameter = parameter;
        }

        private static Function parse(int value) {
            if (isAuto(value)) {
                return AUTO;
            }
            for (Function state : values()) {
                if (state.parameter == value) {
                    return state;
                }
            }
            throw new IllegalStateException("Could not find value");
        }

        private static boolean isAuto(int value) {
            return value >= AUTO.parameter;
        }

    }
    private static final int byteNumber = 38;
    private static final int bitShift = 0;
    private static final int bitMask = 0b1111;

    @Override
    public Function parse(Response response) {
        return Function.parse(response.convertByteOfNumberToInt(byteNumber, bitShift, bitMask));
    }

}
