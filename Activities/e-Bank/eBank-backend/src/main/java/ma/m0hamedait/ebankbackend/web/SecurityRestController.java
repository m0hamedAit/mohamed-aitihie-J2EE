package ma.m0hamedait.ebankbackend.web;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.m0hamedait.ebankbackend.security.ConfConstants;
import ma.m0hamedait.ebankbackend.security.entities.AppRole;
import ma.m0hamedait.ebankbackend.security.entities.AppUser;
import ma.m0hamedait.ebankbackend.security.service.AccountService;
import org.springdoc.core.Constants;
import org.springdoc.core.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Slf4j
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class SecurityRestController {

    private AccountService accountService;

    @GetMapping( "/refresh-token" )
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String jwt_token = request.getHeader("Authorization");
        if( jwt_token!=null && jwt_token.startsWith("Bearer ") ){
            try{
                String jwt = jwt_token.substring(7);
                Algorithm algorithm = Algorithm.HMAC256(ConfConstants.SECRET_KEY);
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(jwt);

                String username= decodedJWT.getSubject();
                AppUser user = accountService.loadUserByUsername(username);

                String jwtAccessToken= JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis()+ 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getAppRoles().stream().map(AppRole::getRolename).collect(Collectors.toList()))
                        .sign( algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", jwtAccessToken);
                tokens.put("refresh_token", jwt);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);


            }catch (Exception e){
                throw e;
            }
        }else
            throw new RuntimeException(" Refresh token is required !!! ");
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/profile")
    public AppUser getUser(Principal principal){
        return accountService.loadUserByUsername( principal.getName() );
    }
}
