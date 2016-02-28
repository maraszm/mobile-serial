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
package pl.ekozefir.mobile.serial.centralstate;

/**
 *
 * @author Michal Marasz
 */
public abstract class TempParser implements MobileParser {

    public enum TempError {

        NO_SENSOR(0xF1), SENSOR_ERROR(0xF0);

        private final int parameter;

        private TempError(int parameter) {
            this.parameter = parameter;
        }

        public boolean check(int valueLow, int valueHigh) {
            if (parameter == valueLow && parameter == valueHigh) {
                return true;
            }
            return false;
        }

    }

    public Object parseTemp(Response response, int highByteNumber, int lowByteNumber) {
        int highByteValue = response.convertByteOfNumberToInt(highByteNumber);
        int lowByteValue = response.convertByteOfNumberToInt(lowByteNumber);
        if (TempError.NO_SENSOR.check(highByteValue, lowByteValue)) {
            return TempError.NO_SENSOR;
        }
        if (TempError.SENSOR_ERROR.check(highByteValue, lowByteValue)) {
            return TempError.SENSOR_ERROR;
        }
        return response.convertBytesOfNumberToFloat(highByteNumber, lowByteNumber);
    }
}
