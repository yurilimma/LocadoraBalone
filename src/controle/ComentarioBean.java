package controle;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpSession;

import dados.ComentarioDAO;
import dados.FabricaConexao;
import modelo.Comentario;
import util.SessionUtil;

@ManagedBean
@SessionScoped 
public class ComentarioBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2364188281598679420L;
	private Comentario comentario;
	private List<Comentario> listaComentarios;
	private ListDataModel<Comentario> comentarios;
	
	

	public void PrepararNovo(){
		try{
			this.comentario = new Comentario();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void PrepararExcluir(){
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
			HttpSession session = SessionUtil.getSession();
			dao.deletarComentario(this.comentario.getIdComentario());
			this.listaComentarios = dao.listarTodos(session.getAttribute("username").toString());
			this.comentarios = new ListDataModel<>(listaComentarios);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public ComentarioBean(){
		this.comentario = new Comentario();
		this.listaComentarios = new ArrayList<>();
		
	}
	public ListDataModel<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(ListDataModel<Comentario> comentarios) {
		this.comentarios = comentarios;
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
}
