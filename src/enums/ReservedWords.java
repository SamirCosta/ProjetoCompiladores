package enums;

public enum ReservedWords {
//    BEGIN("nachinat"),
//    END("konets"),
//    PRINT("pechat"),
//    SCAN("skanivorovat"),
//    IF("yesli"),
//    ELSE("inache"),
//    WHILE("poka"),
//    FOR("dlya"),
//    RETURN("vozvrat"),
//    CLASS("gruppa"),
//    STRING("stroka"),
//    INT("tseloye"),
//    FLOAT("plavat"),
//    BOOLEAN("bulev"),
//    TRUE("vernyy"),
//    FALSE("lozhnyy");

    BEGIN("BEGIN"),
    END("END"),
    PRINT("print"),
    SCAN("scan"),
    IF("if"),
    ELSE("else"),
    WHILE("while"),
    FOR("for"),
    STRING("string"),
    INT("int"),
    FLOAT("float"),
    BOOLEAN("boolean"),
    TRUE("true"),
    FALSE("false");

    private final String word;

    ReservedWords(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public static ReservedWords fromString(String word) {
        for (ReservedWords reserved : ReservedWords.values()) {
            if (reserved.word.equals(word)) {
                return reserved;
            }
        }
        return null;
    }
}
