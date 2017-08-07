package modelo;

import java.io.Serializable;

public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1919618119866719613L;
	
	private int id;
	private String nome;
	private String senha;
	private String casa;

	private int idade;
	private String telefone;
	
	public Usuario(){
		
	}
	public Usuario(int id, String nome, String senha, int idade, String telefone, String casa){
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.idade = idade;
		this.telefone = telefone;
		this.casa = casa;
	}

	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCasa() {
		return casa;
	}
	public void setCasa(String casa) {
		this.casa = casa;
	}

}
