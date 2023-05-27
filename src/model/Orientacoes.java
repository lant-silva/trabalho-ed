package model;
import java.util.Date;
public class Orientacoes {
	String nome;
	String descricao;
	Date data;
	String codigoGrupo;
	
	public Orientacoes(String nome, String descricao, Date data, String codigoGrupo) {
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
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getCodigoGrupo() {
		return codigoGrupo;
	}
	public void setCodigoGrupo(String codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}
}
