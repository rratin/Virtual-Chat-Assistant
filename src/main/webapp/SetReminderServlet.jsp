<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Set Reminder</title>
</head>
<body>
    <h2>Set a Reminder</h2>
    <form action="SetReminderServlet" method="post">
        Message: <input type="text" name="message"><br>
        Time (yyyy-mm-ddThh:mm): <input type="datetime-local" name="reminderTime"><br>
        <input type="submit" value="Set Reminder">
    </form>
</body>
</html>