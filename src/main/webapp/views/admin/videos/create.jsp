<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/header.jsp" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<div class="container mt-5">
  <h2>Add New Video</h2>
  <form action="${pageContext.request.contextPath}/admin/videos/create" method="post">
    <div class="mb-3">
      <label for="title" class="form-label">Title</label>
      <input type="text" class="form-control" id="title" name="title" required>
    </div>

    <div class="mb-3">
      <label for="poster" class="form-label">Poster URL</label>
      <input type="text" class="form-control" id="poster" name="poster">
    </div>

    <div class="mb-3">
      <label for="views" class="form-label">Views</label>
      <input type="number" class="form-control" id="views" name="views" value="0">
    </div>

    <div class="mb-3">
      <label for="description" class="form-label">Description</label>
      <textarea class="form-control" id="description" name="description" rows="3"></textarea>
    </div>

    <div class="mb-3 form-check">
      <input type="checkbox" class="form-check-input" id="active" name="active" checked>
      <label class="form-check-label" for="active">Active</label>
    </div>

    <div class="mb-3">
      <label for="category" class="form-label">Category</label>
      <select class="form-select" id="category" name="categoryId">
        <c:forEach var="cat" items="${categories}">
          <option value="${cat.categoryId}">${cat.categoryName}</option>
        </c:forEach>
      </select>
    </div>

    <button type="submit" class="btn btn-success">Save</button>
    <a href="${pageContext.request.contextPath}/admin/videos" class="btn btn-secondary">Cancel</a>
  </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="/common/footer.jsp" %>
