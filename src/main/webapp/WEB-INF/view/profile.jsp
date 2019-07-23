<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <jsp:include page="../static/navbar.jsp"/>

</head>

<body>

    <div class="container col-md-6">
        <c:set var="name" value='<%=session.getAttribute("userName")%>'/>
        <c:set var="password" value='<%=session.getAttribute("password")%>'/>
        <c:set var="mail" value='<%=session.getAttribute("mail")%>'/>
        <form action="profile" method="post">
                <div class="form-group">
                    <label for="login">Login</label>
                    <input type="text" id="login" class="form-control" value="${name}" required name="login">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" class="form-control" value="${password}" required name="password">
                </div>
                <div class="form-group">
                    <label for="mail">Email</label>
                    <input type="email" id="mail" class="form-control" value="${mail}" name="mail" required>
                </div>
                <div>
                    <button class="btn btn-primary" type="submit">Update</button>
                </div>
            </form>
        </div>

</body>
</html>
