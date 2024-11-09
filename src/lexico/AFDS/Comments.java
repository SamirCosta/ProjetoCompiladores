package lexico.AFDS;

import lexico.AFD;
import utils.Token;

import java.text.CharacterIterator;

public class Comments extends AFD {

    @Override
    public Token evaluate(CharacterIterator code) {
        if (code.current() == '/' && code.next() == '/') {
            return new Token("COMMENT", readComment(code));
        }

        return null;
    }

    private String readComment(CharacterIterator code) {
        StringBuilder comment = new StringBuilder();
        comment.append("//");
        code.next();

        while (code.current() != CharacterIterator.DONE && code.current() != '\n') {
            if (code.current() == '\r') {
                code.next();
                continue;
            }
            comment.append(code.current());
            code.next();
        }

        return comment.toString();
    }

}
