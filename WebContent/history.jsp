<%@ page import="java.util.*,model.Transaction" %>
<%
  List<Transaction> history = (List<Transaction>) request.getAttribute("history");
%>
<html>
<head><title>Transaction History</title></head>
<body>
<h2>Your Transaction History</h2>
<table border="1">
<tr><th>ID</th><th>Type</th><th>Amount</th><th>Date</th></tr>
<%
  if (history != null) {
    for (Transaction t : history) {
%>
<tr>
  <td><%= t.getId() %></td>
  <td><%= t.getType() %></td>
  <td><%= t.getAmount() %></td>
  <td><%= t.getDate() %></td>
</tr>
<%
    }
  }
%>
</table>
<a href="dashboard.jsp">Back to Dashboard</a>
</body>
</html>
