package lexico.AFDS;

import enums.AttributionOperatorEnum;
import enums.ConditionOperatorEnum;
import enums.LogicOperatorEnum;
import lexico.AFD;
import utils.Token;

import java.text.CharacterIterator;

public class LogicOperator extends AFD {
    @Override
    public Token evaluate(CharacterIterator code) {
        StringBuilder str = new StringBuilder();
        str.append(code.current());

        if (code.current() == '&' || code.current() == '|'){
            if (code.next() == '&' && str.toString().equals("&")) {
                str.append(code.current());
                code.next();
                return new Token(LogicOperatorEnum.fromString(str.toString()).name(), str.toString());
            }
            if (code.current() == '|' && str.toString().equals("|")) {
                str.append(code.current());
                code.next();
                return new Token(LogicOperatorEnum.fromString(str.toString()).name(), str.toString());
            }
        }

        if (code.current() == '!'){
            if (code.next() == '=') {
                str.append(code.current());
                code.next();
                return new Token(ConditionOperatorEnum.fromString(str.toString()).name(), str.toString());
            } else {
                return new Token(LogicOperatorEnum.fromString(str.toString()).name(), str.toString());
            }
        }

        return null;
    }
}
