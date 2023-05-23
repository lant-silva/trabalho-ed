package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;


public class ConsultaGrupos implements IConsultaGrupos{

	@Override
	public String[] BuscarGrupo(String pathData, String arquivoGrupos, String codigo) throws Exception {
		String[] aux = new String[3];
		File arquivo = new File(pathData, arquivoGrupos);
		FileInputStream fluxo = new FileInputStream(arquivo);
		InputStreamReader leitor = new InputStreamReader(fluxo);
		BufferedReader buffer = new BufferedReader(leitor);
		String linha = buffer.readLine();
		boolean controle = false;
		while(linha!=null) {
			String[] grupoAtual = linha.split(";");
			if(codigo.equals(grupoAtual[0])) {
				aux[0] = grupoAtual[0];aux[1] = grupoAtual[1];aux[2] = grupoAtual[2];
				controle = true;
			}
			linha = buffer.readLine();
		}
		buffer.close();
		leitor.close();
		fluxo.close();
		
		if(controle==true) {
			return aux;
		}else {
			JOptionPane.showMessageDialog(null, "Grupo não encontrado");
			return null;
		}
	}
}
