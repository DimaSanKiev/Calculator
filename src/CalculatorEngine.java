public class CalculatorEngine {

    private enum Operator {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }

    private double currentTotal;

    public String getTotalString() {
        return currentTotal % 1.0 == 0
                ? Integer.toString((int) currentTotal)
                : String.valueOf(currentTotal);
    }

    public void equal(String number) {
        currentTotal = Double.parseDouble(number);
    }

    public void add(String number) {
        convertToDouble(number, Operator.ADD);
    }

    public void subtract(String number) {
        convertToDouble(number, Operator.SUBTRACT);
    }

    public void multiply(String number) {
        convertToDouble(number, Operator.MULTIPLY);
    }

    public void divide(String number) {
        convertToDouble(number, Operator.DIVIDE);
    }

    private void convertToDouble(String number, Operator operator) {
        double dblNumber = Double.parseDouble(number);
        switch (operator) {
            case ADD:
                add(dblNumber);
                break;
            case SUBTRACT:
                subtract(dblNumber);
                break;
            case MULTIPLY:
                multiply(dblNumber);
                break;
            case DIVIDE:
                divide(dblNumber);
                break;
            default:
                throw new AssertionError(operator.name());
        }
    }

    private void add(double number) {
        currentTotal += number % 1.0 == 0 ? (int) number : number;
    }

    private void subtract(double number) {
        currentTotal -= number % 1.0 == 0 ? (int) number : number;
    }

    private void multiply(double number) {
        currentTotal *= number % 1.0 == 0 ? (int) number : number;
    }

    private void divide(double number) {
        currentTotal /= number % 1.0 == 0 ? (int) number : number;
    }

}

