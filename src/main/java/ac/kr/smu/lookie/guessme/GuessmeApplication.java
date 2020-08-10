package ac.kr.smu.lookie.guessme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EntityScan("ac.kr.smu.lookie.guessme.domain")
@EnableJpaRepositories("ac.kr.smu.lookie.guessme.repository")
@SpringBootApplication
public class GuessmeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuessmeApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
