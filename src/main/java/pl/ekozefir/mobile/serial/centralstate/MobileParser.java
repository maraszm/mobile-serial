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
package pl.ekozefir.mobile.serial.centralstate;

import pl.ekozefir.mobile.serial.centralstate.value.ParsedValue;

/**
 *
 * @author Michal Marasz  
 */
public interface MobileParser<T> {

    ParsedValue<T> parse(Response response);

}
