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

import pl.ekozefir.mobile.serial.centralstate.ParsedValue;
import pl.ekozefir.mobile.serial.centralstate.Response;
import pl.ekozefir.mobile.serial.centralstate.MobileParser;

/**
 *
 * @author Michal Marasz  
 */
public class ZZPP1Parser implements MobileParser {

    private static final int byteNumber = 37;
    private static final int bitShift = 5;
    private static final int bitMask = 1;

    @Override
    public ParsedValue parse(Response response) {
        return new ParsedValue(response.convertByteOfNumberToInt(byteNumber, bitShift, bitMask) == 1);
    }

}
