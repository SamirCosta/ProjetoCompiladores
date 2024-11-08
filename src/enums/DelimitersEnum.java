package enums;

public enum DelimitersEnum {
    OPEN_PAREN("("),
    CLOSE_PAREN(")"),
    OPEN_BRACE("{"),
    CLOSE_BRACE("}"),
    OPEN_BRACKET("["),
    CLOSE_BRACKET("]"),
    SEMICOLON(";"),
    DOT("."),
    COMMA(",");

    private final String delimiter;

    DelimitersEnum(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public static DelimitersEnum fromString(String word) {
        for (DelimitersEnum delimiterValue : DelimitersEnum.values()) {
            if (delimiterValue.delimiter.equals(word)) {
                return delimiterValue;
            }
        }
        return null;
    }

    public static boolean contains(String word) {
        for (DelimitersEnum delimiterValue : DelimitersEnum.values()) {
            if (delimiterValue.getDelimiter().equals(word)) {
                return true;
            }
        }
        return false;
    }
}
