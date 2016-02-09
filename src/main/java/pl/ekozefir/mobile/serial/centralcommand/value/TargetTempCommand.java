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

/**
 *
 * @author Michal Marasz  
 */
public class TargetTempCommand implements MobileCommand {

    private static final int type = 0x06;
    private final float value;

    public TargetTempCommand(float value) {
        this.value = value;
    }

    @Override
    public byte[] createCommand() {
        return MessageBuilder.setType(type).appendFirstAndSecondParameter(value).build();
    }

}
