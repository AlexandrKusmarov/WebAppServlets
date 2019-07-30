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
    <c:forEach var="course" items="${coursesList}">
        <tr>
            <td><c:out value="${course.theme}"/></td>
            <td><c:out value="${course.nameOfCourses}"/></td>
            <td><c:out value="${course.startOfCourses}"/></td>
            <td><c:out value="${course.endOfCourses}"/></td>
            <td><c:out value="${course.price}"/></td>
            <c:choose>
                <c:when test="${role  == \"ADMIN\"}">
                    <td>
                        <form action="updateCourses?id=<c:out value='${course.idCourses}' />">
                            <button style="background-color: #8ddb99" type="submit"
                                    name="id" value="${course.idCourses}">Update
                            </button>
                        </form>
                    </td>
                    <td>
                        <form action="deleteCourses" method="post">
                            <button style="background-color: #db5564" type="submit"
                                    name="idCourses" value="${course.idCourses}">Delete
                            </button>
                        </form>
                    </td>
                </c:when>
                <c:when test="${role  == \"STUDENT\"}">
                    <td>
                        <form action="assignCourses?idUser=<%=session.getAttribute("idUser")%>" method="post">
                            <button style="background-color: #a9db45" type="submit"
                                    name="assignCourses" value="${course.idCourses}">Sign up
                            </button>
                        </form>
                    </td>
                </c:when>
            </c:choose>
        </tr>
    </c:forEach>
    </tbody>
</table>
</html>

