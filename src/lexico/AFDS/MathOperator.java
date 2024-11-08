package lexico.AFDS;

import enums.DelimitersEnum;
import enums.MathOperatorEnum;
import lexico.AFD;
import utils.Token;

import java.text.CharacterIterator;

public class MathOperator extends AFD {
    @Override
    public Token evaluate(CharacterIterator code) {
        String character = String.valueOf(code.current());
        MathOperatorEnum mathOperator = MathOperatorEnum.fromString(character);
        code.next();

        if (mathOperator != null) {
            return new Token(mathOperator.name(), character);
        }

        return null;
    }
}
