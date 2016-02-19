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

    private static final int lackOfTempSensor = 0xF1;
    private static final String lackOfTempSensorText = "NO_SENSOR";
    private static final int damageOfTempSensor = 0xF0;
    private static final String damageOfTempSensorText = "SENSOR_ERROR";
    

    public ParsedValue parseTemp(Response response, int highByteNumber, int lowByteNumber) {
        int highByteValue = response.convertByteOfNumberToInt(highByteNumber);
        int lowByteValue = response.convertByteOfNumberToInt(lowByteNumber);
        if (highByteValue == lackOfTempSensor && lowByteValue == lackOfTempSensor) {
            return new ParsedValue(lackOfTempSensorText);
        }
        if (highByteValue == damageOfTempSensor && lowByteValue == damageOfTempSensor) {
            return new ParsedValue(damageOfTempSensorText);
        }
        return new ParsedValue(response.convertBytesOfNumberToFloat(highByteNumber, lowByteNumber));
    }
}
