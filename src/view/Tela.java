/*
 * Sistema de Gerenciamento de TCC - Trabalho Semestral de Estrutura de Dados ( 3º ADS Tarde ) 
 * Data de início do desenvolvimento: 19/05/2023
 * Data da ultima atualização: 31/05/2023 02:13
 * 
 * Estrutura do corpo: - Variaveis
 * 					   - Corpo das telas
 * 					   - Ações dos botões
 * 					   - Métodos
 * 
 * Informações sobre a diagramação do projeto estão disponíveis no repositório
 * 
 */

package view;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import javax.swing.table.TableModel;

import control.ConsultaGrupos;
import control.ConsultaOrientacoes;
import control.ConsultarSubarea;
import control.InserirAluno;
import control.InserirGrupos;
import control.InserirOrientacoes;
import model.Aluno;
import model.Orientacoes;
import model.Pilha;

public class Tela {
	/*
	 * Variaveis
	 *
	 */
	
	private JFrame frmSistemaDeGesto;
	private JTextField nomeAluno;
	private JTextField raAluno;
	private JTable tabelaAlunos;
	private JTextField inserirNomeGrupo;
	private JTextField inserirTemaGrupo;
	private JTextField inserirCodigoGrupo;
	private JTextField buscaCodigoGrupo;
	private JTable table_1;
	private JTextField txtData;
	private JTextField txtOrientacao;
	private JTable table_2;
	private JTextField inserirCodigoBuscaOrientacao;
	private JTextField dataOrientacao;
	private JTextField nomeOrientacao;
	private JTable tabelaUltimaOrientacao;
	private JTable table_4;
	private JTextField nomeGrupo;
	
	Aluno alunoT1=new Aluno("","","","",0);
	Aluno alunoT2=new Aluno("","","","",0);
	Aluno alunoT3=new Aluno("","","","",0);
	Aluno alunoT4=new Aluno("","","","",0);
	
	private boolean orientacaoValida = true;
	
	private Pilha orientacoes;

