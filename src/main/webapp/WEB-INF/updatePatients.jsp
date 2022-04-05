<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.models.PacienteModel"%>
<% PacienteModel paciente = (PacienteModel) request.getAttribute("paciente");%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Patient</title>

    <!-- page icon -->
    <link rel="icon" type="image/png" href="http://localhost:8080/Tris/static/img/tris-logo-blue.png">

    <!-- link css  -->
    <link rel="stylesheet" href="http://localhost:8080/Tris/static/css/patient.css">

    <!-- javascript -->
    <script src="http://localhost:8080/Tris/static/js/index.js" defer></script>

     <!-- fonts -->
     <link rel="preconnect" href="https://fonts.googleapis.com">
     <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
     <link href="https://fonts.googleapis.com/css2?family=Fira+Code:wght@300;400;500&family=League+Spartan:wght@100;200;300&family=Roboto:ital,wght@0,100;0,300;0,400;0,500;1,100;1,300;1,400&family=Ubuntu:wght@300&display=swap" rel="stylesheet"> 
</head>
<body>
    <main>
        <h1>Editar paciente</h1>
        <section class="form" id="form">
            <form action="update_patient" method="POST">
            	<input type="hidden" name="id" value="<%=paciente.getId()%>">
                <input type="text" name="cpf" placeholder="Informe o CPF" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}" value="<%=paciente.getCpf()%>" required>
                <input type="text" name= "nome" placeholder="Informe o nome" value="<%=paciente.getNome()%>" required>
                <label for="birthdate">Informe a data de nascimento</label>
                <input type="date" name="birthdate">
                <input type="text" name="sus" placeholder="numero do sus" value="<%=paciente.getSus()%>" required>
                <input type="email" name="email" id="email" placeholder="email" value="<%=paciente.getEmail()%>">
                <label for="triagem">Informe o quadro</label>
                <select name="triagem" id="triagem" required>
                    <option value="leve">leve</option>
                    <option value="pouco_urgente">pouco urgente</option>
                    <option value="urgente">urgente</option>
                    <option value="muito_urgente">muito urgente</option>
                </select>
                <label for="descricao">Descrição do quadro</label>
                <textarea name="descricao" id="descricao" cols="35" rows="6" maxlength="5000"><%=paciente.getDescricao()%></textarea>
                <button type="submit" id="update">Editar</button>
            </form>
        </section>
        <img src="../static/img/people_SVG.svg" alt="people">
         <!-- Footer -->
        <footer class="about" id="about" onclick="indexAccessRedirect();">
            <div class="logo-div">
                <div class="logo-img">
                    <img src="../static/img/tris-logo.png" alt="Tris Hospitalar Triage - Logo">
                </div>
                <div class="logo-text">
                    <h1>Tris</h1>
                </div>
            </div>
            <div class="about-devs">
                <i class="fa-brands fa-github"></i>
                <a href="https://github.com/Clary04">Clarissa Wanderley</a>
                <a href="https://github.com/GabPhoenix">Gabriel Carvalho</a>
                <p>all rights reserved <span>&reg;</span></p>
            </div>

        </footer>
    <!-- end footer section -->
    </main>
</body>
</html>