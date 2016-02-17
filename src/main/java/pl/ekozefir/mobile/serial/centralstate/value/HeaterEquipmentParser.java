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
public class HeaterEquipmentParser implements MobileParser {

    private static final Map<Integer, String> values = ImmutableMap.<Integer, String>builder().
            put(2, "ELECTRIC").
            put(6, "ELECTRIC").
            put(9, "ELECTRIC").
            put(13, "ELECTRIC").
            put(3, "WATER_3POINT").
            put(10, "WATER_3POINT").
            put(12, "WATER_3POINT").
            put(4, "WATER_THERMAL").
            put(7, "WATER_THERMAL").
            put(11, "WATER_THERMAL").
            put(14, "EVAPORATOR").
            put(15, "ELECTRIC_2UNIT").
            put(16, "EXCHANGER_3POINT").
            put(17, "EXCHANGER_3POINT").
            build();
    private static final String none = "NONE";
    private static final int byteNumber = 47;

    @Override
    public ParsedValue parse(Response response) {
        return new ParsedValue(values.getOrDefault(response.convertByteOfNumberToInt(byteNumber), none));
    }

}
