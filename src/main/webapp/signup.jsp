<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Signup</title>
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
                        <form action="Signup" method="post" class="p-4">
                            <h3 class="text-center text-info">Sign Up</h3>

                            <div class="form-group">
                                <label for="email" class="text-info">Email:</label><br>
                                <input type="email" name="email" id="email" class="form-control">
                            </div>

                            <div class="form-group">
                                <label for="password" class="text-info">Password:</label><br>
                                <input type="password" name="password" id="password" class="form-control">
                            </div>

                            <div class="form-group">
                                <label for="fullname" class="text-info">Full Name:</label><br>
                                <input type="text" name="fullname" id="fullname" class="form-control">
                            </div>

                            <div class="form-group">
                                <input type="submit" value="Sign Up" class="btn btn-info btn-md">
                            </div>
                        </form>
                    </div>
                </div>
                <p class="text-center text-info">Already have an account? <a href="login.jsp" class="text-decoration-none">Log In</a></p>
            </div>
        </div>
    </div>
</body>

</html>
