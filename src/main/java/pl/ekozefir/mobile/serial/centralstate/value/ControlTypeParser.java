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
import pl.ekozefir.mobile.serial.parameter.ControlType;
import static pl.ekozefir.mobile.serial.parameter.ControlType.DIGITAL_E;
import static pl.ekozefir.mobile.serial.parameter.ControlType.DIGITAL_G;
import static pl.ekozefir.mobile.serial.parameter.ControlType.DIGITAL_O;
import static pl.ekozefir.mobile.serial.parameter.ControlType.STANDARD;

/**
 *
 * @author Michal Marasz
 */
public class ControlTypeParser implements MobileParser<ControlType> {

    private static final InverseEnumMap<ControlType, Integer> values = new InverseEnumMapToValue(
            Maps.immutableEnumMap(ImmutableMap.of(
                    DIGITAL_E, 69,
                    DIGITAL_G, 72,
                    DIGITAL_O, 79,
                    STANDARD, 89
            ))
    );
    private static final int byteNumber = 1;

    @Override
    public ControlType parse(Response response) {
        return values.find(response.convertByteOfNumberToInt(byteNumber));
    }

}
