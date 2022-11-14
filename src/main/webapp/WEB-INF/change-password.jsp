<%--
  Created by IntelliJ IDEA.
  User: justinhollmer
  Date: 11/14/22
  Time: 11:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change User Info</title>
</head>
<body>
    <h1>Change password</h1>
    <form method="post" action="/change-password">
        <label for="old-password">Old Password</label>
        <input id="old-password" name="old-password">
        <label for="new-password">New Password</label>
        <input id="new-password" name="new-password">
        <label for="confirm-new-password">Confirm New Password</label>
        <input id="confirm-new-password" name="confirm-new-password">
        <button type="submit">Submit</button>
    </form>
</body>
</html>
