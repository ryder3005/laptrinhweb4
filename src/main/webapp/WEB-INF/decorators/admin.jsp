
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><dec:title default="My Website"/></title>
    <meta charset="UTF-8">
</head>
<body>
<%@ include file="/common/header.jsp" %>

<!-- Nội dung từ JSP được decorate sẽ xuất hiện ở đây -->
<div class="mainBody">
    <sitemesh:write property="body"/>
</div>
<%@ include file="/common/footer.jsp" %>
</body>
</html>
