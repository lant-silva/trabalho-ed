package model;

public class Aluno {
	String aluno;
	String ra;
	String curso;
	String periodo;
	int ciclo;

	public Aluno(String aluno, String ra, String curso, String periodo, int ciclo) {
		super();
		this.aluno = aluno;
		this.ra = ra;
		this.curso = curso;
		this.periodo = periodo;
		this.ciclo = ciclo;
	}
	
	public String getAluno() {
		return aluno;
	}
	public void setAluno(String aluno) {
		this.aluno = aluno;
	}
	public String getRa() {
		return ra;
	}
	public void setRa(String ra) {
		this.ra = ra;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public int getCiclo() {
		return ciclo;
	}
	public void setCiclo(int ciclo) {
		this.ciclo = ciclo;
	}
}
