package glp.digiteam;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import glp.digiteam.uploadFile.StorageProperties;
import glp.digiteam.uploadFile.StorageService;

import net.unicon.cas.client.configuration.EnableCasClient;

@EnableCasClient
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	

	@Bean
	CommandLineRunner init(StorageService storageService) {
			return (args) -> {
			};
		}
}



