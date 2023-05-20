package control;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import model.Aluno;

public class InserirAluno {
	public void manterAluno(String arquivo, String aluno, String ra, String curso, String periodo, String semestreTxt) {
		int semestre = Integer.parseInt(semestreTxt);
		Aluno novo = new Aluno(aluno, ra, curso, periodo, semestre);
		
		try {
			inserir(arquivo, novo);
		} catch (IOException e) {
			System.err.println("Ocorreu um erro");
		}
		
	}
	
	private void inserir(String nomeArquivo, Aluno aluno) throws IOException {
		String path = System.getProperty("user.dir")+"/data";
		File dir = new File(path);
		String conteudo = aluno.getAluno()+";"+aluno.getRa()+";"+aluno.getCurso()+";"+aluno.getPeriodo()+";"+aluno.getSemestre()+"\n";
		if(!dir.exists()) {
			dir.mkdirs();
		}
		File arquivo = new File(path, nomeArquivo);
		boolean exists = false;
		if(arquivo.exists()) {
			exists = true;
		}
		FileWriter escrita = new FileWriter(arquivo, exists);
		PrintWriter print = new PrintWriter(escrita);
		print.write(conteudo);
		print.flush();
		print.close();
		escrita.close();
		JOptionPane.showMessageDialog(null, "Aluno gravado com sucesso.");
	}

	
}
