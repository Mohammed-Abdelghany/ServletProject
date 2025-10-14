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
<div class="container">
  <div class="text">
    Add Item
  </div>
  <%
  String error = (String) request.getAttribute("errors");

%>

<% if (error != null && !error.isEmpty()) { %>
    <div class="group" style="margin-bottom: 20px;">
        <div style="background: rgba(220, 53, 69, 0.2); 
                    border: 2px solid #dc3545; 
                    border-radius: 25px; 
                    padding: 15px 20px;
                    color: #fff;">
            
                <p style="margin: 5px 0; font-size: 14px;">⚠️ <%= error %></p>
            
        </div>
    </div>
<% } %>


<form action="${pageContext.request.contextPath}/itemservlet" method="post">
    <div class="form-row">
      <div class="input-data">
        <input type="text" required name="name">
        <div class="underline"></div>
        <label>Name</label>
      </div>
      <div class="input-data">
        <input type="text" required name="price">
        <div class="underline"></div>
        <label>PRICE</label>
      </div>
    </div>
    <div class="form-row">
      <div class="input-data">
        <input type="text" required  name="total_number">
        <div class="underline"></div>
        <label>TOTAL_NUMBER</label>
      </div>
    </div>
    <input type="hidden" required  name="action" value="add-item">
    <input type="submit" value="add" class="button">
  </form>

  <p class="back">
    <a href="${pageContext.request.contextPath}/itemservlet" >Back To Items</a>
  </p>
</div>
<!-- partial -->

</body>
</html>
