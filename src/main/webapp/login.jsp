<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f4f7f6;
            font-family: 'Arial', sans-serif;
        }

        .card {
            box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
        }

        .text-info {
            color: #17a2b8;
        }

        .btn-info {
            background-color: #17a2b8;
            border: none;
        }

        .btn-info:hover {
            background-color: #138496;
        }

        .form-control {
            border-radius: 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="row justify-content-center align-items-center" style="height: 100vh;">
            <div class="col-6">
                <div class="card">
                    <div class="card-body">
                        <form action="LoginServlet" method="post" class="p-4">
                            <h3 class="text-center text-info">Login</h3>

                            <div class="form-group">
                                <label for="email" class="text-info">Email:</label><br>
                                <input type="text" name="email" id="email" class="form-control" required>
                            </div>

                            <div class="form-group">
                                <label for="password" class="text-info">Password:</label><br>
                                <input type="password" name="password" id="password" class="form-control" required>
                            </div>

                            <div class="form-group">
                                <input type="submit" value="Login" class="btn btn-info btn-md">
                            </div>
                        </form>
                    </div>
                </div>
                <p class="text-center text-info">Do you want to create account? <a href="signup.jsp" class="text-decoration-none">Signup</a></p>
            </div>
            </div>
        </div>
    </div>
</body>
</html>
