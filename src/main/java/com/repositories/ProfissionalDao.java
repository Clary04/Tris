package com.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.models.ProfissionalModel;
import com.models.UsuarioModel;


public class ProfissionalDao{
	
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
			System.out.println("ProfisionalDAO Conectado com sucesso!");
			return con;			
			} catch (Exception e){
				e.printStackTrace();
			}
		return null;
			
		}
	
//	public List<ProfissionalModel> listarProfissionais(){
//		String sql = "select * from Profissional";
//		List<ProfissionalModel> result = new ArrayList<>();
//		try {
//			Connection con = connect();
//			PreparedStatement s
//			while(res.next()) {
//				ProfissionalModel profissional = new ProfissionalModel();
//				profissional.setId(res.getInt("Id"));
//				profissional.setCpf(res.getString("Cpf"));
//				profissional.setLogin(res.getString("Login"));
//				profissional.setNome(res.getString("Nome"));
//				profissional.setSenha(res.getString("Senha"));
//				profissional.setGrupo(res.getString("Grupo"));
//				result.add(profissional);
//			}
//		} catch(SQLException e) {
//			Logger.getLogger(PacienteDao.class.getName()).log(Level.SEVERE, null, e);
//		}
//		return result;
//	}

	public boolean insertProfissional(ProfissionalModel profissional) {
		String sql = "INSERT INTO Profissional (Cpf, Login, Nome, Senha, Grupo) VALUES (?, ?, ?, ?, ?);";
		try {
			Connection con = connect();
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, profissional.getCpf());
			stm.setString(2, profissional.getLogin());
			stm.setString(3, profissional.getNome());
			stm.setString(4, profissional.getSenha());
			stm.setString(5, profissional.getGrupo());
			stm.execute();
			((Connection) con).close();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		} 
	}

	public boolean login(UsuarioModel user) {
		String sql = "Select login, senha from profissional WHERE login = ? and senha = ?;";
		try {
			Connection con = connect();
			PreparedStatement stm = con.prepareStatement(sql);
			stm.setString(1, user.getLogin());
			stm.setString(2, user.getSenha());
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				return true;
			} 
		}catch(Exception e) {
			e.printStackTrace();
		} 
		return false;
	}
	
//	public boolean updateProfissional(ProfissionalModel profissional) {
//		try {
//			con.AlterarSQL("UPDATE Profissiobal SET Cpf="+ profissional.getCpf()+
//					", Nome="+ profissional.getNome() +
//					", Login=,"+ profissional.getLogin() +
//					", Senha=" + profissional.getSenha() +
//					", Grupo=" + profissional.getGrupo() +
//					"WHERE Id=" + profissional.getId());
//			return true;
//		} catch(Exception e) {
//			e.printStackTrace();
//			return false;
//		}	
//	}
	
//	public boolean removeProfissional(int id) {
//		String sql = "DELETE FROM Profissional WHERE Id=?";
//		try {
//			PreparedStatement stm = cn.prepareStatement(sql);
//			stm.setInt(1, id);
//			stm.execute();
//			return true;
//		} catch(Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
	
}
