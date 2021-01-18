    package Admin;

    import java.io.IOException;
    import java.io.PrintWriter;
    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import javax.servlet.http.HttpSession;
    import newpackage.ConnectionDao;

    public class LoginAdminServlet extends HttpServlet {  
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doPost(request, response);
        }
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            try (PrintWriter out = response.getWriter()) {
                String logEmail = request.getParameter("email");
                String logPass = request.getParameter("password");

                AdminDao db = new AdminDao(ConnectionDao.getConnection());
                Admin admin = db.logAdmin(logEmail, logPass);
                if (admin != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("logAdmin", admin);
                    response.sendRedirect("adminWelcomePage.jsp");
                } else {
                    out.print("Admin doesn't exist.");
                    response.sendRedirect("admin.jsp");
                }    
            }
        }
    }