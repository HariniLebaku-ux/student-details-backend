package com.pip.studentdetails.backend.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;

class RestTemplateConfigTest {

    @Test
    void testRestTemplateBeanCreation() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(RestTemplateConfig.class);

        RestTemplate restTemplate = context.getBean(RestTemplate.class);

        assertThat(restTemplate).isNotNull();
        context.close();
    }
}