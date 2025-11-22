<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/common/header.jsp" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<div class="container mt-5">
    <h2 class="mb-4">Users</h2>
    <a href="${pageContext.request.contextPath}/admin/users/create" class="btn btn-success mb-3">Add New User</a>
    <table class="table table-bordered table-hover">
        <thead class="table-light">
        <tr>
            <th>Username</th>
            <th>Email</th>
            <th>Full Name</th>
            <th>Phone</th>
            <th>Role</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>${user.fullName}</td>
                <td>${user.phone}</td>
                <td><c:out value="${user.admin ? 'Admin' : 'User'}"/></td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/users/edit?id=${user.username}" class="btn btn-primary btn-sm">Edit</a>
                    <a href="${pageContext.request.contextPath}/admin/users/delete?id=${user.username}"
                       class="btn btn-danger btn-sm"
                       onclick="return confirm('Are you sure to delete this user?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="/common/footer.jsp" %>
