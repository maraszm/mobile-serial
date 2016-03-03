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
import pl.ekozefir.mobile.serial.parameter.AhuState;
import static pl.ekozefir.mobile.serial.parameter.AhuState.ALERT;
import static pl.ekozefir.mobile.serial.parameter.AhuState.SHUTDOWN;
import static pl.ekozefir.mobile.serial.parameter.AhuState.STANDBY;
import static pl.ekozefir.mobile.serial.parameter.AhuState.STARTING;
import static pl.ekozefir.mobile.serial.parameter.AhuState.WORK;

/**
 *
 * @author Michal Marasz
 */
public class AhuStateParser implements MobileParser<AhuState> {

    private static final InverseEnumMap<AhuState, Integer> values = new InverseEnumMapToValue(
            Maps.immutableEnumMap(ImmutableMap.of(
                    STARTING, 0x01, WORK, 0x02, SHUTDOWN, 0x03, STANDBY, 0x04, ALERT, 0x05
            ))
    );
    private static final int byteNumber = 3;

    @Override
    public AhuState parse(Response response) {
        return values.find(response.convertByteOfNumberToInt(byteNumber));
    }

}
