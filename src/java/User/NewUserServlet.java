package User;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import newpackage.ConnectionDao;

public class NewUserServlet extends HttpServlet {        
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            //fetch data from registration page
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            //make user object
            User userModel = new User(name, email, password);
            //create a database model
            UserDao regUser = new UserDao(ConnectionDao.getConnection());
            if (regUser.saveUser(userModel)) {
                response.sendRedirect("index.jsp");
            } else {
                String errorMessage = "User Available";
                HttpSession regSession = request.getSession();
                regSession.setAttribute("RegError", errorMessage);
                response.sendRedirect("addNewUser.jsp");
            }
        }
    }
}