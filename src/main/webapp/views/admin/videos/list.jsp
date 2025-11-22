<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/common/header.jsp" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<div class="container mt-5">
  <h2 class="mb-4">Videos</h2>
  <a href="${pageContext.request.contextPath}/admin/videos/create" class="btn btn-success mb-3">Add New Video</a>
  <table class="table table-bordered table-hover">
    <thead class="table-light">
    <tr>
      <th>ID</th>
      <th>Title</th>
      <th>Description</th>
      <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="video" items="${videos}">
      <tr>
        <td>${video.videoId}</td>
        <td>${video.title}</td>
        <td>${video.description}</td>
        <td>
          <a href="${pageContext.request.contextPath}/admin/videos/edit?id=${video.videoId}" class="btn btn-primary btn-sm">Edit</a>
          <a href="${pageContext.request.contextPath}/admin/videos/delete?id=${video.videoId}"
             class="btn btn-danger btn-sm"
             onclick="return confirm('Are you sure to delete this video?');">Delete</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="/common/footer.jsp" %>
