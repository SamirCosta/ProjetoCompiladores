package enums;

public enum MathOperatorEnum {
    PLUS("+"),
    MINUS("-"),
    MULT("*"),
    DIV("/"),
    MODULE("%");

    private final String operator;

    MathOperatorEnum(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public static MathOperatorEnum fromString(String word) {
        for (MathOperatorEnum op : MathOperatorEnum.values()) {
            if (op.operator.equals(word)) {
                return op;
            }
        }
        return null;
    }

    public static boolean contains(String word) {
        for (MathOperatorEnum op : MathOperatorEnum.values()) {
            if (op.getOperator().equals(word)) {
                return true;
            }
        }
        return false;
    }
}
