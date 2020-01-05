<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/css/jquery.validate.css"
	rel="stylesheet" />
<script src="${pageContext.request.contextPath }/js/jquery.js"></script>
<script src="${pageContext.request.contextPath }/js/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath }/js/message_cn.js"></script>

<script>
	var result;

	$(function() {
		result = $('#myForm').validate({

			// 验证规则
			rules : {
				username : {
					required : true,
					rangelength : [ 4, 12 ]
				},

				password : {
					required : true,
					rangelength : [ 6, 12 ]
				},
				repassword : {
					equalTo : '#password'
				},
				age : {
					range : [ 18, 60 ]
				},
				email : {
					email : true
				}
			},

			// 验证通不过时的提示信息——已将放在了 message_cn.js中‘

			// 提示信息显示的位置
			errorPlacement : function(error, element) {
				error.appendTo(element.parent());
			}

		})
	});

	function subForm() {
		if (result.form()) {
			return true;
		} else {
			return false;
		}
	}
</script>
<script type="text/javascript">
	function checkUserName() {
		//获取用户输入的用户名
		var username = $('#username').val();
			$.ajax({
				type : 'POST', //请求方式
				url : "UserDataServlet?fun=checkusername", //请求地址
				data : {"username" : username }, //传到处理数据页面的值
				 
				
				success : function(data) {
					 if (username == "") {
						$('#checkspan').css('color', 'red');
						$('#checkspan').html('用户名为空！');
						$('#subtn').pop('disabled', true);
					} 
					if (data == 'no') {
						$('#checkspan').css('color', 'red');
						$('#checkspan').html('用户名被占用！');
						$('#subtn').pop('disabled', true);
					} else {
						$('#checkspan').css('color', 'green');
						$('#checkspan').html('用户名可用！');
						$('#subtn').pop('disabled', false);
					}
				}
			});
		}		
	
</script>
</head>
<body>

	<c:if test="${param.msg=='adderror' }">
		<div style="color: red">添加失败</div>
	</c:if>

	<c:if test="${param.msg=='addsuccess' }">
		<div style="color: green">添加成功</div>
	</c:if>

	<form action="UserDataServlet" method="post" id="myForm">
		<input type="hidden" name="fun" value="add" />
		<table>
			<tr>
				<td>用户名</td>
				<td><input type="text" id="username" name="username"
					onblur="checkUserName()" /><span id="checkspan"></span></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="password" name="password" id="password" /></td>
			</tr>
			<tr>
				<td>确认密码</td>
				<td><input type="password" name="repassword" /></td>
			</tr>
			<tr>
				<td>年龄</td>
				<td><input type="text" name="age" /></td>
			</tr>
			<tr>
				<td>城市</td>
				<td><select name="city">
						<option value="北京">北京</option>
						<option>上海</option>
						<option>广州</option>
				</select></td>
			</tr>
			<tr>
				<td>邮箱</td>
				<td><input type="text" name="email" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="注册" id="subtn"
					onsubmit="return subForm()" /></td>
			</tr>
		</table>


	</form>
</body>
</html>