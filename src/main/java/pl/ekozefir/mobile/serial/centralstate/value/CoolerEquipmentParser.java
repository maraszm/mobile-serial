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
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import pl.ekozefir.mobile.serial.centralstate.InverseEnumMap;
import pl.ekozefir.mobile.serial.centralstate.InverseEnumMapToCollection;
import pl.ekozefir.mobile.serial.centralstate.MobileParser;
import pl.ekozefir.mobile.serial.centralstate.Response;
import pl.ekozefir.mobile.serial.parameter.CoolerEquipment;
import static pl.ekozefir.mobile.serial.parameter.CoolerEquipment.EVAPORATOR;
import static pl.ekozefir.mobile.serial.parameter.CoolerEquipment.NONE;
import static pl.ekozefir.mobile.serial.parameter.CoolerEquipment.WATER_3POINT;

/**
 *
 * @author Michal Marasz
 */
public class CoolerEquipmentParser implements MobileParser<CoolerEquipment> {

    private static final InverseEnumMap<CoolerEquipment, Integer> values = new InverseEnumMapToCollection(
            Maps.immutableEnumMap(ImmutableMap.of(
                    EVAPORATOR, ImmutableSet.of(5, 6, 7, 12),
                    WATER_3POINT, ImmutableSet.of(8, 9, 10, 11, 13)
            )), NONE);
    private static final int byteNumber = 47;

    @Override
    public CoolerEquipment parse(Response response) {
        return values.find(response.convertByteOfNumberToInt(byteNumber));
    }

}
