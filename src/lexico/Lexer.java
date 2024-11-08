package lexico;

import lexico.AFDS.*;
import lexico.AFDS.Number;
import utils.Token;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private List<Token> tokens;
    private List<AFD> afds;
    private CharacterIterator code;

    public Lexer() {

    }

    public Lexer(String code) {
        tokens = new ArrayList<>();
        afds = new ArrayList<>();
        this.code = new StringCharacterIterator(code);

        afds.add(new Delimiters());
        afds.add(new Reserved());
        afds.add(new ID());
        afds.add(new Number());
        afds.add(new Text());
        afds.add(new Comments());
        afds.add(new AttributionOperator());
        afds.add(new LogicOperator());
        afds.add(new ConditionOperator());
        afds.add(new MathOperator());

    }

    public void skipWhiteSpace() {
        while ( code.current() == ' ' ||
                code.current() == '\n' ||
                code.current() == '\r' ||
                code.current() == '\s' ||
                code.current() == '\t'
        ) {
            code.next();
        }
    }

    public List<Token> getTokens(){
        boolean accepted;

        while(code.current() != CharacterIterator.DONE){
            accepted = false;
            skipWhiteSpace();
            if(code.current() == CharacterIterator.DONE)break;
            for(AFD afd: afds){
                int pos = code.getIndex();
                Token token =  afd.evaluate(code);
                if(token != null){
                    accepted = true;
                    tokens.add(token);
                    break;
                }else{
                    code.setIndex(pos);
                }

            }
            if (accepted) continue;
            throw new RuntimeException("Token not recognized:"+ code.current());
        }
        tokens.add(new Token("EOF","$"));
        return tokens;
    }

}
