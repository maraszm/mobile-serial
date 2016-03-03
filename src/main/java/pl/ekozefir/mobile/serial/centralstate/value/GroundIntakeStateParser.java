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
import pl.ekozefir.mobile.serial.parameter.OnOff;
import static pl.ekozefir.mobile.serial.parameter.OnOff.OFF;
import static pl.ekozefir.mobile.serial.parameter.OnOff.ON;

/**
 *
 * @author Michal Marasz
 */
public class GroundIntakeStateParser implements MobileParser<OnOff> {

    private static final InverseEnumMap<OnOff, Integer> values = new InverseEnumMapToValue(
            Maps.immutableEnumMap(ImmutableMap.of(
                    ON, 0, OFF, 1
            ))
    );
    private static final int byteNumber = 37;
    private static final int bitShift = 0;
    private static final int bitMask = 1;

    @Override
    public OnOff parse(Response response) {
        return values.find(response.convertByteOfNumberToInt(byteNumber, bitShift, bitMask));
    }

}
