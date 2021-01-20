package com.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@ComponentScan(basePackages = "com")
@Import(value = EncoderConfig.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder passwordEncoder;

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(BCryptPasswordEncoder passwordEncoder,
                          @Qualifier("myUserDetailsService") UserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/myapp/login*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .defaultSuccessUrl("/myapp/hello/run", true)
                .and()
                .logout();
    }

     /*@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("John").password("$2a$10$8.7oG3lIQGJO9RjUF1KDnO/DlArDUDVM2IEA11ijaenypfSg81Osq")
                .roles("USER")//123456
                .and()
                .withUser("Jack").password("$2a$10$nflkNpGmd54W/53vnR.dMulHg3DD.qfIQjqefYgiJOIrlb/YOxj3y")
                .roles("USER", "ADMIN");//
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/myapp/login*")
                .permitAll()
                .antMatchers("/myapp/man/find/2").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/myapp/hello/run", true)
                .and()
                .logout();
    }*/
}
