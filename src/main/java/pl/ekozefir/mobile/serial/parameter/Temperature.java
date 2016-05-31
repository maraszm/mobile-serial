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

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Optional;
import static pl.ekozefir.mobile.serial.parameter.TempSensorStatus.NO_SENSOR;
import static pl.ekozefir.mobile.serial.parameter.TempSensorStatus.OK;
import static pl.ekozefir.mobile.serial.parameter.TempSensorStatus.SENSOR_ERROR;

/**
 *
 * @author Michal Marasz
 */
public class Temperature {

    private static final float tempFactor = 10f;
    private static final byte noSensor = (byte) 0xF1;
    private static final byte sensorError = (byte) 0xF0;
    private final TempSensorStatus status;
    private final Optional<Float> value;

    public Temperature(byte highByte, byte lowByte) {
        status = createErrorStatus(highByte, lowByte);
        switch (status) {
            case OK:
                value = Optional.of(convertBytesOfNumberToFloat(highByte, lowByte));
                break;
            default:
                value = Optional.empty();
        }
    }
    
    public Temperature(Float temperature){
        this.status = TempSensorStatus.OK;
        this.value = Optional.of(temperature);
    }
    
    public Temperature(TempSensorStatus status){
        this.status = status;
        this.value = Optional.empty();
    }

    public TempSensorStatus getStatus() {
        return status;
    }

    public Optional<Float> getValue() {
        return value;
    }

    /**
     * Return error name on error or value.
     *
     * @return error name / value
     */
    public String parseSimple() {
        if (!OK.equals(getStatus())) {
            return getStatus().toString();
        }
        return value.get().toString();
    }

    private float convertBytesOfNumberToFloat(byte highByte, byte lowByte) {
        byte[] bytesValuesToConvert = new byte[2];
        bytesValuesToConvert[0] = highByte;
        bytesValuesToConvert[1] = lowByte;
        return ByteBuffer.wrap(bytesValuesToConvert).order(ByteOrder.LITTLE_ENDIAN).getShort() / tempFactor;
    }

    private TempSensorStatus createErrorStatus(byte highByte, byte lowByte) {
        if (highByte == lowByte && highByte == sensorError) {
            return SENSOR_ERROR;
        }
        if (highByte == lowByte && highByte == noSensor) {
            return NO_SENSOR;
        }
        return OK;
    }
}
