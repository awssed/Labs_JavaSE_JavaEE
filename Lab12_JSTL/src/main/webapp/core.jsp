<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 03.05.2024
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<h4>---- out ----</h4>
<c:out value="542 * 8 / 4 = "/><c:out value="${542 * 8 / 4}"/>
<h4>---- catch -----</h4>
<c:out value="10 / 0"/>
<c:catch var ="catchException">
    <% int x = 10 / 0;%>
</c:catch>

<h4>---- if -----</h4>
<c:if test = "${catchException != null}">
    <p>Исключение: ${catchException} <br />
        Сообщение: ${catchException.message}</p>
</c:if>

<h4>---- set ----</h4>
<c:set var = "variable" scope = "page" value = "${2000 * 2}"/>
<c:out value = "variable: ${variable}"/>
<h4>---- remove ----</h4>
<c:remove var = "variable"/>
<c:out value = "variable (после remove): ${variable}"/>

<h4>---- choose, when, otherwise ----</h4>
<c:set var="number" scope="page" value="10"/>
<c:choose>
    <c:when test="${number % 2 == 0}">
        Число четное
    </c:when>
    <c:otherwise>
        Число нечетное
    </c:otherwise>
</c:choose>

<h4>--- forEach ----</h4>
<c:forEach var = "i" begin = "1" end = "5">
    i =  <c:out value = "${i}"/><br/>
</c:forEach>

<h4>--- forTokens ----</h4>
<c:forTokens items="a,b,c,d,e,f,g" delims = "," var="letter">
    <c:out value="${letter}"/><br/>
</c:forTokens>
<h4>---- url ----</h4>
<a href="<c:url value="index.jsp"/>">На главную</a>
</body>
</html>
