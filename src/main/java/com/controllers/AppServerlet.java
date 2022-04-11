package com.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.postgresql.util.PSQLException;

import com.models.PacienteModel;
import com.models.ProfissionalModel;
import com.models.UserLoged;
import com.models.UsuarioModel;
import com.repositories.PacienteDao;
import com.repositories.ProfissionalDao;

/**
 * Servlet implementation class AppServerlet
 */
@WebServlet(urlPatterns =  { "/app", "/signin", "/login", "/create", "/list_patients", "/update_patient", "/delete_patient", "/logout"})

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
		}else if(action.equals("/logout")) {
			HttpSession session = request.getSession();
			session.removeAttribute("isloged");	
			System.out.println("the session ends");
			response.sendRedirect(loginPage);
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
		UserLoged userlg = new UserLoged();
		boolean log = profissionalDao.login(user);
		
		if(log) {
			
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			
			if(request.getAttribute("isloged")!=null) {
				
				Cookie cookie = new Cookie("sessaoCriada", session.getId()); 
				System.out.println(session.getId());
				cookie.setMaxAge(60*60);
				
			} else {
				userlg.setUsername(request.getParameter("username"));
				userlg.setPassword(request.getParameter("password"));
				session.setAttribute("isloged", userlg);
			}
			
			Cookie cookies[] = req.getCookies();
			if(cookies.length > 0) {
				
				for (Cookie cookie : cookies) {
					if(cookie.getName().equals("sessaoCriada")) {
						request.setAttribute("isloged", cookie.getValue());
						
					}
				}		
			}
						
			System.out.println("Loged as: "+ userlg.getUsername());
			request.getRequestDispatcher("/WEB-INF/newPatient.html").forward(request, response);
		} else {
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
		
		if(paciente.getCpf()!= null && paciente.getNome()!=null) {
			try {
				boolean cad = pacienteDao.insertPaciente(paciente);
				if(cad) {
					System.out.println("Paciente cadastrado com sucesso!");
					response.sendRedirect("http://localhost:8080/Tris/list_patients");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			request.getRequestDispatcher("/WEB-INF/newPatient.html").forward(request, response);
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
			response.sendRedirect("http://localhost:8080/Tris/list_patients");
		} else {
			System.out.println("Nï¿½o foi possï¿½vel remover o paciente");
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
						response.sendError(0, "Usuario nï¿½o encontrado");
					}
					
			 }else if(request.getParameter("action").equalsIgnoreCase("delete_patient")) {
				 
				 if(pacienteExiste!=null) {		 
					 this.deletePatient(request, response);				
					} else {
						response.sendError(0, "Usuario nï¿½o encontrado");
					}
			 }
	
		} else{
			 request.setAttribute("pacientes", lista);
		     request.getRequestDispatcher(listPage).forward(request, response);
		 }
		
		
	}
}
