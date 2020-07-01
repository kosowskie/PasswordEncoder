package pl.kosowski.lab1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Collections;

@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new HashFunction();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        User userAdmin = new User("mcnuel",
                getPasswordEncoder().encode("admin1"),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
        User userUser = new User("diuki",
                getPasswordEncoder().encode("qwerty123"),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));

        auth.inMemoryAuthentication().withUser(userAdmin);
        auth.inMemoryAuthentication().withUser(userUser);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/forAdmin","/forUser").hasRole("ADMIN")
                .antMatchers("/forUser").hasRole("USER")
                .and()
                .formLogin().defaultSuccessUrl("/count").permitAll()
                .and()
                .logout().logoutSuccessUrl("/forLogout");
    }
}
