package br.com.moc.calculatorrest;

import ch.qos.logback.access.tomcat.LogbackValve;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = { "br.com.moc.calculatorrest", "br.com.moc.calculatorcore"})
@OpenAPIDefinition(info=@Info(title="MOC-Calculator REST API"))
public class CalculatorRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(CalculatorRestApplication.class, args);
    }

    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addContextValves(new LogbackValve());
        return tomcat;
    }
}