<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 18-5-15
  Time: 上午10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/jquery.min.js"></script>

</head>
<body>
<script language="javascript">
    if(window.jQuery || window.$){
        alert("引入jquery")
    }else{
        alert("没有引入jquery")
    }
</script>
</body>
</html>
