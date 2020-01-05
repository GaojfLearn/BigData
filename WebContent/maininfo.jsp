<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
			<h1>
				欢迎,${sessionScope.userdata.username}
				<a href="LoginOutServlet">退出登录</a>
			</h1>
	</div>
	<a href="${pageContext.request.contextPath }/addData.jsp">添加数据</a>
	<a href="UserDataServlet?fun=find">查询全部</a>
</body>
</html>