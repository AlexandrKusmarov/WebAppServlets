<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<html>
<jsp:include page="../static/navbar.jsp"/>

<table class="table">
    <thead>
    <tr>
        <th>Login</th>
        <th>Role</th>
        <th>Account activity</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="account" items="${accounts}">
        <tr>
            <td><c:out value="${account.login}"/></td>
            <td><c:out value="${account.role}"/></td>

            <c:choose>
                <c:when test="${account.role == ('STUDENT')}">
                    <form action="onOffAccount" method="post">
                        <c:choose>
                            <c:when test="${account.isActive()}">
                                <td><c:out value="active"/>
                                    <button style="background-color: mediumseagreen" type="submit"
                                            name="activity" id="activity" value="${account.idUser}">Disable
                                    </button>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td>
                                    <c:out value="locked"/>
                                    <button style="background-color: palevioletred" type="submit"
                                            name="activity" value="${account.idUser}">Enable
                                    </button>
                                </td>
                            </c:otherwise>
                        </c:choose>
                    </form>
                </c:when>
            </c:choose>
        </tr>
    </c:forEach>
    </tbody>
</table>
</html>
