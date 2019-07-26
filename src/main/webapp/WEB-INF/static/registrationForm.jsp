<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<div class="container col-md-6">
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
        <input type="password" class="form-control" name="password" required id="password"  placeholder="Enter password">
    </div>

    <div>
        <span style="color: maroon">${error}</span>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="text-center">
                <button type="submit" name="button" class="btn btn-primary">Submit</button>
            </div>
        </div>
    </div>
</div>
</div>