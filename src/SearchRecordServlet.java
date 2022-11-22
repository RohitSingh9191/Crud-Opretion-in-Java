import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/search-record")
public class SearchRecordServlet extends HttpServlet 
{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		String eid=request.getParameter("eid");
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/crud","root","12345");
			String query="select * from employeinfo where eid=?";
			PreparedStatement ps=cn.prepareStatement(query);
			ps.setString(1,eid);
			ResultSet rst=ps.executeQuery();
			if(rst.next())
			{
				pw.println("<table border='1' style='border-collapse:collapse;font-size:20px;width:40%'>");
				pw.println("<tr style='background-color:orange;color:white'><th colspan='2' align='center'>Emoloyee Details</th></tr>");
				pw.println("<tr>");
				pw.println("<th align='left'>Employee Id</th>");
				pw.println("<td>"+rst.getString(1)+"</td>");
				pw.println("</tr>");
				pw.println("<tr>");
				pw.println("<th align='left'>Employee First Name</th>");
				pw.println("<td>"+rst.getString(2)+"</td>");
				pw.println("</tr>");
				pw.println("<tr>");
				pw.println("<th align='left'>Employee Last Name</th>");
				pw.println("<td>"+rst.getString(3)+"</td>");
				pw.println("</tr>");
				pw.println("<tr>");
				pw.println("<th align='left'>Employee Phone Number</th>");
				pw.println("<td>"+rst.getString(4)+"</td>");
				pw.println("</tr>");
				pw.println("<tr>");
				pw.println("<th align='left'>Employee Gmail Id</th>");
				pw.println("<td>"+rst.getString(5)+"</td>");
				pw.println("</tr>");
				pw.println("</table>");
				pw.println("<br>");
			}
			else
			{
				pw.println("<h2 style='color:red'>Employee with id "+eid+" not found</h2>");
			}
			pw.println("<a href='/employeeinfo-crud'>Home page</a>");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
}
