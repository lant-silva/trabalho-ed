package control;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class InserirOrientacoes implements IInserirOrientacoes{
	
	/*
	 * Esse código faz a leitura do "lista-grupos.csv", e coleta a linha que for condizente com o código inserido no JTextField
	 * Se for válido, realiza a escrita da orientação no arquivo "lista-orientacoes.csv"
	 */
	
	
	@Override
	public void manterOrientacoes(String pathData, String arquivoGrupos, String codigo, String data, String nome, String descricao) throws Exception {
		data = formatarData(data);
		File arquivo = new File(pathData, arquivoGrupos);
		boolean exists = false;
		File arqOrientacao = new File(pathData, "lista-orientacoes.csv");
		if(arqOrientacao.exists() && arqOrientacao.isFile()) {
			exists = true;
		}
		FileInputStream fluxo = new FileInputStream(arquivo);
		InputStreamReader leitor = new InputStreamReader(fluxo);
		BufferedReader buffer = new BufferedReader(leitor);
		String linha = buffer.readLine();
		String conteudo="";
		while(linha!=null) {
			String[] lista = linha.split(";");
			if(codigo.equals(lista[0])) {
				conteudo = codigo+";"+nome+";"+data+";"+descricao+"\n";
			}
			linha = buffer.readLine();
		}
		
		FileWriter escrita = new FileWriter(arqOrientacao, exists);
		PrintWriter print = new PrintWriter(escrita);
		print.write(conteudo);
		print.flush();
		print.close();
		escrita.close();
		buffer.close();
		leitor.close();
		fluxo.close();
	}
	
	/*
	 * Esse código coleta a data inserida no JTextField e a formata, para o formato padrão de data no Brasil (dd/mm/aaaa) 
	 * Se houverem digitos a mais ou a menos, ou se a data não existir, o sistema apenas retorna como inválido
	 * 
	 */

	private String formatarData(String data) {	
		data = data.replaceAll("[^0-9]", "");
		int dia = Integer.parseInt(data.substring(0 ,2));
		int mes = Integer.parseInt(data.substring(2, 4));
		int ano = Integer.parseInt(data.substring(4, 8));
		if(data.length()==8 && dia > 0 && dia <= 31 && mes > 0 && mes <= 12 && ano >= 1800) {
			String formatar = data.substring(0, 2)+"/"+data.substring(2, 4)+"/"+data.substring(4, 8);
			data = formatar;
		}else {
			JOptionPane.showMessageDialog(null, "Data inválida, inserir no formato correto: (dd/mm/aaaa");
		}
		return data;
	}
}
