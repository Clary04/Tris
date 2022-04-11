<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.models.PacienteModel" %>
<%@ page import="java.lang.String"%>
<% ArrayList<PacienteModel> lista = (ArrayList<PacienteModel>) request.getAttribute("pacientes");%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List</title>

    <!-- page icon -->
    <link rel="icon" type="image/png" href="http://localhost:8080/Tris/static/img/tris-logo-blue.png">

    <!-- link css  -->
    <link rel="stylesheet" href="http://localhost:8080/Tris/static/css/list.css">

    <!-- javascript  -->
    <script src="http://localhost:8080/Tris/static/js/index.js"></script>

    <!-- fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Fira+Code:wght@300;400;500&family=League+Spartan:wght@100;200;300&family=Roboto:ital,wght@0,100;0,300;0,400;0,500;1,100;1,300;1,400&family=Ubuntu:wght@300&display=swap" rel="stylesheet"> 

</head>
<body>
    <main>
        <!--  pt-br
        JSP
        arquivo para receber a lista de pacientes
        Na listagem será passado
        (COR DO QUADRO) Nome - cpf - data de nascimento - sus - quadro de saúde -
        descrição do quadro - email  
        link para editar 
        link para remover 
     -->
        <h1>
            Lista de pacientes
        </h1>  
      <a href="/Tris/create"><button class="btn btn-primary">Voltar</button></a>  
        <table>
            <colgroup>
                <col class="cor">
                <col class="nome">
                <col class="cpf">
                <col class="nascimento">
                <col class="sus">
                <col class="quadro">
                <col class="descrição">
                <col class="email">
            </colgroup>
            <thead>
                <tr>
                    <th>COR</th>
                    <th>Nome</th>
                    <th>Cpf</th>
                    <th>SUS</th>
                    <th>Quadro</th>
                    <th>Descrição</th>
                    <th>Email</th>
                    <th>Editar</th>
                    <th>Remover</th>
                </tr>
            </thead>
            <tbody>
            	<% String style= "blue";%>
    			<%for(int i=0; i<lista.size(); i++){ %>
                <tr class="list_of_patients">
                	<%if(lista.get(i).getQuadro().equals("leve")){
                		style = "#00bef7";
                	} else if(lista.get(i).getQuadro().equals("pouco_urgente")){
                		style = "#29ff62";
                	} else if(lista.get(i).getQuadro().equals("urgente")){
                		style = "#ed9b2f";
                	} else if(lista.get(i).getQuadro().equals("muito_urgente")){
                		style = "#f52f2f";
                	}%>
                    <td class="cor_triagem" bgcolor=<%=style%>>
                    </td>
                    <td>
                    <%=lista.get(i).getNome()%>
                    </td>
                    <td>
                    <%=lista.get(i).getCpf()%>
                    </td>
                    <td>
                    <%=lista.get(i).getSus()%>
                    </td>
                    <td class="quadro_triagem">
                    <%=lista.get(i).getQuadro()%>
                    </td>
                    <td>
                    <%=lista.get(i).getDescricao()%>
                    </td>
                    <td>
                    <%=lista.get(i).getEmail()%>
                    </td>
                    <td class="editar_triagem"><a href="?action=update_patient&id=<%=lista.get(i).getId()%>">editar</a></td>
                    <td class="remover_triagem"><a href="?action=delete_patient&id=<%=lista.get(i).getId()%>">remover</a></td>
                <%}%>
                </tr>
            </tbody>
        </table>
    </main>
     <!-- Footer -->
   <footer class="about" id="about" onclick="indexAccessRedirect();">
    <div class="logo-div">
        <div class="logo-img">
            <img src="http://localhost:8080/Tris/static/img/tris-logo.png" alt="Tris Hospitalar Triage - Logo">
        </div>
        <div class="logo-text">
            <h1>Tris</h1>
        </div>
    </div>
    <div class="about-devs">
        <i class="fa-brands fa-github"></i>
        <a href="https://github.com/Clary04">Clarissa Vanderley</a>
        <a href="https://github.com/GabPhoenix">Gabriel Carvalho</a>
        <p>all rights reserved <span>&reg;</span></p>
    </div>

</footer>
<!-- end footer section -->

</body>
</html>