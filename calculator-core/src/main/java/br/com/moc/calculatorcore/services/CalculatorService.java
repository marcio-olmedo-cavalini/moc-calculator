package br.com.moc.calculatorcore.services;

import br.com.moc.calculatorcore.domain.MocResult;
import br.com.moc.calculatorcore.domain.Operations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CalculatorService {
    public MocResult process(Number a, Number b, String requestId, Operations operation) {
        log.info("[CORE|SESSION-LOG] -{}- Starting {} Service", requestId, operation.toString());
        MocResult result = new MocResult(requestId);
        switch (operation) {
            case SUM:
                result.setResult(a.floatValue() + b.floatValue());
                break;

            case SUBTRACTION:
                result.setResult(a.floatValue() - b.floatValue());
                break;

            case MULTIPLICATION:
                result.setResult(a.floatValue() * b.floatValue());
                break;
            case DIVISION:
                result.setResult(a.floatValue() / b.floatValue());
                break;

            default:
                System.out.println("Default Operation not selected");
                break;
        }

        log.info("[CORE|SESSION-LOG] -{}- Ending {} Service", requestId, operation.toString());
        return result;
    }
}
