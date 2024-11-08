package lexico.AFDS;

import enums.DelimitersEnum;
import lexico.AFD;
import utils.Token;

import java.text.CharacterIterator;

public class Text extends AFD {
    @Override
    public Token evaluate(CharacterIterator code) {
        if (code.current() == '"') {
            String str = readString(code);
            if (str != null) {
                return new Token("TEXT", str);
            }
        }
        return null;
    }

    private String readString(CharacterIterator code) {
        StringBuilder str = new StringBuilder();
        str.append(code.current());
        code.next();

        while (code.current() != CharacterIterator.DONE) {
            str.append(code.current());

            if (code.current() == '"') {
                code.next();
                return str.toString();
            }

            code.next();
        }

        return null;
    }
}
