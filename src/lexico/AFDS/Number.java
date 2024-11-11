package lexico.AFDS;

import enums.*;
import lexico.AFD;
import utils.Token;

import java.text.CharacterIterator;

public class Number extends AFD {

    @Override
    public Token evaluate(CharacterIterator code) {
        String number = "";
        if (code.current() == '-') {
            number += "-";
            code.next();
        }

        if (Character.isDigit(code.current())) {
            number += readNumber(code);

            if (code.current() == '.') {
                number += ".";
                code.next();
                if (Character.isDigit(code.current())) {
                    number += readNumber(code);
                } else {
                    return null;
                }
            }

            if (endNumber(code)) {
                return new Token("NUM", number);
            }
        }
        return null;
    }

    private String readNumber(CharacterIterator code) {
        StringBuilder number = new StringBuilder();
        while (Character.isDigit(code.current())) {
            number.append(code.current());
            code.next();
        }
        return number.toString();
    }

    private boolean endNumber(CharacterIterator code) {
        return code.current() == ' ' ||
                Character.isLetter(code.current()) ||
                DelimitersEnum.contains(String.valueOf(code.current())) ||
                AttributionOperatorEnum.contains(String.valueOf(code.current())) ||
                ConditionOperatorEnum.contains(String.valueOf(code.current())) ||
                MathOperatorEnum.contains(String.valueOf(code.current())) ||
                LogicOperatorEnum.contains(String.valueOf(code.current())) ||
                code.current() == CharacterIterator.DONE ||
                code.current() == '\r' ||
                code.current() == '\t' ||
                code.current() == '\n';
    }

}
