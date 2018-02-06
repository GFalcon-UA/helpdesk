package com.javamog.potapov.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    public SecurityConfig() {
        super();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http.csrf().disable()
                .formLogin()
                .loginPage("/login.html")
                .failureUrl("/login-error.html")
                .successForwardUrl("/index.html")
                .and()
                .logout()
                .logoutSuccessUrl("/login.html")
                .and()


                .authorizeRequests()
//                .antMatchers("/css/*", "/js/*", "**/*.js").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/index.html").authenticated()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/user/**").hasRole("USER")
//                .antMatchers("/shared/**").hasAnyRole("USER","ADMIN")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403.html");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
//
//    @Override
//    @Bean
//    protected AuthenticationManager authenticationManager() throws Exception {
//        final List<AuthenticationProvider> providers = new ArrayList<>(1);
//        providers.add(preauthAuthProvider());
//        return new ProviderManager(providers);
//    }
//
//    @Bean
//    PreAuthenticatedAuthenticationProvider preauthAuthProvider() throws Exception {
//        PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
//        provider.setPreAuthenticatedUserDetailsService(userDetailsServiceWrapper());
//        return provider;
//    }
//
//    @Bean
//    UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> userDetailsServiceWrapper() throws Exception {
//        UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> wrapper = new UserDetailsByNameServiceWrapper<>();
//        wrapper.setUserDetailsService(userSecurityService());
//        return wrapper;
//    }
//
//    @Bean
//    UserDetailsService userSecurityService() {
//        return new UserDetailsServiceImpl();
//    }




}
