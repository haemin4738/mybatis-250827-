package com.back.global.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@RequiredArgsConstructor
public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;

    @Getter
    private int count;

    @Getter
    @Setter
    private String name;

    public int increaseCount() {
        return count++;
    }

    public String getCurrentUrl() {
        String url = req.getRequestURL().toString();
        String queryString = req.getQueryString();

        if (queryString != null && queryString.length() > 0) {
            return url + "?" + queryString;
        }

        return url;
    }
}