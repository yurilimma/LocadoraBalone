package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Post;

public class PostDAO implements InterfacePostDAO{
	Connection conexao;
	public PostDAO(Connection conexao){
		this.conexao = conexao;
	}
	
	@Override
	public void Inserir(Post c, String nomeUsuario) throws SQLException{
		int idUsuario = -1;
		String buscaUsuario = "select id from usuario where nome = ?";
		PreparedStatement ps = this.conexao.prepareStatement(buscaUsuario);
		ps.setString(1, nomeUsuario);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			idUsuario = rs.getInt(1);
		}
		
		String comando = "insert into post(idusuario,titulopost,texto) values ( ?, ?, ?)";
		PreparedStatement ps2 = this.conexao.prepareStatement(comando);
		ps2.setInt(1, idUsuario);
		ps2.setString(2, c.getTituloPost());
		ps2.setString(3, c.getTexto());
		ps2.execute();
		
		
	}
	
	@Override
	public List<Post> listarTodos(String nomeUsuario){
		ResultSet rs = null;
		List<Post> listaPosts = new ArrayList<Post>();
		
		
		try{
			
			String comando = "select * from post where idusuario= (select id from usuario where nome = ?)";
			PreparedStatement ps = this.conexao.prepareStatement(comando);
			ps.setString(1, nomeUsuario);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				int idPost = rs.getInt(1);
				int idAutor = rs.getInt(2);
				String tituloPost = rs.getString(3);
				String texto = rs.getString(4);
								
				
				listaPosts.add(new Post(idPost,idAutor,tituloPost,texto));
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return listaPosts;
	
	}
	@Override
	public List<Post> listarTodos(){
		ResultSet rs = null;
		List<Post> listaPosts = new ArrayList<Post>();
		
		
		try{
			
			String comando = "select * from post";
			PreparedStatement ps = this.conexao.prepareStatement(comando);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				int idPost = rs.getInt(1);
				int idAutor = rs.getInt(2);
				String tituloPost = rs.getString(3);
				String texto = rs.getString(4);
								
				
				listaPosts.add(new Post(idPost,idAutor,tituloPost,texto));
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return listaPosts;
	
	}
	
	
	@Override
	public Boolean deletarPost(int id) throws SQLException{
		String deletaPost = "delete from post where idpost = ?";
		PreparedStatement ps = this.conexao.prepareStatement(deletaPost);
		ps.setInt(1,id);
		
		return ps.execute();
		
	}
	
	@Override
	public Boolean alterarPost(Post p) throws SQLException{
		String alterarPostTexto = "update post set texto = ? where idpost = ?";
		PreparedStatement ps = this.conexao.prepareStatement(alterarPostTexto);
		ps.setString(1,p.getTexto());
		ps.setInt(2,p.getIdPost());
		ps.execute();
		
		String alterarPostTitulo = "update post set titulopost = ? where idpost = ?";
		PreparedStatement ps2 = this.conexao.prepareStatement(alterarPostTitulo);
		ps2.setString(1,p.getTituloPost());
		ps2.setInt(2,p.getIdPost());
		ps2.execute();
		
		
		return ps2.execute();
		
	}
	@Override
	public Post buscarPost(int id) throws SQLException{
		String buscarPost = "select * from post where idpost = ?";
		PreparedStatement ps = this.conexao.prepareStatement(buscarPost);
		ps.setInt(1,id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			int idPost = rs.getInt(1);
			int idAutor = rs.getInt(2);
			String tituloPost = rs.getString(3);
			String texto = rs.getString(4);
			
			Post post = new Post(idPost,idAutor,tituloPost,texto);
			return post;
		}
		return null;
	}

}
