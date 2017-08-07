package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Usuario;

public class UsuarioDAO implements InterfaceUsuarioDAO{
	Connection conexao;
	public UsuarioDAO(Connection conexao){
		this.conexao = conexao;
	}
	
	@Override
	public void inserirUsuario(Usuario c) throws SQLException{
		
		String comando = "insert into usuario(nome,senha,idade,telefone,casa) values (?, ?, ?, ?, ?)";
		PreparedStatement ps = this.conexao.prepareStatement(comando);
		ps.setString(1,c.getNome());
		ps.setString(2, c.getSenha());
		ps.setInt(3, c.getIdade());
		ps.setString(4, c.getTelefone());	// Caso tenha Date ps.setDate(3, new Date(p.getdtCadastro().getTime()));
		ps.setString(5, c.getCasa());
		ps.execute();
	}
	
	@Override
	public Usuario consultarUsuario(Usuario c) throws SQLException{
		
		String comando = "select * from usuario where nome = ?";
		PreparedStatement ps = this.conexao.prepareStatement(comando);
		ps.setString(1,c.getNome());
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			int idUsuario = rs.getInt(1);
			String nomeUsuario = rs.getString(2);
			String senhaUsuario = rs.getString(3);
			int idadeUsuario = rs.getInt(4);
			String telefoneUsuario = rs.getString(5);
			String casaUsuario = rs.getString(6);
			
			
			
			Usuario usuario = new Usuario(idUsuario,nomeUsuario,senhaUsuario,idadeUsuario,telefoneUsuario,casaUsuario);
			return usuario;
		}
		return null;
	}
	@Override
	public Usuario consultarUsuarioPeloPost(int idPost) throws SQLException{
		
		String comando = "select * from usuario where id = (select idusuario from post where idpost = ?)";
		PreparedStatement ps = this.conexao.prepareStatement(comando);
		ps.setInt(1,idPost);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			int idUsuario = rs.getInt(1);
			String nomeUsuario = rs.getString(2);
			String senhaUsuario = rs.getString(3);
			int idadeUsuario = rs.getInt(4);
			String telefoneUsuario = rs.getString(5);
			String casaUsuario = rs.getString(6);
			
			
			
			Usuario usuario = new Usuario(idUsuario,nomeUsuario,senhaUsuario,idadeUsuario,telefoneUsuario,casaUsuario);
			return usuario;
		}
		return null;
	}
	public boolean validate(String user, String password) {
		PreparedStatement ps = null;

		try {
			String comando = "Select nome,senha from usuario where nome = ? and senha = ?";
			ps = this.conexao.prepareStatement(comando);
			ps.setString(1, user);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				//result found, means valid inputs
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("Erro Login: " + ex.getMessage());
			return false;
		}
		return false;
	}
}
