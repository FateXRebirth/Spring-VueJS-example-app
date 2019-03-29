package example.Config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class JwtFilter extends GenericFilterBean {

    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        final String authHeader = request.getHeader("authorization");


        System.out.println(request.getParameter("name"));
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            System.out.println(String.format("%s : %s", entry.getKey(), entry.getValue()[0]));
        }
        System.out.println(request.getParameterValues("name")[0].toString());

//        ObjectMapper mapper = new ObjectMapper();
//        JSONRequest json = mapper.readValue(request.getInputStream(),JSONRequest.class);
//        System.out.println(json);

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);

            chain.doFilter(req, res);
        } else {

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                throw new ServletException("Missing or invalid Authorization header");
            }

            final String token = authHeader.substring(7);

            try {
                Algorithm algorithm = Algorithm.HMAC256("secret");
                JWTVerifier verifier = JWT.require(algorithm)
                        .withIssuer(request.getParameter("name"))
                        .build(); //Reusable verifier instance
                DecodedJWT jwt = verifier.verify(token);
            } catch (JWTVerificationException exception) {
                throw new ServletException("Invalid token");
            }

            chain.doFilter(req, res);
        }
    }
}

