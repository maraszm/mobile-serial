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
public class AhuStateParser implements MobileParser {

    private static final Map<Integer, String> values = ImmutableMap.of(
            1, "STARTING", 2, "WORK", 3, "SHUTDOWN", 4, "STANDBY", 5, "ALERT");
    private static final int byteNumber = 3;

    @Override
    public ParsedValue parse(Response response) {
        return new ParsedValue(values.get(response.convertByteOfNumberToInt(byteNumber)));
    }

}
