package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import model.Aluno;
import model.Arquivos;
import model.Grupo;
import model.ListaObject;

public class InserirGrupos implements IInserirGrupos{
	String[] alunos;
	
	@Override
	public String[] popularListaAlunos()throws Exception{
		
		File listaAlunos = new File(Arquivos.pathData, Arquivos.arquivoAlunos);
		
		FileInputStream fluxo = new FileInputStream(listaAlunos);
		InputStreamReader leitor = new InputStreamReader(fluxo);
		BufferedReader buffer = new BufferedReader(leitor);
		String linha = buffer.readLine();
		int contador = 0;
		ListaObject aux = new ListaObject();
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
	
	public Aluno popularTabelaInserirGrupo(String nomeAluno) throws Exception {
		
		File listaAlunos = new File(Arquivos.pathData, Arquivos.arquivoAlunos);
		
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
	public void inserirGrupos(Aluno[] grupo, String codigoG, String tema,String nomeG, String area, String subarea) throws Exception {
		File dir = new File(Arquivos.pathData);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		File arquivo = new File(Arquivos.pathData, Arquivos.arquivoGrupos);
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
	public void editarGrupos(Aluno[] grupo, String codigoG, String tema, String nomeG, String area, String subarea) throws Exception {
		File arquivo = new File(Arquivos.pathData, Arquivos.arquivoGrupos);
		String alunos = grupo[0].getRa()+";"+grupo[1].getRa()+";"+grupo[2].getRa()+";"+grupo[3].getRa();
		String conteudo = (codigoG+";"+nomeG+";"+tema+";"+area+";"+subarea+";"+alunos+"\n");
		File arquivoTemp;
		boolean exists = false;
		boolean editado = false;
		if(arquivo.exists()) {
			arquivoTemp = new File(Arquivos.pathData, "TEMP"+Arquivos.arquivoGrupos);
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
		if(editado==true) {
			JOptionPane.showMessageDialog(null, "Grupo editado.");			
		}else {
			JOptionPane.showMessageDialog(null, "Grupo não encontrado");
		}
		print.close();
		escrita.close();
		buffer.close();
		leitor.close();
		fluxo.close();
	}
	
	@Override
	public void excluirGrupos(Aluno[] grupo, String codigoG, String tema, String nomeG, String area, String subarea) throws Exception {
		File arquivo = new File(Arquivos.pathData, Arquivos.arquivoGrupos);
		File arquivoTemp;
		boolean excluido = false;
		if(arquivo.exists()) {
			arquivoTemp = new File(Arquivos.pathData, "TEMP"+Arquivos.arquivoGrupos);
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
				excluido = true;
			}else {		
				print.write(linha+"\n");
				print.flush();
			}
			linha = buffer.readLine();
		}
		if(excluido==true) {
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

	public String popularRa(String ra) throws Exception {
		File listaAlunos = new File(Arquivos.pathData, Arquivos.arquivoAlunos);
		
		FileInputStream fluxo = new FileInputStream(listaAlunos);
		InputStreamReader leitor = new InputStreamReader(fluxo);
		BufferedReader buffer = new BufferedReader(leitor);
		String linha = buffer.readLine();
		String envio = null;
		while(linha!=null) {
			String[] lista = linha.split(";");
			if(ra.equals(lista[0])) {
				envio = lista[1];
			}
			linha = buffer.readLine();
		}
		buffer.close();
		leitor.close();
		fluxo.close();
		return envio;
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

	public String[] popularAreas() throws Exception {
		File arquivo = new File(Arquivos.pathData, Arquivos.arquivoAreas);
		
		FileInputStream fluxo = new FileInputStream(arquivo);
		InputStreamReader leitor = new InputStreamReader(fluxo);
		BufferedReader buffer = new BufferedReader(leitor);
		String linha = buffer.readLine();
		String itens = "";
		while(linha!=null) {
			String[] aux = linha.split(";");
			itens = itens + aux[0]+";";
			linha = buffer.readLine();
		}
		String[] areas = itens.split(";");
		buffer.close();
		return areas;
	}
	
	public String[] popularSubareas(String areaAtual) throws Exception{
		File arquivo = new File(Arquivos.pathData, Arquivos.arquivoAreas);
		
		FileInputStream fluxo = new FileInputStream(arquivo);
		InputStreamReader leitor = new InputStreamReader(fluxo);
		BufferedReader buffer = new BufferedReader(leitor);
		String linha = buffer.readLine();
		String itens = "";
		while(linha!=null) {
			String[] aux = linha.split(";");
			if(areaAtual.equals(aux[0])) {
				int tamanho = aux.length;
				for(int i=1;i<tamanho;i++) {
					itens += aux[i]+";";
				}
			}
			linha = buffer.readLine();
		}
		String[] areas = itens.split(";");
		buffer.close();
		return areas;
	}
}