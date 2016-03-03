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
import pl.ekozefir.mobile.serial.parameter.Function;
import static pl.ekozefir.mobile.serial.parameter.Function.AUTO;
import static pl.ekozefir.mobile.serial.parameter.Function.COOLING;
import static pl.ekozefir.mobile.serial.parameter.Function.HEATING;
import static pl.ekozefir.mobile.serial.parameter.Function.RECOVERY;

/**
 *
 * @author Michal Marasz
 */
public class FunctionCreator implements MobileCreator<Function> {

    private static final Map<Function, Integer> values = Maps.immutableEnumMap(ImmutableMap.of(
            AUTO, 0x03, COOLING, 0x01, RECOVERY, 0x02, HEATING, 0x00
    ));
    private static final int type = 0x0C;

    @Override
    public MobileCommand create(Function mobileParameter, char centralId) {
        return MessageBuilder.setType(type, centralId).appendFirstParameter(values.get(mobileParameter)).build();
    }

}
