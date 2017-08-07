package modelo;

import java.io.Serializable;

public class Comentario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 648253507425133354L;
	
	private int idComentario;
	private int idPost;
	private String autor;
	private String descricao;
	
	public Comentario(){
		
	}
	
	public Comentario(int idComentario, int idPost, String autor, String descricao){
		this.idComentario = idComentario;
		this.idPost = idPost;
		this.autor = autor;
		this.descricao = descricao;
	}
	
	public int getIdComentario() {
		return idComentario;
	}
	public void setIdComentario(int idComentario) {
		this.idComentario = idComentario;
	}
	public int getIdPost() {
		return idPost;
	}
	public void setIdPost(int idPost) {
		this.idPost = idPost;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
