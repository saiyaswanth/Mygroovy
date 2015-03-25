

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
 * Servlet implementation class retrievelatlon
 */
@WebServlet("/retrievelatlon")
public class retrievelatlon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public retrievelatlon() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection connection=null;
		String tname = request.getParameter("tname").toString();
		String uid = request.getParameter("uid").toString();
		//	String tname = "Naddress";
		ResultSet rs=null;
		String grquery = "select * from groovyusers where userid=?";
		String retriveQuery = " select * from "+tname+" where stype= ? or stype = ?";
		try{
		connection=DataBaseConnectionClass.getConnection();
		/*
		String dbquery = "use groovy_mg";
		PreparedStatement ps = connection.prepareStatement(dbquery);
		ps.executeUpdate();
		*/
		PreparedStatement pt = connection.prepareStatement(grquery);
		pt.setString(1, uid);
		ResultSet rss=pt.executeQuery();
		rss.next();
		String gender = rss.getString(5);
		
		PreparedStatement pst = connection.prepareStatement(retriveQuery);
		pst.setString(1, gender);
		String s = "unisex";
		pst.setString(2, s);
		rs=pst.executeQuery();
		PrintWriter writer=response.getWriter();
		JSONArray array=new JSONArray();
		while(rs.next())
		{ 
		Double lat=rs.getDouble(4);
		Double lon=rs.getDouble(5);
		JSONObject object=new JSONObject();
		object.put("lat",lat);
		array.put(object);
		JSONObject objec=new JSONObject();
		objec.put("lon",lon);
		array.put(objec);
		System.out.println(lon);
		System.out.println(lat);
		//array.put(object);
		
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
