package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserData;


@WebFilter("/*")
public class LoginFilter implements Filter {

	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//dofilter中的request和response都是servlet。。类型的  
		//需要强转成HttpServlet。。类型才能拿到与servlet中相同的请求和响应对象
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		// getRequestURI 仅获取资源名，不包含参数列表   （/项目名/资源名）
		String uri = req.getRequestURI();
		System.out.println(uri);
		
		if(uri.endsWith("login.jsp")|| uri.endsWith("CheckCodeServlet")|| uri.endsWith("LoginServlet")) { 
			chain.doFilter(request, response);
		}else {
			//获取资源中的session对象
			HttpSession session = req.getSession();
			//拿session对象中的用户信息
			UserData userdata = (UserData)session.getAttribute("userdata");
			if(userdata!=null) {//说明登录过
				chain.doFilter(request, response);
			}else {//没有登录
				res.sendRedirect("login.jsp?msg=pleaselogin");
			}
		}
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
