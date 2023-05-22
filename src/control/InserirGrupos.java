package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import model.Aluno;
import model.Grupo;
import model.Lista;

public class InserirGrupos implements IInserirGrupos{
	String[] alunos;
	public String[] popularListaAlunos(String pathData, String nomeArquivo)throws Exception{
		
		File listaAlunos = new File(pathData, nomeArquivo);
		
		FileInputStream fluxo = new FileInputStream(listaAlunos);
		InputStreamReader leitor = new InputStreamReader(fluxo);
		BufferedReader buffer = new BufferedReader(leitor);
		String linha = buffer.readLine();
		int contador = 0;
		Lista aux = new Lista();
		while(linha!=null) {
			String[] lista = linha.split(";");
			Aluno a = new Aluno(lista[0],lista[1],lista[2],lista[3], Integer.parseInt(lista[4]));
			if(aux.isEmpty()) {
				aux.addFirst(a);
			}else {
				aux.addLast(a);
			}
			contador++;
			linha = buffer.readLine();
		}
		alunos = new String[contador];
		for(int i = 0;i<contador;i++) {
			Aluno auxiliar = (Aluno) aux.get(i);
			alunos[i] = auxiliar.getAluno();
		}
		return alunos;
	}
	
	public Aluno popularTabelaInserirGrupo(String nomeArquivo, String nomeAluno) throws Exception {
		
		String path = System.getProperty("user.dir")+"/data";
		File listaAlunos = new File(path, nomeArquivo);
		
		Aluno retorno=null;
		FileInputStream fluxo = new FileInputStream(listaAlunos);
		InputStreamReader leitor = new InputStreamReader(fluxo);
		BufferedReader buffer = new BufferedReader(leitor);
		String linha = buffer.readLine();
		while(linha!=null) {
			String[] lista = linha.split(";");
			if(lista[0].contains(nomeAluno)) {
				retorno = new Aluno(lista[0],lista[1],lista[2],lista[3], Integer.parseInt(lista[4]));
			}
			linha = buffer.readLine();
		}
		buffer.close();
		leitor.close();
		fluxo.close();
		return retorno;
	}

		
	@Override
	public void inserirGrupos(String arquivoGrupos, String pathData, Aluno[] grupo, String codigoG, String tema,String nomeG, String area, String subarea) throws Exception {
		File dir = new File(pathData);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		File arquivo = new File(pathData, arquivoGrupos);
		Grupo novoGrupo = new Grupo(nomeG, codigoG, tema, area, subarea, grupo);
		String alunos = grupo[0].getRa()+";"+grupo[1].getRa()+";"+grupo[2].getRa()+";"+grupo[3].getRa();
		String conteudo = (codigoG+";"+nomeG+";"+tema+";"+area+";"+subarea+";"+alunos+"\n");
		boolean exists = false;
		if(arquivo.exists()) {
			exists = true;
		}
		
		FileWriter escrita = new FileWriter(arquivo, exists);
		PrintWriter print = new PrintWriter(escrita);
		
		if(!validarGrupo(arquivo, novoGrupo)) {
			print.write(conteudo);
			print.flush();
			JOptionPane.showMessageDialog(null, "Grupo gravado com sucesso.");
		}
		print.close();
		escrita.close();
		
	}

	public String popularRa(String pathData, String arquivoAlunos, String ra) throws Exception {
		File listaAlunos = new File(pathData, arquivoAlunos);
		
		FileInputStream fluxo = new FileInputStream(listaAlunos);
		InputStreamReader leitor = new InputStreamReader(fluxo);
		BufferedReader buffer = new BufferedReader(leitor);
		String linha = buffer.readLine();
		while(linha!=null) {
			String[] lista = linha.split(";");
			if(lista[0].contains(ra)) {
				return lista[1];
			}
			linha = buffer.readLine();
		}
		buffer.close();
		leitor.close();
		fluxo.close();
		return null;
	}
	
	public boolean validarGrupo (File arquivo, Grupo grupo) throws Exception{
		boolean controle = false;
		FileInputStream fluxo = new FileInputStream(arquivo);
		InputStreamReader leitor = new InputStreamReader(fluxo);
		BufferedReader buffer = new BufferedReader(leitor);
		String linha = buffer.readLine();
		while(linha!=null) {
			String[] linhaSeparada = linha.split(";");
			if(linhaSeparada[0].equals(grupo.getCodigo())) {
				JOptionPane.showMessageDialog(null, "O grupo especificado já existe no sistema.");
				controle = true;
			}
//			(Verificar se existem campos vazios, por enquanto em fase de implementação
//			if(linhaSeparada[0]==""||linhaSeparada[1]==""||linhaSeparada[2]=="") {
//				JOptionPane.showMessageDialog(null, "Campos vazios, por favor insira as informações do grupo");
//				controle = true;
//			}
			linha = buffer.readLine();
		}
		buffer.close();
		leitor.close();
		fluxo.close();
		if(controle==true) {
			return true;
		}
		return false;
	}
}