	private InserirAluno ManterAlunos = new InserirAluno();
	private InserirGrupos ManterGrupos = new InserirGrupos();
	private InserirOrientacoes ManterOrientacoes = new InserirOrientacoes(); 
	private ConsultaGrupos BuscarGrupos = new ConsultaGrupos();
	private ConsultaOrientacoes ConsultarUltimaOrientacao = new ConsultaOrientacoes();
	private ConsultarSubarea ConsultarSubArea = new ConsultarSubarea();
	
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
		frmSistemaDeGesto.setTitle("Sistema de Gestão de TCC - "+System.getProperty("os.name"));
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
		        if (raAluno.getText().length() >= 13)
		        {
		            raAluno.setEditable(false);
		        }
		        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
		        {
		            raAluno.setEditable(true);
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
			alunosLista = ManterGrupos.popularListaAlunos();
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
		try {
			String[] areas = ManterGrupos.popularAreas();
			comboAreaInserir.setModel(new DefaultComboBoxModel(areas));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		JComboBox comboSubareaInserir = new JComboBox();
		comboSubareaInserir.setBounds(580, 228, 219, 24);
		inserirGrupos.add(comboSubareaInserir);
		String areaAtual = (String) comboAreaInserir.getSelectedItem();
		String[] subareas=null;
		try {
			subareas = ManterGrupos.popularSubareas(areaAtual);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		comboSubareaInserir.setModel(new DefaultComboBoxModel(subareas));
		
		JButton btnLimparCampos = new JButton("Limpar Campos");

		btnLimparCampos.setBounds(534, 409, 144, 25);
		inserirGrupos.add(btnLimparCampos);
//=================================================================================================//		
		
//=============================[ Tela: Inserir Orientações ]=======================================//
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
				"Codigo Grupo", "Data Orientacao", "Nome Orientacao"
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

		inserirOrientacao.setBounds(198, 409, 169, 25);
		inserirOrientacoes.add(inserirOrientacao);
		
		JButton btnFinalizar = new JButton("Finalizar Ultima Orientação");

		btnFinalizar.setBounds(404, 409, 233, 25);
		inserirOrientacoes.add(btnFinalizar);
		
		codigoGrupoOrientacoes = new JTextField();
		codigoGrupoOrientacoes.setBounds(186, 38, 114, 19);
		inserirOrientacoes.add(codigoGrupoOrientacoes);
		codigoGrupoOrientacoes.setColumns(10);
		
		JButton btnBuscar_3 = new JButton("Buscar");

		btnBuscar_3.setBounds(312, 35, 117, 25);
		inserirOrientacoes.add(btnBuscar_3);
//============================================================================================//		
		
//=======================[ Tela: Consulta de Grupos ]=========================================//
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
//=======================================================================================================//
		
//====================[ Tela: Consultar ultima orientação ]==============================================//		
		JPanel consultarUltimaOrientacao = new JPanel();
		tabbedPane.addTab("Consultar Ultima Orientação", null, consultarUltimaOrientacao, null);
		consultarUltimaOrientacao.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Informe o Código do Grupo:");
		lblNewLabel.setBounds(61, 55, 194, 15);
		consultarUltimaOrientacao.add(lblNewLabel);
		
		inserirCodigoBuscaOrientacao = new JTextField();
		inserirCodigoBuscaOrientacao.setBounds(273, 53, 249, 19);
		consultarUltimaOrientacao.add(inserirCodigoBuscaOrientacao);
		inserirCodigoBuscaOrientacao.setColumns(10);
		
		JButton btnBuscarOrientacao = new JButton("Buscar");

		btnBuscarOrientacao.setBounds(536, 50, 117, 25);
		consultarUltimaOrientacao.add(btnBuscarOrientacao);
		
		JLabel lblDataOrientao_1 = new JLabel("Data Orientação");
		lblDataOrientao_1.setBounds(61, 108, 124, 15);
		consultarUltimaOrientacao.add(lblDataOrientao_1);
		
		dataOrientacao = new JTextField();
		dataOrientacao.setEditable(false);
		dataOrientacao.setBounds(273, 106, 249, 19);
		consultarUltimaOrientacao.add(dataOrientacao);
		dataOrientacao.setColumns(10);
		
		nomeOrientacao = new JTextField();
		nomeOrientacao.setEditable(false);
		nomeOrientacao.setBounds(273, 164, 249, 19);
		consultarUltimaOrientacao.add(nomeOrientacao);
		nomeOrientacao.setColumns(10);
		
		JLabel lblNomeOrientao = new JLabel("Nome Orientação:");
		lblNomeOrientao.setBounds(61, 166, 137, 15);
		consultarUltimaOrientacao.add(lblNomeOrientao);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(12, 246, 503, 135);
		consultarUltimaOrientacao.add(scrollPane_3);
		
		tabelaUltimaOrientacao = new JTable();
		scrollPane_3.setViewportView(tabelaUltimaOrientacao);
		tabelaUltimaOrientacao.setModel(new DefaultTableModel(
			new Object[][] {
				{"", "", ""},
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
		
		JTextArea txtDescricao = new JTextArea();
		txtDescricao.setEditable(false);
		txtDescricao.setLineWrap(true);
		txtDescricao.setBounds(536, 247, 249, 135);
		consultarUltimaOrientacao.add(txtDescricao);
		
		JLabel lblDescrioDaOrientao = new JLabel("Descrição da Orientação");
		lblDescrioDaOrientao.setBounds(572, 220, 174, 15);
		consultarUltimaOrientacao.add(lblDescrioDaOrientao);
		tabelaUltimaOrientacao.getColumnModel().getColumn(0).setPreferredWidth(104);
		tabelaUltimaOrientacao.getColumnModel().getColumn(1).setPreferredWidth(129);
		tabelaUltimaOrientacao.getColumnModel().getColumn(2).setPreferredWidth(366);
//===============================================================================================//		
		
//======================[ Tela: Consultar por Sub-Área ]=========================================//	
		JPanel consultarSubarea = new JPanel();
		tabbedPane.addTab("Consultar por Sub-área", null, consultarSubarea, null);
		consultarSubarea.setLayout(null);
		
		JButton btnBuscar_2 = new JButton("Buscar");

		btnBuscar_2.setBounds(339, 306, 117, 25);
		consultarSubarea.add(btnBuscar_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(112, 129, 569, 134);
		consultarSubarea.add(scrollPane_1);
		
		table_4 = new JTable();
		scrollPane_1.setViewportView(table_4);
		table_4.setModel(new DefaultTableModel(
			new Object[][] {
				{"", "", ""},
			},
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
		table_4.getColumnModel().getColumn(0).setPreferredWidth(101);
		table_4.getColumnModel().getColumn(1).setPreferredWidth(170);
		table_4.getColumnModel().getColumn(2).setPreferredWidth(150);
		
		JComboBox comboConsultaArea = new JComboBox();

		comboConsultaArea.setBounds(199, 69, 206, 24);
		consultarSubarea.add(comboConsultaArea);
		try {
			String[] areaConsulta = ManterGrupos.popularAreas();
			comboConsultaArea.setModel(new DefaultComboBoxModel(areaConsulta));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		JComboBox comboConsultaSubarea = new JComboBox();
		comboConsultaSubarea.setBounds(546, 69, 206, 24);
		consultarSubarea.add(comboConsultaSubarea);
		try {
			String[] subareaConsulta = ManterGrupos.popularSubareas(comboConsultaArea.getSelectedItem().toString());
			comboConsultaSubarea.setModel(new DefaultComboBoxModel(subareaConsulta));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		JLabel lblArea = new JLabel("Área de Conhecimento");
		lblArea.setBounds(30, 74, 174, 15);
		consultarSubarea.add(lblArea);
		
		JLabel lblNewLabel_1 = new JLabel("Sub-área");
		lblNewLabel_1.setBounds(470, 74, 70, 15);
		consultarSubarea.add(lblNewLabel_1);
//======================================================================================================//
		
		/*
		 * Ações do sistema
		 * Variam de botões, cliques do mouse, entradas e saidas, etc. 
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
					ManterAlunos.manterAluno(nome, ra, curso, periodo, ciclo);
					alunosLista = ManterGrupos.popularListaAlunos();
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
					ManterGrupos.inserirGrupos(grupo, codigoG, tema, nomeG, area, subarea);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

//============[ Ação que mostra o RA de um aluno selecionado na tela de Inserir Grupos ==========//
		listaAlunosTabela.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					String aux = (String) listaAlunosTabela.getSelectedItem();
					aux  = ManterGrupos.popularRa(aux);
					textPane.setText(aux);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

//==========[ Ação que faz a busca de um grupo da tela Consultar Grupos ]=========//
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String cod = buscaCodigoGrupo.getText();
				try {
					String[] grupoValores = BuscarGrupos.BuscarGrupo(cod);
					if(grupoValores!=null) {						
						montarTabelaGrupo(grupoValores);
						nomeGrupo.setText(grupoValores[1]);nomeGrupo.setEnabled(true);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});

//=============[ Ação que insere as areas e sub-áreas nos comboBox da tela de Inserir Grupos]=======//
		comboAreaInserir.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String areaAtual = (String) comboAreaInserir.getSelectedItem();
				try {
					String[] subareas = ManterGrupos.popularSubareas(areaAtual);
					comboSubareaInserir.setModel(new DefaultComboBoxModel(subareas));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
//==============[ Ação para verificar a existencia de um grupo na tela Inserir Orientações ]========//
		btnBuscar_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String cod = codigoGrupoOrientacoes.getText();
				try {
					String[] aux = BuscarGrupos.BuscarGrupo(cod);
					if(aux!=null) {
						if(!aux[0].equals(cod)){
							orientacaoValida = false;
						}else {
							orientacaoValida = true;
						}
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

//=============[ Ação para inserir uma orientação em um arquivo CSV ]========================//
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
						ManterOrientacoes.manterOrientacoes(cod, data, nome, descricao);
						table_2.setModel(new DefaultTableModel(
								new Object[][] {
									{"Codigo Grupo", "Data Orientacao", "Nome Orientacao"},
									{cod, data, nome},
								},
								new String[] {
									"Codigo Grupo", "Data Orientacao", "Nome Orientacao"
								}
							) {
								boolean[] columnEditables = new boolean[] {
									false, false, false
								};
								public boolean isCellEditable(int row, int column) {
									return columnEditables[column];
								}
							});
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
		});

//==============[ Ação para finalizar a ultima orientação de um grupo na tela Inserir Orientação ]=======//
		btnFinalizar.addMouseListener(new MouseAdapter() {
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
						ManterOrientacoes.finalizarUltimaOrientacao(cod);
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
		});

//============[ Ação para obter uma formatação correta da data, na tela Inserir Orientação ]===========//
		txtData.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
		        if (!Character.isLetter(c) || Character.isWhitespace(c) || Character.isISOControl(c))
		        {
		            txtData.setEditable(true);
		        }
		        else
		        {
		            txtData.setEditable(false);
		            return;
		        }
				String texto = txtData.getText();
				int keyCode = e.getKeyCode();
				
		        if (txtData.getText().length() >= 10)
		        {
		            txtData.setEditable(false);
		        }
		        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
		        {
		            txtData.setEditable(true);
		        }
		        if ((texto.length() == 2 || texto.length() == 5) && !texto.endsWith("/")) {
		            txtData.setText(texto + "/");
		        }
		        if (keyCode == KeyEvent.VK_BACK_SPACE && (texto.length() == 3 || texto.length() == 6)) {
		            txtData.setText(texto.substring(0, texto.length() - 1));
		        }
			}
		});
		
//==================[ Ação para editar um grupo existente na tela Inserir Grupos ]================//
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
					if(codigoG=="") {
						JOptionPane.showMessageDialog(null, "Insira o codigo do grupo a ser editado");
					}else {
						ManterGrupos.editarGrupos(grupo, codigoG, tema, nomeG, area, subarea);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});

//============[ Ação para excluir um grupo existente na tela Inserir Grupos ]============//
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
					ManterGrupos.excluirGrupos(grupo, codigoG, tema, nomeG, area, subarea);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});	

//==========[ Ação para consultar a ultima orientação de um grupo ]===============//
		btnBuscarOrientacao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					orientacoes = ConsultarUltimaOrientacao.consultarOrientacao(inserirCodigoBuscaOrientacao.getText().toString());
					Orientacoes aux = new Orientacoes("","","","");
					if(!orientacoes.isEmpty()) {						
						aux = (Orientacoes) orientacoes.pop();
					}
					dataOrientacao.setText(aux.getData());
					nomeOrientacao.setText(aux.getNome());
					txtDescricao.setText(aux.getDescricao());
					String[][] mostrarUltimaOrientacao = {{aux.getCodigoGrupo(), aux.getData(), aux.getNome()}};
					montarTabelaOrientacao(mostrarUltimaOrientacao);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

//=============[ Ação para consultar os grupos de uma determinada sub-área, na tela Consultar por Sub-Área ]=========//
		btnBuscar_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					table_4 = ConsultarSubArea.buscarSubarea(comboConsultaArea.getSelectedItem().toString(), comboConsultaSubarea.getSelectedItem().toString(), table_4);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
//============[ Ação para popular o comboBox das sub-áreas ]=====================//
		comboConsultaArea.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

					String areaAtual = (String) comboConsultaArea.getSelectedItem();
					try {
						String[] subareas = ManterGrupos.popularSubareas(areaAtual);
						comboConsultaSubarea.setModel(new DefaultComboBoxModel(subareas));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}
		});
	}
	
	/*
	 * Métodos
	 * Algumas funções para auxiliar a construção do corpo
	 * 
	 */
	
	

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
					{var[0], var[1], var[2]},
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
	
	public void montarTabelaOrientacao(String[][] orientacao) {

			tabelaUltimaOrientacao.setModel(new DefaultTableModel(
					orientacao,
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
		
	}
	
	public void montarTabelaSubarea(String[][] grupos) {
		int tamanho = grupos.length;
		for(int i=0;i<tamanho;i++) {
			for(int j=0;j<3;j++) {
				table_4.getModel().setValueAt(grupos[i][j], i, j);				
			}
		}
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
					alunoT1 = ManterGrupos.popularTabelaInserirGrupo(nomeaux);
				} catch (Exception e1) {
					e1.printStackTrace();
			}
		}else if(alunoT2.getAluno()=="") {
			if(verificarExistenteTabela(nomeaux)==false) {
				try {
					alunoT2 = ManterGrupos.popularTabelaInserirGrupo(nomeaux);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}else if(alunoT3.getAluno()==""){
			if(verificarExistenteTabela(nomeaux)==false) {
				try {
					alunoT3 = ManterGrupos.popularTabelaInserirGrupo(nomeaux);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}else if(alunoT4.getAluno()==""){
			if(verificarExistenteTabela(nomeaux)==false) {
				try {
					alunoT4 = ManterGrupos.popularTabelaInserirGrupo(nomeaux);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}else {
			JOptionPane.showMessageDialog(null, "O grupo já atingiu o número máximo de participantes");				
			
		}
	}
}
