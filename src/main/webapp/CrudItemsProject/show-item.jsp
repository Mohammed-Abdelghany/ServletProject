<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.project.model.Item"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Show Items</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/CrudItemsProject/css/custom.css">
<style>
body {
	background-image:
		url('${pageContext.request.contextPath}/CrudItemsProject/css/istockphoto-1195769833-612x612.jpg');
}
</style>
</head>

<body class="bg-dark text-light">

	<div class="container py-5">
		<div class="card shadow-lg">
			<div
				class="card-header bg-primary text-white d-flex justify-content-between align-items-center">
				<h3 class="mb-0">Items</h3>
			</div>

			<div class="card-body p-0">
				<table class="table table-striped table-hover mb-0">
					<thead class="table-dark">
						<tr>
							<th>ID</th>
							<th>NAME</th>
							<th>PRICE</th>
							<th>TOTAL NUMBER</th>
							<th class="text-center">Action</th>
						</tr>
					</thead>
					<tbody>
						<%
						Item item = (Item) request.getAttribute("item");
						if (item != null) {
						%>
						<tr>
							<td><strong><%=item.getId()%></strong></td>
							<td><%=item.getName()%></td>
							<td>$<%=item.getPrice()%></td>
							<td><%=item.getTotal_number()%></td>
							<td class="text-center"><a
								class="btn btn-warning btn-sm me-1"
								href="itemservlet?action=edit-item&id=<%=item.getId()%>">Update</a>
								<a class="btn btn-danger btn-sm"
								href="itemservlet?action=delete-item&id=<%=item.getId()%>">Delete</a></td>
						</tr>
						<%
						} else {
						%>

						<tr>
							<td colspan="5" class="text-center text-muted">No items
								found.</td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>

			</div>
		</div>
	</div>


</body>


 <!-- تحميل مكتبة SweetAlert2 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<!-- كود الجافاسكربت -->
<script>
<%
String message = (String) session.getAttribute("message");
if (message != null) {
%>
Swal.fire({
    icon: 'success',
    title: 'Success',
    text: '<%= message %>',
    timer: 2000,
    showConfirmButton: false
});
<% session.removeAttribute("message"); %>
<% } %>
</script>
</html>
 