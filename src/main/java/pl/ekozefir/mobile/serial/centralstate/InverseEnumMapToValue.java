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

import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Michal Marasz
 */
public class InverseEnumMapToValue<T extends Enum, V> implements InverseEnumMap<T, V> {

    private final Map<T, V> values;
    private final Optional<T> defaultValue;

    public InverseEnumMapToValue(Map<T, V> values, T defaultValue) {
        this.values = values;
        this.defaultValue = Optional.of(defaultValue);
    }

    public InverseEnumMapToValue(Map<T, V> values) {
        this.values = values;
        this.defaultValue = Optional.empty();
    }

    @Override
    public T find(V value) {
        for (T param : values.keySet()) {
            if (values.get(param).equals(value)) {
                return param;
            }
        }
        return defaultValue.orElseThrow(() -> new IllegalStateException("Could not find value"));

    }
}
