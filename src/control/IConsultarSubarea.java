package control;

import javax.swing.JTable;

public interface IConsultarSubarea {
	public JTable buscarSubarea(String area, String subarea, JTable tabela)throws Exception;
}
