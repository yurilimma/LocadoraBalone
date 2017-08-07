package modelo;

import java.io.Serializable;

public class Post implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3730033541625996725L;
	
	
	private int idPost;
	private int idUsuario;
	private String tituloPost;
	private String texto;
	
	public Post(){
		
	}
	public Post(int idPost, int idUsuario, String tituloPost, String texto){
		this.idPost = idPost;
		this.idUsuario = idUsuario;
		this.tituloPost = tituloPost;
		this.texto = texto;
	}
	public Post(int idPost, String tituloPost, String texto){
		this.idPost = idPost;
		this.tituloPost = tituloPost;
		this.texto = texto;
		
	}
	public Post(String tituloPost, String texto){
		this.tituloPost = tituloPost;
		this.texto = texto;
	}
	
	public int getIdPost() {
		return idPost;
	}
	public void setIdPost(int idPost) {
		this.idPost = idPost;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getTituloPost() {
		return tituloPost;
	}
	public void setTituloPost(String tituloPost) {
		this.tituloPost = tituloPost;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}

}
