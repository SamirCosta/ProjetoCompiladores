package enums;

public enum LogicOperatorEnum {
    AND("&&"),
    OR("||"),
    DIFFERENT("!");

    private final String operator;

    LogicOperatorEnum(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public static LogicOperatorEnum fromString(String word) {
        for (LogicOperatorEnum op : LogicOperatorEnum.values()) {
            if (op.operator.equals(word)) {
                return op;
            }
        }
        return null;
    }

    public static boolean contains(String operator) {
        for (LogicOperatorEnum op : LogicOperatorEnum.values()) {
            if (op.getOperator().equals(operator)) {
                return true;
            }
        }
        return false;
    }
}
