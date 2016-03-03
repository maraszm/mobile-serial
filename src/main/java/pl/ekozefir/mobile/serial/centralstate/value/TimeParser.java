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

import java.text.SimpleDateFormat;
import java.util.Date;
import pl.ekozefir.mobile.serial.centralstate.MobileParser;
import pl.ekozefir.mobile.serial.centralstate.Response;

/**
 *
 * @author Michal Marasz  
 */
public class TimeParser implements MobileParser<String> {

    private static final SimpleDateFormat parserSDF = new SimpleDateFormat("yyyy-M-d HH:mm:ss");

    @Override
    public String parse(Response response) {
        return parserSDF.format(new Date());
    }

}
