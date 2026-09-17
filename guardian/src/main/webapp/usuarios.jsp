<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="model.Usuario" %>

<html>

<head>

    <title>Meu Perfil - Guardian</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

<nav class="navbar">

    <h1>
        GUARDIAN
    </h1>

    <div class="navbar-links">

        <a href="inicio">
            Início
        </a>

        <a href="usuario">
            Meu Perfil
        </a>

        <a href="celular">
            Meu Celular
        </a>

        <a href="logout">
            Sair
        </a>

    </div>

</nav>


<div class="container">

    <div class="card">

        <h2>
            Meu perfil
        </h2>

        <div class="table-container">

            <table>

                <tr>

                    <th>ID</th>
                    <th>Nome</th>
                    <th>E-mail</th>
                    <th>Ações</th>

                </tr>

                <%
                    Usuario usuario =
                        (Usuario) request.getAttribute("usuario");
                %>

                <tr>

                    <td>
                        <%= usuario.getId() %>
                    </td>

                    <td>
                        <%= usuario.getNome() %>
                    </td>

                    <td>
                        <%= usuario.getEmail() %>
                    </td>

                    <td>

                        <form action="usuario"
                              method="post"
                              style="display:inline;">

                            <input
                                type="hidden"
                                name="id"
                                value="<%= usuario.getId() %>">

                            <input
                                type="hidden"
                                name="acao"
                                value="editar">

                            <button
                                type="submit"
                                class="btn-edit">

                                Editar

                            </button>

                        </form>


                        <form action="usuario"
                              method="post"
                              style="display:inline;">

                            <input
                                type="hidden"
                                name="id"
                                value="<%= usuario.getId() %>">

                            <input
                                type="hidden"
                                name="acao"
                                value="excluir">

                            <button
                                type="submit"
                                class="btn-danger"
                                onclick="return confirm('Tem certeza que deseja excluir sua conta?');">

                                Excluir

                            </button>

                        </form>

                    </td>

                </tr>

            </table>

        </div>

    </div>


    <%
        Usuario usuarioEditar =
            (Usuario) request.getAttribute("usuarioEditar");

        if (usuarioEditar != null) {
    %>

    <div class="card">

        <h2>
            Editar meu perfil
        </h2>

        <form action="usuario" method="post">

            <input
                type="hidden"
                name="id"
                value="<%= usuarioEditar.getId() %>">

            <div class="form-grid">

                <div>

                    <label>
                        Nome
                    </label>

                    <input
                        type="text"
                        name="nome"
                        value="<%= usuarioEditar.getNome() %>"
                        required>

                </div>


                <div>

                    <label>
                        E-mail
                    </label>

                    <input
                        type="email"
                        name="email"
                        value="<%= usuarioEditar.getEmail() %>"
                        required>

                </div>


                <div>

                    <label>
                        Senha
                    </label>

                    <input
                        type="password"
                        name="senha"
                        value="<%= usuarioEditar.getSenha() %>"
                        required>

                </div>

            </div>

            <br>

            <button
                type="submit"
                class="btn">

                Salvar alterações

            </button>

        </form>

    </div>

    <%
        }
    %>

</div>

</body>

</html>