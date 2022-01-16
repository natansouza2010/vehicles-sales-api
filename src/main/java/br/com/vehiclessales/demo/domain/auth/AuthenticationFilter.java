package br.com.vehiclessales.demo.domain.auth;

import br.com.vehiclessales.demo.domain.entities.User;
import br.com.vehiclessales.demo.domain.repository.UserRepository;
import br.com.vehiclessales.demo.domain.services.CustomDetailsService;
import br.com.vehiclessales.demo.domain.services.TokenService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private CustomDetailsService customDetailsService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if(token != null){
            try{
                Jwts.parser().setSigningKey("ProjVehiclesSales").parseClaimsJws(token).getBody();
            }catch (IllegalArgumentException e){
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
                return;
            }

        }
        if(token != null){
            Optional<String> userId = tokenService.getUserId(token);
            User user = userRepository.findById(userId.get());

            UserDetails userDetails = customDetailsService.create(user);
            UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
            upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(upat);
        }
        filterChain.doFilter(request,response);
    }
}
