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

import pl.ekozefir.mobile.serial.centralstate.Response;
import pl.ekozefir.mobile.serial.centralstate.MobileParser;
import pl.ekozefir.mobile.serial.centralstate.value.PreheaterEquipmentParser.PreheaterEquipment;

/**
 *
 * @author Michal Marasz
 */
public class PreheaterEquipmentParser implements MobileParser<PreheaterEquipment> {

    public enum PreheaterEquipment {
        NONE(0x00), TRUE(0x01);

        private final int parameter;

        private PreheaterEquipment(int parameter) {
            this.parameter = parameter;
        }

        private static PreheaterEquipment parse(int value) {
            for (PreheaterEquipment state : values()) {
                if (state.parameter == value) {
                    return state;
                }
            }
            throw new IllegalStateException("Could not find value");
        }

    }
    private static final int byteNumber = 46;
    private static final int bitShift = 7;
    private static final int bitMask = 1;

    @Override
    public PreheaterEquipment parse(Response response) {
        return PreheaterEquipment.parse(response.convertByteOfNumberToInt(byteNumber, bitShift, bitMask));
    }

}
