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

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import pl.ekozefir.mobile.serial.centralstate.InverseEnumMap;
import pl.ekozefir.mobile.serial.centralstate.InverseEnumMapToValue;
import pl.ekozefir.mobile.serial.centralstate.MobileParser;
import pl.ekozefir.mobile.serial.centralstate.Response;
import pl.ekozefir.mobile.serial.centralstate.value.AlertMessageParser.AlertMessage;
import static pl.ekozefir.mobile.serial.centralstate.value.AlertMessageParser.AlertMessage.CHANGE_FILTER;
import static pl.ekozefir.mobile.serial.centralstate.value.AlertMessageParser.AlertMessage.CONNECTION_WITH_MAIN_BOARD;
import static pl.ekozefir.mobile.serial.centralstate.value.AlertMessageParser.AlertMessage.DEFROSTING;
import static pl.ekozefir.mobile.serial.centralstate.value.AlertMessageParser.AlertMessage.EKOTOUCH_CONNECTION;
import static pl.ekozefir.mobile.serial.centralstate.value.AlertMessageParser.AlertMessage.EXTRACT_FAN;
import static pl.ekozefir.mobile.serial.centralstate.value.AlertMessageParser.AlertMessage.FIRE_PROTECTION;
import static pl.ekozefir.mobile.serial.centralstate.value.AlertMessageParser.AlertMessage.MAIN_BOARD;
import static pl.ekozefir.mobile.serial.centralstate.value.AlertMessageParser.AlertMessage.NONE;
import static pl.ekozefir.mobile.serial.centralstate.value.AlertMessageParser.AlertMessage.OVERHEATING;
import static pl.ekozefir.mobile.serial.centralstate.value.AlertMessageParser.AlertMessage.ROTARY_EXCHANGER;
import static pl.ekozefir.mobile.serial.centralstate.value.AlertMessageParser.AlertMessage.SUPPLY_FAN;
import static pl.ekozefir.mobile.serial.centralstate.value.AlertMessageParser.AlertMessage.TEMPERATURE_SENSOR;
import static pl.ekozefir.mobile.serial.centralstate.value.AlertMessageParser.AlertMessage.THERMAL_PROTECTION;
import static pl.ekozefir.mobile.serial.centralstate.value.AlertMessageParser.AlertMessage.WATER_HEATER_ANTIFROST_PROTECTION;

/**
 *
 * @author Michal Marasz
 */
public class AlertMessageParser implements MobileParser<AlertMessage> {

    public enum AlertMessage {
        NONE, TEMPERATURE_SENSOR, MAIN_BOARD, SUPPLY_FAN, EXTRACT_FAN, THERMAL_PROTECTION,
        WATER_HEATER_ANTIFROST_PROTECTION, CONNECTION_WITH_MAIN_BOARD, OVERHEATING, CHANGE_FILTER,
        DEFROSTING, ROTARY_EXCHANGER, FIRE_PROTECTION, EKOTOUCH_CONNECTION;
    }
    private static final InverseEnumMap<AlertMessage, Integer> values = new InverseEnumMapToValue(
            Maps.immutableEnumMap(ImmutableMap.<AlertMessage, Integer>builder().put(NONE, 0).
                    put(TEMPERATURE_SENSOR, 1).
                    put(MAIN_BOARD, 2).
                    put(SUPPLY_FAN, 3).
                    put(EXTRACT_FAN, 4).
                    put(THERMAL_PROTECTION, 5).
                    put(WATER_HEATER_ANTIFROST_PROTECTION, 6).
                    put(CONNECTION_WITH_MAIN_BOARD, 7).
                    put(OVERHEATING, 8).
                    put(CHANGE_FILTER, 9).
                    put(DEFROSTING, 12).
                    put(ROTARY_EXCHANGER, 13).
                    put(FIRE_PROTECTION, 14).
                    put(EKOTOUCH_CONNECTION, 53).
                    build()));

    private static final int byteNumber = 41;

    @Override
    public AlertMessage parse(Response response) {
        return values.find(response.convertByteOfNumberToInt(byteNumber));
    }

}
