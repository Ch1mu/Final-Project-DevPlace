package FinalProject.MvcMessages.Config;



import FinalProject.MvcMessages.Controllers.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class SecSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService uS;
    @Autowired
    AuthenticationSuccessHandler successHandler;
    @Autowired
    PasswordEncoder pE;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {


        auth.
                userDetailsService(uS)
                .passwordEncoder(pE);

    }


    @Override
    protected void configure(final HttpSecurity http) throws Exception
    {

           http

                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user*").hasRole("USER")
                .antMatchers("/login*", "/signup*").permitAll()
                .anyRequest().authenticated()
                .and()
                   .csrf().disable()
                .formLogin()
                   .loginPage("/login")
                   .successHandler(successHandler)
                .failureUrl("/login?error=true")
                   .usernameParameter("username")
                   .passwordParameter("password")
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID");




    }



}
