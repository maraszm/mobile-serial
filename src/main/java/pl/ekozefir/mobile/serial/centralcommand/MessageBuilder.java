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
package pl.ekozefir.mobile.serial.centralcommand;

/**
 *
 * @author Michal Marasz
 */
public class MessageBuilder {

    private final byte messageType;
    private char centralId = 'A';
    private byte firstParam = 0x00;
    private byte secondParam = 0x00;
    private byte thirdParam = 0x00;

    private MessageBuilder(int messageType, char centralId) {
        this.messageType = (byte) messageType;
        this.centralId = centralId;
    }

    private MobileCommand messageOfBytes(int byte0, int byte1, int byte2, int byte3, char centralId) {
        byte[] bytes = new byte[5];
        bytes[0] = (byte) byte0;
        bytes[1] = (byte) byte1;
        bytes[2] = (byte) byte2;
        bytes[3] = (byte) byte3;
        bytes[4] = calculateCrc(bytes);
        return new MobileCommand(bytes, centralId);
    }

    private byte convertFloatToHighByte(float value) {
        int newValue = (int) (value * 10);
        return (byte) ((newValue >> 8) & 0xFF);
    }

    private byte convertFloatToLowByte(float value) {
        int newValue = (int) (value * 10);
        return (byte) (newValue & 0xFF);
    }

    private byte calculateCrc(byte[] bytes) {
        byte crc = (byte) 0xAA;
        for (int index = 0; index < 4; index++) {
            crc ^= bytes[index];
        }
        return crc;
    }

    public MessageBuilder appendFirstAndSecondParameter(float value) {
        this.firstParam = convertFloatToHighByte(value);
        this.secondParam = convertFloatToLowByte(value);
        return this;
    }

    public MessageBuilder appendFirstParameter(int parameter) {
        this.firstParam = (byte) parameter;
        return this;
    }

    public MessageBuilder appendSecondParameter(int parameter) {
        this.secondParam = (byte) parameter;
        return this;
    }

    public MessageBuilder appendThirdParameter(int parameter) {
        this.thirdParam = (byte) parameter;
        return this;
    }

    public MobileCommand build() {
        return messageOfBytes(messageType, firstParam, secondParam, thirdParam, centralId);
    }

    public static MessageBuilder setType(int messageType, char centralId) {
        return new MessageBuilder(messageType, centralId);
    }
}
