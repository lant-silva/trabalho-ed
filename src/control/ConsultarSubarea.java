package control;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Arquivos;
import model.ListaString;


public class ConsultarSubarea implements IConsultarSubarea{
	File arquivo = new File(Arquivos.pathData, Arquivos.arquivoGrupos);
	int areaNumero=count();
	ListaString[] areas;
	Object[][] newTabela;
	
	public ConsultarSubarea() {
		areas = new ListaString[areaNumero];
		for(int i=0;i<areaNumero;i++) {
			areas[i] = new ListaString();
		}
	}
	
	@Override
	public JTable buscarSubarea(String area, String subarea, JTable tabela) throws Exception {
		int aux = hashCode(area);
		ListaString gruposSub = areas[aux];
		File arquivoGrupos = new File(Arquivos.pathData, Arquivos.arquivoGrupos);
		
		FileInputStream fluxo = new FileInputStream(arquivoGrupos);
		InputStreamReader leitor = new InputStreamReader(fluxo);
		BufferedReader buffer = new BufferedReader(leitor);
		String linha = buffer.readLine();
		
		while(linha!=null) {
			String[] linhaAux = linha.split(";");
			if(linhaAux[4].equals(subarea)) {
				String inserir = linhaAux[0]+";"+linhaAux[1]+";"+linhaAux[2];
				if(gruposSub.isEmpty()) {
					gruposSub.addFirst(inserir);
				}else {
					gruposSub.addLast(inserir);
				}
			}
			linha = buffer.readLine();
		}
		return criarTabela(gruposSub, tabela);
	}
	
	private JTable criarTabela(ListaString gruposSub, JTable tabela) {
		int tamanhoLista = gruposSub.size();
		newTabela = new Object[tamanhoLista][3];		
		for(int i=0;i<tamanhoLista;i++) {
			for(int j=0;j<3;j++) {
				try {
					String linha = gruposSub.get(i);
					String[] separar = linha.split(";");
					newTabela[i][j] = separar[j];
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		while(!gruposSub.isEmpty()) {
			try {
				gruposSub.removeLast();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		tabela.setModel(new DefaultTableModel(
			newTabela,
			new String[] {
				"Codigo Grupo", "Nome Grupo", "Temas"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		return tabela;
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
				linha = buffer.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return areas;
	}
	
	private int hashCode(String area) {
		try(BufferedReader hashBuffer = new BufferedReader(new FileReader(arquivo))) {
			String linha;areaNumero=0;
			while((linha = hashBuffer.readLine())!=null) {
				areaNumero++;
				if(linha.contains(area)) {
					return areaNumero;
				}
			}
			hashBuffer.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}
}
