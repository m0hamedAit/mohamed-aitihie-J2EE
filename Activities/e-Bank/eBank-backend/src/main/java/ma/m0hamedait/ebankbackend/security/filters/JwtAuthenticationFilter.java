package ma.m0hamedait.ebankbackend.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import ma.m0hamedait.ebankbackend.security.ConfConstants;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        return authenticationToken;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request
            , HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(ConfConstants.SECRET_KEY);
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        String jwtAccessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+ 60*1000)) //access token expires after 1min
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getAuthorities().stream()
                        .map(ga->ga.getAuthority()).collect(Collectors.toList()))
                .sign(algorithm);

        String jwtRefreshToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+15*60*1000)) //refresh token expires after 15min
                .withIssuer(request.getRequestURL().toString())
                .sign(algorithm);

        Map<String,String> tokens = new HashMap<>();
        tokens.put("access-token", jwtAccessToken);
        tokens.put("refresh-token", jwtRefreshToken);
        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);  // send tokens in response body
    }
}
