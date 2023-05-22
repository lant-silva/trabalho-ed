package control;
import model.Aluno;

public interface IInserirGrupos {
	public void inserirGrupos(String arquivoGrupos, String pathData, Aluno[] grupo, String codigoG, String tema, String nomeG, String area, String subarea) throws Exception;
}
