package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserData;
import dao.UserDataDao;
import impl.UserDataDaoImpl;

@WebServlet("/UserDataServlet")
public class UserDataServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/chartset=utf-8");
		request.setCharacterEncoding("utf-8");

		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 拿到表单提交的问号后面数据
		String fun = request.getParameter("fun");
		// 比对拿到的的数据名
		System.out.println(fun);
		if ("add".equals(fun)) {
			add(request, response);
		} else if ("find".equals(fun)) {
			find(request, response);
		}else if ("preUpDate".equals(fun)) {
			preUpDate(request, response);
		}else if ("updateUser".equals(fun)) {
			updateUser(request, response);
		}else if ("delData".equals(fun)) {
			delData(request, response);
		}else if ("checkusername".equals(fun)) {
			checkNameData(request, response);
		}
	}

	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String age = request.getParameter("age");
		String city = request.getParameter("city");
		String email = request.getParameter("email");

		UserData ud = new UserData();
		ud.setUsername(username);
		ud.setPassword(password);
		ud.setAge(Integer.parseInt(age));
		ud.setCity(city);
		ud.setEmail(email);

		UserDataDao dao = new UserDataDaoImpl();
		int result = dao.insertUserData(ud);

		if (result > 0) {
			response.sendRedirect("addData.jsp?msg=addsuccess");

		} else {
			response.sendRedirect("addData.jsp?msg=adderror");
		}
	}

	protected void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserDataDao dao = new UserDataDaoImpl();
		List<UserData> list = dao.findAll();
		//System.out.println(list);
		request.setAttribute("userdatalist", list);
		request.getRequestDispatcher("showData.jsp").forward(request, response);

	}

	protected void preUpDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		UserDataDao dao = new UserDataDaoImpl();
		UserData userdata = dao.findUserDataById(Integer.parseInt(id));
		request.setAttribute("userdata", userdata);
		request.getRequestDispatcher("update.jsp").forward(request, response);
	
	}
	
	protected void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String id = request.getParameter("id");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String age = request.getParameter("age");
		String city = request.getParameter("city");
		String email = request.getParameter("email");
		
		UserDataDao dao = new UserDataDaoImpl();
		UserData userdata = new UserData();
		userdata.setId(Integer.parseInt(id));
		userdata.setUsername(username);
		userdata.setPassword(password);
		userdata.setAge(Integer.parseInt(age));
		userdata.setCity(city);
		userdata.setEmail(email);
		int result = dao.upDate(userdata);
		if(result>0) {
			response.sendRedirect("UserDataServlet?fun=find&msg=updatesuccess");
		}else {
			response.sendRedirect("UserDataServlet?fun=find&msg=updateerror");
		}
	}
	
	protected void delData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			UserDataDao dao = new UserDataDaoImpl();
			String id = request.getParameter("id");
			
			int result = dao.delDataById(Integer.parseInt(id));
			if(result>0) {
				response.sendRedirect("UserDataServlet?fun=find&msg=delsuccess");
			}else {
				response.sendRedirect("UserDataServlet?fun=find&msg=delerror");
			}
	
	}
	
	protected void checkNameData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//获取输入的用户名
				String name = request.getParameter("username");
				System.out.println(name+"用户输入的名字");
				UserDataDao dao = new UserDataDaoImpl();
				//比对数据库中的用户名
				UserData userdata = dao.findDataByName(name);
				System.out.println(userdata+"数据库中根据名字查找的数据");
				PrintWriter out = response.getWriter();
				if(userdata!=null) {
					out.print("no");
				}else {
					out.print("yes");
				}
	}
	
}
