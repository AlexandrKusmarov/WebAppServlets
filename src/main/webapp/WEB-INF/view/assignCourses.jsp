<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../static/navbar.jsp"/>
<table class="table">
    <thead>
    <div class="row">
        <div class="col-md-12 text-center">
            <h4>Assign courses to teacher ${login}</h4>
        </div>
    </div>
    <tr>
        <th>Theme</th>
        <th>Course name</th>
        <th>Start date</th>
        <th>End date</th>
        <th>Price</th>
        <th>Assign</th>
    </tr>
    </thead>
    <tbody>
    <form action="assignCourses" method="post">
    <c:forEach var="course" items="${coursesList}">
            <tr>
                <td><c:out value="${course.theme}"/></td>
                <td><c:out value="${course.nameOfCourses}"/></td>
                <td><c:out value="${course.startOfCourses}"/></td>
                <td><c:out value="${course.endOfCourses}"/></td>
                <td><c:out value="${course.price}"/></td>
                <td>
                    <div class="form-check">
                        <input type="checkbox" name="assignCourses" class="form-check-input" id="${course.idCourses}">
                        <label class="form-check-label" for="${course.idCourses}"></label>
                    </div>
                </td>
            </tr>
    </c:forEach>
    <div>
        <button class="btn btn-primary" type="submit">Assign</button>
    </div>
    </form>
    </tbody>
</table>
</html>
