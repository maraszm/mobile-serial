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
import pl.ekozefir.mobile.serial.centralstate.value.ControlTypeParser.ControlType;

/**
 *
 * @author Michal Marasz
 */
public class ControlTypeParser implements MobileParser<ControlType> {

    public enum ControlType {
        DIGITAL_E(69),
        DIGITAL_G(72),
        DIGITAL_O(79),
        STANDARD(89);

        private final int parameter;

        private ControlType(int parameter) {
            this.parameter = parameter;
        }

        private static ControlType parse(int value) {
            for (ControlType state : values()) {
                if (state.parameter == value) {
                    return state;
                }
            }
            throw new IllegalStateException("Could not find value");
        }

    }

    private static final int byteNumber = 1;

    @Override
    public ControlType parse(Response response) {
        return ControlType.parse(response.convertByteOfNumberToInt(byteNumber));
    }

}
