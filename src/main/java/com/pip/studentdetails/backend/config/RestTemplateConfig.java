package com.pip.studentdetails.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class responsible for exposing a RestTemplate bean.
 *
 * RestTemplate is Spring's HTTP client used for making synchronous REST calls
 * to external services (e.g., other microservices or external APIs).
 * By defining the RestTemplate as a @Bean, it becomes part of the Spring
 * application context and can be injected wherever needed using @Autowired.
 *
 * Note: For new applications, Spring recommends WebClient (reactive) as a
 * more modern alternative, but RestTemplate is perfectly suitable for simple,
 * blocking HTTP requests.
 */
@Configuration
public class RestTemplateConfig {

    /**
     * Creates and registers a RestTemplate bean in the Spring context.
     * @return a new RestTemplate instance
     * This instance can be injected into services or components for calling
     * backend APIs using methods such as:
     * - getForObject()
     * - postForObject()
     * - exchange()
     * - delete()
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}