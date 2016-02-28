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
import pl.ekozefir.mobile.serial.centralcommand.value.TimeProgrammerCreator.TimeProgrammer;

/**
 *
 * @author Michal Marasz
 */
public class TimeProgrammerCreator implements MobileCreator<TimeProgrammer> {

    public enum TimeProgrammer {
        ON(0x01), OFF(0x00);

        private final int parameter;

        private TimeProgrammer(int parameter) {
            this.parameter = parameter;
        }

    }
    private static final int type = 0x09;

    @Override
    public MobileCommand create(TimeProgrammer mobileParameter, char centralId) {
        return MessageBuilder.setType(type, centralId).appendFirstParameter(mobileParameter.parameter).build();
    }

}
