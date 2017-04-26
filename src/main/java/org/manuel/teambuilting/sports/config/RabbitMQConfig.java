package org.manuel.teambuilting.sports.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Manuel Doncel Martos
 * @since 07/12/2016.
 */
@Configuration
public class RabbitMQConfig  {

    private static final String TIMESTAMP_FORMAT = "yyyy-MM-dd\'T\'HH:mm:ss.SSSZ";

    @Bean
	public ObjectMapper objectMapper() {
		final ObjectMapper jsonObjectMapper = new ObjectMapper();
		jsonObjectMapper.configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, false);
		jsonObjectMapper.findAndRegisterModules();
		return jsonObjectMapper;
	}

    @Bean(name = "eventMessageConverter")
    public MessageConverter messageConverter() {
        final Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        // Jackson deserialization point issue
        converter.setJsonObjectMapper(objectMapper());
        return converter;
    }

}