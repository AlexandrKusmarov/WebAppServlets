<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<html>
<div style="text-align: center">
    <h1>Login</h1>
</div>
<div class="container col-md-4">
    <form action="login" method="post">
        <div class="form-group">
            <label for="login">Login</label>
            <input type="text" class="form-control" name="login" id="login" required placeholder="Enter login">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" name="password" id="password" required
                   placeholder="Enter password">
        </div>

        <div>
            <span style="color: maroon">${error}</span>
        </div>

        <div class="row">
            <div class="col-md-6">
                <button type="submit" class="btn btn-primary">Sign in</button>
            </div>
            <div class="col-md-6 text-right">
                <button class="btn btn-info" onclick="location.href='/registration'">Create</button>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 text-center">
                <h4>Please login or create new account</h4>
            </div>
        </div>
    </form>
</div>
</html>
