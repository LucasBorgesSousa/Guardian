<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="model.Celular" %>

<html>

<head>

    <title>Meu Celular - Guardian</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

<nav class="navbar">

    <h1>GUARDIAN</h1>

    <div class="navbar-links">

        <a href="inicio">Início</a>

        <a href="usuario">Meu Perfil</a>

        <a href="celular">Meu Celular</a>

        <a href="logout">Sair</a>

    </div>

</nav>


<div class="container">

    <%
        String erro = (String) request.getAttribute("erro");

        if (erro != null) {
    %>

        <div class="erro">
            <%= erro %>
        </div>

    <%
        }
    %>


    <div class="card">

        <h2>Meu celular</h2>

        <div class="table-container">

            <table>

                <tr>

                    <th>ID</th>
                    <th>Marca</th>
                    <th>Modelo</th>
                    <th>IMEI</th>
                    <th>Status</th>
                    <th>Ações</th>

                </tr>

                <%
                    List<Celular> celulares =
                        (List<Celular>) request.getAttribute("celulares");

                    for (Celular celular : celulares) {
                %>

                <tr>

                    <td>
                        <%= celular.getId() %>
                    </td>

                    <td>
                        <%= celular.getMarca() %>
                    </td>

                    <td>
                        <%= celular.getModelo() %>
                    </td>

                    <td>
                        <%= celular.getIMEI() %>
                    </td>

                    <td>
                        <%= celular.getStatusCelular() %>
                    </td>

                    <td>

                        <form action="celular"
                              method="post"
                              style="display:inline;">

                            <input
                                type="hidden"
                                name="id"
                                value="<%= celular.getId() %>">

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


                        <form action="celular"
                              method="post"
                              style="display:inline;">

                            <input
                                type="hidden"
                                name="id"
                                value="<%= celular.getId() %>">

                            <input
                                type="hidden"
                                name="acao"
                                value="excluir">

                            <button
                                type="submit"
                                class="btn-danger"
                                onclick="return confirm('Tem certeza que deseja excluir este aparelho?');">

                                Excluir

                            </button>

                        </form>


                        <button
                            type="button"
                            class="btn"
                            onclick="alert('Comando de bloqueio enviado para o aparelho.');">

                            🔒 Travar aparelho

                        </button>


                        <button
                            type="button"
                            class="btn"
                            onclick="alert('Solicitação de localização enviada. Localização disponível: não disponível.');">

                            📍 Localizar aparelho

                        </button>

                    </td>

                </tr>

                <%
                    }
                %>

            </table>

        </div>

    </div>


    <%
        Celular celularEditar =
            (Celular) request.getAttribute("celularEditar");

        if (celularEditar != null) {
    %>

    <div class="card">

        <h2>Editar celular</h2>

        <form action="celular" method="post">

            <input
                type="hidden"
                name="id"
                value="<%= celularEditar.getId() %>">

            <div class="form-grid">

                <div>

                    <label>Marca</label>

                    <input
                        type="text"
                        name="marca"
                        value="<%= celularEditar.getMarca() %>"
                        required>

                </div>


                <div>

                    <label>Modelo</label>

                    <input
                        type="text"
                        name="modelo"
                        value="<%= celularEditar.getModelo() %>"
                        required>

                </div>


                <div>

                    <label>IMEI</label>

                    <input
                        type="text"
                        name="IMEI"
                        value="<%= celularEditar.getIMEI() %>"
                        required>

                </div>


                <div>

                    <label>Status</label>

                    <select
                        name="statusCelular"
                        required>

                        <option
                            value="ATIVO"
                            <%= "ATIVO".equalsIgnoreCase(
                                celularEditar.getStatusCelular())
                                ? "selected"
                                : "" %>>

                            ATIVO

                        </option>

                        <option
                            value="INATIVO"
                            <%= "INATIVO".equalsIgnoreCase(
                                celularEditar.getStatusCelular())
                                ? "selected"
                                : "" %>>

                            INATIVO

                        </option>

                    </select>

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


    <div class="card">

        <h2>Cadastrar meu celular</h2>

        <form action="celular" method="post">

            <div class="form-grid">

                <div>

                    <label>Marca</label>

                    <input
                        type="text"
                        name="marca"
                        required>

                </div>


                <div>

                    <label>Modelo</label>

                    <input
                        type="text"
                        name="modelo"
                        required>

                </div>


                <div>

                    <label>IMEI</label>

                    <input
                        type="text"
                        name="IMEI"
                        required>

                </div>


                <div>

                    <label>Status</label>

                    <select
                        name="statusCelular"
                        required>

                        <option value="ATIVO">
                            ATIVO
                        </option>

                        <option value="INATIVO">
                            INATIVO
                        </option>

                    </select>

                </div>

            </div>

            <br>

            <button
                type="submit"
                class="btn">

                Cadastrar meu celular

            </button>

        </form>

    </div>

</div>

</body>

</html>