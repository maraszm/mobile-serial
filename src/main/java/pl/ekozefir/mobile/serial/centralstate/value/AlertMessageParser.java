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
import java.util.Map;
import pl.ekozefir.mobile.serial.centralstate.Response;
import pl.ekozefir.mobile.serial.centralstate.MobileParser;

/**
 *
 * @author Michal Marasz  
 */
public class AlertMessageParser implements MobileParser {

    private static final Map<Integer, String> values = ImmutableMap.<Integer, String>builder().
            put(0, "AWARIA_BRAK").
            put(1, "AWARIA_CZUJNIKA").
            put(2, "AWARIA_BAZY").
            put(3, "AWARIA_TK_NAW").
            put(4, "AWARIA_TK_WYW").
            put(5, "AWARIA_BOT").
            put(6, "AWARIA_ZNW").
            put(7, "AWARIA_POLACZEN").
            put(8, "AWARIA_PRZEGRZANIE").
            put(9, "AWARIA_FILTR").
            put(10, "AWARIA_FAKTURA_1").
            put(11, "AWARIA_FAKTURA_2").
            put(12, "AWARIA_ODSZRANIANIA").
            put(13, "AWARIA_ROTOR").
            put(53, "AWARIA_POLACZENIA").
            build();
    private static final int byteNumber = 41;

    @Override
    public ParsedValue parse(Response response) {
        return new ParsedValue(values.get(response.convertByteOfNumberToInt(byteNumber)));
    }

}
