package servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserData;
import dao.UserDataDao;
import impl.UserDataDaoImpl;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//				通过请求浏览器 获取提交的名字和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// 获取用户输入的验证码 checkCode是输入框的name值
		String checkcode = request.getParameter("checkCode");
		// 验证生成之后被存放在session中 这里是拿到存放生成验证码的session
		HttpSession session = request.getSession();
		String syanzhengma = (String) session.getAttribute("yanzhengma");
		//System.out.println(syanzhengma);
		// 判断生成的验证码和用户输入的验证码是否匹配
		if (syanzhengma.equalsIgnoreCase(checkcode)) {// 验证码正确才能登录
			UserDataDao dao = new UserDataDaoImpl();
			// 根据用户名和密码都满足的条件去查询数据库中的数据
			UserData userdata = dao.logining(username, password);
			if (userdata != null) { // 说明数据库中有满足条件的数据
				// 拿到表单中对于存放用户名的 cookie生命周期选择
				String record = request.getParameter("record");
				int num = Integer.parseInt(record);
					if (num != 0) {
						// 设置存放username的编码格式
						username = URLEncoder.encode(username, "utf-8");
						// 将用户名存放到cookie中
						Cookie cookie = new Cookie("cusername", username);
						cookie.setMaxAge(num * 60 * 60 * 24);
						// 将cookie响应给浏览器
						response.addCookie(cookie);						
					}
					// 将登录人信息放入session
					session.setAttribute("userdata", userdata);
					// 显示登陆成功后的页面（重定向）
					response.sendRedirect("maininfo.jsp");
			}else {//登录失败
				// 如果没有满足条件的数据 则返回登录界面 打印错误信息（打印在重定向的登录界面）
				response.sendRedirect("login.jsp?msg=loginerror");
			}
		} else {//验证码不正确
			response.sendRedirect("login.jsp?msg=checkcodeerror");
		}
	}
}
