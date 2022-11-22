import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/save-record")
public class SaveRecordServlet extends HttpServlet 
{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		String eid=request.getParameter("t1");
		String firstname=request.getParameter("t2");
		String lastname=request.getParameter("t3");
		String phone=request.getParameter("t4");
		String email=request.getParameter("t5");
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/crud","root","12345");
			String query="insert into employeinfo values(?,?,?,?,?)";
			PreparedStatement ps=cn.prepareStatement(query);
			ps.setString(1,eid);
			ps.setString(2,firstname);
			ps.setString(3,lastname);
			ps.setString(4,phone);
			ps.setString(5,email);
			ps.executeUpdate();
			pw.write("<h2>Employee record has been inserted successfully</h2>");
			pw.println("<a href='/employeeinfo-crud'>Home page</a>");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
}
