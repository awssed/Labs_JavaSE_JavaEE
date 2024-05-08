<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 11.04.2024
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task2</title>
</head>
<body>
<div class="registration" style="display: flex; flex-direction: column">
    <form action="ServletTask2" method="post">
        <h2>Регистрация</h2>
        <h3>Пользователь не найден</h3>
        <p><input type="text" name="userLogin" placeholder="Введите логин" size="18" maxlength="30" required/></p>
        <p><input type="password" name="userPassword" placeholder="Введите пароль" size="18" maxlength="30" required/></p>
        <p>
            <button type="submit" name="action" value="register">Отправить</button>
        </p>
    </form>
</div>
</body>
</html>
