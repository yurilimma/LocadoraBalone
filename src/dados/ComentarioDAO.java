package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Comentario;

public class ComentarioDAO implements InterfaceComentarioDAO{
	Connection conexao;
	public ComentarioDAO(Connection conexao){
		this.conexao = conexao;
	}
	
	public void Inserir(Comentario c, int idPost) throws SQLException{
		
		String comando = "insert into comentario(idpost,autor,descricao) values ( ?, ?, ?)";
		PreparedStatement ps2 = this.conexao.prepareStatement(comando);
		ps2.setInt(1, idPost);
		ps2.setString(2, c.getAutor());
		ps2.setString(3, c.getDescricao());
		ps2.execute();
		
		
	}
	
	@Override
	public List<Comentario> listarTodos(String tituloPost){
		ResultSet rs = null;
		List<Comentario> listaComentarios = new ArrayList<Comentario>();
		try{
			String comando = "select * from comentario where idpost= (select idpost from post where titulopost = ?)";
			PreparedStatement ps = this.conexao.prepareStatement(comando);
			ps.setString(1, tituloPost);
			rs = ps.executeQuery();
			while(rs.next()){
				int idComentario = rs.getInt(1);
				int idPost = rs.getInt(2);
				String autor = rs.getString(3);
				String descricao = rs.getString(4);
				listaComentarios.add(new Comentario(idComentario,idPost,autor,descricao));
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return listaComentarios;
	}
	@Override
	public List<Comentario> listarTodos(int idPost){
		ResultSet rs = null;
		List<Comentario> listaComentarios = new ArrayList<Comentario>();
		try{
			String comando = "select * from comentario where idpost= ?";
			PreparedStatement ps = this.conexao.prepareStatement(comando);
			ps.setInt(1, idPost);
			rs = ps.executeQuery();
			while(rs.next()){
				int idComentario = rs.getInt(1);
				idPost = rs.getInt(2);
				String autor = rs.getString(3);
				String descricao = rs.getString(4);
				listaComentarios.add(new Comentario(idComentario,idPost,autor,descricao));
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return listaComentarios;
	}
	
	@Override
	public Boolean deletarComentario(int id) throws SQLException{
		String deletaComentario = "delete from comentario where idcomentario = ?";
		PreparedStatement ps = this.conexao.prepareStatement(deletaComentario);
		ps.setInt(1,id);
		
		return ps.execute();
		
	}
	
}
