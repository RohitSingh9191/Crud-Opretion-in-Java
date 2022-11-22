import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/delete-record")
public class DeleteRcordServlet extends HttpServlet 
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
			String query="delete from employeinfo where eid=?";
			PreparedStatement ps=cn.prepareStatement(query);
			ps.setString(1,eid);
			int n=ps.executeUpdate();
			if(n>=1)
			{
				pw.write("<h2>Product record has been deleted successfully</h2>");
			}
			else
			{
				pw.write("<h2 style='color:red'>Product with id "+eid+" does not exist</h2>");
			}
			pw.println("<a href='/employeeinfo-crud'>Home page</a>");
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
}
