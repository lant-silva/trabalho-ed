/*
 * Sistema de Gerenciamento de TCC - Trabalho Semestral de Estrutura de Dados ( 3º ADS Tarde ) 
 * Data de finalização: A ser determinada
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */

package view;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import control.InserirAluno;
import model.Aluno;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class Tela {

	private JFrame frmSistemaDeGesto;
	private JTextField nomeAluno;
	private JTextField raAluno;
	private String arquivoAlunos = "lista-alunos.csv";
	private InserirAluno controle = new InserirAluno();
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTable table_1;
	private JTextField txtDia;
	private JTextField txtMes;
	private JTextField txtAno;
	private JTextField txtOrientacao;
	private JTable table_2;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTable table_3;
	
	/**
	 * Launch the application.
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
//===============================================================================================================================//		
		
		
//========================[ Tela Inserir Grupos ]================================================================================//		
		JPanel inserirGrupos = new JPanel();
		tabbedPane.addTab("Inserir Grupos", null, inserirGrupos, null);
		inserirGrupos.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(110, 26, 309, 24);
		inserirGrupos.add(comboBox);
		
		JButton btnAdicionarAluno = new JButton("Adicionar Aluno");
		btnAdicionarAluno.setBounds(431, 25, 144, 25);
		inserirGrupos.add(btnAdicionarAluno);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(617, 29, 182, 21);
		inserirGrupos.add(textPane);
		
		JButton btnInserirGrupo = new JButton("Inserir Grupo");
		btnInserirGrupo.setBounds(228, 409, 127, 25);
		inserirGrupos.add(btnInserirGrupo);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditar.setBounds(367, 409, 117, 25);
		inserirGrupos.add(btnEditar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(496, 409, 117, 25);
		inserirGrupos.add(btnExcluir);
		
		table = new JTable();
		table.setFont(new Font("Dialog", Font.PLAIN, 10));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Nome Aluno", "R.A", "Curso", "Periodo", "Semestre"},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(134);
		table.getColumnModel().getColumn(1).setPreferredWidth(132);
		table.getColumnModel().getColumn(2).setPreferredWidth(192);
		table.getColumnModel().getColumn(3).setPreferredWidth(114);
		table.getColumnModel().getColumn(4).setPreferredWidth(125);
		table.setBounds(12, 95, 787, 64);
		inserirGrupos.add(table);
		
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
		
		textField = new JTextField();
		textField.setBounds(188, 191, 245, 19);
		inserirGrupos.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(188, 231, 245, 19);
		inserirGrupos.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(188, 273, 245, 19);
		inserirGrupos.add(textField_2);
		
		JLabel lblCodigoGrupo = new JLabel("Código Grupo:");
		lblCodigoGrupo.setBounds(470, 193, 105, 15);
		inserirGrupos.add(lblCodigoGrupo);
		
		textField_3 = new JTextField();
		textField_3.setBounds(580, 191, 114, 19);
		inserirGrupos.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblSubrea = new JLabel("Sub-área:");
		lblSubrea.setBounds(470, 233, 70, 15);
		inserirGrupos.add(lblSubrea);
		
		textField_4 = new JTextField();
		textField_4.setBounds(580, 231, 219, 19);
		inserirGrupos.add(textField_4);
		textField_4.setColumns(10);
		
		JPanel consultaGruposCodigo = new JPanel();
		tabbedPane.addTab("Consulta de Grupos", (Icon) null, consultaGruposCodigo, null);
		consultaGruposCodigo.setLayout(null);
		
		JLabel lblCdigoDoGrupo = new JLabel("Código do Grupo");
		lblCdigoDoGrupo.setBounds(120, 76, 118, 15);
		consultaGruposCodigo.add(lblCdigoDoGrupo);
		
		textField_5 = new JTextField();
		textField_5.setBounds(282, 74, 287, 19);
		consultaGruposCodigo.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(581, 71, 117, 25);
		consultaGruposCodigo.add(btnBuscar);
		
		JLabel lblNomeGrupo_1 = new JLabel("Nome Grupo:");
		lblNomeGrupo_1.setBounds(120, 133, 92, 15);
		consultaGruposCodigo.add(lblNomeGrupo_1);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setBounds(282, 133, 287, 21);
		consultaGruposCodigo.add(textPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"Codigo Grupo", "Nome Grupo", "Tema"},
				{null, null, null},
			},
			new String[] {
				"New column", "New column", "New column"
			}
		));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(140);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(125);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(280);
		table_1.setBounds(120, 189, 578, 141);
		consultaGruposCodigo.add(table_1);
		
		JPanel inserirOrientacoes = new JPanel();
		tabbedPane.addTab("Inserir Orientações", null, inserirOrientacoes, null);
		inserirOrientacoes.setLayout(null);
		
		JLabel lblCdigoGrupo = new JLabel("Código Grupo:");
		lblCdigoGrupo.setBounds(58, 40, 101, 15);
		inserirOrientacoes.add(lblCdigoGrupo);
		
		JComboBox comboCodigoGrupo = new JComboBox();
		comboCodigoGrupo.setBounds(188, 35, 148, 24);
		inserirOrientacoes.add(comboCodigoGrupo);
		
		JLabel lblDataOrientao = new JLabel("Data Orientação");
		lblDataOrientao.setBounds(58, 83, 117, 15);
		inserirOrientacoes.add(lblDataOrientao);
		
		txtDia = new JTextField();
		txtDia.setBounds(188, 81, 39, 19);
		inserirOrientacoes.add(txtDia);
		txtDia.setColumns(10);
		
		JLabel label = new JLabel("/");
		label.setBounds(228, 83, 11, 15);
		inserirOrientacoes.add(label);
		
		txtMes = new JTextField();
		txtMes.setColumns(10);
		txtMes.setBounds(238, 81, 39, 19);
		inserirOrientacoes.add(txtMes);
		
		JLabel label_1 = new JLabel("/");
		label_1.setBounds(278, 83, 11, 15);
		inserirOrientacoes.add(label_1);
		
		txtAno = new JTextField();
		txtAno.setColumns(10);
		txtAno.setBounds(289, 81, 47, 19);
		inserirOrientacoes.add(txtAno);
		
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
				{"Codigo Grupo", "Data Orienta\u00E7ao", "Nome Orienta\u00E7ao"},
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
		
		JButton btnNewButton = new JButton("Inserir Orientação");
		btnNewButton.setBounds(167, 409, 169, 25);
		inserirOrientacoes.add(btnNewButton);
		
		JButton btnEditar_1 = new JButton("Editar");
		btnEditar_1.setBounds(348, 409, 117, 25);
		inserirOrientacoes.add(btnEditar_1);
		
		JButton btnExcluir_1 = new JButton("Excluir");
		btnExcluir_1.setBounds(477, 409, 117, 25);
		inserirOrientacoes.add(btnExcluir_1);
		
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
		
		table_3 = new JTable();
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
				{"Codigo Grupo", "Data Orientacao", "Nome Orientacao"},
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
				"New column", "New column", "New column"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_3.getColumnModel().getColumn(0).setPreferredWidth(104);
		table_3.getColumnModel().getColumn(1).setPreferredWidth(129);
		table_3.getColumnModel().getColumn(2).setPreferredWidth(366);
		table_3.setBounds(97, 234, 556, 135);
		consultarUltimaOrientacao.add(table_3);
		
		JPanel consultarSubarea = new JPanel();
		tabbedPane.addTab("Consultar por Sub-área", null, consultarSubarea, null);

		
		
//====================[ Ação que gera a lista de alunos da tela "Inserir Grupo" ]========================//		
		inserirGrupos.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				Aluno criar = null;

				
			}
		});
		
//====================[ Ação do botão Gravar Aluno da tela "Inserir Aluno" ]=============================//		
		btnGravarAluno.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String aluno = nomeAluno.getText();
				String ra = raAluno.getText();
				String curso = (String) comboCurso.getSelectedItem();
				String periodo = (String) comboPeriodo.getSelectedItem();
				String semestre="";
				for(int i=1;i<=6;i++) {
					String a = (String)(comboSemestre.getSelectedItem());
					if(a.contains(Integer.toString(i))) {
						semestre = Integer.toString(i);
					}
				}
				try {
					controle.manterAluno(arquivoAlunos, aluno, ra, curso, periodo, semestre);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}
