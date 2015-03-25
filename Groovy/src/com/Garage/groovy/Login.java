package com.Garage.groovy;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity
{
	EditText userid,password;
	static String r,p;
	Button submit,register;
	static String urlString1;
	static String urlString2;
	static int indi = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		Log.d("loginpage","26");
		userid = (EditText)findViewById(R.id.editText1);
		password = (EditText)findViewById(R.id.editText2);
		submit = (Button)findViewById(R.id.button1);
		register = (Button)findViewById(R.id.button2);
		submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) 
			{
				Log.d("loginpage","37");
				
				// TODO Auto-generated method stub
				r = userid.getText().toString();
				p = password.getText().toString();
				urlString1 = "http://ec2-54-148-114-230.us-west-2.compute.amazonaws.com:8080/GroovyServer/Login?userid="+r+"&Pswrd="+p+"";
				//urlString1 = "http://10.0.2.2:5000/GroovyServer/Login?userid="+r+"&Pswrd="+p+"";
				//urlString1 = "http://14.139.82.130:5000/Server/Login?userid="+r+"&Pswrd="+p+"";
				MyTask m = new MyTask();
				m.execute(urlString1);
			}
		});
		
		
		Toast.makeText(getApplicationContext(), "create/login", Toast.LENGTH_LONG).show();
		register.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) 
			{
				Log.d("loginpage","54");
				
				// TODO Auto-generated method stub
				startActivity(new Intent(Login.this,registration.class));
			
				
				
				/*	r = userid.getText().toString();
				p = password.getText().toString();
				urlString = "http://10.0.2.2:5000/Server/Login?userid="+r+"&Pswrd="+p+"";
				MyTask m = new MyTask();
				m.execute(urlString);
			*/
			}
		});
		
	}
	private class MyTask extends AsyncTask<String, Void, String>
	{
		String response;
		
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			Log.d("loginpage","121");
			
			response=ConnectionClass.send(urlString1);
			return response;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "gt response as" + result, Toast.LENGTH_LONG).show();
			if(result.equals("1"))
			{
				Log.d("loginpage","132");
				Toast.makeText(getApplicationContext(), "login details are corrrect... u will be proceeded", Toast.LENGTH_LONG).show();
				Intent i = new Intent(Login.this,MainActivity.class);
				i.putExtra("uid", r);
				startActivity(i);
			}
			else
			{
				Log.d("loginpage","143");
				
				Toast.makeText(getApplicationContext(), "login details are incorrrect..try again", Toast.LENGTH_LONG).show();
			}
		}
		
	}

	

	
	

}
