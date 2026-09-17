<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="model.Usuario" %>

<%
    Usuario usuario =
        (Usuario) session.getAttribute("usuario");
%>

<html>

<head>

    <title>Início - Guardian</title>

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

    <div class="card welcome">

        <h2>
            Bem-vindo, <%= usuario.getNome() %>!
        </h2>

        <p>
            Proteja e gerencie seus dispositivos através do Guardian.
        </p>

    </div>


    <div class="menu-cards">

        <div class="menu-card">

            <h3>
                Meu Perfil
            </h3>

            <p>
                Visualize e gerencie seus dados pessoais e sua conta.
            </p>

            <a href="usuario">
                Acessar
            </a>

        </div>


        <div class="menu-card">

            <h3>
                Meu Celular
            </h3>

            <p>
                Gerencie seu aparelho e acesse recursos de proteção.
            </p>

            <a href="celular">
                Acessar
            </a>

        </div>

    </div>

</div>

</body>

</html>