package JunHyeong.fitnessService.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // CSRF 설정 Disable
        http.csrf().disable();
        http.authorizeHttpRequests()
                .requestMatchers("/user/**").authenticated() // 보완 해야한다
                //.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')") // 둘만 가능하다.
                //.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')")
                .requestMatchers("/admin/**").hasRole("ROLE_ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin() // 로그인 페이지로 이동
                .loginPage("/login") // 로그인 페이지 url
                .loginProcessingUrl("/loginProc")  // /loginProc 주소가 호출되면 시큐리티가 낚아채서 대신 로그인을 진행시킨다 -> 컨트롤러 안만들어도 된다.
                .defaultSuccessUrl("/"); // 로그인에 성공하면 /로 이동한다. 다른 페이지 요청으로 로그인 하면 로그인 성공시 요청 페이지로 이동한다.
        // .usernameParameter("username2") 이용시 파라미터 변수명 변경가능
        return http.build();
    }
}


