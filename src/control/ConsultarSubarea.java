package control;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import model.Arquivos;
import model.ListaString;


public class ConsultarSubarea implements IConsultarSubarea{
	File arquivo = new File(Arquivos.pathData, Arquivos.arquivoGrupos);
	int areaNumero=count();
	ListaString[] grupos;
	
	public ConsultarSubarea() {
		grupos = new ListaString[areaNumero];
		for(int i=0;i<areaNumero;i++) {
			grupos[i] = new ListaString();
		}
	}
	
	@Override
	public void buscarSubarea(String subarea) throws Exception {

	}
	
	private int count(){
		int areas=0;
		try {
			FileInputStream fluxo = new FileInputStream(arquivo);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha;
			linha = buffer.readLine();
			while(linha!=null) {
				areas++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return areas;
	}
	
	public int hashCode(int area) {
		return 0; //Incompleto
	}
}
