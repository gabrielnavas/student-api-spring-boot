package repository_spring.repository_spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import repository_spring.repository_spring.service.user.UserDetailServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
  @Autowired
  private UserDetailServiceImpl userDetailServiceImpl;

  @Bean
  public PasswordEncoder passwordEncoder() {
    // usando bcrypt para encoder e matcher
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .userDetailsService(userDetailServiceImpl)
      .passwordEncoder(passwordEncoder());
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
      .csrf().disable() // não é aplicação web, e sim uma API restfull
      .authorizeRequests()

        .antMatchers("/api/clients/**").hasAnyRole("USER", "ADMIN")
        .antMatchers("/api/orders/**").hasAnyRole("USER", "ADMIN")
        .antMatchers("/api/products/**").hasRole("ADMIN")
        .and()
        .httpBasic();
  }
}
