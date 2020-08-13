package edu.miu.ebuy;

import edu.miu.ebuy.config.DataGenerate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;

@SpringBootApplication
@EnableAsync
public class EbuyApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(EbuyApplication.class, args);
        //DataGenerate.Generate();
    }

}
