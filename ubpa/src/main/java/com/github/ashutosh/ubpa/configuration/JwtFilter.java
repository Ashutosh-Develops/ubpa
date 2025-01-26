package com.github.ashutosh.ubpa.configuration;

import com.github.ashutosh.ubpa.constant.jwt.JwtConstant;
import com.github.ashutosh.ubpa.service.JwtService;
import com.github.ashutosh.ubpa.service.UserDetailsServiceImplementation;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailsServiceImplementation userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String jwt= request.getHeader(JwtConstant.JWT_HEADER);

        if(jwt!=null){
            jwt=jwt.substring(7);   // Fetch token after bearer keyword
            try{

                String email = jwtService.getEmailFromToken(jwt);

                if (email != null&& SecurityContextHolder.getContext().getAuthentication()==null) {
                    UserDetails userDetail = userDetailsService.loadUserByUsername(email);
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetail,
                            null,
                            userDetail.getAuthorities()
                    );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }

                // Continue the filter chain
                filterChain.doFilter(request, response);

            }catch(Exception e){
                throw new BadCredentialsException(e.getMessage());
            }
        }

        filterChain.doFilter(request, response);

    }
}
