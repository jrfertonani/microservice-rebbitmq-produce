package microservices.pedidos.config;

import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.ObjectMapper;


@Configuration
public class RabbitMQConfig {

    @Bean
    public JacksonJsonMessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        return new JacksonJsonMessageConverter(String.valueOf(objectMapper));
    }

}
