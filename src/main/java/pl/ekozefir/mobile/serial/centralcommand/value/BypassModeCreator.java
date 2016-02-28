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
import pl.ekozefir.mobile.serial.centralcommand.value.BypassModeCreator.BypassMode;

/**
 *
 * @author Michal Marasz
 */
public class BypassModeCreator implements MobileCreator<BypassMode> {

    public enum BypassMode {
        AUTO(0x02), OFF(0x00), ON(0x01);
        
        private final int parameter;
        
        private BypassMode(int parameter){
            this.parameter = parameter;
        }
        
    }
    private static final int type = 0x0A;

    @Override
    public MobileCommand create(BypassMode mobileParameter, char centralId) {
        return MessageBuilder.setType(type, centralId).appendFirstParameter(mobileParameter.parameter).build();
    }
}
