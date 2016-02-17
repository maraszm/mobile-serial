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

import pl.ekozefir.mobile.serial.centralstate.ParsedValue;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import pl.ekozefir.mobile.serial.centralstate.Response;
import pl.ekozefir.mobile.serial.centralstate.MobileParser;

/**
 *
 * @author Michal Marasz  
 */
public class AlertMessageParser implements MobileParser {

    private static final Map<Integer, String> values = ImmutableMap.<Integer, String>builder().
            put(0, "NO_FAILURE").
            put(1, "TEMPERATURE_SENSOR_FAILURE").
            put(2, "MAIN_BOARD_FAILURE").
            put(3, "SUPPLY_FAN_FAILURE").
            put(4, "EXTRACT_FAN_FAILURE").
            put(5, "THERMAL_PROTECTION_ENABLED").
            put(6, "WATER_HEATER_ANTIFROST_PROTECTION_ENABLED").
            put(7, "CONNECTION_WITH_MAIN_BOARD_FAILURE").
            put(8, "OVERHEATING").
            put(9, "CHANGE_FILTER").
            put(12, "DEFROSTING_FAILURE").
            put(13, "ROTARY_EXCHANGER_FAILURE").
            put(14, "FIRE_PROTECTION_ENABLED").
            put(53, "EKOTOUCH_CONNECTION_FAILURE").
            build();
    private static final int byteNumber = 41;

    @Override
    public ParsedValue parse(Response response) {
        return new ParsedValue(values.get(response.convertByteOfNumberToInt(byteNumber)));
    }

}
