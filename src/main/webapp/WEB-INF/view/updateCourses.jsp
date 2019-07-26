<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../static/navbar.jsp"/>
</head>
<body>
<div class="container col-md-6">
    <form action="updateCourses?id=${id}" method="post">

        <div class="form-group">
            <label for="theme">Theme</label>
            <input type="text" id="theme" class="form-control" value="${theme}" required name="theme">
        </div>
        <div class="form-group">
            <label for="courseName">Name of course</label>
            <input type="text" id="courseName" class="form-control" value="${courseName}" required name="courseName">
        </div>
        <div class="form-group">
            <label for="courseStart">Course begin</label>
            <input type="date" id="courseStart" class="form-control" value="${courseStart}" name="courseStart" required>
        </div>
        <div class="form-group">
            <label for="courseEnd">Course end</label>
            <input type="date" id="courseEnd" class="form-control" value="${courseEnd}" name="courseEnd" required>
        </div>
        <div class="form-group">
            <label for="price">Price</label>
            <input type="number" id="price" class="form-control" value="${price}" name="price" required>
        </div>
        <div>
            <button class="btn btn-info" type="submit">Save</button>
        </div>
    </form>
</div>
</body>
</html>
