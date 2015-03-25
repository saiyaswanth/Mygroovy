package com.Garage.groovy;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class registration extends Activity
{
	EditText name,emailid,phnum,date,password,userid;
	RadioGroup genderGroup;
	RadioButton male,female;
	String n,r,ph,pa,pwd,uid;
	Button submit;
	TextView tv;
	static String urlString;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		name =(EditText)findViewById(R.id.editText1);
		emailid =(EditText)findViewById(R.id.editText2);
		phnum =(EditText)findViewById(R.id.editText3);
		submit = (Button)findViewById(R.id.button1);
		password = (EditText)findViewById(R.id.editText4);
		genderGroup = (RadioGroup)findViewById(R.id.radioGroup1);
		male = (RadioButton)findViewById(R.id.radio1);
		female = (RadioButton)findViewById(R.id.radio0);
		userid = (EditText)findViewById(R.id.editText5);
		submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub
				n = name.getText().toString();
				r = emailid.getText().toString();
				ph = phnum.getText().toString();
				uid = userid.getText().toString();
					if(male.isSelected())
					{
						pa = male.getText().toString();
					}
					else
					{
						pa = female.getText().toString();
					}
				
				pwd = password.getText().toString();

			//	urlString = "http://10.0.2.2:5000/GroovyServer/Registration?Name="+n+"&Emailid="+r+"&PhNum="+ph+"&gender="+pa+"&password="+pwd+"&userid="+uid+"";                              
				urlString = "http://ec2-54-148-114-230.us-west-2.compute.amazonaws.com:8080/GroovyServer/Registration?Name="+n+"&Emailid="+r+"&PhNum="+ph+"&gender="+pa+"&password="+pwd+"&userid="+uid+"";                              
				
				MyTask m = new MyTask();
				m.execute(urlString);
			}
		});
				
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
				Intent i = new Intent(registration.this,Login.class);
				startActivity(i);
			}
			else 
			{
				Toast.makeText(getApplicationContext(),"Register with different details", Toast.LENGTH_LONG).show();
			}
		}
		
	}

}
