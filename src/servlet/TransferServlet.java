package servlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import db.DBConnection;

public class TransferServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        int userId = (int) req.getSession().getAttribute("userId");
        String receiver = req.getParameter("receiver");
        double amount = Double.parseDouble(req.getParameter("amount"));

        try {
            Connection con = DBConnection.getConnection();
            con.setAutoCommit(false);

            PreparedStatement ps1 = con.prepareStatement("SELECT * FROM users WHERE username=?");
            ps1.setString(1, receiver);
            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {
                int receiverId = rs.getInt("id");

                PreparedStatement deduct = con.prepareStatement("UPDATE users SET balance=balance-? WHERE id=?");
                deduct.setDouble(1, amount);
                deduct.setInt(2, userId);
                deduct.executeUpdate();

                PreparedStatement add = con.prepareStatement("UPDATE users SET balance=balance+? WHERE id=?");
                add.setDouble(1, amount);
                add.setInt(2, receiverId);
                add.executeUpdate();

                PreparedStatement log1 = con.prepareStatement("INSERT INTO transactions (user_id, type, amount) VALUES (?, 'debit', ?)");
                log1.setInt(1, userId);
                log1.setDouble(2, amount);
                log1.executeUpdate();

                PreparedStatement log2 = con.prepareStatement("INSERT INTO transactions (user_id, type, amount) VALUES (?, 'credit', ?)");
                log2.setInt(1, receiverId);
                log2.setDouble(2, amount);
                log2.executeUpdate();

                con.commit();
                res.sendRedirect("dashboard.jsp?msg=success");
            } else {
                res.sendRedirect("transfer.jsp?msg=user_not_found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
