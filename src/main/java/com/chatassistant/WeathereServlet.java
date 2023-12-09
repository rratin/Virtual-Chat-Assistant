package com.chatassistant;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Servlet implementation class WeathereServlet
 */
public class WeathereServlet extends HttpServlet {
	private static final String API_KEY = "2284eb06dd5578bac3c9f072691a4ae2";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WeathereServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		double latitude = 33.44;
	    double longitude = -94.04;

	    String urlString = "https://api.openweathermap.org/data/3.0/onecall?lat=" + latitude
	                       + "&lon=" + longitude + "&exclude=hourly,daily&appid=" + API_KEY;

	    URL url = new URL(urlString);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setRequestMethod("GET");
	    conn.connect();

	    int responsecode = conn.getResponseCode();

	    if (responsecode != 200) {
	        log("Error fetching weather data: HttpResponseCode " + responsecode);
	        response.setContentType("application/json");
	        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	        PrintWriter out = response.getWriter();
	        out.print("{\"error\":\"Unable to fetch weather data. Please try again later.\"}");
	    } else {
	        StringBuilder inline = new StringBuilder();
	        Scanner scanner = new Scanner(url.openStream());

	        while (scanner.hasNext()) {
	            inline.append(scanner.nextLine());
	        }
	        scanner.close();

	        response.setContentType("application/json");
	        PrintWriter out = response.getWriter();
	        out.print(inline.toString());
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
