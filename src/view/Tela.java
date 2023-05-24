/*
 * Sistema de Gerenciamento de TCC - Trabalho Semestral de Estrutura de Dados ( 3º ADS Tarde ) 
 * Data de finalização: A ser determinada
 * 
 * Estrutura do corpo: - Variaveis
 * 					   - Corpo das telas
 * 					   - Ações dos botões
 * 					   - Métodos
 * 
 * 
 */

package view;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import control.ConsultaGrupos;
import control.InserirAluno;
import control.InserirGrupos;
import control.InserirOrientacoes;
import model.Aluno;

public class Tela {
	/*
	 * Variaveis
	 *
	 */
	
	private JFrame frmSistemaDeGesto;
	private JTextField nomeAluno;
	private JTextField raAluno;
	private static final String pathData = System.getProperty("user.dir")+File.separator+"data";
	private static final String arquivoAlunos = "lista-alunos.csv";
	private static final String arquivoGrupos = "lista-grupos.csv";
	private JTable tabelaAlunos;
	private JTextField inserirNomeGrupo;
	private JTextField inserirTemaGrupo;
	private JTextField inserirCodigoGrupo;
	private JTextField buscaCodigoGrupo;
	private JTable table_1;
	private JTextField txtData;
	private JTextField txtOrientacao;
	private JTable table_2;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTable table_3;
	private JTextField textField_9;
	private JTable table_4;
	private JTextField nomeGrupo;
	
	Aluno alunoT1=new Aluno("","","","",0);
	Aluno alunoT2=new Aluno("","","","",0);
	Aluno alunoT3=new Aluno("","","","",0);
	Aluno alunoT4=new Aluno("","","","",0);
	
	private boolean orientacaoValida = true;

	private InserirAluno ManterAlunos = new InserirAluno();
	private InserirGrupos ManterGrupos = new InserirGrupos();
	private InserirOrientacoes ManterOrientacoes = new InserirOrientacoes(); 
	private ConsultaGrupos BuscarGrupos = new ConsultaGrupos();
	
	String[] alunosLista=null; //Tela Inserir Grupos: Implementação do comboBox de alunos
	private JTextField codigoGrupoOrientacoes;
	
