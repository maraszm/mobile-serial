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
import pl.ekozefir.mobile.serial.centralstate.Response;
import pl.ekozefir.mobile.serial.centralstate.MobileParser;
import pl.ekozefir.mobile.serial.centralstate.value.HeaterStateParser.HeaterState;

/**
 *
 * @author Michal Marasz
 */
public class HeaterStateParser implements MobileParser<HeaterState> {

    public enum HeaterState {
        ON(0x01), OFF(0x00);

        private final int parameter;

        private HeaterState(int parameter) {
            this.parameter = parameter;
        }

        private static HeaterState parse(int value) {
            return Stream.of(values()).
                    filter(parameter -> parameter.parameter == value).
                    findAny().orElseThrow(() -> new IllegalStateException("Could not find value"));
        }

    }
    private static final int byteNumber = 39;
    private static final int bitShift = 7;
    private static final int bitMask = 1;

    @Override
    public HeaterState parse(Response response) {
        return HeaterState.parse(response.convertByteOfNumberToInt(byteNumber, bitShift, bitMask));
    }

}
