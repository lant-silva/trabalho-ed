package model;

public class Aluno {
	String aluno;
	String ra;
	String curso;
	String periodo;
	int semestre;

	public Aluno(String aluno, String ra, String curso, String periodo, int semestre) {
		super();
		this.aluno = aluno;
		this.ra = ra;
		this.curso = curso;
		this.periodo = periodo;
		this.semestre = semestre;
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
	public int getSemestre() {
		return semestre;
	}
	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
}
