<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Details</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f4f7f6;
            font-family: 'Arial', sans-serif;
        }
        .container {
            max-width: 600px;
            margin-top: 20px;
        }
        h1 {
            color: #17a2b8;
            text-align: center;
        }
        p {
            color: #333333;
            font-size: 16px;
        }
        .btn-danger {
            background-color: #dc3545;
            border: none;
        }
        .btn-danger:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>User Details</h1>
        <p><strong>Full Name:</strong> ${user.fullname}</p>
        <p><strong>Email:</strong> ${user.email}</p>
        <p><strong>Password:</strong> **********</p>
        
        <form action="DeactivateAccountServlet" method="POST">
            <input type="submit" value="Deactivate My Account" class="btn btn-danger">
        </form>
    </div>
</body>
</html>
