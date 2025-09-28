<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
<div style='padding:10px;'>
<form method="post" action="../lec1/task2.jsp">
<div class="mb-3">
    <label  class="form-label">Full name</label>
    <input placeholder='write your full name' type="text" class="form-control" name="fullname" >
  </div>
  <div class="mb-3">
    <label for="exampleInputPassword1" class="form-label">Password</label>
    <input type="password" class="form-control" id="exampleInputPassword1" name="password">
  </div>
  
  <div class="mb-3">
    <label  class="form-label">age</label>
    <input placeholder='write your age' type="number" class="form-control" name="age" >
  </div>
      <div>
      <label>Address (Radio):</label><br>
      <input type="radio" id="cairo" name="addressRadio" value="cairo">
      <label for="cairo">Cairo</label><br>

      <input type="radio" id="alex" name="addressRadio" value="alex">
      <label for="alex">Alex</label><br>

      <input type="radio" id="menofia" name="addressRadio" value="menofia">
      <label for="menofia">Menofia</label>
    </div>	
     <div>
      <label for="addressSelect">Address (Select):</label>
      <select id="addressSelect" name="addressSelect">
        <option value="cairo">Cairo</option>
        <option value="alex">Alex</option>
        <option value="menofia">Menofia</option>
      </select>
    </div>
  <button type="submit" class="btn btn-primary">Submit</button>
</form>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</html>