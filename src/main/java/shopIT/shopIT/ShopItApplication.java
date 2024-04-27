package shopIT.shopIT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import shopIT.shopIT.config.RSAKeyProperties;

@SpringBootApplication
@EnableConfigurationProperties(RSAKeyProperties.class)
public class ShopItApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopItApplication.class, args);
	}

}
