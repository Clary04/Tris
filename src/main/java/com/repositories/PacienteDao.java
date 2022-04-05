package com.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.models.PacienteModel;

public class PacienteDao{

	private String url;
	private String user;
	private String password;
	private Connection con;
	
//	Connection
	public Connection connect(){
		url = "jdbc:postgresql://localhost:5432/Tris";
		user = "postgres";
		password = "1234";
		
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("PacienteDAO Conectado com sucesso!");
			return con;			
			} catch (Exception e){
			e.printStackTrace();
			}
		return null;
			
		}
	
	/*
	 * LIST ALL PATIENTS AND RETURN A LIST
	 */
	public ArrayList<PacienteModel> listarPacientes(){
		String sql = "select * from paciente order by Id;";
		ArrayList<PacienteModel> result = new ArrayList<>();
		try {
			Connection con = connect();
			PreparedStatement stm = con.prepareStatement(sql);
			ResultSet res =  stm.executeQuery();
			while(res.next()) {
				PacienteModel paciente = new PacienteModel();
				paciente.setId(res.getInt("Id"));
				paciente.setCpf(res.getString("Cpf_paciente"));
				paciente.setNome(res.getString("Nome"));
				paciente.setSus(res.getString("Sus"));
				paciente.setEmail(res.getString("Email"));
				paciente.setQuadro(res.getString("Quadro"));
				paciente.setDescricao(res.getString("Descricao"));
				result.add(paciente);
			}
			con.close();
			return result;
		} catch(SQLException e) {
			Logger.getLogger(PacienteDao.class.getName()).log(Level.SEVERE, null, e);
			return null;
		}
	}

	/*
	 * INSERT NEW PATIENT METHOD
	 */
	public boolean insertPaciente(PacienteModel paciente) {
		String sql = "INSERT INTO Paciente (cpf_paciente, nome, sus, email, quadro, descricao)"
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		try {
			Connection con = connect();
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, paciente.getCpf());
			stm.setString(2, paciente.getNome());
			stm.setString(3, paciente.getSus());
			stm.setString(4, paciente.getEmail());
			stm.setString(5, paciente.getQuadro());
			stm.setString(6, paciente.getDescricao());
			stm.execute();
			return true;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} 
	}
	
	/*
	 * UPDATE PATIENTS METHOD
	 */
	public boolean updatePaciente(PacienteModel paciente) {
		
		String sql = "UPDATE paciente SET cpf_paciente = ?, nome = ?, sus = ?, email = ?, "
				+ "quadro = ?, descricao = ? Where id = ?";
		try {
			Connection con = connect();
			PreparedStatement stm = con.prepareStatement(sql);		
			stm.setString(1, paciente.getCpf());
			stm.setString(2, paciente.getNome());
			stm.setString(3, paciente.getSus());
			stm.setString(4, paciente.getEmail());
			stm.setString(5, paciente.getQuadro());
			stm.setString(6, paciente.getDescricao());
			stm.setInt(7, paciente.getId());
			stm.execute();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	// GET PACIENTE BY ID
	
	public PacienteModel getPacienteById(int Id) {
		
		PacienteModel paciente = null;
		
		String sql = "SELECT * FROM Paciente WHERE id=?";
		try {
			Connection con = connect();
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, Id);
			ResultSet res = stm.executeQuery();
			
			while(res.next()) {				
				paciente = new PacienteModel();		
				paciente.setId(res.getInt("Id"));
				paciente.setCpf(res.getString("Cpf_paciente"));
				paciente.setNome(res.getString("Nome"));
				paciente.setSus(res.getString("Sus"));
				paciente.setEmail(res.getString("Email"));
				paciente.setQuadro(res.getString("Quadro"));
				paciente.setDescricao(res.getString("Descricao"));
			}			
			
		} catch(Exception e) {
			e.printStackTrace();
			
		}	
		
		return paciente;
	}
	
	/*
	 * REMOVE PATIENTS METHOD
	 */
	public boolean removePaciente(int id) {
		String sql = "DELETE FROM Paciente WHERE Id=?";
		try {
			Connection con = connect();
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setInt(1, id);
			stm.execute();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

}
