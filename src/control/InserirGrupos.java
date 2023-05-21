package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import model.Aluno;
import model.Lista;

public class InserirGrupos implements IInserirGrupos{
	String[] alunos;
	public String[] popularListaAlunos(String nomeArquivo)throws Exception{
		
		String path = System.getProperty("user.dir")+"/data";
		File listaAlunos = new File(path, nomeArquivo);
		
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
	public void inserirGrupos(Aluno aluno) throws Exception {
		
		
	}
}