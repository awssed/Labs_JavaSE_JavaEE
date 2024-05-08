<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 03.05.2024
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<sql:setDataSource var = "snapshot" driver = "com.mysql.cj.jdbc.Driver"
                   url = "jdbc:mysql://localhost:3306/Lab10?autoReconnect=true&useSSL=false"
                   user = "root"  password = "qazwsx2005"/>

<sql:query dataSource = "${snapshot}" sql = "select * from students" var = "result" />
<table>
    <tr>
        <th>Id</th>
        <th>ФИО</th>
        <th>Курс</th>
        <th>Группа</th>
    </tr>
    <c:forEach items="${result.rows}" var="res">
        <tr>
            <td><c:out value="${res.studentId}"/></td>
            <td><c:out value="${res.fio}"/></td>
            <td><c:out value="${res.course}"/></td>
            <td><c:out value="${res.groupNum}"/></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
