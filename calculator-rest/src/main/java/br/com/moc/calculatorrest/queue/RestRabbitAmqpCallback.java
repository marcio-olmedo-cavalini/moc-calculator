package br.com.moc.calculatorrest.queue;

import br.com.moc.calculatorcore.domain.MocOperation;
import br.com.moc.calculatorcore.domain.MocResult;
import br.com.moc.calculatorcore.domain.Operations;
import br.com.moc.calculatorrest.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
public class RestRabbitAmqpCallback {
    private final AsyncRabbitTemplate asyncRabbitTemplate;
    private final DirectExchange directExchange;
    private static final String ROUTING_KEY = "calculation";

    public RestRabbitAmqpCallback(AsyncRabbitTemplate asyncRabbitTemplate, DirectExchange directExchange) {
        this.asyncRabbitTemplate = asyncRabbitTemplate;
        this.directExchange = directExchange;
    }

    public MocResult sendOperationToQueue(Number a, Number b, Operations operation, String requestId) {
        log.info("[REST|SESSION-LOG] -{}- Sending {} Operation Request to Queue", requestId, operation.toString());
        MocResult mocResult = null;
        MocOperation mocOperation = new MocOperation(a, b, operation, requestId);

        AsyncRabbitTemplate.RabbitConverterFuture<MocResult> rabbitConverterFuture;

        try {
            rabbitConverterFuture = asyncRabbitTemplate.convertSendAndReceiveAsType(
                    directExchange.getName(),
                    ROUTING_KEY,
                    mocOperation,
                    new ParameterizedTypeReference<MocResult>() {
                    });

            if (MDC.getCopyOfContextMap() == null)
                MDC.put(Constants.REQUEST_ID, requestId);

            rabbitConverterFuture.addCallback(new ListenableFutureCallback<MocResult>() {
                @Override
                public void onFailure(Throwable ex) {
                    log.error("[REST|SESSION-LOG] -{}- Error to obtain response for Operation {}", requestId, mocOperation, ex);
                }

                @Override
                public void onSuccess(MocResult registrationDto) {
                    log.info("[REST|SESSION-LOG] -{}- Operation received {}", requestId, mocOperation);
                }
            });

            mocResult = rabbitConverterFuture.get();

        } catch (InterruptedException | ExecutionException exception) {
            log.error("[REST|SESSION-LOG] -{}- Fail to send Operation request to queue, {}", requestId, exception.getMessage());
        }

        return mocResult;
    }
}
