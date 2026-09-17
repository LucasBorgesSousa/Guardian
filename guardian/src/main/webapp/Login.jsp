<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>

    <title>Login - Guardian</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

<div class="auth-container">

    <div class="auth-box">

        <h1 class="logo">
            GUARDIAN
        </h1>

        <h2>
            Entrar
        </h2>

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

        <form action="login" method="post">

            <div class="form-group">

                <label>
                    E-mail
                </label>

                <input
                    type="email"
                    name="email"
                    required>

            </div>

            <div class="form-group">

                <label>
                    Senha
                </label>

                <input
                    type="password"
                    name="senha"
                    required>

            </div>

            <button
                type="submit"
                class="btn">

                Entrar

            </button>

        </form>

        <div class="auth-link">

            Ainda não possui uma conta?

            <a href="cadastro">
                Cadastre-se
            </a>

        </div>

    </div>

</div>

</body>

</html>