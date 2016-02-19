/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.ekozefir.mobile.serial.centralstate.value;

import pl.ekozefir.mobile.serial.centralstate.ParsedValue;
import pl.ekozefir.mobile.serial.centralstate.Response;
import pl.ekozefir.mobile.serial.centralstate.TempParser;

/**
 *
 * @author Michal Marasz
 */
public class TempExtractParser extends TempParser {

    private static final int highByteNumber = 8;
    private static final int lowByteNumber = 7;

    @Override
    public ParsedValue parse(Response response) {
        return parseTemp(response, highByteNumber, lowByteNumber);
    }
}
