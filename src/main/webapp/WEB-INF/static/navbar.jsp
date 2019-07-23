<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #e3f2fd;" >
    <a class="navbar-brand" href="#">Home</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="coursesList">Courses <span class="sr-only">(current)</span></a>
            </li>
            <c:set var="role" value='<%=session.getAttribute("role")%>'/>
            <c:choose>
                <c:when test="${role  == \"ADMIN\"}">
                    <li class="nav-item">
                        <a class="nav-link" href="accounts">Accounts</a>
                    </li>
                </c:when>
            </c:choose>
            <li class="nav-item">
                <a class="nav-link" href="addTeacher">Add Teacher</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="profile">Profile</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Dropdown link
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </li>
        </ul>
    </div>
    <div>
        <div><%=session.getAttribute("userName")%></div>
        <div style="text-align: right">
            <form method="post" action="logout">
                <button type="submit" name="logout" id="logoutid">Logout</button>
            </form>
        </div>
    </div>
</nav>
