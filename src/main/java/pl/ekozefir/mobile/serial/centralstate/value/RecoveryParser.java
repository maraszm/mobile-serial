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

import pl.ekozefir.mobile.serial.centralstate.MobileParser;
import pl.ekozefir.mobile.serial.centralstate.Response;
import pl.ekozefir.mobile.serial.centralstate.TempParser;

/**
 *
 * @author Michal Marasz
 */
public class RecoveryParser implements MobileParser {

    private final static BypassEquipmentParser bypassEquipment = new BypassEquipmentParser();
    private final static BypassStateParser bypassState = new BypassStateParser();
    private static final PreheaterEquipmentParser preheaterEquipment = new PreheaterEquipmentParser();
    private static final TempPreheaterParser tempPreheater = new TempPreheaterParser();
    private static final GroundIntakeEquipmentParser intakeEquipment = new GroundIntakeEquipmentParser();
    private static final GroundIntakeStateParser intakeState = new GroundIntakeStateParser();
    private static final TempIntakeGroundParser tempIntakeGround = new TempIntakeGroundParser();
    private static final TempIntakeWallParser tempIntakeWall = new TempIntakeWallParser();
    private static final TempSupplyParser tempSupply = new TempSupplyParser();
    private static final TempExtractParser tempExtract = new TempExtractParser();

    @Override
    public Object parse(Response response) {
        try {
            return getRecovery(response);
        } catch (Exception ex) {
            return "NONE";
        }
    }

    private Object getRecovery(Response response) {
        if (bypassState.parse(response) == BypassStateParser.BypassState.ON
                && bypassEquipment.parse(response) == BypassEquipmentParser.BypassEquipment.TRUE) {
            return "NONE";
        }
        float tc;
        if (preheaterEquipment.parse(response) == PreheaterEquipmentParser.PreheaterEquipment.TRUE) {
            tc = getTemp(tempPreheater, response);
        } else if (intakeEquipment.parse(response) == GroundIntakeEquipmentParser.GroundInakeEquipment.TRUE
                && intakeState.parse(response) == GroundIntakeStateParser.GroundIntakeState.ON) {
            tc = getTemp(tempIntakeGround, response);
        } else {
            tc = getTemp(tempIntakeWall, response);
        }
        float tempExtractValue = getTemp(tempExtract, response);
        float tempSupplyValue = getTemp(tempSupply, response);
        if (tempExtractValue - tc == 0) {
            return "NONE";
        }
        float recovery = (tempSupplyValue - tc) / (tempExtractValue - tc) * 100;
        if (recovery <= 97 && recovery >= 40) {
            return Math.round(recovery);
        }
        return "NONE";
    }

    private float getTemp(MobileParser parser, Response response) {
        Object valueFromTemp = parser.parse(response);
        if (!(valueFromTemp instanceof TempParser.TempError)) {
            return (float) valueFromTemp;
        }
        throw new IllegalStateException("Error with temp sensor " + valueFromTemp);
    }
}
