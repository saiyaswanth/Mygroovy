package com.Garage.groovy;



import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Booking extends Activity
{
	Button b;
	static String urlString;
	@Override
	protected void onStart() 
	{
		// TODO Auto-generated method stub
		super.onStart();
		setContentView(R.layout.bking);
		b = (Button)findViewById(R.id.button1);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				int amount = 200;
				String customerid = "qwe";
				int saloonid = 1;
				int year=2015,year2=2015,month=2,month2=2,day=2,day2=2,hour=1,hour2=1,minute=0,minute2=30;
		//		urlString = "http://10.0.2.2:5000/GroovyServer/Booking?amount="+amount+"&customerid="+customerid+"&saloonid="+saloonid+"&year="+year+"&month="+month+"&day="+day+"&hour="+hour+"&minute="+minute+"&year2="+year2+"&month2="+month2+"&day2="+day2+"&hour2="+hour2+"&minute2="+minute2+"";                 

				urlString = "http://ec2-54-148-114-230.us-west-2.compute.amazonaws.com:8080/GroovyServer/Booking?amount="+amount+"&customerid="+customerid+"&saloonid="+saloonid+"&year="+year+"&month="+month+"&day="+day+"&hour="+hour+"&minute="+minute+"&year2="+year2+"&month2="+month2+"&day2="+day2+"&hour2="+hour2+"&minute2="+minute2+"";                 

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
			switch (result) {
			case "1":
				Toast.makeText(getApplicationContext(),"yup done", Toast.LENGTH_LONG).show();
				break;
			case "2":
				Toast.makeText(getApplicationContext(),"No Money", Toast.LENGTH_LONG).show();
				break;
			case "3":
				Toast.makeText(getApplicationContext(),"house full...try another slot", Toast.LENGTH_LONG).show();
				break;
			default:
				Toast.makeText(getApplicationContext(),"booking failed ", Toast.LENGTH_LONG).show();
				break;
			}
			
			
		}
		
	}


}
