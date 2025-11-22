<%--
  Created by IntelliJ IDEA.
  User: caoth
  Date: 11/22/2025
  Time: 10:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/header.jsp" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<div class="container mt-5">
  <h2>Add New Category</h2>
  <form action="${pageContext.request.contextPath}/admin/categories/create" method="post">
    <div class="mb-3">
      <label for="name" class="form-label">Category Name</label>
      <input type="text" class="form-control" name="name" id="name" required>
    </div>
    <button type="submit" class="btn btn-success">Save</button>
    <a href="${pageContext.request.contextPath}/admin/categories" class="btn btn-secondary">Cancel</a>
  </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="/common/footer.jsp" %>

