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
import pl.ekozefir.mobile.serial.centralstate.value.ZZPPParser.ZZPP;

/**
 *
 * @author Michal Marasz  
 */
public class ZZPPParser implements MobileParser<ZZPP> {

    public enum ZZPP {
        ZZPP1(0b001), ZZPP2(0b010), ZZPP3(0b100), NONE(0b00);

        private final int parameter;

        private ZZPP(int parameter) {
            this.parameter = parameter;
        }

        private static ZZPP parse(int value) {
            for (ZZPP state : values()) {
                if (state.parameter == value) {
                    return state;
                }
            }
            throw new IllegalStateException("Could not find value");
        }

    }
    private static final int byteNumber = 37;
    private static final int bitShift = 5;
    private static final int bitMask = 0b111;

    @Override
    public ZZPP parse(Response response) {
        return ZZPP.parse(response.convertByteOfNumberToInt(byteNumber, bitShift, bitMask));
    }

}
