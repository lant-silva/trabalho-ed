package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import model.Arquivos;
import model.Orientacoes;
import model.Pilha;

public class ConsultaOrientacoes implements IConsultaOrientacoes{
	Pilha orientacoes = new Pilha();
	Orientacoes orientacao;
	@Override
	public Pilha consultarOrientacao(String codigo) throws Exception {
		File arquivo = new File(Arquivos.pathData, Arquivos.arquivoOrientacoes);
		if(!arquivo.exists()) {
			throw new Exception("Não há orientações");
		}
		FileInputStream fluxo = new FileInputStream(arquivo);
		InputStreamReader leitor = new InputStreamReader(fluxo);
		BufferedReader buffer = new BufferedReader(leitor);
		String linha = buffer.readLine();
		while(linha!=null) {
			String[] aux = linha.split(";");
			if(codigo.equals(aux[0])) {
				orientacao = new Orientacoes(aux[0],aux[1],aux[2],aux[3]);
				orientacoes.push(orientacao);
			}
			linha = buffer.readLine();
		}
		buffer.close();
		leitor.close();
		fluxo.close();
		if(orientacoes.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Esse grupo não possui orientações ou o grupo não existe");
			return orientacoes = new Pilha();
		}
		return orientacoes;
	}
}
