<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<html>
<jsp:include page="../static/navbar.jsp"/>
<form action="assignCourses?idUser=${idUser}" method="post">
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

        <c:forEach var="course" items="${coursesList}">
            <tr>
                <td><c:out value="${course.theme}"/></td>
                <td><c:out value="${course.nameOfCourses}"/></td>
                <td><c:out value="${course.startOfCourses}"/></td>
                <td><c:out value="${course.endOfCourses}"/></td>
                <td><c:out value="${course.price}"/></td>
                <td>
                    <div class="form-check">
                        <input type="checkbox" name="assignCourses" value="${course.idCourses}"
                               class="form-check-input" id="${course.idCourses}">
                        <label class="form-check-label" for="${course.idCourses}"></label>

                        <c:forEach var="idC" items="${coursesId}">
                            <c:if test='${idC == course.idCourses}'>
                                <script>
                                    $("#${course.idCourses}").prop('checked', 'checked');
                                </script>
                            </c:if>
                        </c:forEach>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="row">
        <div class="col-md-11 text-right">
            <button class="btn btn-primary" name="idUser" value="${idUser}" type="submit"
                    onclick="return ValidateCourseSelection()">Assign
            </button>
        </div>
    </div>
</form>

<script type="text/javascript">
    function ValidateCourseSelection() {
        var checkboxes = document.getElementsByName("assignCourses");
        var numberOfCheckedItems = 0;
        for (var i = 0; i < checkboxes.length; i++) {
            if (checkboxes[i].checked)
                numberOfCheckedItems++;
        }
        if (!numberOfCheckedItems > 0) {
            alert("You must select at least one course");
            return false;
        }
    }

</script>

</html>
