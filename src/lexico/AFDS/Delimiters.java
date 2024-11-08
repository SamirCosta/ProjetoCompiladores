package lexico.AFDS;

import enums.DelimitersEnum;
import lexico.AFD;
import utils.Token;

import java.text.CharacterIterator;

public class Delimiters extends AFD {
    @Override
    public Token evaluate(CharacterIterator code) {
        String character = String.valueOf(code.current());
        DelimitersEnum delimiter = DelimitersEnum.fromString(character);
        code.next();

        if (delimiter != null) {
            return new Token(delimiter.name(), character);
        }

        return null;
    }
}
