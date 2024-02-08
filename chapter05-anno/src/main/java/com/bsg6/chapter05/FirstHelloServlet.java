package com.bsg6.chapter05;

import com.samskivert.mustache.Mustache;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Objects;

@WebServlet(urlPatterns = "/hello1")
public class FirstHelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {
        try (var in = Objects.requireNonNull(this
                .getClass()
                .getResourceAsStream("/hello.html"))) {
            try (var reader = new InputStreamReader(in)) {
                var output = Mustache.compiler().compile(reader)
                        .execute(Map.of("name", "world"));
                response.getWriter().println(output);
            }
        }
    }
}
