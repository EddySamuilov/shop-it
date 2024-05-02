package shopIT.shopIT.config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class ApplicationSecurityConfiguration {

  private final RSAKeyProperties rsaKeyProperties;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.authorizeHttpRequests((auth) -> auth
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
//      .requestMatchers("/**").permitAll() // TODO: This line grants access to all resources in the APP for testing simplicity. TO BE REMOVED IN FUTURE
            .requestMatchers("/", "/users/login", "/users/register").permitAll()
            .requestMatchers("/**").authenticated())
        .formLogin(withDefaults())
//    .formLogin(loginConfig -> loginConfig
//      .loginPage("/users/login")
//      .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
//      .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
//      .defaultSuccessUrl("/")
//      .failureForwardUrl("/users/login-error"))
//    .logout(logoutConfig -> logoutConfig
//      .logoutUrl("/users/logout")
//      .logoutSuccessUrl("/")
//      .invalidateHttpSession(true)
//      .deleteCookies("JSESSIONID"))
        .oauth2Login(withDefaults()) // TODO: This is related to login with google/github/etc
//    .httpBasic(withDefaults())
//    .csrf(AbstractHttpConfigurer::disable) // CSRF disabled
//    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//    .oauth2ResourceServer(oAuth2ResourceServerConfigurer -> oAuth2ResourceServerConfigurer.jwt(withDefaults()))
        .build();
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    return NimbusJwtDecoder.withPublicKey(rsaKeyProperties.publicKey()).build();
  }
  @Bean
  public JwtEncoder jwtEncoder() {
    JWK jwk = new RSAKey.Builder(rsaKeyProperties.publicKey()).privateKey(rsaKeyProperties.privateKey()).build();
    JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwk));
    return new NimbusJwtEncoder(jwkSource);
  }
}