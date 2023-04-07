package nz.co.joyhu;

import nz.co.joyhu.service.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final JwtTokenProvider jwtTokenProvider;

    public JwtRequestFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

        log.info("JWT filter get called with request {}", request);

        final String requestTokenHeader = request.getHeader("Authorization");
        log.info("requesttoken header {}", requestTokenHeader);

        if (hasTokenHeader(requestTokenHeader)) {
            log.info("Request has token, need to validate token");
            final String jwtToken = requestTokenHeader.substring(7);
            log.info("token  = {}", jwtToken);
        }

        filterChain.doFilter(request, response);
    }

    private boolean hasTokenHeader(String requestTokenHeader) {
        return requestTokenHeader != null && requestTokenHeader.startsWith("Bearer");
    }
}
