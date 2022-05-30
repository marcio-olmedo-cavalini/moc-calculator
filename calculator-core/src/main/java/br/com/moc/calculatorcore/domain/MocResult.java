package br.com.moc.calculatorcore.domain;

import java.util.UUID;

public class MocResult {
    private UUID requestId;
    private Number result;

    public MocResult() {
    }

    public MocResult(String requestId) {
        this.requestId = UUID.fromString(requestId);
    }

    public Number getResult() {
        return result;
    }

    public void setResult(Number result) {
        this.result = result;
    }

    public UUID getRequestId() {
        return requestId;
    }

    public void setRequestId(UUID requestId) {
        this.requestId = requestId;
    }
}
