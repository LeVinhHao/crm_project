package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {""})
public class CustomFilter  implements Filter {

//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest)servletRequest;
        HttpServletResponse response= (HttpServletResponse)servletResponse;
        Cookie[] cookies= request.getCookies();

        if(cookies!=null && cookies.length>0){
            boolean isLogin=false;
            for(Cookie c:cookies){
                if("username".equals(c.getName())) {
                    isLogin = true;
//                    response.sendRedirect(request.getContextPath()+"/roles");
                    filterChain.doFilter(request, response);
                    break;
                }else {
                    isLogin=false;
                    response.sendRedirect(request.getContextPath()+"/login");
                }
                if(isLogin){
                    filterChain.doFilter(request,response);
                }else {
                    response.sendRedirect(request.getContextPath()+"/login");
                }
            }
        }else {
            response.sendRedirect(request.getContextPath()+"/login");
        }
    }
}
