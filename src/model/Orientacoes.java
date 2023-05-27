package model;

public class Orientacoes {
	String nome;
	String descricao;
	String data;
	String codigoGrupo;
	
	public Orientacoes(String codigoGrupo, String nome, String descricao, String data) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.data = data;
		this.codigoGrupo = codigoGrupo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

	public String getCodigoGrupo() {
		return codigoGrupo;
	}
	public void setCodigoGrupo(String codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}
	
}
