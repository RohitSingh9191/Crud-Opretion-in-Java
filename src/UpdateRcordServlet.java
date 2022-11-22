import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/update-record")
public class UpdateRcordServlet extends HttpServlet 
{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		String id=request.getParameter("eid");
		String fn=request.getParameter("firstname");
		String ln=request.getParameter("lastname");
		String pn=request.getParameter("phonenumber");
		String gm=request.getParameter("emailid");
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/crud","root","12345");
			String query="update employeinfo set firstname=?,lastname=?,phonenumber=?,emailid=? where eid=?";
			PreparedStatement ps=cn.prepareStatement(query);
			ps.setString(5,id);
			ps.setString(1,fn);
			ps.setString(2,ln);
			ps.setString(3,pn);
			ps.setString(4,gm);
			ps.executeUpdate();
			pw.write("<h2>Employee record has been updated successfully</h2>");
			pw.println("<a href='/employeeinfo-crud'>Home page</a>");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
}
