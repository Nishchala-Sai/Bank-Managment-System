<%@ page import="javax.servlet.http.*,java.sql.*,db.DBConnection" %>
<%
  HttpSession session = request.getSession(false);
  if (session == null || session.getAttribute("username") == null) {
      response.sendRedirect("index.jsp");
      return;
  }
  String username = (String) session.getAttribute("username");
  Connection con = DBConnection.getConnection();
  PreparedStatement ps = con.prepareStatement("SELECT balance FROM users WHERE username=?");
  ps.setString(1, username);
  ResultSet rs = ps.executeQuery();
  double balance = 0;
  if (rs.next()) balance = rs.getDouble("balance");
%>
<html>
<head><title>Dashboard</title></head>
<body>
<h2>Welcome, <%= username %></h2>
<p>Current Balance: ₹<%= balance %></p>
<a href="transfer.jsp">Transfer Money</a><br>
<a href="HistoryServlet">View Transactions</a><br>
<a href="index.jsp">Logout</a>
</body>
</html>
