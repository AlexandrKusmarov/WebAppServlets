<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<html>
<table class="table">
    <thead>
    <tr>
        <th>Theme</th>
        <th>Course name</th>
        <th>Start date</th>
        <th>End date</th>
        <th>Price</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="course" items="${coursesList}">
        <tr>
            <td><c:out value="${course.theme}" /></td>
            <td><c:out value="${course.nameOfCourses}" /></td>
            <td><c:out value="${course.startOfCourses}" /></td>
            <td><c:out value="${course.endOfCourses}" /></td>
            <td><c:out value="${course.price}" /></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</html>
