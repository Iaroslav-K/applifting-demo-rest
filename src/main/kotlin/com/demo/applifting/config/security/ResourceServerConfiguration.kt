package com.demo.applifting.config.security

import com.demo.applifting.repository.UserRepository
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter


@Configuration
@EnableResourceServer
class ResourceServerConfiguration(
    private val userRepository: UserRepository
) : ResourceServerConfigurerAdapter() {


    override fun configure(http: HttpSecurity) {
        http
            .httpBasic().disable()
            .csrf().disable()
            .addFilterBefore(
                CustomAuthenticationFilter(userRepository),
                BasicAuthenticationFilter::class.java
            )
            .authorizeRequests()
            .antMatchers("/")
            .permitAll()
            .anyRequest()
            .authenticated()
    }
}