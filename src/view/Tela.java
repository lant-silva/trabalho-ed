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

public class Tela {

	private JFrame frmSistemaDeGesto;
	private JTextField nomeAluno;
	private JTextField raAluno;
	private String arquivoAlunos = "lista-alunos.csv";
	private InserirAluno controle = new InserirAluno();
	
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
		
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 2, 816, 491);
		frmSistemaDeGesto.getContentPane().add(tabbedPane);
		
		JPanel inserirAluno = new JPanel();
		tabbedPane.addTab("Inserir Aluno", null, inserirAluno, null);
		inserirAluno.setLayout(null);
		
		JTextPane txtNomeAluno = new JTextPane();
		txtNomeAluno.setFont(new Font("Dialog", Font.BOLD, 12));
		txtNomeAluno.setBackground(new Color(238, 238, 238));
		txtNomeAluno.setEditable(false);
		txtNomeAluno.setText("Nome Aluno:");
		txtNomeAluno.setBounds(153, 60, 106, 27);
		inserirAluno.add(txtNomeAluno);
		
		nomeAluno = new JTextField();
		nomeAluno.setBounds(307, 62, 365, 25);
		inserirAluno.add(nomeAluno);
		nomeAluno.setColumns(10);
		
		JTextPane txtRaAluno = new JTextPane();
		txtRaAluno.setText("RA Aluno:");
		txtRaAluno.setFont(new Font("Dialog", Font.BOLD, 12));
		txtRaAluno.setEditable(false);
		txtRaAluno.setBackground(UIManager.getColor("Button.background"));
		txtRaAluno.setBounds(153, 99, 106, 27);
		inserirAluno.add(txtRaAluno);
		
		raAluno = new JTextField();
		raAluno.setColumns(10);
		raAluno.setBounds(307, 99, 365, 25);
		inserirAluno.add(raAluno);
		
		JTextPane txtCurso = new JTextPane();
		txtCurso.setText("Curso:");
		txtCurso.setFont(new Font("Dialog", Font.BOLD, 12));
		txtCurso.setEditable(false);
		txtCurso.setBackground(UIManager.getColor("Button.background"));
		txtCurso.setBounds(153, 154, 106, 27);
		inserirAluno.add(txtCurso);
		
		JComboBox comboCurso = new JComboBox();
		comboCurso.setModel(new DefaultComboBoxModel(new String[] {"Análise e Desenvolvimento de Sistemas", "Desenvolvimento de Software Multi-plataforma", "Comércio Exterior", "Gestão de Recursos Humanos", "Polímeros", "Marketing Digital"}));
		comboCurso.setBounds(307, 154, 365, 27);
		inserirAluno.add(comboCurso);
		
		JTextPane txtPeriodo = new JTextPane();
		txtPeriodo.setText("Período:");
		txtPeriodo.setFont(new Font("Dialog", Font.BOLD, 12));
		txtPeriodo.setEditable(false);
		txtPeriodo.setBackground(UIManager.getColor("Button.background"));
		txtPeriodo.setBounds(153, 209, 106, 27);
		inserirAluno.add(txtPeriodo);
		
		JComboBox comboPeriodo = new JComboBox();
		comboPeriodo.setModel(new DefaultComboBoxModel(new String[] {"Manha", "Tarde", "Noite"}));
		comboPeriodo.setBounds(307, 209, 365, 27);
		inserirAluno.add(comboPeriodo);
		
		JTextPane txtCiclo = new JTextPane();
		txtCiclo.setText("Cíclo/Semestre:");
		txtCiclo.setFont(new Font("Dialog", Font.BOLD, 12));
		txtCiclo.setEditable(false);
		txtCiclo.setBackground(UIManager.getColor("Button.background"));
		txtCiclo.setBounds(153, 271, 106, 27);
		inserirAluno.add(txtCiclo);
		
		JComboBox comboSemestre = new JComboBox();
		comboSemestre.setModel(new DefaultComboBoxModel(new String[] {"1º Semestre", "2º Semestre", "3º Semestre", "4º Semestre", "5º Semestre", "6º Semestre"}));
		comboSemestre.setBounds(307, 271, 365, 27);
		inserirAluno.add(comboSemestre);
		
		JButton btnGravarAluno = new JButton("Gravar Aluno");
		
		btnGravarAluno.setBounds(325, 363, 149, 25);
		inserirAluno.add(btnGravarAluno);
		
		JPanel inserirGrupos = new JPanel();
		tabbedPane.addTab("Inserir Grupos", null, inserirGrupos, null);
		
		JPanel consultaGruposCodigo = new JPanel();
		tabbedPane.addTab("Consulta de Grupos", (Icon) null, consultaGruposCodigo, null);
		
		JPanel inserirOrientacoes = new JPanel();
		tabbedPane.addTab("Inserir Orientações", null, inserirOrientacoes, null);
		
		JPanel consultarUltimaOrientacao = new JPanel();
		tabbedPane.addTab("Consultar Ultima Orientação", null, consultarUltimaOrientacao, null);
		
		JPanel consultarSubarea = new JPanel();
		tabbedPane.addTab("Consultar por Sub-área", null, consultarSubarea, null);
		
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
				controle.manterAluno(arquivoAlunos, aluno, ra, curso, periodo, semestre);
			}
		});
	}
}
