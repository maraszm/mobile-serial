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
package pl.ekozefir.mobile.serial.centralcommand.value;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.Map;
import pl.ekozefir.mobile.serial.centralcommand.MessageBuilder;
import pl.ekozefir.mobile.serial.centralcommand.MobileCommand;
import pl.ekozefir.mobile.serial.centralcommand.MobileCreator;
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
public class OperatingSensorCreator implements MobileCreator<OperatingSensor> {
    
    private static final Map<OperatingSensor, Integer> values = Maps.immutableEnumMap(ImmutableMap.<OperatingSensor, Integer>builder().
            put(EXTRACT_SENSOR_MANUAL, 0x06).
            put(ADDITIONAL_SENSOR_MANUAL, 0x02).
            put(SUPPLY_SENSOR_MANUAL, 0x01).
            put(CONTROLLER_SENSOR_MANUAL, 0x00).
            put(ADDITIONAL_SENSOR_AUTO, 0x05).
            put(EXTRACT_SENSOR_AUTO, 0x04).
            put(CONTROLLER_SENSOR_AUTO, 0x03).build()
    );
    private static final int type = 0x07;
    
    @Override
    public MobileCommand create(OperatingSensor mobileParameter, char centralId) {
        return MessageBuilder.setType(type, centralId).appendFirstParameter(values.get(mobileParameter)).build();
    }
    
}
