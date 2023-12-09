<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Saved Notes</title>
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
            margin-left: 20px; 
        }
        .btn-link {
            color: #17a2b8;
        }
        .btn-link:hover {
            color: #138496;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Your Notes</h1>
        <%
            Object obj = request.getAttribute("notes");
            if (obj instanceof ArrayList) {
                ArrayList<String> notes = (ArrayList<String>) obj;
                if (notes != null && !notes.isEmpty()) {
                    int noteNumber = 1; 
                    for (String note : notes) {
                        out.println("<p>Note " + noteNumber + ": " + note + "</p>");
                        noteNumber++; 
                    }
                } else {
                    out.println("<p>No notes saved.</p>");
                }
            } else {
                out.println("<p>Error retrieving notes.</p>");
            }
        %>
        <a href="home.jsp" class="btn btn-link">Back to Home</a>
    </div>
</body>
</html>
