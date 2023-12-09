<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Change Password</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f4f7f6;
            font-family: 'Arial', sans-serif;
        }
        .container {
            max-width: 500px;
            margin-top: 20px;
        }
        .form-group label {
            color: #17a2b8;
        }
        .btn-info {
            background-color: #17a2b8;
            border: none;
        }
        .btn-info:hover {
            background-color: #138496;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="text-center" style="color: #17a2b8;">Change Password</h1>

        <form action="ChangePasswordServlet" method="POST">
            <div class="form-group">
                <label for="currentPassword">Current Password:</label>
                <input type="password" class="form-control" name="currentPassword" id="currentPassword">
            </div>
            <div class="form-group">
                <label for="newPassword">New Password:</label>
                <input type="password" class="form-control" name="newPassword" id="newPassword">
            </div>
            <div class="form-group">
                <label for="confirmNewPassword">Confirm New Password:</label>
                <input type="password" class="form-control" name="confirmNewPassword" id="confirmNewPassword">
            </div>
            <input type="submit" value="Change Password" class="btn btn-info">
        </form>
    </div>
</body>
</html>