	/**
	 * Main
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela window = new Tela();
					window.frmSistemaDeGesto.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tela() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
//==================[ Criação da Tela ]=======================================//
		frmSistemaDeGesto = new JFrame();
		frmSistemaDeGesto.setTitle("Sistema de Gestão de TCC");
		frmSistemaDeGesto.setBounds(100, 100, 824, 521);
		frmSistemaDeGesto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSistemaDeGesto.getContentPane().setLayout(null);
//============================================================================//
		
		
//=================[ Tela Inserir Aluno ]================================================================================//		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 2, 816, 491);
		frmSistemaDeGesto.getContentPane().add(tabbedPane);
		
		JPanel inserirAluno = new JPanel();
		tabbedPane.addTab("Inserir Aluno", null, inserirAluno, null);
		inserirAluno.setLayout(null);
		
		nomeAluno = new JTextField();
		nomeAluno.setBounds(307, 62, 365, 25);
		inserirAluno.add(nomeAluno);
		nomeAluno.setColumns(10);
		
		raAluno = new JTextField();
		
		//Código para impedir caracteres não-numéricos de serem inseridos
		raAluno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char i = e.getKeyChar();
                if (!Character.isLetter(i) || Character.isWhitespace(i) || Character.isISOControl(i))
                {
                    raAluno.setEditable(true);
                }
                else
                {
                    raAluno.setEditable(false);
                }
			}
		});
		raAluno.setColumns(10);
		raAluno.setBounds(307, 99, 365, 25);
		inserirAluno.add(raAluno);
		
		JComboBox comboCurso = new JComboBox();
		comboCurso.setModel(new DefaultComboBoxModel(new String[] {"Análise e Desenvolvimento de Sistemas", "Desenvolvimento de Software Multi-plataforma", "Comércio Exterior", "Gestão de Recursos Humanos", "Polímeros", "Marketing Digital"}));
		comboCurso.setBounds(307, 154, 365, 27);
		inserirAluno.add(comboCurso);
		
		JComboBox comboPeriodo = new JComboBox();
		comboPeriodo.setModel(new DefaultComboBoxModel(new String[] {"Manha", "Tarde", "Noite"}));
		comboPeriodo.setBounds(307, 209, 365, 27);
		inserirAluno.add(comboPeriodo);
		
		JComboBox comboSemestre = new JComboBox();
		comboSemestre.setModel(new DefaultComboBoxModel(new String[] {"1º Semestre", "2º Semestre", "3º Semestre", "4º Semestre", "5º Semestre", "6º Semestre"}));
		comboSemestre.setBounds(307, 271, 365, 27);
		inserirAluno.add(comboSemestre);
		
		JButton btnGravarAluno = new JButton("Gravar Aluno");

		
		btnGravarAluno.setBounds(325, 363, 149, 25);
		inserirAluno.add(btnGravarAluno);
		
		JLabel lblNomeAluno = new JLabel("Nome Aluno:");
		lblNomeAluno.setBounds(153, 67, 89, 15);
		inserirAluno.add(lblNomeAluno);
		
		JLabel lblRaAluno = new JLabel("RA Aluno:");
		lblRaAluno.setBounds(153, 104, 70, 15);
		inserirAluno.add(lblRaAluno);
		
		JLabel lblCurso = new JLabel("Curso:");
		lblCurso.setBounds(153, 160, 70, 15);
		inserirAluno.add(lblCurso);
		
		JLabel lblPerodo = new JLabel("Período:");
		lblPerodo.setBounds(153, 215, 70, 15);
		inserirAluno.add(lblPerodo);
		
		JLabel lblCclosemestre = new JLabel("Cíclo/Semestre:");
		lblCclosemestre.setBounds(153, 277, 110, 15);
		inserirAluno.add(lblCclosemestre);
//====================================================================================================================//		
		

//===================[ Tela Inserir Grupos ]==========================================================================//
		JPanel inserirGrupos = new JPanel();
		tabbedPane.addTab("Inserir Grupos", null, inserirGrupos, null);
		inserirGrupos.setLayout(null);
		
		JComboBox listaAlunosTabela = new JComboBox();
		try {
			alunosLista = ManterGrupos.popularListaAlunos(pathData, arquivoAlunos);
			listaAlunosTabela.setModel(new DefaultComboBoxModel(alunosLista));
			listaAlunosTabela.setMaximumRowCount(999);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		listaAlunosTabela.setBounds(110, 26, 309, 24);
		inserirGrupos.add(listaAlunosTabela);
		
		
		JButton btnAdicionarAluno = new JButton("Adicionar Aluno");

		btnAdicionarAluno.setBounds(431, 25, 144, 25);
		inserirGrupos.add(btnAdicionarAluno);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(617, 29, 182, 21);
		inserirGrupos.add(textPane);
		
		String raSelecionado = (String) listaAlunosTabela.getSelectedItem();
		try {
			textPane.setText(ManterGrupos.popularRa(pathData, arquivoAlunos, raSelecionado));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		JButton btnInserirGrupo = new JButton("Inserir Grupo");

		btnInserirGrupo.setBounds(139, 409, 127, 25);
		inserirGrupos.add(btnInserirGrupo);
		
		JButton btnEditar = new JButton("Editar");


		btnEditar.setBounds(278, 409, 117, 25);
		inserirGrupos.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");

		btnExcluir.setBounds(407, 409, 117, 25);
		inserirGrupos.add(btnExcluir);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(12, 95, 787, 86);
		inserirGrupos.add(scrollPane_2);
		
		tabelaAlunos = new JTable();
		tabelaAlunos.setEnabled(false);
		scrollPane_2.setViewportView(tabelaAlunos);
		tabelaAlunos.setFont(new Font("Dialog", Font.PLAIN, 10));
		montarTabela();
		
		tabelaAlunos.getColumnModel().getColumn(0).setResizable(false);
		tabelaAlunos.getColumnModel().getColumn(0).setPreferredWidth(134);
		tabelaAlunos.getColumnModel().getColumn(1).setResizable(false);
		tabelaAlunos.getColumnModel().getColumn(1).setPreferredWidth(132);
		tabelaAlunos.getColumnModel().getColumn(2).setResizable(false);
		tabelaAlunos.getColumnModel().getColumn(2).setPreferredWidth(192);
		tabelaAlunos.getColumnModel().getColumn(3).setResizable(false);
		tabelaAlunos.getColumnModel().getColumn(3).setPreferredWidth(114);
		tabelaAlunos.getColumnModel().getColumn(4).setResizable(false);
		tabelaAlunos.getColumnModel().getColumn(4).setPreferredWidth(125);
		
		JLabel lblNomeAluno_1 = new JLabel("Nome Aluno:");
		lblNomeAluno_1.setBounds(12, 31, 89, 15);
		inserirGrupos.add(lblNomeAluno_1);
		
		JLabel lblRa = new JLabel("RA:");
		lblRa.setBounds(593, 31, 70, 15);
		inserirGrupos.add(lblRa);
		
		JLabel lblMembros = new JLabel("Membros:");
		lblMembros.setBounds(12, 68, 70, 15);
		inserirGrupos.add(lblMembros);
		
		JLabel lblNomeGrupo = new JLabel("Nome Grupo:");
		lblNomeGrupo.setBounds(12, 193, 105, 15);
		inserirGrupos.add(lblNomeGrupo);
		
		JLabel lblreaDeConhecimento = new JLabel("Área de Conhecimento:");
		lblreaDeConhecimento.setBounds(12, 233, 182, 15);
		inserirGrupos.add(lblreaDeConhecimento);
		
		JLabel lblTemaGrupo = new JLabel("Tema Grupo:");
		lblTemaGrupo.setBounds(12, 275, 105, 15);
		inserirGrupos.add(lblTemaGrupo);
		
		inserirNomeGrupo = new JTextField();
		inserirNomeGrupo.setBounds(188, 191, 245, 19);
		inserirGrupos.add(inserirNomeGrupo);
		inserirNomeGrupo.setColumns(10);
		
		inserirTemaGrupo = new JTextField();
		inserirTemaGrupo.setColumns(10);
		inserirTemaGrupo.setBounds(188, 273, 245, 19);
		inserirGrupos.add(inserirTemaGrupo);
		
		JLabel lblCodigoGrupo = new JLabel("Código Grupo:");
		lblCodigoGrupo.setBounds(470, 193, 105, 15);
		inserirGrupos.add(lblCodigoGrupo);
		
		inserirCodigoGrupo = new JTextField();
		inserirCodigoGrupo.setBounds(580, 191, 114, 19);
		inserirGrupos.add(inserirCodigoGrupo);
		inserirCodigoGrupo.setColumns(10);
		
		JLabel lblSubrea = new JLabel("Sub-área:");
		lblSubrea.setBounds(470, 233, 70, 15);
		inserirGrupos.add(lblSubrea);
		
		JComboBox comboAreaInserir = new JComboBox();
		comboAreaInserir.setBounds(188, 228, 245, 24);
		inserirGrupos.add(comboAreaInserir);
		
		JComboBox comboSubareaInserir = new JComboBox();
		comboSubareaInserir.setBounds(580, 228, 219, 24);
		inserirGrupos.add(comboSubareaInserir);
		
		JButton btnLimparCampos = new JButton("Limpar Campos");

		btnLimparCampos.setBounds(534, 409, 144, 25);
		inserirGrupos.add(btnLimparCampos);
		
		JPanel inserirOrientacoes = new JPanel();
		tabbedPane.addTab("Inserir Orientações", null, inserirOrientacoes, null);
		inserirOrientacoes.setLayout(null);
		
		JLabel lblCdigoGrupo = new JLabel("Código Grupo:");
		lblCdigoGrupo.setBounds(58, 40, 101, 15);
		inserirOrientacoes.add(lblCdigoGrupo);
		
		JLabel lblDataOrientao = new JLabel("Data Orientação");
		lblDataOrientao.setBounds(58, 83, 117, 15);
		inserirOrientacoes.add(lblDataOrientao);
		
		txtData = new JTextField();
		txtData.setBounds(188, 81, 112, 19);
		inserirOrientacoes.add(txtData);
		txtData.setColumns(10);
		
		JLabel lblNovaOrientao = new JLabel("Nova Orientação:");
		lblNovaOrientao.setBounds(58, 127, 123, 15);
		inserirOrientacoes.add(lblNovaOrientao);
		
		txtOrientacao = new JTextField();
		txtOrientacao.setBounds(188, 125, 227, 19);
		inserirOrientacoes.add(txtOrientacao);
		txtOrientacao.setColumns(10);
		
		JLabel lblDescrioOrientao = new JLabel("Descrição Orientação:");
		lblDescrioOrientao.setBounds(58, 169, 162, 15);
		inserirOrientacoes.add(lblDescrioOrientao);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(58, 196, 694, 98);
		inserirOrientacoes.add(scrollPane);
		
		JTextArea descricaoOrientacao = new JTextArea();
		scrollPane.setViewportView(descricaoOrientacao);
		descricaoOrientacao.setLineWrap(true);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{"Codigo Grupo", "Data Orientacao", "Nome Orientacao"},
				{null, null, null},
			},
			new String[] {
				"C\u00F3digo Grupo", "Data Orienta\u00E7\u00E3o", "Nome Orienta\u00E7\u00E3o"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_2.getColumnModel().getColumn(0).setPreferredWidth(178);
		table_2.getColumnModel().getColumn(1).setPreferredWidth(143);
		table_2.getColumnModel().getColumn(2).setPreferredWidth(257);
		table_2.setBounds(81, 324, 630, 32);
		inserirOrientacoes.add(table_2);
		
		JButton inserirOrientacao = new JButton("Inserir Orientação");

		inserirOrientacao.setBounds(167, 409, 169, 25);
		inserirOrientacoes.add(inserirOrientacao);
		
		JButton btnEditar_1 = new JButton("Editar");
		btnEditar_1.setBounds(348, 409, 117, 25);
		inserirOrientacoes.add(btnEditar_1);
		
		JButton btnExcluir_1 = new JButton("Excluir");
		btnExcluir_1.setBounds(477, 409, 117, 25);
		inserirOrientacoes.add(btnExcluir_1);
		
		codigoGrupoOrientacoes = new JTextField();
		codigoGrupoOrientacoes.setBounds(186, 38, 114, 19);
		inserirOrientacoes.add(codigoGrupoOrientacoes);
		codigoGrupoOrientacoes.setColumns(10);
		
		JButton btnBuscar_3 = new JButton("Buscar");

		btnBuscar_3.setBounds(312, 35, 117, 25);
		inserirOrientacoes.add(btnBuscar_3);
		
		JPanel consultaGruposCodigo = new JPanel();
		tabbedPane.addTab("Consulta de Grupos", (Icon) null, consultaGruposCodigo, null);
		consultaGruposCodigo.setLayout(null);
		
		JLabel lblCdigoDoGrupo = new JLabel("Código do Grupo");
		lblCdigoDoGrupo.setBounds(120, 76, 118, 15);
		consultaGruposCodigo.add(lblCdigoDoGrupo);
		
		buscaCodigoGrupo = new JTextField();
		buscaCodigoGrupo.setBounds(282, 74, 287, 19);
		consultaGruposCodigo.add(buscaCodigoGrupo);
		buscaCodigoGrupo.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");

		btnBuscar.setBounds(581, 71, 117, 25);
		consultaGruposCodigo.add(btnBuscar);
		
		JLabel lblNomeGrupo_1 = new JLabel("Nome Grupo:");
		lblNomeGrupo_1.setBounds(120, 133, 92, 15);
		consultaGruposCodigo.add(lblNomeGrupo_1);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(120, 189, 578, 141);
		consultaGruposCodigo.add(scrollPane_4);
		
		table_1 = new JTable();
		scrollPane_4.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"", "", ""},
			},
			new String[] {
				"Codigo Grupo", "Nome Grupo", "Tema"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		nomeGrupo = new JTextField();
		nomeGrupo.setEditable(false);
		nomeGrupo.setBounds(282, 131, 287, 19);
		consultaGruposCodigo.add(nomeGrupo);
		nomeGrupo.setColumns(10);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(140);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(125);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(280);
		
		JPanel consultarUltimaOrientacao = new JPanel();
		tabbedPane.addTab("Consultar Ultima Orientação", null, consultarUltimaOrientacao, null);
		consultarUltimaOrientacao.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Informe o Código do Grupo:");
		lblNewLabel.setBounds(61, 55, 194, 15);
		consultarUltimaOrientacao.add(lblNewLabel);
		
		textField_6 = new JTextField();
		textField_6.setBounds(273, 53, 249, 19);
		consultarUltimaOrientacao.add(textField_6);
		textField_6.setColumns(10);
		
		JButton btnBuscar_1 = new JButton("Buscar");
		btnBuscar_1.setBounds(536, 50, 117, 25);
		consultarUltimaOrientacao.add(btnBuscar_1);
		
		JLabel lblDataOrientao_1 = new JLabel("Data Orientação");
		lblDataOrientao_1.setBounds(61, 108, 124, 15);
		consultarUltimaOrientacao.add(lblDataOrientao_1);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setBounds(273, 106, 249, 19);
		consultarUltimaOrientacao.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setEditable(false);
		textField_8.setBounds(273, 164, 249, 19);
		consultarUltimaOrientacao.add(textField_8);
		textField_8.setColumns(10);
		
		JLabel lblNomeOrientao = new JLabel("Nome Orientação:");
		lblNomeOrientao.setBounds(61, 166, 137, 15);
		consultarUltimaOrientacao.add(lblNomeOrientao);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(97, 234, 556, 135);
		consultarUltimaOrientacao.add(scrollPane_3);
		
		table_3 = new JTable();
		scrollPane_3.setViewportView(table_3);
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
				{"", "", ""},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Codigo Grupo", "Data Orienta\u00E7\u00E3o", "Nome Orienta\u00E7\u00E3o"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_3.getColumnModel().getColumn(0).setPreferredWidth(104);
		table_3.getColumnModel().getColumn(1).setPreferredWidth(129);
		table_3.getColumnModel().getColumn(2).setPreferredWidth(366);
		
		JPanel consultarSubarea = new JPanel();
		tabbedPane.addTab("Consultar por Sub-área", null, consultarSubarea, null);
		consultarSubarea.setLayout(null);
		
		JLabel lblInformeASubrea = new JLabel("Informe a Sub-área:");
		lblInformeASubrea.setBounds(112, 74, 141, 15);
		consultarSubarea.add(lblInformeASubrea);
		
		textField_9 = new JTextField();
		textField_9.setBounds(271, 74, 281, 17);
		consultarSubarea.add(textField_9);
		textField_9.setColumns(10);
		
		JButton btnBuscar_2 = new JButton("Buscar");
		btnBuscar_2.setBounds(564, 69, 117, 25);
		consultarSubarea.add(btnBuscar_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(112, 129, 569, 134);
		consultarSubarea.add(scrollPane_1);
		
		table_4 = new JTable();
		scrollPane_1.setViewportView(table_4);
		table_4.setModel(new DefaultTableModel(
			new Object[][] {
				{"", "", "", ""},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Codigo Grupo", "Nome Grupo", "Temas", "\u00C1rea de Conhecimento"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_4.getColumnModel().getColumn(0).setPreferredWidth(101);
		table_4.getColumnModel().getColumn(1).setPreferredWidth(170);
		table_4.getColumnModel().getColumn(2).setPreferredWidth(150);
		table_4.getColumnModel().getColumn(3).setPreferredWidth(212);

		/*
		 * Ações do sistema
		 * Variam de botões, cliques do mouse, entradas e saidas, etc. 
		 * 
		 * 
		 */
		

//===================[ Ação que insere um aluno na lista de alunos da tela "Inserir Aluno" ]============//		
		btnGravarAluno.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nome = nomeAluno.getText();
				String ra = raAluno.getText();
				String curso = (String) comboCurso.getSelectedItem();
				String periodo = (String) comboPeriodo.getSelectedItem();
				String semestre = (String) comboSemestre.getSelectedItem();
				semestre = semestre.substring(0,1);
				int ciclo = Integer.parseInt(semestre);
				try {
					ManterAlunos.manterAluno(pathData, arquivoAlunos, nome, ra, curso, periodo, ciclo);
					alunosLista = ManterGrupos.popularListaAlunos(pathData, arquivoAlunos);
					listaAlunosTabela.setModel(new DefaultComboBoxModel(alunosLista));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
//==========================[ Botão que adiciona um aluno em um grupo ]=========================//		
		btnAdicionarAluno.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String nomeaux = (String) listaAlunosTabela.getSelectedItem();
				popularTabela(nomeaux);
				montarTabela();	
			}
		});
		
		listaAlunosTabela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					textPane.setText(ManterGrupos.popularRa(pathData, arquivoAlunos, raSelecionado));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
