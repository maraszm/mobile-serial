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

/**
 *
 * @author Michal Marasz
 */
public class ChangeCentralCreator implements MobileCreator {

    private static final int type = 0xAB;
    private static final int param = 0x55;

    @Override
    public MobileCommand create(Object mobileParameter, char centralId) {
        return MessageBuilder.setType(type, centralId).appendFirstParameter(param).appendSecondParameter(centralId).build();
    }

}
