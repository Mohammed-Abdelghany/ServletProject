<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.project.model.Item"%>
<%@ page import="com.project.model.User"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Items Management</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- SweetAlert2 -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <style>
        body {
            background: #0f172a;
            font-family: 'Inter', sans-serif;
            color: #e2e8f0;
        }

        .card {
            background: #1e293b;
            border: 1px solid #334155;
            border-radius: 16px;
            box-shadow: 0 4px 16px rgba(0, 0, 0, 0.5);
        }

        .card-header {
            background: linear-gradient(90deg, #3b82f6, #8b5cf6);
            color: #f8fafc;
            font-weight: 600;
            letter-spacing: 0.5px;
            border-top-left-radius: 16px;
            border-top-right-radius: 16px;
        }

        .table {
            margin-bottom: 0;
            color: #e2e8f0;
        }

        thead {
            background-color: #334155;
            color: #f1f5f9;
            text-transform: uppercase;
            font-size: 13px;
            letter-spacing: 0.4px;
        }

        tbody tr {
            transition: background 0.2s ease;
        }

        tbody tr:hover {
            background-color: #1e293b;
        }

        .btn {
            border-radius: 8px;
            font-weight: 500;
            transition: all 0.2s ease;
        }

        .btn:hover {
            transform: translateY(-2px);
        }

        .btn-warning {
            background: #facc15;
            color: #1e1e1e;
            border: none;
        }

        .btn-warning:hover {
            background: #eab308;
            color: #fff;
        }

        .btn-danger {
            background: #b91c1c;
            color: #fef2f2;
            border: none;
        }

        .btn-danger:hover {
            background: #dc2626;
            color: #fff;
        }

        .btn-success {
            background: #15803d;
            color: #f0fdf4;
            border: none;
        }

        .btn-success:hover {
            background: #16a34a;
            color: #fff;
        }

        .logout-btn {
            background: #dc2626;
            border: none;
            color: #fff;
        }

        .logout-btn:hover {
            background: #b91c1c;
        }

        .no-items {
            text-align: center;
            padding: 60px 0;
            color: #94a3b8;
        }

        .no-items h4 {
            color: #f8fafc;
            margin-bottom: 10px;
        }

        th, td {
            border-bottom: 1px solid #334155;
            padding: 12px 16px;
        }

        a {
            color: #60a5fa;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        /* Header customization */
        .header-info {
            display: flex;
            align-items: center;
            justify-content: space-between;
            flex-wrap: wrap;
        }

        .header-user {
            font-size: 15px;
            color: #f8fafc;
        }

        .header-links a {
            color: #fff;
            text-decoration: none;
            margin-left: 15px;
            font-weight: 500;
        }

        .header-links a:hover {
            text-decoration: underline;
        }
    </style>
</head>

<body>
<%
    User currentUser = (User) session.getAttribute("currentUser");
    String username = (currentUser != null) ? currentUser.getName() : "Guest";
    System.out.println(username);
%>

<div class="container py-5">
    <div class="card">
        <div class="card-header">
            <div class="header-info">
                <div class="header-user">
                    👋 Welcome, <strong><%= (currentUser != null) ? currentUser.getName() : "Guest" %></strong>
                </div>

                <div class="header-links d-flex align-items-center">
                    <a href="${pageContext.request.contextPath}/ProfileController" class="btn btn-sm btn-warning me-2">⚙️ Edit Profile</a>

                    <form action="${pageContext.request.contextPath}/AuthController" method="post" style="display:inline;">
                        <button type="submit" name="action" value="logout" class="btn btn-danger btn-sm logout-btn">🚪 Logout</button>
                    </form>
                </div>
            </div>
        </div>

        <div class="card-body p-0">
            <table class="table table-hover align-middle">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Total Number</th>
                        <th class="text-center">Action</th>
                    </tr>
                </thead>
                <tbody>
                <%
                    List<Item> items = (List<Item>) request.getAttribute("items");
                    if (items != null && !items.isEmpty()) {
                        for (Item item : items) {
                %>
                <tr>
                    <td><strong>#<%= item.getId() %></strong></td>
                    <td><%= item.getName() %></td>
                    <td>$<%= String.format("%.2f", item.getPrice()) %></td>
                    <td><%= item.getTotal_number() %></td>
                    <td class="text-center">
                        <a class="btn btn-warning btn-sm me-1"
                           href="${pageContext.request.contextPath}/ItemServlet?action=show-item&id=<%= item.getId() %>">👁️ Show</a>
                        <a class="btn btn-warning btn-sm me-1"
                           href="${pageContext.request.contextPath}/ItemServlet?action=edit-item&id=<%= item.getId() %>">✏️ Update</a>
                        <button type="button" class="btn btn-danger btn-sm delete-btn"
                            data-id="<%= item.getId() %>">🗑️ Delete</button>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="5" class="no-items">
                        <h4>📦 No items found</h4>
                        <p>Start by adding your first item!</p>
                    </td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>

            <div class="text-center mt-4 mb-3">
                <a class="btn btn-success" href="${pageContext.request.contextPath}/CrudItemsProject/add-item.jsp">
                    ➕ Add Item
                </a>
            </div>
        </div>
    </div>
</div>

<script>
document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('.delete-btn').forEach(button => {
        button.addEventListener('click', function() {
            const itemId = this.getAttribute('data-id');
            const deleteUrl = window.location.origin +
                              window.location.pathname.replace(/show-items\.jsp$/, '') +
                              "ItemServlet?action=delete-item&id=" + itemId;

            Swal.fire({
                title: 'هل أنت متأكد؟',
                text: "لن يمكنك التراجع بعد الحذف!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#ef4444',
                cancelButtonColor: '#6b7280',
                confirmButtonText: 'نعم، احذف العنصر',
                cancelButtonText: 'إلغاء'
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.href = deleteUrl;
                }
            });
        });
    });
});
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
