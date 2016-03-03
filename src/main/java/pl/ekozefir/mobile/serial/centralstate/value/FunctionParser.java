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
import com.google.common.collect.Maps;
import pl.ekozefir.mobile.serial.centralstate.InverseEnumMap;
import pl.ekozefir.mobile.serial.centralstate.InverseEnumMapToValue;
import pl.ekozefir.mobile.serial.centralstate.MobileParser;
import pl.ekozefir.mobile.serial.centralstate.Response;
import pl.ekozefir.mobile.serial.parameter.Function;
import static pl.ekozefir.mobile.serial.parameter.Function.AUTO;
import static pl.ekozefir.mobile.serial.parameter.Function.COOLING;
import static pl.ekozefir.mobile.serial.parameter.Function.HEATING;
import static pl.ekozefir.mobile.serial.parameter.Function.RECOVERY;

/**
 *
 * @author Michal Marasz
 */
public class FunctionParser implements MobileParser<Function> {

    private static final InverseEnumMap<Function, Integer> values = new InverseEnumMapToValue(
            Maps.immutableEnumMap(ImmutableMap.of(
                    COOLING, 0x01, HEATING, 0x00, RECOVERY, 0x02, AUTO, 0x08
            ))
    );
    private static final int byteNumber = 38;
    private static final int bitShift = 0;
    private static final int bitMask = 0b1111;

    @Override
    public Function parse(Response response) {
        return values.find(response.convertByteOfNumberToInt(byteNumber, bitShift, bitMask));
    }

}
