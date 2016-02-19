/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ekozefir.mobile.serial.centralstate.value;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import pl.ekozefir.mobile.serial.centralstate.MobileParser;
import pl.ekozefir.mobile.serial.centralstate.ParsedValue;
import pl.ekozefir.mobile.serial.centralstate.Response;

/**
 *
 * @author Michal Marasz
 */
public class FunctionParser implements MobileParser {

    private static final Map<Integer, String> values = ImmutableMap.of(0, "HEATING", 1, "COOLING", 2, "RECOVERY", 3, "AUTO");
    private static final String autoValue = "AUTO";
    private static final int maxValue = 8;
    private static final int byteNumber = 38;
    private static final int bitShift = 0;
    private static final int bitMask = 15;

    @Override
    public ParsedValue parse(Response response) {
        int value = response.convertByteOfNumberToInt(byteNumber, bitShift, bitMask);
        if (isAuto(value)) {
            return new ParsedValue(autoValue);
        }
        return new ParsedValue(values.get(value));
    }

    private boolean isAuto(int value) {
        return value >= maxValue;
    }
}
