<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome Home</title>
    <style>
        #chatBox, #timerDisplay {
            border: 1px solid #ccc;
            padding: 10px;
            margin-bottom: 10px;
        }
        #chatBox {
            height: 300px;
            overflow-y: scroll;
        }
        #timerDisplay {
            color: red;
        }
        
		body {
		    background-color: white;
		    color: black;
		}
		
	
		.dark-theme {
		    background-color: black;
		    color: white;
		}
        
    </style>
</head>
<body>
	
	
    <%
        String email = (String) session.getAttribute("email");
        String greeting = "Welcome back, " + email + "!";
    %>
    <h1><%= greeting %></h1>
   <% String dailyTip = (String) request.getAttribute("dailyTip"); %>
<div id="dailyTip" style="margin-top: 20px; padding: 10px; border: 1px solid #ccc;">
    <strong>Today's Tip:</strong> <%= dailyTip != null ? dailyTip : "No tip for today." %>
</div>
    <div style="text-align: right;">
    	<button id="themeToggleButton">Switch to Dark Mode</button>
    	<label for="soundToggle">Sound Notifications:</label>
		<input type="checkbox" id="soundToggle" checked> On/Off
        <a href="editProfile.jsp">Edit Profile</a>
        <a href="changePassword.jsp">Change Password</a>|
        <a href="ViewDetailsServlet">View Profile</a> 
        <a href="ViewNotesServlet">View Notes</a>|
        <a href="LogoutServlet">Logout</a>
    </div>
    
    
    <div id="chatBox">
        
    </div>

    <input type="text" id="userInput" placeholder="Ask a question or give a command...">
    <button onclick="sendQuestion()">Send</button>
    
    <form action="FeedbackServlet" method="POST">
    <h3>Your Feedback</h3>
    <textarea name="feedback" rows="4" cols="50" placeholder="Enter your feedback here..."></textarea><br>
    <input type="submit" value="Submit Feedback">
