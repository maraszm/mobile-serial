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
public class ProportionOffCommand implements MobileCommand {

    private static final int type = 0x0F;
    private static final int parameter = 0x00;

    @Override
    public byte[] createCommand() {
        return MessageBuilder.setType(type).appendFirstParameter(parameter).build();
    }

}
