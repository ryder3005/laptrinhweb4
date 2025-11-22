<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/header.jsp" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<div class="container mt-5">
  <h2>Edit Video</h2>
  <form action="${pageContext.request.contextPath}/admin/videos/edit" method="post">
    <input type="hidden" name="id" value="${video.videoId}">

    <div class="mb-3">
      <label for="title" class="form-label">Title</label>
      <input type="text" class="form-control" id="title" name="title" value="${video.title}" required>
    </div>

    <div class="mb-3">
      <label for="poster" class="form-label">Poster URL</label>
      <input type="text" class="form-control" id="poster" name="poster" value="${video.poster}">
    </div>

    <div class="mb-3">
      <label for="views" class="form-label">Views</label>
      <input type="number" class="form-control" id="views" name="views" value="${video.views}">
    </div>

    <div class="mb-3">
      <label for="description" class="form-label">Description</label>
      <textarea class="form-control" id="description" name="description" rows="3">${video.description}</textarea>
    </div>

    <div class="mb-3 form-check">
      <input type="checkbox" class="form-check-input" id="active" name="active"
             <c:if test="${video.active}">checked</c:if>>
      <label class="form-check-label" for="active">Active</label>
    </div>

    <div class="mb-3">
      <label for="category" class="form-label">Category</label>
      <select class="form-select" id="category" name="categoryId">
        <c:forEach var="cat" items="${categories}">
          <option value="${cat.categoryId}"
                  <c:if test="${video.category.categoryId == cat.categoryId}">selected</c:if>>
              ${cat.categoryName}
          </option>
        </c:forEach>
      </select>
    </div>

    <button type="submit" class="btn btn-primary">Update</button>
    <a href="${pageContext.request.contextPath}/admin/videos" class="btn btn-secondary">Cancel</a>
  </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="/common/footer.jsp" %>
