package lexico.AFDS;

import enums.AttributionOperatorEnum;
import enums.ConditionOperatorEnum;
import lexico.AFD;
import utils.Token;

import java.text.CharacterIterator;

public class ConditionOperator extends AFD {

    @Override
    public Token evaluate(CharacterIterator code) {
        StringBuilder str = new StringBuilder();
        str.append(code.current());

        if (code.current() == '<' || code.current() == '>'){
            if (code.next() == '=') {
                str.append(code.current());
                code.next();
                return new Token(ConditionOperatorEnum.fromString(str.toString()).name(), str.toString());
            } else {
                return new Token(ConditionOperatorEnum.fromString(str.toString()).name(), str.toString());
            }
        }

        return null;
    }
}
