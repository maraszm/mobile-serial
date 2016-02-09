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

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Objects;
import org.apache.log4j.Logger;

/**
 *
 * @author Michal Marasz  
 */
public class Response {

    private static final Logger log = Logger.getLogger(Response.class);
    private static final int expectedBytesNumber = 51;
    private static final byte firstCrcValue = (byte) 0xAA;
    private static final int firstWrongAhuType = 0xFF;
    private static final int secondWrongAhuType = 0x00;
    private static final float tempFactor = 10f;
    private static final int ahuTypeNumber = 1;
    private static final int crcByteNumber = 50; 
    private final byte[] bytes;

    public Response(byte[] bytes) {
        Objects.requireNonNull(bytes);
        checkSize(bytes);
        checkAhuType(bytes);
        checkCrc(bytes);
        this.bytes = Arrays.copyOf(bytes, expectedBytesNumber);
    }

    public float convertBytesOfNumberToFloat(int highNumberByte, int lowNumberByte) {
        checkIndex(highNumberByte);
        checkIndex(lowNumberByte);
        byte[] bytesValuesToConvert = new byte[2];
        bytesValuesToConvert[0] = bytes[highNumberByte];
        bytesValuesToConvert[1] = bytes[lowNumberByte];
        return ByteBuffer.wrap(bytesValuesToConvert).order(ByteOrder.LITTLE_ENDIAN).getShort() / tempFactor;
    }

    public int convertByteOfNumberToInt(int number) {
        checkIndex(number);
        return bytes[number] & 0xFF;
    }

    public int convertByteOfNumberToInt(int byteNumber, int bitRightShift, int bitMask) {
        checkIndex(byteNumber);
        return ((convertByteOfNumberToInt(byteNumber) >> bitRightShift) & bitMask);
    }

    private void checkIndex(int indexToCheck) {
        if (indexToCheck < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (indexToCheck >= expectedBytesNumber) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkSize(byte[] bytes) {
        if (bytes.length != expectedBytesNumber) {
            log.debug("Error with frame - number of bytes");
            throw new ArrayIndexOutOfBoundsException("Error with frame - number of bytes");
        }
    }

    private void checkCrc(byte[] bytes) {
        byte crc = firstCrcValue;
        for (int index = 0; index < bytes.length - 1; index++) {
            crc ^= bytes[index];
        }
        int crcValue = bytes[crcByteNumber];
        if(crcValue != crc){
            log.debug("Wrong CRC");
            throw new IllegalStateException("Wrong CRC");
        }
    }

    private void checkAhuType(byte[] bytes) {
        int ahuValue = (bytes[1] & 0xFF);
        if(ahuValue == firstWrongAhuType ||
                ahuValue == secondWrongAhuType){
            log.debug("Unknown ahu type");
            throw new IllegalStateException("Unknown ahu type");
        }
    }
}
