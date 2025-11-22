<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/header.jsp" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

<div class="container mt-5">
  <h2>Edit Category</h2>
  <form action="${pageContext.request.contextPath}/admin/categories/edit" method="post">
    <input type="hidden" name="id" value="${category.categoryId}">

    <div class="mb-3">
      <label for="name" class="form-label">Category Name</label>
      <input type="text" class="form-control" id="name" name="name" value="${category.categoryName}" required>
    </div>

    <div class="mb-3">
      <label for="categorycode" class="form-label">Category Code</label>
      <input type="text" class="form-control" id="categorycode" name="categorycode" value="${category.categorycode}">
    </div>

    <div class="mb-3">
      <label for="images" class="form-label">Images URL</label>
      <input type="text" class="form-control" id="images" name="images" value="${category.images}">
    </div>

    <div class="mb-3 form-check">
      <input type="checkbox" class="form-check-input" id="status" name="status"
             <c:if test="${category.Status}">checked</c:if>>
      <label class="form-check-label" for="status">Active</label>
    </div>

    <div class="mb-3">
      <label for="user" class="form-label">Owner User</label>
      <select class="form-select" id="user" name="userId" required>
        <c:forEach var="user" items="${users}">
          <option value="${user.Username}"
                  <c:if test="${category.user.Username == user.Username}">selected</c:if>>
              ${user.FullName}
          </option>
        </c:forEach>
      </select>
    </div>

    <button type="submit" class="btn btn-primary">Update</button>
    <a href="${pageContext.request.contextPath}/admin/categories" class="btn btn-secondary">Cancel</a>
  </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="/common/footer.jsp" %>
