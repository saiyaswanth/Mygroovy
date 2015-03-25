package com.Garage.groovy;



import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class retreivelatlon extends Activity
{
	TextView tv1,tv2;
	static String urlString1;
	public String[] pokemon;
	public String[] pokemon1;
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		setContentView(R.layout.latlon);
		tv1 = (TextView)findViewById(R.id.textView1);
		tv2 = (TextView)findViewById(R.id.textView2);
		String r ="saloons";
		String u ="asdf";
	//	urlString1 = "http://10.0.2.2:5000/GroovyServer/retrievelatlon?tname="+r+"";
		urlString1 = "http://ec2-54-148-114-230.us-west-2.compute.amazonaws.com:8080/GroovyServer/retrievelatlon?tname="+r+"&uid="+u+"";
		
		MyTask m = new MyTask();
		m.execute(urlString1);
	}
	private class MyTask extends AsyncTask<String, Void, String>
	{
		String response;
		public ArrayList<String> info;
		public ArrayList<String> info1;
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
//			Toast.makeText(getActivity(), "gt response as" + result, Toast.LENGTH_LONG).show();
		
			try{
				 JSONArray ary=new JSONArray(result);
					info = new ArrayList<String>();
					info1 = new ArrayList<String>();
					
					Log.d("not","onpostexecute");
					Log.d("len",ary.length()+"");
					for(int i = 0; i < ary.length(); i++) 
					{
						Log.d("r","63");
						if(ary.getJSONObject(i).has("lat"))
						{
							Log.d("r","66");
							String infos=ary.getJSONObject(i).getString("lat");
							info.add(infos);
						}
						if(ary.getJSONObject(i).has("lon"))
						{
							Log.d("r","72");
							String infos=ary.getJSONObject(i).getString("lon");
							info1.add(infos);
						}
						
						Log.d("r","77");
					}		 
					Log.d("not","onpostExectute");
					pokemon = info.toArray(new String[info.size()]);
					pokemon1 = info1.toArray(new String[info1.size()]);
					
					//					NotificationFragment n = new NotificationFragment();
					
					Toast.makeText(getApplicationContext(),"length is "+pokemon.length, Toast.LENGTH_LONG).show();

					int i =0;
					for(i=0;i<pokemon.length;i++)
					{

						Toast.makeText(retreivelatlon.this,"lat"+pokemon[i], Toast.LENGTH_LONG).show();
						Toast.makeText(retreivelatlon.this,"lon"+pokemon1[i], Toast.LENGTH_LONG).show();
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

