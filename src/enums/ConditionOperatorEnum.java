package enums;

public enum ConditionOperatorEnum {
    IS_EQUAL("=="),
    IS_DIFFERENT("!="),
    GREATER_EQUAL(">="),
    MINOR_EQUAL("<="),
    GREATER(">"),
    MINOR("<");

    private final String operator;

    ConditionOperatorEnum(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public static ConditionOperatorEnum fromString(String word) {
        for (ConditionOperatorEnum op : ConditionOperatorEnum.values()) {
            if (op.operator.equals(word)) {
                return op;
            }
        }
        return null;
    }

    public static boolean contains(String operator) {
        for (ConditionOperatorEnum op : ConditionOperatorEnum.values()) {
            if (op.getOperator().equals(operator)) {
                return true;
            }
        }
        return false;
    }
}
