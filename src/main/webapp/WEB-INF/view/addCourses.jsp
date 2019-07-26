<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<jsp:include page="../static/navbar.jsp"/>

<body>
<div class="container col-md-6">
    <form action="addCourses" method="post">
        <c:import url="../static/addCourseForm.jsp"/>
        <div>
            <button class="btn btn-primary" type="submit">Create</button>
        </div>
    </form>
</div>
</body>
</html>
