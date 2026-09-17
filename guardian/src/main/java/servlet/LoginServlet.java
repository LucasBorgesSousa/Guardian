package servlet;

import java.io.IOException;

import controller.UsuarioController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Usuario;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        UsuarioController controller = new UsuarioController();

        Usuario usuario = controller.login(email, senha);

        if (usuario != null) {

            HttpSession session = request.getSession();

            session.setAttribute("usuario", usuario);

            response.sendRedirect("inicio");

        } else {

            request.setAttribute(
                "erro",
                "E-mail ou senha inválidos."
            );

            request.getRequestDispatcher("/login.jsp").forward(
                request,
                response
            );
        }
    }
}