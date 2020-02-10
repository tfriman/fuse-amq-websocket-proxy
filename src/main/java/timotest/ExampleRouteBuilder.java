package timotest;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ExampleRouteBuilder extends RouteBuilder {

    @Override
    public void configure() {

        from("timer://foo?fixedRate=true&period=10000")
                .routeId("timerRoute")
                .transform(simple("value " + new Date()))
                .log("timer fired, sending ${body}")
                .to("{{queue.test}}")
                .end();

        from("{{queue.test}}")
                .routeId("consumerRoute")
                .streamCaching()
                .log(LoggingLevel.INFO, "got message ${body}")
                .end();

    }
}
