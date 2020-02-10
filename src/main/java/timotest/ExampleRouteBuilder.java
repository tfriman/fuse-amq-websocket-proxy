package timotest;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ExampleRouteBuilder extends RouteBuilder {
    public static final String ROUTE_ID = "exampleRoute";

    @Override
    public void configure() throws Exception {

        from("timer://foo?fixedRate=true&period=60000")
                .log("timer fired ${body}")
                .transform(simple("value " + new Date()))
                .to("amqpws://localhost:5672/testamqp")
                .end();

        from("amqpws://localhost:5672/testamqp")
                .routeId(ROUTE_ID)
                .streamCaching()
                .log(LoggingLevel.INFO, "got message ${body}")
                .end();

    }
}
