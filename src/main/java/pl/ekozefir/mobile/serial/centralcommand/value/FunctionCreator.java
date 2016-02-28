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
import pl.ekozefir.mobile.serial.centralcommand.value.FunctionCreator.Function;

/**
 *
 * @author Michal Marasz
 */
public class FunctionCreator implements MobileCreator<Function> {

    public enum Function {
        AUTO(0x03), COOLING(0x01), RECOVERY(0x02), HEATING(0x00);

        private final int parameter;

        private Function(int parameter) {
            this.parameter = parameter;
        }

    }

    private static final int type = 0x0C;

    @Override
    public MobileCommand create(Function mobileParameter, char centralId) {
        return MessageBuilder.setType(type, centralId).appendFirstParameter(mobileParameter.parameter).build();
    }

}
