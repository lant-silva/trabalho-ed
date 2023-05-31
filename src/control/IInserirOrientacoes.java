package control;

public interface IInserirOrientacoes {
	public void manterOrientacoes(String codigo, String data, String nome, String descricao)throws Exception;
	public void finalizarUltimaOrientacao(String codigo) throws Exception;
}
