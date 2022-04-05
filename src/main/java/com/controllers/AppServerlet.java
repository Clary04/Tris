package com.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.models.PacienteModel;
import com.models.ProfissionalModel;
import com.models.UsuarioModel;
import com.repositories.Conexao;
import com.repositories.PacienteDao;
import com.repositories.ProfissionalDao;

/**
 * Servlet implementation class AppServerlet
 */
@WebServlet(urlPatterns =  { "/app", "/signin", "/login", "/create", "/list_patients", "/update_patient", "/delete_patient"})

public class AppServerlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PacienteDao pacienteDao = new PacienteDao();
	private ProfissionalDao profissionalDao =  new ProfissionalDao();
    private PacienteModel paciente = new PacienteModel();
    private ProfissionalModel profissional = new ProfissionalModel();
    private UsuarioModel user = new UsuarioModel();
    private List<PacienteModel> pacientes = new ArrayList<PacienteModel>();
    
    private final String listPage = "/WEB-INF/listPatients.jsp";
    private final String loginPage = "login.html";
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppServerlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if(action.equals("/signin")) {
			this.signin(request, response);
		} else if(action.equals("/login")) {
			this.login(request, response);
		} else if(action.equals("/create")) {
			this.createNewPatient(request, response);
		} else if(action.equals("/update_patient")) {
			this.updatePatient(request, response);
		} else if(action.equals("/delete_patient")) {
			this.deletePatient(request, response);
		}else if(action.equals("/list_patients")) {
			this.listPatients(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/*
	 * THIS METHOD IS TO CREATE NEW PROFESSIONALS 
	 * AND AFTER REDIRECT THEM TO LOGIN PAGE 
	 */
	protected void signin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		profissional.setCpf(request.getParameter("cpf"));
		profissional.setNome(request.getParameter("realname"));
		profissional.setLogin(request.getParameter("username"));
		profissional.setSenha(request.getParameter("user_password"));
		profissional.setGrupo(request.getParameter("group"));

		boolean sign = profissionalDao.insertProfissional(profissional);
		
		if(sign) {
			System.out.println("Cadastrado");
			response.sendRedirect(loginPage);
		} else {
			System.out.println("Não foi cadastrado");
			response.sendRedirect("signin.html");
		}
	
		
	}
	
	/*
	 * THIS METHOD IS TO LOGIN (FOR PROFESSIONALS)
	 * AND AFTER, REDIRECT THEM TO THE PAGE 'CREATE NEW PATIENT'
	 */
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		user.setLogin(request.getParameter("username"));
		user.setSenha(request.getParameter("password"));
		
		boolean log = profissionalDao.login(user);
		
		if(log) {
			System.out.println("Logado");
			request.getRequestDispatcher("/WEB-INF/newPatient.html").forward(request, response);
		} else {
			System.out.println("Não foi logado");
			response.sendRedirect(loginPage);
		}
		
	}
	
	/*
	 * THIS METHOD IS TO CREATE NEW PATIENT AND AFTER 
	 * REDIRECT TO 'LIST PATIENTS PAGE'
	 */
	protected void createNewPatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		paciente.setCpf(request.getParameter("cpf"));
		paciente.setNome(request.getParameter("nome"));
		paciente.setSus(request.getParameter("sus"));
		paciente.setEmail(request.getParameter("email"));
		paciente.setQuadro(request.getParameter("triagem"));
		paciente.setDescricao(request.getParameter("descricao"));
		boolean cad = pacienteDao.insertPaciente(paciente);

		if(cad) {
			System.out.println("Paciente cadastrado com sucesso!");
			response.sendRedirect("http://localhost:8080/Tris/WEB-INF/");
			
		} else {
			System.out.println("Não foi possível cadastrar");
			response.sendError(0, "Impossivel cadastrar");
		}
	}
	
	/*
	 * TODO UPDATE PATIENT METHOD
	 */
	public void updatePatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		paciente.setId(Integer.parseInt((request.getParameter("id"))));
		paciente.setCpf(request.getParameter("cpf"));
		paciente.setNome(request.getParameter("nome"));
		paciente.setSus(request.getParameter("sus"));
		paciente.setEmail(request.getParameter("email"));
		paciente.setQuadro(request.getParameter("triagem"));
		paciente.setDescricao(request.getParameter("descricao"));
		boolean update = pacienteDao.updatePaciente(paciente);
		
		if(update) {
			System.out.println("Paciente atualizado com sucesso!");
			response.sendRedirect("http://localhost:8080/Tris/list_patients");
		} else {
			System.out.println("Não foi possível fazer a atualização");
			response.sendError(0, "Impossivel atualizar");
		}
			
			
	}
	
	/*
	 * TODO DELETE PATIENT METHOD
	 */
	public void deletePatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	
		int id = Integer.parseInt((request.getParameter("id")));
		
		boolean remove = pacienteDao.removePaciente(id);
		
		if(remove) {
			System.out.println("Paciente removido com sucesso!");
			this.listPatients(request, response);
		} else {
			System.out.println("Não foi possível remover o paciente");
			response.sendError(0, "Impossivel remover");
		}
	}

	/*
	 * TODO LIST PATIENTS METHOD
	 */
	public void listPatients(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ArrayList<PacienteModel> lista = pacienteDao.listarPacientes();
				
		if(request.getParameter("action")!=null) {
			
			PacienteModel pacienteExiste = pacienteDao.getPacienteById(Integer.parseInt((request.getParameter("id"))));
			
			 if(request.getParameter("action").equalsIgnoreCase("update_patient")) {
									
					if(pacienteExiste!=null) {		 
						request.setAttribute("paciente", pacienteExiste);
						request.getRequestDispatcher("/WEB-INF/updatePatients.jsp").forward(request, response);			
					} else {
						response.sendError(0, "Usuario não encontrado");
					}
					
			 }else if(request.getParameter("action").equalsIgnoreCase("delete_patient")) {
				 
				 if(pacienteExiste!=null) {		 
					 this.deletePatient(request, response);				
					} else {
						response.sendError(0, "Usuario não encontrado");
					}
			 }
	
		} else {
			 request.setAttribute("pacientes", lista);
		     request.getRequestDispatcher(listPage).forward(request, response);
		 }
		
		
	}
}
