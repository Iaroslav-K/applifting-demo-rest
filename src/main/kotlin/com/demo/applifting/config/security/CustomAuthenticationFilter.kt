package com.demo.applifting.config.security

import com.demo.applifting.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.RememberMeAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Most probably is not the best solution.
 */
class CustomAuthenticationFilter(
    private val userRepository: UserRepository
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader("Authentication")
        val user = userRepository.findByAccessToken(token)
        if (token == null || user == null) {
            response.status = HttpStatus.FORBIDDEN.value()
        } else {
            val auth = RememberMeAuthenticationToken(
                token,
                User
                    .builder()
                    .authorities(emptyList()) // there is no need in any roles
                    .username(user.email) // used to get specific information for current user
                    .password("") // password cannot be empty
                    .build(),
                emptyList()
            )
            SecurityContextHolder.getContext().authentication = auth
        }
        filterChain.doFilter(request, response)
    }
}
