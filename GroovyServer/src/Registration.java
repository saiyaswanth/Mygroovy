

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		Connection connection=null;
		PrintWriter out = response.getWriter();
		 try{
			 
			 
			 	connection=DataBaseConnectionClass.getConnection();
			 	
			 	/*
			 	 String dbquery = "use groovy_mg";
				PreparedStatement ps = connection.prepareStatement(dbquery);
				ps.executeUpdate();
			 	*/
				String Name = request.getParameter("Name").toString();
				String rollno = request.getParameter("Emailid").toString();
				String phnum = request.getParameter("PhNum").toString();
				String gender = request.getParameter("gender").toString();
				String password = request.getParameter("password").toString();
				String userid = request.getParameter("userid").toString();
				
				int wa = 0;
				
				PreparedStatement pst = connection.prepareStatement("insert into groovyusers values(?,?,?,?,?,?,?)");
				pst.setString(2,Name);
				pst.setString(3,rollno);
				pst.setString(4,phnum);
				pst.setString(5,gender);
				pst.setString(6,password);
				pst.setString(1,userid);
				pst.setInt(7, wa);
				
				out.print(pst.executeUpdate());
				
				}
				catch(Exception e)
			{
				e.printStackTrace();
			}finally{
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
