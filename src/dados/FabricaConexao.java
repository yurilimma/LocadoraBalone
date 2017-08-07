package dados;

import java.sql.Connection;
import java.sql.DriverManager;

public class FabricaConexao {
	private Connection conexao;
	
	public Connection fazerConexao(){
		try{
			Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres","0123456");
		}catch (Exception e){
			e.printStackTrace();
		}
		return conexao;
	}
	
	public void fecharConexao(){
		try{
			this.conexao.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
