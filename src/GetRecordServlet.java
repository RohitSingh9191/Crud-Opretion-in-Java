import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/get-record")
public class GetRecordServlet extends HttpServlet 
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
				pw.println("<form action='update-record' method='post'>");
				pw.println("<table border='1' style='border-collapse:collapse;font-size:20px;width:40%'>");
				pw.println("<tr style='background-color:orange;color:white'><th colspan='2' align='center'>Employee Details</th></tr>");
				pw.println("<tr>");
				pw.println("<th align='left'>Employee Id</th>");
				pw.println("<td><input type='text' value='"+rst.getString(1)+"' readonly name='eid' style='font-size:18px;width:100%'></td>");
				pw.println("</tr>");
				pw.println("<tr>");
				pw.println("<th align='left'>Edit Employee First Name</th>");
				pw.println("<td><input type='text' value='"+rst.getString(2)+"' name='firstname' style='font-size:18px;width:100%'></td>");
				pw.println("</tr>");
				pw.println("<tr>");
				pw.println("<th align='left'>Edit Employee Last Name</th>");
				pw.println("<td><input type='text' value='"+rst.getString(3)+"' name='lastname' style='font-size:18px;width:100%'></td>");
				pw.println("</tr>");
				pw.println("<tr>");
				pw.println("<th align='left'>Edit Employee Phone Number</th>");
				pw.println("<td><input type='text' value='"+rst.getString(4)+"' name='phonenumber' style='font-size:18px;width:100%'></td>");
				pw.println("</tr>");
				pw.println("<th align='left'>Edit Employee Gmail Id</th>");
				pw.println("<td><input type='text' value='"+rst.getString(5)+"' name='emailid' style='font-size:18px;width:100%'></td>");
				pw.println("</tr>");
				pw.println("<tr>");
				pw.println("<td colspan='2' align='right'><button  style='font-size:18px'>Update Record</button></td>");
				pw.println("</tr>");
				pw.println("</table>");
				pw.println("</form>");
			}
			else
			{
				pw.println("<h2 style='color:red'>Product with id "+eid+" not found</h2>");
			}
			pw.println("<a href='/employeeinfo-crud'>Home page</a>");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
}
