package nz.co.joyhu;

import nz.co.joyhu.service.JwtTokenProvider;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class JwtRequestFilterTest {

    private JwtRequestFilter filter;

    @Before
    public void setUp() throws Exception {
        final JwtTokenProvider tokenProvider = mock(JwtTokenProvider.class);
        filter = new JwtRequestFilter(tokenProvider);
    }

    @Test
    public void Can_filter_header_with_jwt_token() throws ServletException, IOException {

        // Given
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final FilterChain filterChain = mock(FilterChain.class);
        given(request.getHeader("Authorization")).willReturn("Bearer" + someString(17));

        // When
        filter.doFilterInternal(request, response, filterChain);

        // Then
        then(filterChain).should().doFilter(request, response);
    }

    @Test
    public void Can_filter_header_without_token_header() throws ServletException, IOException {

        // Given
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final FilterChain filterChain = mock(FilterChain.class);
        given(request.getHeader("Authorization")).willReturn(null);

        // When
        filter.doFilterInternal(request, response, filterChain);

        // Then
        then(filterChain).should().doFilter(request, response);
    }

    @Test
    public void Can_filter_header_without_right_token() throws ServletException, IOException {

        // Given
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final FilterChain filterChain = mock(FilterChain.class);
        given(request.getHeader("Authorization")).willReturn(someString(11));

        // When
        filter.doFilterInternal(request, response, filterChain);

        // Then
        then(filterChain).should().doFilter(request, response);
    }
}
