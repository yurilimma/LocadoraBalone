package dados;

import java.sql.SQLException;

import modelo.Usuario;

public interface InterfaceUsuarioDAO {


	void inserirUsuario(Usuario c) throws SQLException;

	Usuario consultarUsuario(Usuario c) throws SQLException;

	boolean validate(String user, String password);

	Usuario consultarUsuarioPeloPost(int idPost) throws SQLException;

}
