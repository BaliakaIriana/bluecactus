package com.snovibox.bluecactus;

import com.snovibox.bluecactus.configuration.filters.JwtTokenFilter;
import com.snovibox.bluecactus.configuration.utils.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BluecactusApplication {

    public static void main(String[] args) {
        SpringApplication.run(BluecactusApplication.class, args);
    }
}
