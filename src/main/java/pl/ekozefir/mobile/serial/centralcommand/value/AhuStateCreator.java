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
import pl.ekozefir.mobile.serial.parameter.OnOff;
import static pl.ekozefir.mobile.serial.parameter.OnOff.OFF;
import static pl.ekozefir.mobile.serial.parameter.OnOff.ON;

/**
 *
 * @author Michal Marasz
 */
public class AhuStateCreator implements MobileCreator<OnOff> {

    private static final Map<OnOff, Integer> values = Maps.immutableEnumMap(ImmutableMap.of(
            ON, 0, OFF, 1
    ));
    private static final int type = 0x01;

    @Override
    public MobileCommand create(OnOff mobileParameter, char centralId) {
        return MessageBuilder.setType(type, centralId).appendFirstParameter(values.get(mobileParameter)).build();
    }

}
