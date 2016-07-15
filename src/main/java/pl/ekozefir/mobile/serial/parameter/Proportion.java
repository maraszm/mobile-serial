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
package pl.ekozefir.mobile.serial.parameter;

/**
 *
 * @author Michal Marasz
 */
public class Proportion {
    private final OnOff isProportionWorking;
    private final Integer supply;
    private final Integer extract;

    public Proportion(OnOff isProportionWorking, Integer supply, Integer extract) {
        this.isProportionWorking = isProportionWorking;
        this.supply = supply;
        this.extract = extract;
    }

    public OnOff getIsProportionWorking() {
        return isProportionWorking;
    }

    public Integer getSupply() {
        return supply;
    }

    public Integer getExtract() {
        return extract;
    }
    
    
}
