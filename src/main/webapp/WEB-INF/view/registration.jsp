<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<form action="registration" method="post">
    <div class="form-group">
        <label for="login">Login</label>
        <input type="text" class="form-control" id="login" name="login" placeholder="Enter login">
    </div>
    <div class="form-group">
        <label for="email">Email address</label>
        <input type="email" class="form-control" name="email" id="email" aria-describedby="emailHelp" placeholder="Enter email">
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" name="password" id="password" placeholder="Enter password">
    </div>

    <button type="submit" class="btn btn-primary">Submit</button>
</form>
</html>
