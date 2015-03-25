package com.Garage.groovy;



import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

public class fbrgmlogin extends Activity
{
	static String urlString;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		String uid="asdf";
		String pwd= "";
		String pa ="asdf";
		String ph ="asdf";
		String r = "asdf";
		String n = "asdf";
		urlString = "http://ec2-54-148-114-230.us-west-2.compute.amazonaws.com:8080/GroovyServer/Registration?Name="+n+"&Emailid="+r+"&PhNum="+ph+"&gender="+pa+"&password="+pwd+"&userid="+uid+"";                              
		
		MyTask m = new MyTask();
		m.execute(urlString);
	}

	private class MyTask extends AsyncTask<String, Void, String>
	{
		String response;
		
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
				 response=ConnectionClass.send(urlString);
			return response;
	}
	
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "gt response as" + result, Toast.LENGTH_LONG).show();
			if(result.equals("1"))
			{
				Toast.makeText(getApplicationContext(),"ur details have been entered successfully",Toast.LENGTH_LONG).show();
				
			}
			else 
			{
				Toast.makeText(getApplicationContext(),"Register with different details", Toast.LENGTH_LONG).show();
			}
		}
		
	}

}
