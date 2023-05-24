package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Aluno;
import model.Grupo;
import model.Lista;

public class InserirGrupos implements IInserirGrupos{
	String[] alunos;
	
	@Override
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
		
		if(!validarGrupo(arquivo, novoGrupo, conteudo)) {
			print.write(conteudo);
			print.flush();
			JOptionPane.showMessageDialog(null, "Grupo gravado com sucesso.");
		}
		print.close();
		escrita.close();
		
	}
	
	@Override
	public void editarGrupos(String arquivoGrupos, String pathData, Aluno[] grupo, String codigoG, String tema, String nomeG, String area, String subarea) throws Exception {
		File arquivo = new File(pathData, arquivoGrupos);
		String alunos = grupo[0].getRa()+";"+grupo[1].getRa()+";"+grupo[2].getRa()+";"+grupo[3].getRa();
		String conteudo = (codigoG+";"+nomeG+";"+tema+";"+area+";"+subarea+";"+alunos+"\n");
		File arquivoTemp;
		boolean exists = false;
		if(arquivo.exists()) {
			arquivoTemp = new File(pathData, "TEMP"+arquivoGrupos);
		}else {
			return;
		}
		FileInputStream fluxo = new FileInputStream(arquivo);
		InputStreamReader leitor = new InputStreamReader(fluxo);
		BufferedReader buffer = new BufferedReader(leitor);
		FileWriter escrita = new FileWriter(arquivoTemp, exists);
		PrintWriter print = new PrintWriter(escrita);
	
		String linha = buffer.readLine();
		while(linha!=null) {
			String[] aux = linha.split(";");
			if(codigoG.equals(aux[0])) {
				print.write(conteudo);
				print.flush();
			}else {		
				print.write(linha+"\n");
				print.flush();
			}
			linha = buffer.readLine();
		}
		arquivoTemp.renameTo(arquivo);
		JOptionPane.showMessageDialog(null, "Grupo editado.");
		print.close();
		escrita.close();
		buffer.close();
		leitor.close();
		fluxo.close();
	}
	
	@Override
	public void excluirGrupos(String arquivoGrupos, String pathData, Aluno[] grupo, String codigoG, String tema, String nomeG, String area, String subarea) throws Exception {
		File arquivo = new File(pathData, arquivoGrupos);
		String alunos = grupo[0].getRa()+";"+grupo[1].getRa()+";"+grupo[2].getRa()+";"+grupo[3].getRa();
		String conteudo = (codigoG+";"+nomeG+";"+tema+";"+area+";"+subarea+";"+alunos+"\n");
		File arquivoTemp;
		boolean controle = false;
		if(arquivo.exists()) {
			arquivoTemp = new File(pathData, "TEMP"+arquivoGrupos);
		}else {
			return;
		}
		FileInputStream fluxo = new FileInputStream(arquivo);
		InputStreamReader leitor = new InputStreamReader(fluxo);
		BufferedReader buffer = new BufferedReader(leitor);
		FileWriter escrita = new FileWriter(arquivoTemp);
		PrintWriter print = new PrintWriter(escrita);
	
		String linha = buffer.readLine();
		while(linha!=null) {
			String[] aux = linha.split(";");
			if(codigoG.equals(aux[0])) {
				controle = true;
			}else {		
				print.write(linha+"\n");
				print.flush();
			}
			linha = buffer.readLine();
		}
		if(controle==true) {
			arquivoTemp.renameTo(arquivo);
			JOptionPane.showMessageDialog(null, "Grupo excluido.");
		}else {
			JOptionPane.showMessageDialog(null, "Grupo não encontrado");
			arquivoTemp.delete();
		}
		print.close();
		escrita.close();
		buffer.close();
		leitor.close();
		fluxo.close();
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
	
	private boolean validarGrupo (File arquivo, Grupo grupo, String conteudo) throws Exception{
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
			linha = buffer.readLine();
		}
		
		String[] verif = conteudo.split(";");
		
		if(verif[5].equals("")) {
			JOptionPane.showMessageDialog(null, "Insira pelo menos um aluno no grupo");
			controle = true;
		}
		
		
		if(verif[0].equals("")||verif[1].equals("")||verif[2].equals("")) {
				JOptionPane.showMessageDialog(null, "Campos vazios, por favor insira as informações do grupo");
				controle = true;
		}
		linha = buffer.readLine();
		buffer.close();
		leitor.close();
		fluxo.close();
		if(controle==true) {
			return true;
		}
		return false;
	}


}