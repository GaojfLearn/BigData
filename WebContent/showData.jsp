<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

		function delData(id){
			var flag = confirm('确认删除吗？');
			if(flag){
				location="${pageContext.request.contextPath }/UserDataServlet?fun=delData&id="+id;
			}
		}
</script>
</head>
<body>

	<c:if test="${param.msg=='delsuccess' }">
		<div style="color: green">删除成功</div>
	</c:if>

	<c:if test="${param.msg=='delerror' }">
		<div style="color: green">删除失败</div>
	</c:if>

	<c:if test="${param.msg=='updatesuccess' }">
		<div style="color: red">更新成功！！</div>
	</c:if>

	<c:if test="${param.msg=='updaterror' }">
		<div style="color: green">更新失败！！</div>
	</c:if>

	<table align="center" border="1px" width="60%" cellspacing="0">
		<tr>
			<th>序号</th>
			<th>用户名</th>
			<th>密码</th>
			<th>年龄</th>
			<th>城市</th>
			<th>邮箱</th>
			<th>操作</th>
		</tr>
		<!-- El表达式   JSTL   都是用来替代  <%-- <% %> --%>中的代码
       items是遍历传过来的list数组（数组中存放的是不同的用户信息）  
       requestScope意思是从request作用域中取值 userdatalist是作用域中存的值 对应的名
       var是 每取出集合中的一个对象 赋给 var变量   
       varStatus  用来产生序号  
       -->
		<c:forEach items="${requestScope.userdatalist}" var="u"
			varStatus="num">
			<tr>
				<!--  num.index 序号从零开始
               num.count 序号从1开始
			   num.first  true/false  是否是第一个
			   num.last   true/false  是否是最后一个
          -->
				<td>${num.count }</td>
				<td>${u.username }</td>
				<td>${u.password }</td>
				<td>${u.age }</td>
				<td>${u.city }</td>
				<td>${u.email }</td>
				<td>
					<!--因为更新是对某一条数据做的操作，所以需要有servlet处理传过去的数据  这个数据是一个id值  --> <a
					href="${pageContext.request.contextPath }/UserDataServlet?fun=preUpDate&id=${u.id}">更新</a>
					<!-- 在本页面写一个函数 判断是否真的删除  用弹出提示 --> <a
					href="javascript:delData('${u.id }')">删除</a>
				</td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>