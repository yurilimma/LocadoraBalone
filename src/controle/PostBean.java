package controle;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

import dados.ComentarioDAO;
import dados.FabricaConexao;
import dados.PostDAO;
import dados.UsuarioDAO;
import modelo.Comentario;
import modelo.Post;
import modelo.Usuario;
import util.SessionUtil;

@ManagedBean
@SessionScoped 
public class PostBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3235778991439947273L;
	
	private Post post;
	private List<Post> listaPosts;
	private ListDataModel<Post> posts;
	
	private Comentario comentario;
	private List<Comentario> listaComentarios;
	private ListDataModel<Comentario> comentarios;
	
	private Usuario usuario;

	public PostBean(){
		this.post = new Post();
		this.listaPosts = new ArrayList<>();
	}
	
	public void inicializar(){
		try{
			FabricaConexao fabrica = new FabricaConexao();
			Connection conn = fabrica.fazerConexao();
			PostDAO dao = new PostDAO(conn);
			this.listaPosts = dao.listarTodos();
			this.posts = new ListDataModel<>(listaPosts);
			
			fabrica.fecharConexao();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

	public void PrepararNovo(){
		try{
			this.post = new Post();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void PrepararNovoComentario(){
		try{
			this.comentario = new Comentario();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void CadastrarNovo(){
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
	
	public void CadastrarNovoComentario(Post p, Comentario c){
		try{
			FabricaConexao fabrica = new FabricaConexao();
			Connection conn = fabrica.fazerConexao();
			ComentarioDAO dao = new ComentarioDAO(conn);
			dao.Inserir(c, p.getIdPost());
			
			this.listaComentarios = dao.listarTodos(p.getIdPost());
			this.comentarios = new ListDataModel<>(listaComentarios);
			
			fabrica.fecharConexao();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public void PrepararLerComentario(){
		try{
			this.comentario = comentarios.getRowData();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public String prepararDetalharPost(int idPost,String tituloPost, String texto){
		try{
			this.post = new Post(idPost,tituloPost,texto);
			FabricaConexao fabrica = new FabricaConexao();
			Connection conn = fabrica.fazerConexao();
			ComentarioDAO comentarioDao = new ComentarioDAO(conn);
			this.listaComentarios = comentarioDao.listarTodos(tituloPost);
			this.comentarios = new ListDataModel<>(listaComentarios);
			UsuarioDAO ususarioDao = new UsuarioDAO(conn);
			this.usuario =  ususarioDao.consultarUsuarioPeloPost(idPost);
			
			return "autenticacao/DetalharPost.xhtml";
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "autenticacao/DetalharPost.xhtml";
	}
	
	public String prepararEditar(int idPost,String tituloPost, String texto){
		try{
			this.post = new Post(idPost,tituloPost,texto);
			FabricaConexao fabrica = new FabricaConexao();
			Connection conn = fabrica.fazerConexao();
			ComentarioDAO comentarioDao = new ComentarioDAO(conn);
			this.listaComentarios = comentarioDao.listarTodos(tituloPost);
			this.comentarios = new ListDataModel<>(listaComentarios);
			
			return "EditarPost.xhtml";
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public void EditarPost(Post p){
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
	public void PrepararExcluir(){
		try{
			this.post = posts.getRowData();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void ExcluirPost(){
		try{
			FabricaConexao fabrica = new FabricaConexao();
			Connection conn = fabrica.fazerConexao();
			PostDAO dao = new PostDAO(conn);
			HttpSession session = SessionUtil.getSession();
			dao.deletarPost(this.post.getIdPost());
			this.listaPosts = dao.listarTodos(session.getAttribute("username").toString());
			this.posts = new ListDataModel<>(listaPosts);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void PrepararExcluirComentario(){
		try{
			this.comentario = comentarios.getRowData();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void ExcluirComentario(){
		try{
			FabricaConexao fabrica = new FabricaConexao();
			Connection conn = fabrica.fazerConexao();
			ComentarioDAO dao = new ComentarioDAO(conn);
			dao.deletarComentario(this.comentario.getIdComentario());
			this.listaComentarios = dao.listarTodos(this.post.getTituloPost());
			this.comentarios = new ListDataModel<>(listaComentarios);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public ListDataModel<Post> getPosts() {
		return posts;
	}

	public void setPosts(ListDataModel<Post> posts) {
		this.posts = posts;
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

	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}

	public List<Comentario> getListaComentarios() {
		return listaComentarios;
	}

	public void setListaComentarios(List<Comentario> listaComentarios) {
		this.listaComentarios = listaComentarios;
	}

	public ListDataModel<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(ListDataModel<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
