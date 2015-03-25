package com.Garage.groovy;

import java.util.ArrayList;

import org.json.JSONArray;


import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class RetrieveCusDetails extends Activity
{
	static String urlString;
	public String[] pokemon;
	public String[] pokemon1;
	public String[] pokemon2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.retrivecusdetail);
		String r ="parth";
		urlString= "http://ec2-54-148-114-230.us-west-2.compute.amazonaws.com:8080/GroovyServer/RetrieveCusDetails?userid="+r+"";
		MyTask m = new MyTask();
		m.execute(urlString);

	}
	private class MyTask extends AsyncTask<String, Void, String>
	{
		String response;
		public ArrayList<String> info;
		public ArrayList<String> info1;
		public ArrayList<String> info2;
		
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			Log.d("loginpage","121");
			
			response=ConnectionClass.send(urlString);
			return response;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
//			Toast.makeText(getActivity(), "gt response as" + result, Toast.LENGTH_LONG).show();
		
			try{
				 JSONArray ary=new JSONArray(result);
					info = new ArrayList<String>();
					info1 = new ArrayList<String>();
					info2 = new ArrayList<String>();
					
					Log.d("not","onpostexecute");
					Log.d("len",ary.length()+"");
					for(int i = 0; i < ary.length(); i++) 
					{
						Log.d("r","63");
						if(ary.getJSONObject(i).has("Name"))
						{
							Log.d("r","66");
							String infos=ary.getJSONObject(i).getString("Name");
							info.add(infos);
						}
						if(ary.getJSONObject(i).has("emailid"))
						{
							Log.d("r","72");
							String infos=ary.getJSONObject(i).getString("emailid");
							info1.add(infos);
						}
						if(ary.getJSONObject(i).has("phnum"))
						{
							Log.d("r","72");
							String infos=ary.getJSONObject(i).getString("phnum");
							info2.add(infos);
						}

						Log.d("r","77");
					}		 
					Log.d("not","onpostExectute");
					pokemon = info.toArray(new String[info.size()]);
					pokemon1 = info1.toArray(new String[info1.size()]);
					pokemon2 = info1.toArray(new String[info2.size()]);
					
					//					NotificationFragment n = new NotificationFragment();
					
					Toast.makeText(getApplicationContext(),"length is "+pokemon.length, Toast.LENGTH_LONG).show();

					int i =0;
					for(i=0;i<pokemon.length;i++)
					{

						Toast.makeText(RetrieveCusDetails.this,"Name"+pokemon[i], Toast.LENGTH_LONG).show();
						Toast.makeText(RetrieveCusDetails.this,"emailid"+pokemon1[i], Toast.LENGTH_LONG).show();
						Toast.makeText(RetrieveCusDetails.this,"phnum"+pokemon2[i], Toast.LENGTH_LONG).show();
						
						Log.d("pokedata",pokemon[i]);
					
						//m.putPoke(pokemon[i]);
					}		
					
				
			
			}catch(Exception e)
			{
				System.out.print(e);
			}
			
		}
		
	}
}
