package lexico.AFDS;

import enums.AttributionOperatorEnum;
import enums.ConditionOperatorEnum;
import enums.DelimitersEnum;
import enums.MathOperatorEnum;
import lexico.AFD;
import utils.Token;
import java.text.CharacterIterator;

public class ID extends AFD {

    @Override
    public Token evaluate(CharacterIterator code) {
        if (Character.isLetter(code.current()) || code.current() == '_') {
            String id = readID(code);
            if (endID(code)) {
                return new Token("ID", id);
            }
        }
        return null;
    }

    private String readID(CharacterIterator code) {
        StringBuilder id = new StringBuilder();
        id.append(code.current());
        code.next();

        while (Character.isLetterOrDigit(code.current()) || code.current() == '_') {
            id.append(code.current());
            code.next();
        }
        return id.toString();
    }

    private boolean endID(CharacterIterator code) {
        return code.current() == ' ' ||
                DelimitersEnum.contains(String.valueOf(code.current())) ||
                AttributionOperatorEnum.contains(String.valueOf(code.current())) ||
                ConditionOperatorEnum.contains(String.valueOf(code.current())) ||
                MathOperatorEnum.contains(String.valueOf(code.current())) ||
                code.current() == CharacterIterator.DONE ||
                code.current() == '\r' ||
                code.current() == '\t' ||
                code.current() == '\n';
    }

}
