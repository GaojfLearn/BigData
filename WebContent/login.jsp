<%@page import="java.net.URLDecoder"%>
<%@page import="utils.CookieUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- 在这里导入验证规则使用的包  获取登录状态的提示信息 -->
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<script type="text/javascript">
		function changeCode(){
			//生成验证码
			document.getElementById('img').src="CheckCodeServlet?aaa="+new Date();
		}
	
</script>
</head>

 	   <%
			  Cookie[] cs = request.getCookies();
			  Cookie cookie = CookieUtils.findCookieByName(cs, "cusername");
		  	  String str ="";
			  if(cookie!=null){
		  		  str = cookie.getValue();
		  		  str = URLDecoder.decode(str,"utf-8");
		  	  }
		  
		  %>
<body>
		<!-- 让登录状态的提示信息显示到一个div中 -->
			<div style="color:red">
					<c:if test="${param.msg=='loginerror' }">
						用户名或密码错误！！！
					</c:if>
					 <c:if test="${param.msg=='checkcodeerror' }">
				          验证码错误！！！
				     </c:if>
				      <c:if test="${param.msg=='pleaselogin' }">
				          请登录！！！
				     </c:if>
			</div>
			
			<!-- servlet 中的请求头 属于httpsevrletrequest接口
			 获取请求URL中属于WEB应用程序的路径，这个路径以“/”开头，
			表示相对于整个WEB站点的根目录，路径结尾不含“/”。 -->
			<form action="${pageContext.request.contextPath}/LoginServlet" method="post">
					
					用户名:<input type="text"  name="username" value="<%=str %>" /><br/>
					密码  :<input type="password"  name="password" /><br/>
					记录用户名：<select name="record">
                				<option value="0">不记录</option>
                				<option value="7">记录七天</option>
                				<option value="30">记录30天</option>
                		  </select><br/>
              	   验证码：<input type="text" name="checkCode" size="15"/>
                	<img src="CheckCodeServlet" id="img"><a href="javascript:changeCode()">看不清，换一张</a>
               	 <br/>
					<!-- 按钮的类型是submit 能直接提交到后台 不用onclick -->
					<input type="submit" value="登录" >
					
			</form>
</body>
</html>