package ru.otus.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class LogFilter implements Filter {


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        if ((request.getRequestURI().contains("create")
                || request.getRequestURI().contains("edit")
                || request.getRequestURI().contains("delete"))
                && !request.getRequestURI().contains("comment")) {
            if (request.getUserPrincipal().getName().equals("admin")) {
                chain.doFilter(req, res);
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "You have no rights to do this action.");
            }
        }
        else
            chain.doFilter(req, res);
    }
}