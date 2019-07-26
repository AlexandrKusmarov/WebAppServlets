<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="../static/navbar.jsp"/>
<form action="addTeacher" method="post">
    <div style="text-align: center">
        <h1>Add new teacher</h1>
    </div>
    <c:import url="../static/registrationForm.jsp" />
</form>
</html>
