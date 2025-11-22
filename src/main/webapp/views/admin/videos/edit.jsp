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
      <input type="text" class="form-control" name="title" id="title" value="${video.title}" required>
    </div>
    <div class="mb-3">
      <label for="description" class="form-label">Description</label>
      <textarea class="form-control" name="description" id="description" rows="3" required>${video.description}</textarea>
    </div>
    <button type="submit" class="btn btn-primary">Update</button>
    <a href="${pageContext.request.contextPath}/admin/videos" class="btn btn-secondary">Cancel</a>
  </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="/common/footer.jsp" %>
