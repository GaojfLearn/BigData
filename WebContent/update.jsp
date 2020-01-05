<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>updateuser</title>
<link href="${pageContext.request.contextPath }/css/jquery.validate.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath }/js/jquery.js"></script>
<script src="${pageContext.request.contextPath }/js/jquery.validate.js"></script>
<script src="${pageContext.request.contextPath }/js/message_cn.js"></script>

<script>
   var result;
   
   $(function(){
	   result = $('#myForm').validate({
		   
		   // 验证规则
		   rules:{
			   username:{
				   required:true,
				   rangelength:[4,12]
			   },
			   
			   password:{
				   required:true,
				   rangelength:[6,12]
			   },
			   repassword:{
				   equalTo:'#password'
			   },
			   age:{
				   range:[18,60]
			   },
			   email:{
				   email:true
			   }
		   },
		   
		   // 验证通不过时的提示信息——已将放在了 message_cn.js中‘
		   
		   // 提示信息显示的位置
		   errorPlacement:function(error,element){
			   error.appendTo(element.parent());
		   }
		   
	   })
   });
   
   //所有规则都符合   return true才能提交表单
   function subForm(){
	   if(result.form()){
		   return true;
	   }else{
		   return false;
	   }
   }

</script>

</head>
<body>
   
      
   
   <form action="UserDataServlet" method="post" id="myForm">
  		 <input type="hidden" name="fun" value="updateUser" />
   		<input type="hidden" name="id" value="${requestScope.userdata.id }"/>
   		
   
      <table>
         <tr>
            <td>用户名</td>
            <td>
               <input type="text" name="username" value="${requestScope.userdata.username }"/>
            </td>
         </tr>
         <tr>
            <td>密码</td>
            <td><input type="password" name="password" id="password" value="${requestScope.userdata.password }" /></td>
         </tr>
         <tr>
            <td>确认密码</td>
            <td><input type="password" name="repassword" /></td>
         </tr>
         <tr>
            <td>年龄</td>
            <td><input type="text" name="age" value="${requestScope.userdata.age }"/></td>
         </tr>
         <tr>
            <td>城市</td>
            <td>
               <select name="city">
		          <option value="北京" <c:if test='${requestScope.userdata.city=="北京" }'>selected</c:if> >北京</option>
		          <option <c:if test='${requestScope.userdata.city=="上海" }'>selected</c:if>>上海</option>
		          <option <c:if test='${requestScope.userdata.city=="广州" }'>selected</c:if>>广州</option>
		     </select>
            </td>
         </tr>
         <tr>
            <td>邮箱</td>
            <td><input type="text" name="email" value="${requestScope.userdata.email }"/></td>
         </tr>
         <tr>
            <td colspan="2"><input type="submit" value="更新数据" onsubmit="return subForm()" /></td>
         </tr>
      </table>
      
		  
   </form>
</body>
</html>