package model;
import java.util.Date;
public class Orientacoes {
	String nome;
	String descricao;
	Date data;
	String codigo;
	public Orientacoes(String nome, String descricao, Date data, String codigo) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.data = data;
		this.codigo = codigo;
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
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
