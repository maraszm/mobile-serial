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
import pl.ekozefir.mobile.serial.centralstate.value.OperatingSensorParser.OperatingSensor;

/**
 *
 * @author Michal Marasz
 */
public class OperatingSensorParser implements MobileParser<OperatingSensor> {

    public enum OperatingSensor {
        CONTROLLER_SENSOR_AUTO(0b1000),
        EXTRACT_SENSOR_AUTO(0b1001),
        ADDITIONAL_SENSOR_AUTO(0b1010),
        CONTROLLER_SENSOR_MANUAL(0b0000),
        SUPPLY_SENSOR_MANUAL(0b0001),
        ADDITIONAL_SENSOR_MANUAL(0b0010),
        EXTRACT_SENSOR_MANUAL(0b0011);

        private final int parameter;

        private OperatingSensor(int parameter) {
            this.parameter = parameter;
        }

        private static OperatingSensor parse(int value) {
            for (OperatingSensor state : values()) {
                if (state.parameter == value) {
                    return state;
                }
            }
            throw new IllegalStateException("Could not find value");
        }
    }
    private static final int byteNumber = 40;
    private static final int bitShift = 0;
    private static final int bitMask = 15;

    @Override
    public OperatingSensor parse(Response response) {
        return OperatingSensor.parse(response.convertByteOfNumberToInt(byteNumber, bitShift, bitMask));
    }

}
