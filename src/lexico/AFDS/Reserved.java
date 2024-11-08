package lexico.AFDS;

import enums.DelimitersEnum;
import lexico.AFD;
import enums.ReservedWords;
import utils.Token;

import java.text.CharacterIterator;

public class Reserved extends AFD {

    @Override
    public Token evaluate(CharacterIterator code) {
        String word = readWord(code);
        if (endReserved(code)) {
            ReservedWords reserved = ReservedWords.fromString(word);
            if (reserved != null) {
                return new Token(reserved.name(), word);
            }
        }
        return null;
    }

    private String readWord(CharacterIterator code) {
        StringBuilder id = new StringBuilder();
        id.append(code.current());
        code.next();

        while (Character.isLetter(code.current())) {
            id.append(code.current());
            code.next();
        }
        return id.toString();
    }

    public static boolean endReserved(CharacterIterator code) {
        return code.current() == ' ' ||
                DelimitersEnum.contains(String.valueOf(code.current())) ||
                code.current() == CharacterIterator.DONE ||
                code.current() == '\r' ||
                code.current() == '\t' ||
                code.current() == '\n';
    }
}
