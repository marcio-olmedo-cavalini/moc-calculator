package br.com.moc.calculatorrest.controller;

import br.com.moc.calculatorcore.domain.MocResult;
import br.com.moc.calculatorcore.domain.Operations;
import br.com.moc.calculatorrest.constant.Constants;
import br.com.moc.calculatorrest.queue.RestRabbitAmqpCallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
public class CalculatorController {

    @Autowired
    private RestRabbitAmqpCallback restRabbitAmqpCallback;

    @GetMapping("/sum")
    public ResponseEntity<?> sum(@RequestParam(name = "a") Number a, @RequestParam(name = "b") Number b, HttpServletResponse response) {
        String requestId = response.getHeader(Constants.REQUEST_ID);
        log.info("[REST|SESSION-LOG] -{}- Starting SUM Request - Received a={} and b={} for operation", requestId, a, b);
        log.info("[REST|SESSION-LOG] -{}- Calling SUM Service", requestId);
        MocResult result = restRabbitAmqpCallback.sendOperationToQueue(a, b, Operations.SUM, requestId);
        log.info("[REST|SESSION-LOG] -{}- Ending SUM Request", requestId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/subtraction")
    public ResponseEntity<?> subtraction(@RequestParam(name = "a") Number a, @RequestParam(name = "b") Number b, HttpServletResponse response) {
        String requestId = response.getHeader(Constants.REQUEST_ID);
        log.info("[REST|SESSION-LOG] -{}- Starting SUBTRACTION Request", requestId);
        log.info("[REST|SESSION-LOG] -{}- Calling SUBTRACTION Service", requestId);
        MocResult result = restRabbitAmqpCallback.sendOperationToQueue(a, b, Operations.SUBTRACTION, requestId);
        log.info("[REST|SESSION-LOG] -{}- Ending SUBTRACTION Request", requestId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/multiplication")
    public ResponseEntity<?> multiplication(@RequestParam(name = "a") Number a, @RequestParam(name = "b") Number b, HttpServletResponse response) {
        String requestId = response.getHeader(Constants.REQUEST_ID);
        log.info("[REST|SESSION-LOG] -{}- Starting MULTIPLICATION Request", requestId);
        log.info("[REST|SESSION-LOG] -{}- Calling MULTIPLICATION Service", requestId);
        MocResult result = restRabbitAmqpCallback.sendOperationToQueue(a, b, Operations.MULTIPLICATION, requestId);
        log.info("[REST|SESSION-LOG] -{}- Ending MULTIPLICATION Request", requestId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/division")
    public ResponseEntity<?> division(@RequestParam(name = "a") Number a, @RequestParam(name = "b") Number b, HttpServletResponse response) {
        String requestId = response.getHeader(Constants.REQUEST_ID);
        log.info("[REST|SESSION-LOG] -{}- Starting DIVISION Request", requestId);
        log.info("[REST|SESSION-LOG] -{}- Calling DIVISION Service", requestId);
        MocResult result = restRabbitAmqpCallback.sendOperationToQueue(a, b, Operations.DIVISION, requestId);
        log.info("[REST|SESSION-LOG] -{}- Ending DIVISION Request", requestId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
