package timotest;

import org.apache.camel.component.amqp.AMQPComponent;
import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Value("${amq.brokerURL}")
    private String brokerUrl;
    @Value("${amq.userName}")
    private String userName;
    @Value("${amq.password}")
    private String password;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean(name = "amqpws")
    public AMQPComponent amqpComponent() {
        JmsConnectionFactory qpid = new JmsConnectionFactory(userName, password, brokerUrl);
        return new AMQPComponent(qpid);
    }
}
