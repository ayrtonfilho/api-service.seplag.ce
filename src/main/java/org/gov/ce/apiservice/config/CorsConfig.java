package org.gov.ce.apiservice.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${cors.key_private}")
    protected String key_private;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                .allowedHeaders("*")
                .exposedHeaders("Authorization")
                .allowCredentials(true)
                .maxAge(3600);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                String keyHeader = request.getHeader("Key-HeaderAPI-SEPLAG-CE");
                String serverLocal = request.getLocalName();
                String method = request.getMethod();
                String requestURI = request.getRequestURI();
                String serverName = request.getLocalName();
                String remoteHost = request.getRemoteHost();

                if (keyHeader != null && keyHeader.equals(key_private) && serverLocal.equals("localhost")) {
                    System.out.println(String.format("REQUEST AUTHORIZED: IP: %s | Method: %s | Request URI: %s | Server Name: %s", remoteHost, method, requestURI, serverName));
                    return true;
                } else {

                    System.out.println("=".repeat(70));
                    System.out.println(String.format("#WARN - REQUEST UNAUTHORIZED: IP: %s | Method: %s | Request URI: %s | Server Name: %s", remoteHost, method, requestURI, serverName));
                    System.out.println("=".repeat(70) + "\n");

                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("Error 401 - UNAUTHORIZED");
                    return false;
                }
            }
        }).addPathPatterns("/**");
    }
}