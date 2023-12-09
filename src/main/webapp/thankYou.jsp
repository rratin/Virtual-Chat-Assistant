<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thank You</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7f6;
            text-align: center;
            padding-top: 50px;
        }
        .thank-you-container {
            background-color: #fff;
            margin: auto;
            padding: 20px;
            border: 1px solid #ddd;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 80%;
            max-width: 600px;
        }
        h1 {
            color: #17a2b8;
        }
        p {
            color: #333333;
            font-size: 16px;
        }
        .btn-link {
            display: inline-block;
            margin-top: 20px;
            color: #17a2b8;
            text-decoration: none;
            font-weight: bold;
        }
        .btn-link:hover {
            color: #138496;
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="thank-you-container">
        <h1>Thank You for Your Feedback!</h1>
        <p>Your feedback is valuable to us and helps improve our service.</p>
        <a href="home.jsp" class="btn-link">Return to Home</a>
    </div>
</body>
</html>
