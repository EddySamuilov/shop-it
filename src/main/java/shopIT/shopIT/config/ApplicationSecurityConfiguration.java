package shopIT.shopIT.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class ApplicationSecurityConfiguration {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests((auth) -> auth
      .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
      .requestMatchers("/**").permitAll() // TODO: This line grants access to all resources in the APP for testing simplicity. TO BE REMOVED IN FUTURE
//      .requestMatchers("/", "/users/login", "/users/register").permitAll()
//      .requestMatchers("/statistics").hasAnyRole(Role.ADMIN.name())
//      .requestMatchers("/**").authenticated()
    ).csrf().disable()
    .formLogin(loginConfig -> loginConfig
      .loginPage("/users/login")
      .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
      .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
      .defaultSuccessUrl("/")
      .failureForwardUrl("/users/login-error")
    )
    .logout(logoutConfig -> logoutConfig
      .logoutUrl("/users/logout")
      .logoutSuccessUrl("/")
      .invalidateHttpSession(true)
      .deleteCookies("JSESSIONID")
    );

    return http.build();
  }
}