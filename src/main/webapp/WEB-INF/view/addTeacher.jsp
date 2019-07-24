<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<div>
    <h1>Add new teacher</h1>
</div>
<head>
    <form action="addTeacher" method="post">
        <div class="form-group">
            <label for="login">Login</label>
            <input type="text" class="form-control" id="login" name="login" required placeholder="Enter login">
        </div>
        <div class="form-group">
            <label for="email">Email address</label>
            <input type="email" class="form-control" name="email" id="email" required aria-describedby="emailHelp"
                   placeholder="Enter email">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" name="password" required id="password"
                   placeholder="Enter password">
        </div>

        <div>
            <span style="color: maroon">${error}</span>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>

    </form>
</head>
<body>

</body>
</html>
