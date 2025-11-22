<%--
  Created by IntelliJ IDEA.
  User: caoth
  Date: 11/22/2025
  Time: 10:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<div class="container mt-5">
  <h2 class="mb-4">Categories</h2>
  <a href="${pageContext.request.contextPath}/admin/categories/create" class="btn btn-success mb-3">Add New Category</a>
  <table class="table table-bordered table-hover">
    <thead class="table-light">
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="cat" items="${categories}">
      <tr>
        <td>${cat.categoryId}</td>
        <td>${cat.categoryName}</td>
        <td>
          <a href="${pageContext.request.contextPath}/admin/categories/edit?id=${cat.getCategoryId}" class="btn btn-primary btn-sm">Edit</a>
          <a href="${pageContext.request.contextPath}/admin/categories/delete?id=${cat.getCategoryId}"
             class="btn btn-danger btn-sm"
             onclick="return confirm('Are you sure to delete this category?');">Delete</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

