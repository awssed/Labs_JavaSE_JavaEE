<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 19.04.2024
  Time: 19:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link href="Styles/login.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form">
    <h1>Регистрация</h1>
    <p style="color:white; text-align: center">${ErrorMessage}</p>
    <form method="post" action="Register">
        <div class="input-form">
            <input type="text" name="login" placeholder="Логин">
        </div>
        <div class="input-form">
            <input type="password" name="password" placeholder="Пароль"/>
        </div>
        <div class="input-form">
            <input type="password" name="repPassword" placeholder="Повторить пароль"/>
        </div>
        <div class="input-form">
            <input type="submit" value="Зарегистрироваться">
        </div>
    </form>
    <a href="login.jsp" class="go-other">Войти</a>>
</div>
</body>
</html>
