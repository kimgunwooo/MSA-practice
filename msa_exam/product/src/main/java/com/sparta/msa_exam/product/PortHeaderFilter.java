package com.sparta.msa_exam.product;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PortHeaderFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        int port = servletRequest.getServerPort();
        httpResponse.setHeader("X-Server-Port", String.valueOf(port));
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
