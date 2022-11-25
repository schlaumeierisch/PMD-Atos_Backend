package nl.hva.backend

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@EnableWebSecurity
class SecurityConfiguration : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http
            .csrf().disable()
            .headers().frameOptions().disable()
            .and()
            // define accessible folders/paths (without being logged in)
            .authorizeRequests().antMatchers(
                "/css/**",
                "/fonts/**",
                "/img/**",
                "/js/**",
                "/rest/**",
                "/v3/api-docs/**",
                "/swagger-ui/**",
                "/h2-console/**"
            ).permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            // replace default login page from spring by our login page
            .loginPage("/login")
            .permitAll()
            .and()
            .logout()
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .logoutRequestMatcher(AntPathRequestMatcher("/logout"))
            .permitAll()
    }
}