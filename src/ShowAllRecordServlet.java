import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/list")
public class ShowAllRecordServlet extends HttpServlet 
{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost/crud","root","12345");
			String query="select * from employeinfo";
			Statement st=cn.createStatement();
			ResultSet rst=st.executeQuery(query);
			pw.println("<table border='1' style='border-collapse:collapse;font-size:20px;width:50%'>");
			pw.println("<tr><th>Employee Id</th><th>Employee First Name</th><th>Employee Last Name</th><th>Employee Phone Number</th><th>Employee Email Id</th></tr>");
			while(rst.next())
			{
				pw.println("<tr>");
				pw.println("<td>"+rst.getString(1)+"</td>");
				pw.println("<td>"+rst.getString(2)+"</td>");
				pw.println("<td>"+rst.getString(3)+"</td>");
				pw.println("<td>"+rst.getString(4)+"</td>");
				pw.println("<td>"+rst.getString(5)+"</td>");
				pw.println("</tr>");
			}
			pw.println("</table><br><br>");
			pw.println("<a href='/employeeinfo-crud'>Home page</a>");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
}
