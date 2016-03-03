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
import java.util.Collection;
import pl.ekozefir.mobile.serial.centralstate.InverseEnumMap;
import pl.ekozefir.mobile.serial.centralstate.InverseEnumMapToCollection;
import pl.ekozefir.mobile.serial.centralstate.MobileParser;
import pl.ekozefir.mobile.serial.centralstate.Response;
import pl.ekozefir.mobile.serial.parameter.HeaterEquipment;
import static pl.ekozefir.mobile.serial.parameter.HeaterEquipment.ELECTRIC;
import static pl.ekozefir.mobile.serial.parameter.HeaterEquipment.ELECTRIC_2UNIT;
import static pl.ekozefir.mobile.serial.parameter.HeaterEquipment.EVAPORATOR;
import static pl.ekozefir.mobile.serial.parameter.HeaterEquipment.EXCHANGER_3POINT;
import static pl.ekozefir.mobile.serial.parameter.HeaterEquipment.NONE;
import static pl.ekozefir.mobile.serial.parameter.HeaterEquipment.WATER_3POINT;
import static pl.ekozefir.mobile.serial.parameter.HeaterEquipment.WATER_THERMAL;

/**
 *
 * @author Michal Marasz
 */
public class HeaterEquipmentParser implements MobileParser<HeaterEquipment> {

    private static final InverseEnumMap<HeaterEquipment, Integer> values = new InverseEnumMapToCollection(
            Maps.immutableEnumMap(ImmutableMap.<HeaterEquipment, Collection>builder().
                    put(ELECTRIC, ImmutableSet.of(2, 6, 9, 13)).
                    put(WATER_3POINT, ImmutableSet.of(3, 10, 12)).
                    put(WATER_THERMAL, ImmutableSet.of(4, 7, 11)).
                    put(EVAPORATOR, ImmutableSet.of(14)).
                    put(ELECTRIC_2UNIT, ImmutableSet.of(15)).
                    put(EXCHANGER_3POINT, ImmutableSet.of(16, 17)).
                    build()
            ), NONE);
    private static final int byteNumber = 47;

    @Override
    public HeaterEquipment parse(Response response) {
        return values.find(response.convertByteOfNumberToInt(byteNumber));
    }

}
