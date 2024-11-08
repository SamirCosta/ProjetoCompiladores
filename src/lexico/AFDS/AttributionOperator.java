package lexico.AFDS;

import enums.AttributionOperatorEnum;
import enums.ConditionOperatorEnum;
import enums.MathOperatorEnum;
import lexico.AFD;
import utils.Token;

import java.text.CharacterIterator;

public class AttributionOperator extends AFD {

    @Override
    public Token evaluate(CharacterIterator code) {
        StringBuilder str = new StringBuilder();
        str.append(code.current());

        if (code.current() == '=') {
            if (code.next() == '=') {
                str.append(code.current());
                code.next();
                return new Token(ConditionOperatorEnum.fromString(str.toString()).name(), str.toString());
            } else {
                return new Token(AttributionOperatorEnum.fromString(str.toString()).name(), str.toString());
            }
        }

        code.next();
        str.append(code.current());

        AttributionOperatorEnum attributionOperator = AttributionOperatorEnum.fromString(str.toString());

        if (attributionOperator != null) {
            code.next();
            return new Token(attributionOperator.name(), str.toString());
        }

        return null;
    }
}
