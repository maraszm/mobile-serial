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

import pl.ekozefir.mobile.serial.centralcommand.MessageBuilder;
import pl.ekozefir.mobile.serial.centralcommand.MobileCommand;
import pl.ekozefir.mobile.serial.centralcommand.MobileCreator;
import pl.ekozefir.mobile.serial.centralcommand.value.OperatingSensorCreator.OperatingSensor;

/**
 *
 * @author Michal Marasz
 */
public class OperatingSensorCreator implements MobileCreator<OperatingSensor> {
    public enum OperatingSensor {
        
        CONTROLLER_SENSOR_MANUAL(0x00),
        SUPPLY_SENSOR_MANUAL(0x01),
        ADDITIONAL_SENSOR_MANUAL(0x02),
        EXTRACT_SENSOR_MANUAL(0x06),
        CONTROLLER_SENSOR_AUTO(0x03),
        EXTRACT_SENSOR_AUTO(0x04),
        ADDITIONAL_SENSOR_AUTO(0x05);
        
        private final int parameter;

        private OperatingSensor(int parameter) {
            this.parameter = parameter;
        }

    }
    private static final int type = 0x07;

    @Override
    public MobileCommand create(OperatingSensor mobileParameter, char centralId) {
        return MessageBuilder.setType(type, centralId).appendFirstParameter(mobileParameter.parameter).build();
    }

}
