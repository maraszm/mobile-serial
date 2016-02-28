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

import java.util.stream.Stream;
import pl.ekozefir.mobile.serial.centralstate.ParsedValue;
import pl.ekozefir.mobile.serial.centralstate.Response;
import pl.ekozefir.mobile.serial.centralstate.MobileParser;
import pl.ekozefir.mobile.serial.centralstate.value.HeaterCoolantEquipmentParser.HeaterCoolantEquipment;

/**
 *
 * @author Michal Marasz
 */
public class HeaterCoolantEquipmentParser implements MobileParser<HeaterCoolantEquipment> {

    public enum HeaterCoolantEquipment {
        TRUE(0x01), NONE(0x00);

        private final int parameter;

        private HeaterCoolantEquipment(int parameter) {
            this.parameter = parameter;
        }

        private static HeaterCoolantEquipment parse(int value) {
            return Stream.of(values()).
                    filter(parameter -> parameter.parameter == value).
                    findAny().orElseThrow(() -> new IllegalStateException("Could not find value"));
        }

    }
    private static final int byteNumber = 46;
    private static final int bitShift = 3;
    private static final int bitMask = 1;

    @Override
    public HeaterCoolantEquipment parse(Response response) {
        return HeaterCoolantEquipment.parse(response.convertByteOfNumberToInt(byteNumber, bitShift, bitMask));
    }

}
