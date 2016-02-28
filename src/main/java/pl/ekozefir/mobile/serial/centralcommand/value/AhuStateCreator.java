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
import pl.ekozefir.mobile.serial.centralcommand.value.AhuStateCreator.AhuState;

/**
 *
 * @author Michal Marasz
 */
public class AhuStateCreator implements MobileCreator<AhuState> {

    public enum AhuState {
        ON(0x00), OFF(0x01);

        private final int parameter;

        private AhuState(int parameter) {
            this.parameter = parameter;
        }

    }
    private static final int type = 0x01;

    @Override
    public MobileCommand create(AhuState mobileParameter, char centralId) {
        return MessageBuilder.setType(type, centralId).appendFirstParameter(mobileParameter.parameter).build();
    }

}
