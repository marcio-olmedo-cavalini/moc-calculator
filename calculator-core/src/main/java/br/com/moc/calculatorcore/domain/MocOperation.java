package br.com.moc.calculatorcore.domain;

public class MocOperation {
    private Number a;
    private Number b;
    private Operations operation;
    private String requestId;

    public MocOperation() {
    }

    public MocOperation(Number a, Number b, Operations operation, String requestId) {
        this.a = a;
        this.b = b;
        this.operation = operation;
        this.requestId = requestId;
    }

    public Number getA() {
        return a;
    }

    public void setA(Number a) {
        this.a = a;
    }

    public Number getB() {
        return b;
    }

    public void setB(Number b) {
        this.b = b;
    }

    public Operations getOperation() {
        return operation;
    }

    public void setOperation(Operations operation) {
        this.operation = operation;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public String toString() {
        return "MocOperation{" +
                "a=" + a +
                ", b=" + b +
                ", Operation=" + operation.toString() +
                '}';
    }
}
