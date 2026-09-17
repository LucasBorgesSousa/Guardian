<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>

<head>

    <title>Cadastro - Guardian</title>

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
            Criar conta
        </h2>

        <form action="cadastro" method="post">

            <div class="form-group">

                <label>
                    Nome
                </label>

                <input
                    type="text"
                    name="nome"
                    required>

            </div>

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

                Criar conta

            </button>

        </form>

        <div class="auth-link">

            Já possui uma conta?

            <a href="login">
                Fazer login
            </a>

        </div>

    </div>

</div>

</body>

</html>