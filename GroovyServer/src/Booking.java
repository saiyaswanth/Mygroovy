

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Booking
 */
@WebServlet("/Booking")
public class Booking extends HttpServlet {
	
	public java.sql.Timestamp maketimestamp(int year,int month,int day,int hour,int minute,int second,int millisecond)
	{
		
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DATE, day);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		cal.set(Calendar.MILLISECOND, millisecond);
		
		return new java.sql.Timestamp(cal.getTimeInMillis());
		
	}
	
	
	
	
	private static final long serialVersionUID = 1L;
	static int i ;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Booking() {
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
			 	System.out.println(66);
			 	String y = request.getParameter("year").toString();
				int year = Integer.parseInt(y);
				String m = request.getParameter("month").toString();
				int month = Integer.parseInt(m);
				String d = request.getParameter("day").toString();
				int day = Integer.parseInt(d);
				String h = request.getParameter("hour").toString();
				int hour = Integer.parseInt(h);
				String mi = request.getParameter("minute").toString();
				int minute = Integer.parseInt(mi);
			 	System.out.println(77);

			 	String y2 = request.getParameter("year2").toString();
				int year2 = Integer.parseInt(y2);
				String m2 = request.getParameter("month2").toString();
				int month2 = Integer.parseInt(m2);
				String d2 = request.getParameter("day2").toString();
				int day2 = Integer.parseInt(d2);
				String h2 = request.getParameter("hour2").toString();
				int hour2 = Integer.parseInt(h2);
				String mi2 = request.getParameter("minute2").toString();
				int minute2 = Integer.parseInt(mi2);

			 	System.out.println(90);
			 
			 	String amount = request.getParameter("amount").toString();
				int amt = Integer.parseInt(amount);
				String cid = request.getParameter("customerid").toString();
				String saloonid = request.getParameter("saloonid").toString();
				int sid = Integer.parseInt(saloonid); 
				
			 	System.out.println(98);

				
				connection=DataBaseConnectionClass.getConnection();
				/*
				String dbquery = "use groovy_mg";
				PreparedStatement ps = connection.prepareStatement(dbquery);
				ps.executeUpdate();
				*/

				
				String countQuery = "select count(*) from booking";
				PreparedStatement ptt = connection.prepareStatement(countQuery);
				ResultSet rss=ptt.executeQuery();
				rss.next();
				i = rss.getInt(1);
				i++;
				
				
				String retriveQuery = "select * from groovyusers where userid='"+cid+"'";
				PreparedStatement pst = connection.prepareStatement(retriveQuery);
				ResultSet rs=pst.executeQuery();
				System.out.println(105);
				rs.next();
				int wa = rs.getInt(7);
				if(amt<=wa)
				{
				
				wa=wa-amt;
			 	System.out.println(112);
				PreparedStatement p = connection.prepareStatement("update groovyusers set walletamount= ? where userid= ? ");
				p.setInt(1,wa);
				p.setString(2, cid);
				p.executeUpdate();
			 	System.out.println(116);

				Timestamp t = maketimestamp(year, month, day, 0, 0, 0, 0);
				Timestamp t1 = maketimestamp(year, month, day, hour, minute, 0, 0);
				Timestamp t2 = maketimestamp(year2, month2, day2, hour2, minute2, 0, 0);
				
			 	System.out.println(122);
				retriveQuery = "select count(*) from booking where saloonid=? and bookeddate=? and bookedstartt>=? and bookedendt<=?";
			 	PreparedStatement pst2 = connection.prepareStatement(retriveQuery);
				pst2.setInt(1, sid);
				pst2.setTimestamp(2, t);
				pst2.setTimestamp(3, t1);
				pst2.setTimestamp(4, t2);

				ResultSet rs2=pst2.executeQuery();
				System.out.println(126);
				rs2.next();
			 	
			 	System.out.println(126);
				int bookedcount = rs2.getInt(1);
				
			 	System.out.println(129);
			 	retriveQuery = "select * from saloons where saloonid="+sid+"";
			 	PreparedStatement pst3 = connection.prepareStatement(retriveQuery);
				ResultSet rs3=pst3.executeQuery();
				System.out.println(126);
				rs3.next();
			 	System.out.println(133);
				int seatcount = rs3.getInt(9);
				
				System.out.println(bookedcount);
				System.out.println(seatcount);
				
				
				if(bookedcount<=seatcount)
				{
				
				 	System.out.println(139);
					PreparedStatement pst4 = connection.prepareStatement("insert into booking values(?,?,?,?,?,?,?,?)");
					pst4.setInt(1,i);
					pst4.setTimestamp(2, new java.sql.Timestamp(System.currentTimeMillis()));
					pst4.setTimestamp(3, t1);
					pst4.setTimestamp(4, t2);
					pst4.setInt(5,amt);
					pst4.setString(6,cid);
					pst4.setInt(7,sid);
					pst4.setTimestamp(8,t);
				 	System.out.println(149);
					out.print(pst4.executeUpdate());
				
				}
				else
				{
				 	System.out.println(155);
					out.print(3);
				}
				
				
				}
				else
				{
				 	System.out.println(163);
					out.print(2);
				}
				
				
				
				
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
