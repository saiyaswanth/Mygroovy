

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class Nserdb
 */
@WebServlet("/Nserdb")
public class Nserdb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Nserdb() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.print("reached");
		Connection connection=null;
		String tname = request.getParameter("tname").toString();
		
		//String tname="service";
	//	int id=0;
		System.out.print("reached"+"41");
		int id = Integer.parseInt(request.getParameter("id"));
	//	id++;
		System.out.print(id);
//		response.setContentType("text/html");
		ResultSet rs=null;
		String retriveQuery = " select * from service where serviceid="+id+"";
		try{
		connection=DataBaseConnectionClass.getConnection();
		/*
		String dbquery = "use groovy_mg";
		PreparedStatement ps = connection.prepareStatement(dbquery);
		ps.executeUpdate();
		*/
		PreparedStatement pst = connection.prepareStatement(retriveQuery);
		rs=pst.executeQuery();
		PrintWriter writer=response.getWriter();
		JSONArray array=new JSONArray();
		while(rs.next())
		{ 
		String Name=rs.getString(2);
		System.out.println(Name);
		
		JSONObject object=new JSONObject();
		object.put("Name",Name);
		JSONObject objectt=new JSONObject();
		int f = rs.getInt(3);
		objectt.put("f",f);
		
		array.put(object);
		array.put(objectt);
		}
		writer.print(array.toString());
		}catch(Exception e)
		{
			System.out.print(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
