
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<div class="container col-md-6">
    <form action="updateCourses" method="post">
        <div class="form-group">
            <label for="theme">Theme</label>
            <input type="text" id="theme" class="form-control" required name="theme">
        </div>
        <div class="form-group">
            <label for="courseName">Name of course</label>
            <input type="text" id="courseName" class="form-control" required name="courseName">
        </div>
        <div class="form-group">
            <label for="courseStart">Course begin</label>
            <input type="date" id="courseStart" class="form-control" name="courseStart" required>
        </div>
        <div class="form-group">
            <label for="courseEnd">Course end</label>
            <input type="date" id="courseEnd" class="form-control" name="courseEnd" required>
        </div>
        <div class="form-group">
            <label for="coursePrice">Price</label>
            <input type="number" id="coursePrice" class="form-control" name="coursePrice" required>
        </div>
        <div>
            <button class="btn btn-primary" type="submit">Create</button>
        </div>
    </form>
</div>
</body>
</html>
