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

import pl.ekozefir.mobile.serial.centralstate.MobileParser;
import pl.ekozefir.mobile.serial.centralstate.Response;

/**
 *
 * @author Michal Marasz  
 */
public class ExtractAirEfficiencyParser implements MobileParser<Integer> {

    private static final int byteNumber = 5;

    @Override
    public Integer parse(Response response) {
        return response.convertByteOfNumberToInt(byteNumber);
    }

}
