<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7f6;
            text-align: center;
            padding: 50px;
        }
        .error-container {
            background-color: #fff;
            margin: auto;
            padding: 20px;
            border: 1px solid #ddd;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 80%;
            max-width: 600px; 
        }
        .error-message {
            color: #dc3545; 
        }
        h1 {
            color: #17a2b8;
        }
    </style>
</head>
<body>
<div class="error-container">
    <h1>Oops! Something went wrong.</h1>
    <p class="error-message">
        <% 
       
        String errorMessage = (String) request.getAttribute("error");
        if (errorMessage == null || errorMessage.isEmpty()) {
            out.println("An unknown error occurred.");
        } else {
            out.println(errorMessage);
        }
        %>
    </p>
</div>
</body>
</html>
