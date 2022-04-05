package com.repositories;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	private static String driver = "org.postgresql.Driver";
	private static String url = "jdbc:postgresql://localhost:5432/Tris";
	private static String user = "postgres";
	private static String password = "1234";
			
	public static Connection conectar(){
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("Erro de conexão com o banco");
			e.printStackTrace();
		}
		return con;
	}
}
