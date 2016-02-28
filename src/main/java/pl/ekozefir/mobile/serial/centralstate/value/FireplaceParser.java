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
import pl.ekozefir.mobile.serial.centralstate.value.FireplaceParser.Fireplace;

/**
 *
 * @author Michal Marasz
 */
public class FireplaceParser implements MobileParser<Fireplace> {

    public enum Fireplace {
        ON(0x01), OFF(0x00);

        private final int parameter;

        private Fireplace(int parameter) {
            this.parameter = parameter;
        }

        private static Fireplace parse(int value) {
            for (Fireplace state : values()) {
                if (state.parameter == value) {
                    return state;
                }
            }
            throw new IllegalStateException("Could not find value");
        }

    }
    private static final int byteNumber = 37;
    private static final int bitShift = 4;
    private static final int bitMask = 1;

    @Override
    public Fireplace parse(Response response) {
        return Fireplace.parse(response.convertByteOfNumberToInt(byteNumber, bitShift, bitMask));
    }

}
