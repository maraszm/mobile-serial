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
import pl.ekozefir.mobile.serial.centralstate.value.AhuStateParser.AhuState;

/**
 *
 * @author Michal Marasz
 */
public class AhuStateParser implements MobileParser<AhuState> {

    public enum AhuState {
        STARTING(0x01),
        WORK(0x02),
        SHUTDOWN(0x03),
        STANDBY(0x04),
        ALERT(0x05);

        private final int parameter;

        private AhuState(int parameter) {
            this.parameter = parameter;
        }

        private static AhuState parse(int value) {
            for (AhuState state : values()) {
                if (state.parameter == value) {
                    return state;
                }
            }
            throw new IllegalStateException("Could not find value");
        }

    }

    private static final int byteNumber = 3;

    @Override
    public AhuState parse(Response response) {
        return AhuState.parse(response.convertByteOfNumberToInt(byteNumber));
    }

}
