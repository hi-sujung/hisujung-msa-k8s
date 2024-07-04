//package com.hisujung.microservice.config;
//
//import io.github.bucket4j.Bandwidth;
//import io.github.bucket4j.Bucket;
//import io.github.bucket4j.Refill;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//
//import java.io.IOException;
//import java.time.Duration;
//
//public class ThrottlingFilter implements javax.servlet.Filter {
//
//    private Bucket createNewBucket() {
//        long overdraft = 50;
//        Refill refill = Refill.greedy(10, Duration.ofSeconds(1));
//        Bandwidth limit = Bandwidth.classic(overdraft, refill);
//        return Bucket.builder().addLimit(limit).build();
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
//        HttpSession session = httpRequest.getSession(true);
//
//        String appKey = SecurityUtils.getThirdPartyAppKey();
//        Bucket bucket = (Bucket) session.getAttribute("throttler-" + appKey);
//        if (bucket == null) {
//            bucket = createNewBucket();
//            session.setAttribute("throttler-" + appKey, bucket);
//        }
//
//        // tryConsume returns false immediately if no tokens available with the bucket
//        if (bucket.tryConsume(1)) {
//            // the limit is not exceeded
//            filterChain.doFilter(servletRequest, servletResponse);
//        } else {
//            // limit is exceeded
//            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
//            httpResponse.setContentType("text/plain");
//            httpResponse.setStatus(429);
//            httpResponse.getWriter().append("Too many requests");
//        }
//    }
//
//}