//============[ Ação que limpa os campos da tela "Inserir Grupos" ]==============//		
		btnLimparCampos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				inserirNomeGrupo.setText("");
				inserirCodigoGrupo.setText("");
				inserirTemaGrupo.setText("");
				alunoT1=new Aluno("","","","",0);
				alunoT2=new Aluno("","","","",0);
				alunoT3=new Aluno("","","","",0);
				alunoT4=new Aluno("","","","",0);
				montarTabela();
			}
		});
		
//============[ Ação que insere um grupo na lista de grupos, da tela "Inserir Grupos" ]==========//
		btnInserirGrupo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Aluno[] grupo = {alunoT1,alunoT2,alunoT3,alunoT4};
				String codigoG = inserirCodigoGrupo.getText();
				String tema = inserirTemaGrupo.getText();
				String nomeG = inserirNomeGrupo.getText();
				String area = (String) comboAreaInserir.getSelectedItem();
				String subarea = (String) comboSubareaInserir.getSelectedItem();
				try {
					ManterGrupos.inserirGrupos(arquivoGrupos, pathData, grupo, codigoG, tema, nomeG, area, subarea);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String cod = buscaCodigoGrupo.getText();
				try {
					String[] grupoValores = BuscarGrupos.BuscarGrupo(pathData, arquivoGrupos, cod);
					montarTabelaGrupo(grupoValores);
					nomeGrupo.setText(grupoValores[1]);nomeGrupo.setEnabled(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		btnBuscar_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String cod = codigoGrupoOrientacoes.getText();
				try {
					String[] aux = BuscarGrupos.BuscarGrupo(pathData, arquivoGrupos, cod);
					if(!aux[0].equals(cod)){
						orientacaoValida = false;
					}else {
						orientacaoValida = true;
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		inserirOrientacao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String cod = codigoGrupoOrientacoes.getText();
				String data = txtData.getText();
				String nome = txtOrientacao.getText();
				String descricao = descricaoOrientacao.getText();
				if(!orientacaoValida) {	
					JOptionPane.showMessageDialog(null, "O grupo especificado não existe");
				}else {
					try {
						ManterOrientacoes.manterOrientacoes(pathData, arquivoGrupos, cod, data, nome, descricao);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Aluno[] grupo = {alunoT1,alunoT2,alunoT3,alunoT4};
				String codigoG = inserirCodigoGrupo.getText();
				String tema = inserirTemaGrupo.getText();
				String nomeG = inserirNomeGrupo.getText();
				String area = (String) comboAreaInserir.getSelectedItem();
				String subarea = (String) comboSubareaInserir.getSelectedItem();
				try {
					ManterGrupos.editarGrupos(arquivoGrupos, pathData, grupo, codigoG, tema, nomeG, area, subarea);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		
		btnExcluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Aluno[] grupo = {alunoT1,alunoT2,alunoT3,alunoT4};
				String codigoG = inserirCodigoGrupo.getText();
				String tema = inserirTemaGrupo.getText();
				String nomeG = inserirNomeGrupo.getText();
				String area = (String) comboAreaInserir.getSelectedItem();
				String subarea = (String) comboSubareaInserir.getSelectedItem();
				try {
					ManterGrupos.excluirGrupos(arquivoGrupos, pathData, grupo, codigoG, tema, nomeG, area, subarea);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
	}
	
	public void montarTabela() {
		tabelaAlunos.setModel(new DefaultTableModel(
				new Object[][] {
					{alunoT1.getAluno(),alunoT1.getRa(),alunoT1.getCurso(),alunoT1.getPeriodo(),Integer.toString(alunoT1.getCiclo())},
					{alunoT2.getAluno(),alunoT2.getRa(),alunoT2.getCurso(),alunoT2.getPeriodo(),Integer.toString(alunoT2.getCiclo())},
					{alunoT3.getAluno(),alunoT3.getRa(),alunoT3.getCurso(),alunoT3.getPeriodo(),Integer.toString(alunoT3.getCiclo())},
					{alunoT4.getAluno(),alunoT4.getRa(),alunoT4.getCurso(),alunoT4.getPeriodo(),Integer.toString(alunoT4.getCiclo())},
				},
				new String[] {
					"Nome Aluno", "R.A", "Curso", "Periodo", "Semestre"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
	}
	
	public void montarTabelaGrupo(String[] var) {
		table_1.setModel(new DefaultTableModel(
				new Object[][] {
					{var[1], var[0], var[2]},
				},
				new String[] {
					"Codigo Grupo", "Nome Grupo", "Tema"
				}
			) {
				boolean[] columnEditables = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
	}
	
	public boolean verificarExistenteTabela(String aluno) {
		if(aluno.equals(alunoT1.getAluno())||aluno.equals(alunoT2.getAluno())||aluno.equals(alunoT3.getAluno())||aluno.equals(alunoT4.getAluno())) {
			JOptionPane.showMessageDialog(null, "O aluno já foi inserido no grupo atual");
			return true;
		}else {
			return false;
		}
	}
	
	public void popularTabela(String nomeaux) {
		
		if(alunoT1.getAluno()=="") {
				try {
					alunoT1 = ManterGrupos.popularTabelaInserirGrupo(arquivoAlunos, nomeaux);
				} catch (Exception e1) {
					e1.printStackTrace();
			}
		}else if(alunoT2.getAluno()=="") {
			if(verificarExistenteTabela(nomeaux)==false) {
				try {
					alunoT2 = ManterGrupos.popularTabelaInserirGrupo(arquivoAlunos, nomeaux);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}else if(alunoT3.getAluno()==""){
			if(verificarExistenteTabela(nomeaux)==false) {
				try {
					alunoT3 = ManterGrupos.popularTabelaInserirGrupo(arquivoAlunos, nomeaux);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}else if(alunoT4.getAluno()==""){
			if(verificarExistenteTabela(nomeaux)==false) {
				try {
					alunoT4 = ManterGrupos.popularTabelaInserirGrupo(arquivoAlunos, nomeaux);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}else {
			JOptionPane.showMessageDialog(null, "O grupo já atingiu o número máximo de participantes");					
		}
	}
}
