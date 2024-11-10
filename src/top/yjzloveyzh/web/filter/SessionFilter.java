/**
 *
 */
package top.yjzloveyzh.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import top.yjzloveyzh.common.Constants;
import top.yjzloveyzh.common.pojo.User;

public class SessionFilter implements Filter {

    private String notNeedLoginPages = "";
    protected FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;

        if(filterConfig.getInitParameter("notNeedLoginPages") != null) {
            notNeedLoginPages = filterConfig.getInitParameter("notNeedLoginPages");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String uri =  request.getRequestURI();
        String requestedUri = uri.substring(request.getContextPath().length() + 1);

        if (requestedUri.indexOf(".") > 0) {
            filterChain.doFilter(request, response);
        } else {
            String [] pages = notNeedLoginPages.split(",");
            boolean isAllow = false;

            for (String page : pages) {

                if (page.equals(requestedUri)) {
                    isAllow = true;
                    break;
                }
            }

            if (isAllow) {
                filterChain.doFilter(request, response);
            } else {
                HttpSession session = request.getSession();
                User user = (User)session.getAttribute(Constants.User.SESSION_USER_KEY);

                if (user == null) {
                    response.sendRedirect(request.getContextPath() + "/user/toLogin");
                } else {
                    filterChain.doFilter(request, response);
                }
            }
        }
    }

    @Override
    public void destroy() {
        this.notNeedLoginPages = null;
    }
}