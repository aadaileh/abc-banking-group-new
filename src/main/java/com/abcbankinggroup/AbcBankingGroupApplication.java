package com.abcbankinggroup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <h1>ABC Banking Group Application</h1>
 *
 * <p>
 * Main "Application" class that is used to bootstrap the Spring Boot application.
 * </p>
 *
 * @Author  Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
 * @version 1.0
 * @since   26.01.2018
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AbcBankingGroupApplication {

    /**
     * Main method, that is invoked on application startup. It can be started in the IDE (also in
     * debugging mode) for local development.
     */
    public static void main(String[] args) {
        SpringApplication.run(AbcBankingGroupApplication.class, args);
    }
}
