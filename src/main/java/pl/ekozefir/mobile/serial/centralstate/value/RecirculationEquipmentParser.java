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
import pl.ekozefir.mobile.serial.centralstate.Response;
import pl.ekozefir.mobile.serial.centralstate.MobileParser;
import pl.ekozefir.mobile.serial.centralstate.value.RecirculationEquipmentParser.RecirculationEquipment;
import static pl.ekozefir.mobile.serial.centralstate.value.RecirculationEquipmentParser.RecirculationEquipment.NONE;
import static pl.ekozefir.mobile.serial.centralstate.value.RecirculationEquipmentParser.RecirculationEquipment.TRUE;

/**
 *
 * @author Michal Marasz
 */
public class RecirculationEquipmentParser implements MobileParser<RecirculationEquipment> {

    public enum RecirculationEquipment {
        NONE, TRUE;
    }
    private static final InverseEnumMap<RecirculationEquipment, Integer> values = new InverseEnumMapToValue(
            Maps.immutableEnumMap(ImmutableMap.of(
                    TRUE, 1, NONE, 0
            ))
    );
    private static final int byteNumber = 46;
    private static final int bitShift = 4;
    private static final int bitMask = 1;

    @Override
    public RecirculationEquipment parse(Response response) {
        return values.find(response.convertByteOfNumberToInt(byteNumber, bitShift, bitMask));
    }

}
