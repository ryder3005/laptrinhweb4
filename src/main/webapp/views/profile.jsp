<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.entity.User" %>
<%
  User user = (User) request.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Profile</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .profile-img {
      width: 150px;
      height: 150px;
      object-fit: cover;
      border-radius: 50%;
    }
  </style>
</head>
<body>
<div class="container mt-5">
  <h2>User Profile</h2>
  <form action="<%=request.getContextPath()%>/profile" method="post" enctype="multipart/form-data">
    <div class="mb-3">
      <label class="form-label">Username</label>
      <input type="text" class="form-control" value="<%=user.getUsername()%>" disabled>
    </div>

    <div class="mb-3">
      <label class="form-label">Full Name</label>
      <input type="text" name="fullName" class="form-control" value="<%=user.getFullName() != null ? user.getFullName() : ""%>" required>
    </div>

    <div class="mb-3">
      <label class="form-label">Email</label>
      <input type="email" class="form-control" value="<%=user.getEmail()%>" disabled>
    </div>

    <div class="mb-3">
      <label class="form-label">Phone</label>
      <input type="text" name="phone" class="form-control" value="<%=user.getPhone() != null ? user.getPhone() : ""%>">
    </div>

    <div class="mb-3">
      <label class="form-label">Current Image</label><br>
      <% if(user.getImage() != null){ %>
      <img src="<%=request.getContextPath()%>/image?fname=<%=user.getImage()%>" alt="User Image" class="profile-img mb-2" id="previewImg">
      <% } else { %>
      <img src="https://via.placeholder.com/150" alt="No Image" class="profile-img mb-2" id="previewImg">
      <% } %>
    </div>

    <div class="mb-3">
      <label class="form-label">Change Image</label>
      <input type="file" name="image" class="form-control" accept="image/*" onchange="previewImage(event)">
    </div>

    <button type="submit" class="btn btn-primary">Update Profile</button>
    <a href="<%=request.getContextPath()%>/dashboard" class="btn btn-secondary">Cancel</a>
  </form>
</div>

<script>
  function previewImage(event) {
    const reader = new FileReader();
    reader.onload = function(){
      const output = document.getElementById('previewImg');
      output.src = reader.result;
    }
    reader.readAsDataURL(event.target.files[0]);
  }
</script>
</body>
</html>
