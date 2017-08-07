package teste;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import dados.FabricaConexao;
import dados.UsuarioDAO;
import junit.framework.Assert;

public class UsuarioDAOTeste {
	FabricaConexao fabrica = new FabricaConexao();
	Connection conexao =fabrica.fazerConexao();
	
	UsuarioDAO dao = new UsuarioDAO(conexao);
	
	/** Testa se o usuário é cadastrado ou não**/
	@Test
	public void testConsultarUsuarioPeloPost() throws SQLException {
		Assert.assertNotNull(dao.consultarUsuarioPeloPost(9));
	}
	
	

}
