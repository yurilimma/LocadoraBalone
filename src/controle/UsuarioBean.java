package controle;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

import dados.FabricaConexao;
import dados.PostDAO;
import dados.UsuarioDAO;
import modelo.Post;
import modelo.Usuario;
import util.SessionUtil;

@ManagedBean
@SessionScoped 
public class UsuarioBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4371561726326363973L;
	private Usuario usuario;
	
	private Post post;
	private List<Post> listaPosts;
	private ListDataModel<Post> posts;
	
	

	public void prepararNovo(){
		try{
			this.usuario = new Usuario();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void inicializar(){
		try{
			FabricaConexao fabrica = new FabricaConexao();
			Connection conn = fabrica.fazerConexao();
						
			fabrica.fecharConexao();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void cadastrarNovo(){
		try{
			FabricaConexao fabrica = new FabricaConexao();
			Connection conn = fabrica.fazerConexao();
			UsuarioDAO dao = new UsuarioDAO(conn);
			dao.inserirUsuario(this.usuario);
			
			FacesContext context = FacesContext.getCurrentInstance();
	        
	        context.addMessage(null, new FacesMessage("Cadastro realizado com sucesso!!",  "Bem-vindo a Westeros!") );
			
			fabrica.fecharConexao();
			
		}catch(Exception e){
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Erro ao cadastrar Usuário. " , e.getMessage()));
		}
		
	}
	
	public String validateUsernamePassword() {
		FabricaConexao fabrica = new FabricaConexao();
		Connection conn = fabrica.fazerConexao();
		
		UsuarioDAO dao = new UsuarioDAO(conn);
		boolean valid = dao.validate(this.usuario.getNome(), this.usuario.getSenha());
		if (valid) {
			HttpSession session = SessionUtil.getSession();
			session.setAttribute("username", this.usuario.getNome());
			/** Lista posts associados aquele usuário */
			try{
				
				PostDAO daoPost = new PostDAO(conn);
				this.listaPosts = daoPost.listarTodos(session.getAttribute("username").toString());
				this.posts = new ListDataModel<>(listaPosts);
				
				fabrica.fecharConexao();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return "admin";
			
			
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Usuário incorreto",
							"Por favor verifique o usuário e senha"));
			return "login";
		}
	}

	//logout event, invalidate session
	public void logout() throws IOException {
		HttpSession session = SessionUtil.getSession();
		session.invalidate();
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect("autenticacao/Login.xhtml");
	}
	
	
	//* */
	public void prepararNovoPost(){
		try{
			this.post = new Post();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void cadastrarNovoPost(){
		try{
			FabricaConexao fabrica = new FabricaConexao();
			Connection conn = fabrica.fazerConexao();
			PostDAO dao = new PostDAO(conn);
			HttpSession session = SessionUtil.getSession();
			
			dao.Inserir(this.post, session.getAttribute("username").toString());
			
			this.listaPosts = dao.listarTodos(session.getAttribute("username").toString());
			this.posts = new ListDataModel<>(listaPosts);
			
			fabrica.fecharConexao();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void editarPost(Post p){
		try{
			FabricaConexao fabrica = new FabricaConexao();
			Connection conn = fabrica.fazerConexao();
			PostDAO dao = new PostDAO(conn);
			dao.alterarPost(p);
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Alteração realizada com sucesso!!",  " ") );
	        
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void prepararExcluirPost(){
		try{
			this.post = posts.getRowData();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void excluirPost(){
		try{
			FabricaConexao fabrica = new FabricaConexao();
			Connection conn = fabrica.fazerConexao();
			PostDAO dao = new PostDAO(conn);
			HttpSession session = SessionUtil.getSession();
			dao.deletarPost(this.post.getIdPost());
			this.listaPosts = dao.listarTodos(session.getAttribute("username").toString());
			this.posts = new ListDataModel<>(listaPosts);
		}catch(Exception e){
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,  "Não foi possível excluir o post informado.", "O mesmo possui comentários, exclua-os primeiro.") );
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public UsuarioBean(){
		this.usuario = new Usuario();
		this.post = new Post();
	}
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public List<Post> getListaPosts() {
		return listaPosts;
	}

	public void setListaPosts(List<Post> listaPosts) {
		this.listaPosts = listaPosts;
	}

	public ListDataModel<Post> getPosts() {
		return posts;
	}

	public void setPosts(ListDataModel<Post> posts) {
		this.posts = posts;
	}
	
}


