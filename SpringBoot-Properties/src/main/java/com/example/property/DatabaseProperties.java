package com.example.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Configuration
@ConfigurationProperties(prefix = "database")
@Getter
@Setter
public class DatabaseProperties {

    @Getter
    @Setter
    public static class Server {

        /**
         * The IP of the database server
         */
        private String ip;

        /**
         * The Port of the database server.
         * The Default value is 443.
         * The allowed values are in the range 400-4000.
         */
        @Min(400)
        @Max(800)
        private int port = 443;
    }

    private String username;
    private String password;
    private Server server;
}
