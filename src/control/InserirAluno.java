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
import model.Arquivos;

public class InserirAluno implements IInserirAluno{
	@Override
	public void manterAluno(String aluno, String ra, String curso, String periodo, int ciclo) throws Exception {
		Aluno novo = new Aluno(aluno, ra, curso, periodo, ciclo);
		try {
			if(!validar(novo)) {				
				inserir(novo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//==================[ Insere um aluno no arquivo csv ]==========================//
	private void inserir(Aluno aluno) throws Exception {
		File dir = new File(Arquivos.pathData);
		String conteudo = aluno.getAluno()+";"+aluno.getRa()+";"+aluno.getCurso()+";"+aluno.getPeriodo()+";"+aluno.getCiclo()+"\n";
		if(!dir.exists()) {
			dir.mkdirs();
		}
		File arquivo = new File(Arquivos.pathData, Arquivos.arquivoAlunos);
		boolean exists = false;
		boolean control = false;
		if(arquivo.exists()) {
			exists = true;
		}
		
		FileWriter escrita = new FileWriter(arquivo, exists);
		PrintWriter print = new PrintWriter(escrita);
		if(!alunoExiste(arquivo, aluno)) {
			print.write(conteudo);
			print.flush();
			JOptionPane.showMessageDialog(null, "Aluno gravado com sucesso.");
		}
		print.close();
		escrita.close();
	}
	
//====================[ Verifica se um aluno já existe na lista ]===================//
	private boolean alunoExiste(File arquivo, Aluno aluno) throws IOException {
		FileInputStream fluxo = new FileInputStream(arquivo);
		InputStreamReader leitor = new InputStreamReader(fluxo);
		BufferedReader buffer = new BufferedReader(leitor);
		String linha = buffer.readLine();
		while(linha!=null) {
			String[] linhaSeparada = linha.split(";");
			if(aluno.getRa().equals(linhaSeparada[1])) {
				JOptionPane.showMessageDialog(null, "O aluno especificado já existe no sistema.");
				return true;
			}
			linha = buffer.readLine();
		}
		buffer.close();
		leitor.close();
		fluxo.close();
		return false;
	}
	
	private boolean validar(Aluno aluno) {
		boolean control=false;
		if(aluno.getAluno().equals("")) {
			JOptionPane.showMessageDialog(null, "Insira o nome do aluno");
			control=true;
		}else if(aluno.getRa().equals("")) {
			JOptionPane.showMessageDialog(null, "Insira o RA do aluno");
			control=true;
		}
		return control;
	}
}
