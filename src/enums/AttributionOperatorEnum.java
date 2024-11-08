package enums;

public enum AttributionOperatorEnum {
    ATTRIBUTION("="),
    SUM_ATTRIBUTION("+="),
    SUBTRACTION_ATTRIBUTION("-="),
    MULTIPLICATION_ATTRIBUTION("*="),
    DIVISION_ATTRIBUTION("/="),
    MODULE_ATTRIBUTION("%=");

    private final String operator;

    AttributionOperatorEnum(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public static AttributionOperatorEnum fromString(String word) {
        for (AttributionOperatorEnum attributor : AttributionOperatorEnum.values()) {
            if (attributor.operator.equals(word)) {
                return attributor;
            }
        }
        return null;
    }

    public static boolean contains(String operator) {
        for (AttributionOperatorEnum attributor : AttributionOperatorEnum.values()) {
            if (attributor.getOperator().equals(operator)) {
                return true;
            }
        }
        return false;
    }
}
