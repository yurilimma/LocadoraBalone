package dados;

import java.sql.SQLException;
import java.util.List;

import modelo.Post;

public interface InterfacePostDAO {

	Boolean deletarPost(int id) throws SQLException;

	Boolean alterarPost(Post p) throws SQLException;

	Post buscarPost(int id) throws SQLException;

	List<Post> listarTodos(String nomeUsuario);

	void Inserir(Post c, String nomeUsuario) throws SQLException;

	List<Post> listarTodos();

}
