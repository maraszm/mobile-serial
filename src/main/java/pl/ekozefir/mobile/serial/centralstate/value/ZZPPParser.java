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
import pl.ekozefir.mobile.serial.parameter.ZZPP;
import static pl.ekozefir.mobile.serial.parameter.ZZPP.NONE;
import static pl.ekozefir.mobile.serial.parameter.ZZPP.ZZPP1;
import static pl.ekozefir.mobile.serial.parameter.ZZPP.ZZPP2;
import static pl.ekozefir.mobile.serial.parameter.ZZPP.ZZPP3;

/**
 *
 * @author Michal Marasz
 */
public class ZZPPParser implements MobileParser<ZZPP> {

    private static final InverseEnumMap<ZZPP, Integer> values = new InverseEnumMapToValue(
            Maps.immutableEnumMap(ImmutableMap.of(
                    ZZPP3, 4, ZZPP2, 2, ZZPP1, 1, NONE, 0
            ))
    );
    private static final int byteNumber = 37;
    private static final int bitShift = 5;
    private static final int bitMask = 0b111;

    @Override
    public ZZPP parse(Response response) {
        return values.find(response.convertByteOfNumberToInt(byteNumber, bitShift, bitMask));
    }

}
