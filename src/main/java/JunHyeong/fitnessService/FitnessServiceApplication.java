package JunHyeong.fitnessService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "JunHyeong.fitnessService.entity")
public class FitnessServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitnessServiceApplication.class, args);
	}

}
