package br.com.demo.javaclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import org.apache.tomcat.util.http.fileupload.IOUtils;

public class JavaClientTest {

	public static void main(String[] args) {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		String user = "lucas";
		String pass = "teste";
		try {
			URL url = new URL("http://localhost:8080/v1/protected/students/10");
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.addRequestProperty("Authorization", "Basic "+encodeUsernamePassword(user, pass));
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder jsonSB = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				jsonSB.append(line);
			}
			System.out.println(jsonSB.toString());
		} catch (Exception e) {
		e.printStackTrace();	
		} finally {
			IOUtils.closeQuietly(reader);
			if(conn != null) {
				conn.disconnect();
			}
		}
	}
	
	private static String encodeUsernamePassword(String username, String password) {
		String userAndPass = username+":"+password;
		return new String (Base64.getEncoder().encodeToString(userAndPass.getBytes()));
	}
}
