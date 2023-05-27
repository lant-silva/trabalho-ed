package control;
import model.Aluno;

public interface IInserirGrupos {
	public void inserirGrupos(Aluno[] grupo, String codigoG, String tema, String nomeG, String area, String subarea) throws Exception;
	public void editarGrupos(Aluno[] grupo, String codigoG, String tema, String nomeG, String area, String subarea) throws Exception;
	public void excluirGrupos(Aluno[] grupo, String codigoG, String tema, String nomeG, String area, String subarea) throws Exception;
	public String[] popularListaAlunos()throws Exception;
	
}