</form>

    <script>
   
    function sendQuestion() {
        var input = document.getElementById("userInput").value;
        var chatBox = document.getElementById("chatBox");

        chatBox.innerHTML += "You: " + input + "<br>";

       
        var response = getResponse(input);
        handleSpecialCommands(input);

       
        chatBox.innerHTML += "Assistant: " + response + "<br>";
        
        document.getElementById("userInput").value = '';
        
    }

    function getResponse(input) {
        input = input.toLowerCase().trim();
        
        let faqResponse = handleFAQ(input);
        if (faqResponse !== "I'm not sure about that. Try asking something else!") {
            return faqResponse;
        }
        
       
        if (input.match(/\b(add|subtract|\+|-)\b/)) {
            return performMathCalculation(input);
        }
        if (input.includes("convert") && (input.includes("inches") || input.includes("cm"))) {
            return convertUnits(input);
        }

        if (input.includes("joke") || input.includes("inspire") || input.includes("quote")) {
            return tellJokeOrQuote(input);
        }
        if (input.includes("help")) {
            return getHelpGuide();
        }
        let searchResponse = performWebSearch(input);
        if (searchResponse) return searchResponse;

        let contactResponse = getContactDetails(input);
        if (contactResponse) return contactResponse;
        
        if (input.includes("movie")) {
            getMovieSuggestion();
            return "Fetching a movie suggestion for you...";
        }
        
        if (input === "check server status") {
            checkServerStatus();
            return "Checking server status...";
        }
        
        if (input === "thank you" || input === "thanks") {
            return "You're welcome! If you have any more questions, feel free to ask.";
        }
        
        const timerMatch = input.match(/set timer for (\d+) minutes?/i);
        if (timerMatch) {
            const duration = parseInt(timerMatch[1]);
            setTimer(duration);
            return `Setting a timer for ${duration} minutes.`;
        }
        if (input.includes("current date") || input.includes("time")) {
            return new Date().toLocaleString();
        }
        if (input.includes("weather")) {
            handleWeatherQuery(input);
            return "Fetching weather information...";
        }
        
        

      
        switch(input) {
            case "how do i change my password?":
                return "To change your password, go to your profile settings and select 'Change Password'.";
            case "how do i update my email or name?":
                return "You can update your email or name from your profile page. Click on 'Edit Profile' to make changes.";
            case "hi":
                return "Hello";
            default:
                return "I am not sure how to answer that. Can you please ask a different question?";
        }
    }
    
     	

        function performMathCalculation(input) {
    	console.log("Performing math calculation for:", input);
      
        try {
            var numbers = input.match(/-?\d+/g).map(Number);
            if (numbers.length < 2) {
                return "Please provide two numbers for the calculation.";
            }
            if (input.includes("add") || input.includes("+")) {
                return numbers[0] + numbers[1];
            } else if (input.includes("subtract") || input.includes("-")) {
                return numbers[0] - numbers[1];
            }
        } catch (e) {
            return "Sorry, I couldn't perform the calculation.";
        }
    }

    function handleSpecialCommands(input) {
        input = input.toLowerCase().trim();

        if (input === "take notes") {
            var notesUI = '<textarea id="noteInput" placeholder="Type your note here..."></textarea><br>' +
                          '<button onclick="saveNote()">Save Note</button>';
            document.getElementById("chatBox").innerHTML += notesUI;
            
        } else if (input === "add reminder") {
            window.location.href = 'SetReminderServlet.jsp';
        }
    }

    function saveNote() {
        var note = document.getElementById("noteInput").value;
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "NoteServlet", true);
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

        xhr.onreadystatechange = function() {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                alert("Note saved successfully");
                document.getElementById("noteInput").value = ''; 
            } else if (this.readyState === XMLHttpRequest.DONE) {
                alert("Error saving note");
            }
        };

        xhr.send("note=" + encodeURIComponent(note));
    }
    function convertUnits(input) {
        let match = input.match(/(\d+)\s*(inches|inch|in|centimeters|cm)/i);
        if (match) {
            let value = parseFloat(match[1]);
            let unit = match[2].toLowerCase();
            if (unit === "inches" || unit === "inch" || unit === "in") {
                return value * 2.54 + " centimeters";
            } else if (unit === "centimeters" || unit === "cm") {
                return value / 2.54 + " inches";
            }
        }
        return "I'm not sure how to convert that.";
    }
    function tellJokeOrQuote(input) {
        let jokes = ["Why don't scientists trust atoms? Because they make up everything!",
                     "Parallel lines have so much in common. It’s a shame they’ll never meet."];
        let quotes = ["The only way to do great work is to love what you do. - Steve Jobs",
                      "Life is what happens when you're busy making other plans. - John Lennon"];

        if (input.includes("joke")) {
            return jokes[Math.floor(Math.random() * jokes.length)];
        } else if (input.includes("inspire") || input.includes("quote")) {
            return quotes[Math.floor(Math.random() * quotes.length)];
        }
        return "I'm not sure what you're asking for. You can ask for a joke or a quote!";
    }
    function handleFAQ(input) {
        let faqResponses = {
            "what can you do": "I can convert units, tell jokes, inspire you with quotes, set reminders, take notes and answer your FAQs!",
            "how to use": "Just type your question or command, and I'll respond. Try asking for a joke, a quote, or a unit conversion."
            
        };

        input = input.toLowerCase();
        if (faqResponses[input]) {
            return faqResponses[input];
        }
        return "I'm not sure about that. Try asking something else!";
    }
    function getHelpGuide() {
        return "Help Guide:\n" +
               "- Convert units: 'Convert X inches to cm'\n" +
               "- Hear a joke: 'Tell me a joke'\n" +
               "- Get a motivational quote: 'Inspire me'\n" +
               "- FAQ: 'What can you do?'\n" +
               "Type your command to get started!";
    }
    function performWebSearch(input) {
        if (input.includes("search for ")) {
            let query = input.replace("search for ", "");
            let searchUrl = "https://www.google.com/search?q=" + encodeURIComponent(query);
            return "Here is the search result: <a href='" + searchUrl + "' target='_blank'>Search for '" + query + "'</a>";
        }
        return "";
    }
    function getContactDetails(input) {
        if (input.includes("contact details") || input.includes("support team")) {
            return "You can contact our support team at support@chat.com or call us at +44 7123456789.";
        }
        return "";
    }
	
    function getMovieSuggestion() {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "movieServlet", true);

        xhr.onreadystatechange = function() {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                var chatBox = document.getElementById("chatBox");
                chatBox.innerHTML += "Assistant: Here's a movie suggestion - " + this.responseText + "<br>";
            }
        };

        xhr.send();
    }
    
    function checkServerStatus() {
        var xhr = new XMLHttpRequest();
        xhr.open("GET", "CheckServerStatus", true);

        xhr.onreadystatechange = function() {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                var chatBox = document.getElementById("chatBox");
                chatBox.innerHTML += "Server Status: " + this.responseText + "<br>";
            }
        };

        xhr.send();
    }
    
    document.getElementById('themeToggleButton').addEventListener('click', function() {
        var body = document.body;
        var isDarkTheme = body.classList.toggle('dark-theme');
        this.textContent = isDarkTheme ? 'Switch to Light Mode' : 'Switch to Dark Mode';
    });
	
    function setTimer(duration) {
        var minutes = duration;
        var chatBox = document.getElementById("chatBox");
        var timerId = setTimeout(function() {
            chatBox.innerHTML += "<br>Assistant: Your " + minutes + "-minute timer is up!";
        }, minutes * 60000);

        return timerId;
    }
	
    var isSoundEnabled = true;

    document.getElementById('soundToggle').addEventListener('change', function() {
        isSoundEnabled = this.checked;
    });

    function beep() {
        if (!soundToggle) return;

        var audioCtx = new (window.AudioContext || window.webkitAudioContext)();
        var oscillator = audioCtx.createOscillator();
        var gainNode = audioCtx.createGain();

        oscillator.connect(gainNode);
        gainNode.connect(audioCtx.destination);

        gainNode.gain.value = 1; 
        oscillator.frequency.value = 1000; 
        oscillator.type = 'sine'; 

        oscillator.start();
        setTimeout(function() {
            oscillator.stop();
        }, 200); 
    }
    function handleWeatherQuery(input) {
        if (input.includes("weather")) {
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "WeatherServlet", true);

            xhr.onreadystatechange = function() {
                if (this.readyState === XMLHttpRequest.DONE) {
                    if (this.status === 200) {
                        var response = JSON.parse(this.responseText);
                        displayWeatherResponse(response);
                    } else {
                        document.getElementById("chatBox").innerHTML += "<br>Assistant: Unable to fetch weather information at the moment.";
                    }
                }
            };

            xhr.send();
        }
    }

    function displayWeatherResponse(response) {
       
        var weatherInfo = "Weather information: " + JSON.stringify(response);
        document.getElementById("chatBox").innerHTML += "<br>Assistant: " + weatherInfo;
    }





    </script>
</body>
</html>
