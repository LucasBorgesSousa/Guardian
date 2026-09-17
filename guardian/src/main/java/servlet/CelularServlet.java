package servlet;

import java.io.IOException;
import java.util.List;

import controller.CelularController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Celular;
import model.Usuario;

@WebServlet("/celular")
public class CelularServlet extends HttpServlet {

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        CelularController controller =
            new CelularController();

        HttpSession session =
            request.getSession(false);

        // Segurança: verifica se existe usuário logado
        if (session == null ||
            session.getAttribute("usuario") == null) {

            response.sendRedirect("login");
            return;
        }

        Usuario usuarioLogado =
            (Usuario) session.getAttribute("usuario");

        // ID vem diretamente da sessão
        int usuarioId =
            usuarioLogado.getId();

        String id =
            request.getParameter("id");

        String marca =
            request.getParameter("marca");

        String modelo =
            request.getParameter("modelo");

        String IMEI =
            request.getParameter("IMEI");

        String statusCelular =
            request.getParameter("statusCelular");

        String acao =
            request.getParameter("acao");

        try {

            /*
             * EXCLUIR CELULAR
             */
            if ("excluir".equals(acao)) {

                int idCelular =
                    Integer.parseInt(id);

                Celular celular =
                    controller.buscarPorId(idCelular);

                // Verifica se o celular pertence ao usuário
                if (celular == null ||
                    celular.getUsuarioId() != usuarioId) {

                    response.sendRedirect("celular");
                    return;
                }

                controller.excluir(idCelular);

                response.sendRedirect("celular");

                return;
            }


            /*
             * EDITAR CELULAR
             */
            if ("editar".equals(acao)) {

                int idCelular =
                    Integer.parseInt(id);

                Celular celular =
                    controller.buscarPorId(idCelular);

                // Verifica se o celular pertence ao usuário
                if (celular == null ||
                    celular.getUsuarioId() != usuarioId) {

                    response.sendRedirect("celular");
                    return;
                }

                request.setAttribute(
                    "celularEditar",
                    celular
                );

                doGet(request, response);

                return;
            }


            /*
             * CADASTRAR CELULAR
             *
             * Se não existe ID, significa que é
             * um novo celular.
             */
            if (id == null || id.isEmpty()) {

                Celular celular =
                    new Celular(
                        0,
                        marca,
                        modelo,
                        IMEI,
                        statusCelular,
                        usuarioId
                    );

                controller.cadastrar(celular);

            } else {

                /*
                 * ATUALIZAR CELULAR
                 */

                int idCelular =
                    Integer.parseInt(id);

                Celular celularExistente =
                    controller.buscarPorId(idCelular);

                // Segurança contra alteração de outro usuário
                if (celularExistente == null ||
                    celularExistente.getUsuarioId() != usuarioId) {

                    response.sendRedirect("celular");
                    return;
                }

                Celular celular =
                    new Celular(
                        idCelular,
                        marca,
                        modelo,
                        IMEI,
                        statusCelular,
                        usuarioId
                    );

                controller.atualizar(celular);
            }

            response.sendRedirect("celular");

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

        CelularController controller =
            new CelularController();

        HttpSession session =
            request.getSession(false);

        // Segurança
        if (session == null ||
            session.getAttribute("usuario") == null) {

            response.sendRedirect("login");
            return;
        }

        Usuario usuarioLogado =
            (Usuario) session.getAttribute("usuario");

        int usuarioId =
            usuarioLogado.getId();

        /*
         * Busca todos os celulares normalmente...
         */
        List<Celular> todosCelulares =
            controller.listar();

        /*
         * ...mas envia para a JSP somente os celulares
         * pertencentes ao usuário logado.
         */
        List<Celular> celulares =
            new java.util.ArrayList<>();

        for (Celular celular : todosCelulares) {

            if (celular.getUsuarioId() == usuarioId) {

                celulares.add(celular);
            }
        }

        request.setAttribute(
            "celulares",
            celulares
        );

        request.getRequestDispatcher(
            "/celulares.jsp"
        ).forward(
            request,
            response
        );
    }
}