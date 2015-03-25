

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
 * Servlet implementation class RetrieveCusDetails
 */
@WebServlet("/RetrieveCusDetails")
public class RetrieveCusDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveCusDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userid = request.getParameter("userid").toString();
		Connection connection=null;
		ResultSet rs=null;
		
		System.out.println(userid);
		
		String retriveQuery="select * from groovyusers where userid = ? ";
		try{
			connection=DataBaseConnectionClass.getConnection();
			PreparedStatement pst = connection.prepareStatement(retriveQuery);
			pst.setString(1	, userid);
			rs=pst.executeQuery();
			PrintWriter writer=response.getWriter();
			//JSONObject object=new JSONObject();
			JSONArray array=new JSONArray();
			if(rs.next())
			{ 
				String Name = rs.getString(2);
				String emailid = rs.getString(3);
				String phnum = rs.getString(4);
				JSONObject object=new JSONObject();
				object.put("Name",Name);
				array.put(object);
				JSONObject objec=new JSONObject();
				object.put("emailid",emailid);
				array.put(objec);
				JSONObject obje=new JSONObject();
				object.put("phnum",phnum);
				array.put(obje);
			System.out.print("done");
				writer.print(array.toString());
				
			}
			else
			{
				System.out.print("unregistered user id");
				writer.print(0);
			}
			}catch(Exception e)
			{
				System.out.println(e);
			}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
