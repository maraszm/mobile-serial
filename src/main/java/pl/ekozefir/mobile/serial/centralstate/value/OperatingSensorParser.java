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

import com.google.common.collect.ImmutableMap;
import pl.ekozefir.mobile.serial.centralstate.Response;
import pl.ekozefir.mobile.serial.centralstate.MobileParser;

/**
 *
 * @author Michal Marasz  
 */
public class OperatingSensorParser implements MobileParser {

    private static final ImmutableMap<Integer, String> values = ImmutableMap.<Integer, String>builder().
            put(0b1000, "CONTROLLER_SENSOR_AUTO").
            put(0b1001, "EXTRACT_SENSOR_AUTO").
            put(0b1010, "ADDITIONAL_SENSOR_AUTO").
            put(0b0000, "CONTROLLER_SENSOR_MANUAL").
            put(0b0001, "SUPPLY_SENSOR_MANUAL").
            put(0b0010, "ADDITIONAL_SENSOR_MANUAL").
            put(0b0011, "EXTRACT_SENSOR_MANUAL").
            build();
    private static final int byteNumber = 40;
    private static final int bitShift = 0;
    private static final int bitMask = 15;

    @Override
    public ParsedValue parse(Response response) {
        return new ParsedValue(values.get(response.convertByteOfNumberToInt(byteNumber, bitShift, bitMask)));
    }

}
