package br.com.moc.calculatorrest;

import br.com.moc.calculatorrest.controller.CalculatorController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculatorApplicationTests {

        @Autowired
        private CalculatorController calculatorController;

        @Test
        public void contextLoads() {
            Assert.assertNotNull(calculatorController);
        }
}
