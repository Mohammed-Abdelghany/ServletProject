<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>ADD Item</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
   <link rel="stylesheet" href="${pageContext.request.contextPath}/CrudItemsProject/css/add-item.css">

</head>
<body>
<!-- partial:index.partial.html -->
<div class="container">
  <div class="text">
    Update Item
  </div>
  <form action="itemservlet" method="post">
  <input type="hidden" required  name="id" value="${item.id}">
    <div class="form-row">
      <div class="input-data">
        <input type="text" required name="name" value="${item.name}">
        <div class="underline"></div>
        <label>Name</label>
      </div>
      <div class="input-data">
        <input type="text" required name="price" value="${item.price}">
        <div class="underline"></div>
        <label>PRICE</label>
      </div>
    </div>
    <div class="form-row">
      <div class="input-data">
        <input type="text" required  name="total_number" value="${item.total_number}">
        <div class="underline"></div>
        <label>TOTAL_NUMBER</label>
      </div>
    </div>
    <input type="hidden" required  name="action" value="update-item">
    <input type="submit" value="Update" class="button">
  </form>

  <p class="back">
    <a href="ServletCourse/itemservlet" >Back To Items</a>
  </p>
</div>
<!-- partial -->

</body>
</html>
