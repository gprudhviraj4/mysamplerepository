/*import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.HttpClient;
import org.apache.http.HttpResponse;*/

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.ResponseContent;

import java.io.IOException;

import javax.swing.text.html.parser.Entity;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.sun.corba.se.pept.transport.Connection;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;



public class callrest{
		static final String USERNAME     = "gprudhviraj4@test.com";
	    static final String PASSWORD     = "Jun@12345f1tIDyX2lI8XSxJls1WlCr3I";
	    static final String LOGINURL     = "https://testgpr-dev-ed.my.salesforce.com";
	    static final String GRANTSERVICE = "/services/oauth2/token?grant_type=password";
	  
	    static final String CLIENTID     = "3MVG9ZL0ppGP5UrDoy7Wo525.BOzJgSfVL3EvV3AGMmXXMJW9T9x7X25H4s1AhvzTq.7pERKIMQZucsE71kJW";
	    static final String CLIENTSECRET = "2536847404239269749";
	    
		public static void main(String[] args) {
				
//https://testgpr-dev-ed.my.salesforce.com/services/oauth2/token?grant_type=password&client_id=3MVG9ZL0ppGP5UrDoy7Wo525.BOzJgSfVL3EvV3AGMmXXMJW9T9x7X25H4s1AhvzTq.7pERKIMQZucsE71kJW&client_secret=2536847404239269749&username=gprudhviraj4@test.com&password=Feb@12345N1EiPRt8zSYJu9PMK3B9ht1K
	    try {	
	    	
	    	//HttpClient httpclient = HttpClientBuilder.create().build();
	    	
	    	//DefaultHttpClient httpclient = new DefaultHttpClient();


	    	// Assemble the login request URL
	    	String loginURL = LOGINURL+GRANTSERVICE+"&client_id=" + CLIENTID +"&client_secret=" + CLIENTSECRET +"&username=" + USERNAME +"&password="+ PASSWORD;
	    	// Login requests must be POSTs
	    	System.out.println("login url: "+loginURL);
	    	HttpPost httpPost = new HttpPost(loginURL);
	    	HttpClient httpclient1 = HttpClientBuilder.create().build();
	    	HttpResponse response = null;
	    	
	    	try {
	    		// Execute the login POST request
	    	
	    		response = httpclient1.execute(httpPost);
	    		} catch (ClientProtocolException cpException) {
	    		// Handle protocol exception
	    			System.out.println("exception: "+cpException);
	    		} catch (IOException ioException) {
	    		// Handle system IO exception
	    			System.out.println("ioException: "+ioException);
	    		}
	    	
	    	// verify response is HTTP OK
	    	final int statusCode = response.getStatusLine().getStatusCode();
	    	if (statusCode != HttpStatus.SC_OK) {
	    	System.out.println("Error authenticating to Force.com:"+statusCode);
	    	// Error is in EntityUtils.toString(response.getEntity())
	    	return;
	    	}

	        
	            // Execute the login POST request
	         //   response = httpclient.execute(httpPost);
	            System.out.println("response : "+response.getStatusLine().getStatusCode());
	           
	            
	            String getResult = null;
	            try {
	            getResult = EntityUtils.toString(response.getEntity());
	            System.out.println(getResult.toString());
	            JSONObject jsonObject = null;
	            String loginAccessToken = null;
	            String loginInstanceUrl = null;
	            try {
	            jsonObject = (JSONObject)new JSONTokener(getResult).nextValue();
	            loginAccessToken = jsonObject.getString("access_token");
	            loginInstanceUrl = jsonObject.getString("instance_url");
	            } catch (JSONException jsonException) {
	            // Handle JSON exception
	            }
	            System.out.println(response.getStatusLine());
	            System.out.println("Successful login");
	            System.out.println(" instance URL: "+loginInstanceUrl);
	            System.out.println(" access token/session ID:"+loginAccessToken);
	           
	            HttpPost pst = new HttpPost(loginInstanceUrl+"/services/apexrest/gpr/callrest/");
	            HttpResponse response2 = null;
	            
	            pst.addHeader("AUTHORIZATION","Bearer "+loginAccessToken);
	            pst.addHeader("Content-Type","Application/Json");
	           
	            pst.setEntity(new StringEntity("{\"argument0\":\"raj\"}"));
	            
	           //get.addHeader("AUTHORIZATION",loginAccessToken);
	            response2 = httpclient1.execute(pst);
	           String getResult2= EntityUtils.toString(response2.getEntity());
	            System.out.println(getResult2.toString());
	            
	            
	            } catch (IOException ioException) {
	            // Handle system IO exception
	            }
	            
	     }catch(Exception e) {
	    	e.printStackTrace();
	    }
	    
		}
}



	

