package servlet;

import java.io.IOException;

import controller.UsuarioController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Usuario;

public class UsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        UsuarioController controller =
            new UsuarioController();

        HttpSession session =
            request.getSession(false);

        if (session == null ||
            session.getAttribute("usuario") == null) {

            response.sendRedirect("login");
            return;
        }

        Usuario usuarioLogado =
            (Usuario) session.getAttribute("usuario");

        int idUsuarioLogado =
            usuarioLogado.getId();

        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String acao = request.getParameter("acao");

        try {

            /*
             * EXCLUIR
             */
            if ("excluir".equals(acao)) {

                int idUsuario =
                    Integer.parseInt(id);

                // Impede excluir outro usuário
                if (idUsuario != idUsuarioLogado) {

                    response.sendRedirect("usuario");
                    return;
                }

                controller.excluir(idUsuario);

                session.invalidate();

                response.sendRedirect("login");

                return;
            }

            /*
             * EDITAR
             */
            if ("editar".equals(acao)) {

                int idUsuario =
                    Integer.parseInt(id);

                // Impede editar outro usuário
                if (idUsuario != idUsuarioLogado) {

                    response.sendRedirect("usuario");
                    return;
                }

                Usuario usuario =
                    controller.buscarPorId(idUsuario);

                request.setAttribute(
                    "usuarioEditar",
                    usuario
                );

                doGet(request, response);

                return;
            }


            if (id == null || id.isEmpty()) {

                response.sendRedirect("usuario");
                return;
            }

            int idUsuario =
                Integer.parseInt(id);


            if (idUsuario != idUsuarioLogado) {

                response.sendRedirect("usuario");
                return;
            }

            Usuario usuario =
                new Usuario(
                    idUsuario,
                    nome,
                    email,
                    senha
                );

            controller.atualizar(usuario);

            session.setAttribute(
                "usuario",
                usuario
            );

            response.sendRedirect("usuario");

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute(
                "erro",
                e.getMessage()
            );

            doGet(request, response);
        }
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        UsuarioController controller =
            new UsuarioController();

        HttpSession session =
            request.getSession(false);

        if (session == null ||
            session.getAttribute("usuario") == null) {

            response.sendRedirect("login");
            return;
        }

        Usuario usuarioLogado =
            (Usuario) session.getAttribute("usuario");

        int idUsuarioLogado =
            usuarioLogado.getId();


        Usuario usuario =
            controller.buscarPorId(idUsuarioLogado);

        request.setAttribute(
            "usuario",
            usuario
        );

        request.getRequestDispatcher(
            "/usuarios.jsp"
        ).forward(
            request,
            response
        );
    }
}