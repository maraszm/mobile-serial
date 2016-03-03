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

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Optional;
import pl.ekozefir.mobile.serial.centralstate.InverseEnumMap;
import pl.ekozefir.mobile.serial.centralstate.InverseEnumMapToValue;
import static pl.ekozefir.mobile.serial.parameter.TempSensorStatus.NO_SENSOR;
import static pl.ekozefir.mobile.serial.parameter.TempSensorStatus.OK;
import static pl.ekozefir.mobile.serial.parameter.TempSensorStatus.SENSOR_ERROR;

/**
 *
 * @author Michal Marasz
 */
public class Temperature {

    private static final float tempFactor = 10f;
    private static final InverseEnumMap<TempSensorStatus, Byte> values = new InverseEnumMapToValue(Maps.immutableEnumMap(ImmutableMap.of(
            NO_SENSOR, 0xF1, SENSOR_ERROR, 0xF0
    )));
    private final TempSensorStatus status;

    private final Optional<Float> value;

    public Temperature(byte highByte, byte lowByte) {
        if (highByte == lowByte) {
            this.status = values.find(lowByte);
            this.value = Optional.empty();
        } else {
            this.status = OK;
            this.value = Optional.of(convertBytesOfNumberToFloat(highByte, lowByte));
        }

    }

    public TempSensorStatus getStatus() {
        return status;
    }

    public Optional<Float> getValue() {
        return value;
    }

    private float convertBytesOfNumberToFloat(byte highByte, byte lowByte) {
        byte[] bytesValuesToConvert = new byte[2];
        bytesValuesToConvert[0] = highByte;
        bytesValuesToConvert[1] = lowByte;
        return ByteBuffer.wrap(bytesValuesToConvert).order(ByteOrder.LITTLE_ENDIAN).getShort() / tempFactor;
    }
}
