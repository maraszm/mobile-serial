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

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 *
 * @author Michal Marasz
 */
public class InverseEnumMapToCollection<T extends Enum, V> implements InverseEnumMap<T, V> {

    private final Map<T, Collection<V>> values;
    private final Optional<T> defaultValue;

    public InverseEnumMapToCollection(Map<T, Collection<V>> values) {
        Objects.nonNull(values);
        this.defaultValue = Optional.empty();
        this.values = values;
    }

    public InverseEnumMapToCollection(Map<T, Collection<V>> values, T defaultValue) {
        Objects.nonNull(values);
        this.defaultValue = Optional.of(defaultValue);
        this.values = values;
    }

    @Override
    public T find(V value) {
        Objects.nonNull(value);
        for (T param : values.keySet()) {
            if (checkCollection(param, value)) {
                return param;
            }
        }
        return defaultValue.orElseThrow(() -> new IllegalStateException("Could not find value"));
    }

    private boolean checkCollection(T enumValue, V value) {
        return values.get(enumValue).contains(value);
    }
}
