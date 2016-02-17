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
public class CoolerEquipmentParser implements MobileParser {

    private static final Map<Integer, String> values = ImmutableMap.<Integer, String>builder().
            put(5, "EVAPORATOR").
            put(6, "EVAPORATOR").
            put(7, "EVAPORATOR").
            put(12, "EVAPORATOR").
            put(8, "WATER_3POINT").
            put(9, "WATER_3POINT").
            put(10, "WATER_3POINT").
            put(11, "WATER_3POINT").
            put(13, "WATER_3POINT").
            build();
    private static final String none = "NONE";
    private static final int byteNumber = 47;

    @Override
    public ParsedValue parse(Response response) {
        return new ParsedValue(values.getOrDefault(response.convertByteOfNumberToInt(byteNumber), none));
    }

}
