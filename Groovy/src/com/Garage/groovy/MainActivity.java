package com.Garage.groovy;


import java.util.ArrayList;

import org.json.JSONArray;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity 
{
	String urlString;
	ListView lv;
	Button req;
	public String[] pokemon;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		req = (Button)findViewById(R.id.button1);
		lv = (ListView)findViewById(R.id.listView1);
		req.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 String i = getIntent().getExtras().getString("uid");
							
				String tabname="saloons";
//				urlString="http://10.0.2.2:5000/GroovyServer/NLocdb?tname="+tabname+"";
				urlString="http://ec2-54-148-114-230.us-west-2.compute.amazonaws.com:8080/GroovyServer/NLocdb?tname="+tabname+"&uid="+i+"";
				MyTask m = new MyTask();
				m.execute(urlString);
				
				
			}
		});
		
	}


	public class MyTask extends AsyncTask<String, String, String>
	{
	String response;
	
	public ArrayList<String> info;
	ArrayAdapter<String> adapter;
	/*	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
//		adapter = (ArrayAdapter<String>)getListAdapter();
	}
	*/
	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub
			 response=ConnectionClass.send(urlString);
				Log.d("not","doInBackground");
		return response;
	}
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
//		Toast.makeText(getActivity(), "gt response as" + result, Toast.LENGTH_LONG).show();
	
		try{
			 JSONArray ary=new JSONArray(result);
				info = new ArrayList<String>();
				Log.d("not","onpostexecute");
				for(int i = 0; i < ary.length(); i++) 
				{
					if(ary.getJSONObject(i).has("Name"))
					{
						String infos=ary.getJSONObject(i).getString("Name");
						info.add(infos);
					}
				
				}		 
				Log.d("not","onpostExectute");
				pokemon = info.toArray(new String[info.size()]);
//				NotificationFragment n = new NotificationFragment();
				MainActivity m = new MainActivity();
				
				Toast.makeText(getApplicationContext(),"length is "+pokemon.length, Toast.LENGTH_LONG).show();

				Toast.makeText(MainActivity.this,pokemon[1], Toast.LENGTH_LONG).show();
		

				int i =0;
				for(i=0;i<pokemon.length;i++)
				{

					Toast.makeText(MainActivity.this,pokemon[i], Toast.LENGTH_LONG).show();

					Log.d("pokedata",pokemon[i]);
				
					//m.putPoke(pokemon[i]);
				}		
				
				ArrayAdapter<String> a = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,pokemon);
				lv.setAdapter(a);	
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "asdf", Toast.LENGTH_LONG).show();
				Intent i = new Intent(MainActivity.this,ServiceDisplay.class);
				i.putExtra("saloon", arg2);
				
				startActivity(i);
			}
			
		});		
		}catch(Exception e)
		{
			System.out.print(e);
		}
		
	}
	

}
	int f=0;
	private void putPoke(String string) {
		// TODO Auto-generated method stub
		
		Log.d("data recieved is ",string);
		
		pokemon[f++]=string;
		
		Log.d("not","onputpoke");
		
	}
	
	
}
