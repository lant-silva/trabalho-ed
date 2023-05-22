package model;
public class Grupo {
	private String nomeG;
	private String codigo;
	private String tema;
	private String area;
	private String subarea;
	private Aluno[] participantes;
	
	public Grupo(String nomeG, String codigo, String tema, String area, String subarea, Aluno[] participantes) {
		super();
		this.nomeG = nomeG;
		this.codigo = codigo;
		this.tema = tema;
		this.area = area;
		this.subarea = subarea;
		this.participantes = participantes;
	}
	
	public String getNomeG() {
		return nomeG;
	}
	public void setNomeG(String nomeG) {
		this.nomeG = nomeG;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getSubarea() {
		return subarea;
	}
	public void setSubarea(String subarea) {
		this.subarea = subarea;
	}
	public Aluno[] getParticipantes() {
		return participantes;
	}
	public void setParticipantes(Aluno[] participantes) {
		this.participantes = participantes;
	}
	
}
