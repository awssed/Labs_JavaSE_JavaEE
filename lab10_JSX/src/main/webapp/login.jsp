<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 19.04.2024
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="Styles/login.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="form">
    <h1>Вход</h1>
    <p style="color:white; text-align: center">${ErrorMessage}</p>
    <form method="post" action="Login">
        <div class="input-form">
            <input type="text" placeholder="Логин" name="login">
        </div>
        <div class="input-form">
            <input type="password" placeholder="Пароль" name="password"/>
        </div>
        <div class="input-form">
            <input type="submit" value="Войти">
        </div>
    </form>
    <a href="register.jsp" class="go-to-register">Зарегистрироваться</a>>
</div>
</body>
</html>
