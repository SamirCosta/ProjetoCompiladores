package utils;

public class Token {
    public String type;
    public String lexema;

    public Token() {

    }

    public Token(String type, String lexema) {
        this.type = type;
        this.lexema = lexema;
    }

    @Override
    public String toString() {
        return "<" + type + ", " + lexema + ">";
    }


}

