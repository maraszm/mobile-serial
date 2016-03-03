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
import pl.ekozefir.mobile.serial.parameter.OperatingSensor;
import static pl.ekozefir.mobile.serial.parameter.OperatingSensor.ADDITIONAL_SENSOR_AUTO;
import static pl.ekozefir.mobile.serial.parameter.OperatingSensor.ADDITIONAL_SENSOR_MANUAL;
import static pl.ekozefir.mobile.serial.parameter.OperatingSensor.CONTROLLER_SENSOR_AUTO;
import static pl.ekozefir.mobile.serial.parameter.OperatingSensor.CONTROLLER_SENSOR_MANUAL;
import static pl.ekozefir.mobile.serial.parameter.OperatingSensor.EXTRACT_SENSOR_AUTO;
import static pl.ekozefir.mobile.serial.parameter.OperatingSensor.EXTRACT_SENSOR_MANUAL;
import static pl.ekozefir.mobile.serial.parameter.OperatingSensor.SUPPLY_SENSOR_MANUAL;

/**
 *
 * @author Michal Marasz
 */
public class OperatingSensorParser implements MobileParser<OperatingSensor> {

    private static final InverseEnumMap<OperatingSensor, Integer> values = new InverseEnumMapToValue(
            Maps.immutableEnumMap(ImmutableMap.<OperatingSensor, Integer>builder().
                    put(EXTRACT_SENSOR_MANUAL, 0b0011).
                    put(ADDITIONAL_SENSOR_MANUAL, 0b0010).
                    put(SUPPLY_SENSOR_MANUAL, 0b0001).
                    put(CONTROLLER_SENSOR_MANUAL, 0b0000).
                    put(ADDITIONAL_SENSOR_AUTO, 0b1010).
                    put(EXTRACT_SENSOR_AUTO, 0b1001).
                    put(CONTROLLER_SENSOR_AUTO, 0b1000).build()
            )
    );
    private static final int byteNumber = 40;
    private static final int bitShift = 0;
    private static final int bitMask = 15;

    @Override
    public OperatingSensor parse(Response response) {
        return values.find(response.convertByteOfNumberToInt(byteNumber, bitShift, bitMask));
    }

}
