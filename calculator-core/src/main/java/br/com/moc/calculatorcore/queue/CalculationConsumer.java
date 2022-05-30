package br.com.moc.calculatorcore.queue;

import br.com.moc.calculatorcore.domain.MocOperation;
import br.com.moc.calculatorcore.domain.MocResult;
import br.com.moc.calculatorcore.services.CalculatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CalculationConsumer {
    private final CalculatorService calculatorService;

    public CalculationConsumer(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @RabbitListener(queues = "#{queue.name}", concurrency = "5")
    public MocResult calculate(MocOperation operation) {
        log.info("[CORE|SESSION-LOG] -{}- Received an Operation {} from queue", operation.getRequestId(), operation);
        return calculatorService.process(operation.getA(), operation.getB(), operation.getRequestId(), operation.getOperation());
    }
}
