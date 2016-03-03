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
public enum AlertMessage {
    NONE, TEMPERATURE_SENSOR, MAIN_BOARD, SUPPLY_FAN, EXTRACT_FAN, THERMAL_PROTECTION,
    WATER_HEATER_ANTIFROST_PROTECTION, CONNECTION_WITH_MAIN_BOARD, OVERHEATING, CHANGE_FILTER,
    DEFROSTING, ROTARY_EXCHANGER, FIRE_PROTECTION, EKOTOUCH_CONNECTION;
}
