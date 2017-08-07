package dados;

import java.sql.SQLException;
import java.util.List;

import modelo.Comentario;

public interface InterfaceComentarioDAO {

	List<Comentario> listarTodos(String tituloComentario);

	Boolean deletarComentario(int id) throws SQLException;

	void Inserir(Comentario c, int idPost) throws SQLException;

	List<Comentario> listarTodos(int idPost);

}
