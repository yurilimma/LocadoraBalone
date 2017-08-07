package teste;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import dados.FabricaConexao;
import dados.PostDAO;
import junit.framework.Assert;
import modelo.Post;

public class PostDAOTeste {
	FabricaConexao fabrica = new FabricaConexao();
	Connection conexao =fabrica.fazerConexao();
	
	
	PostDAO dao = new PostDAO(conexao);
	
	public void setUp()throws Exception{
		this.conexao = fabrica.fazerConexao();
	}
	
	@Test
	public void testListarTodos() throws SQLException {
		Assert.assertNotNull(dao.listarTodos());
	}
	@Test
	public void testDeletarPost() throws SQLException {
		Assert.assertEquals(dao.deletarPost(15), dao.deletarPost(15));
	}
	
	/** Testa alterar um post que não existe**/
	@Test
	public void testAlterarPost() throws SQLException {
		Assert.assertEquals(dao.alterarPost(new Post(9,19,"Mudou","Mudou")), dao.alterarPost(new Post(99,19,"Mudou","Mudou")));
	}
}
