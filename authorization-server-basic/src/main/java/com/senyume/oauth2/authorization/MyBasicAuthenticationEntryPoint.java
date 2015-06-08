package com.senyume.oauth2.authorization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Jigish Patel
 */

@Component
public class MyBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    private static Logger logger = LoggerFactory.getLogger(MyBasicAuthenticationEntryPoint.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("auth-server");
        super.afterPropertiesSet();
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        logger.info("In MyBasicAuthenticationEntryPoint");
        response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
        StringBuilder responseBody = new StringBuilder();
        if(authException instanceof BadCredentialsException) {
            responseBody.append("{\"code\":\"1001\"").append(",")
                        .append("\"status\":401").append(",")
                        .append("\"message\":\"Bad username or password\"").append("}");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            PrintWriter printWriter = response.getWriter();
            printWriter.write(responseBody.toString());
        }else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        }
    }
}
