package control;

import model.Grupo;

public interface IConsultaGrupos {
	public String[] BuscarGrupo(String pathData, String arquivoGrupos, String codigo)throws Exception;
}
