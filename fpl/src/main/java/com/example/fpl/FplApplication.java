package com.example.fpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@SpringBootApplication
public class FplApplication {

	public static void main(String[] args) {
		SpringApplication.run(FplApplication.class, args);
	}
	
	/**
     * This Registers the custom HttpMessageConverter.
     * @return
     */
    @Bean
    public HttpMessageConverters converters() {

       List<HttpMessageConverter<?>> converters = new ArrayList<>();
        
        //To suppress null objects in Response
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        objectMapper.setSerializationInclusion(Include.NON_EMPTY);
        objectMapper.disable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setDateFormat(new SimpleDateFormat("YYYY-MM-DD"));
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        jsonConverter.setObjectMapper(objectMapper);
        converters.add(jsonConverter);
        return new HttpMessageConverters(true, converters);
    }

}
