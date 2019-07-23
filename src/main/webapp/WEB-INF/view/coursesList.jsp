<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Bootstrap CSS -->


<html>
<jsp:include page="../static/navbar.jsp"/>
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
    <div>
        <span style="color: maroon">${updated}</span>
    </div>
    <c:forEach var="course" items="${coursesList}">
        <tr>
            <td><c:out value="${course.theme}"/></td>
            <td><c:out value="${course.nameOfCourses}"/></td>
            <td><c:out value="${course.startOfCourses}"/></td>
            <td><c:out value="${course.endOfCourses}"/></td>
            <td><c:out value="${course.price}"/></td>
            <td>
                <form action="deleteCourses" method="post">
                    <button style="background-color: #db5564" type="submit"
                            name="idCourses" value="${course.idCourses}">Delete
                    </button>
                </form>
            </td>
            <td>
                <a href="Update">Update</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</html>

