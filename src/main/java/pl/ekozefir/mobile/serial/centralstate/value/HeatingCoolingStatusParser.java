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
 * Only for backward compatibility. Joined with {@link HeatingCoolingModeParser} to {@link FunctionParser}
 */
@Deprecated
public class HeatingCoolingStatusParser implements MobileParser {

    private static final Map<Integer, String> values = ImmutableMap.of(0, "HEATING", 1, "COOLING", 2, "NONE");
    private static final int byteNumber = 38;
    private static final int bitShift = 0;
    private static final int bitMask = 7;

    @Override
    public ParsedValue parse(Response response) {
        return new ParsedValue(values.get(response.convertByteOfNumberToInt(byteNumber, bitShift, bitMask)));
    }

}
