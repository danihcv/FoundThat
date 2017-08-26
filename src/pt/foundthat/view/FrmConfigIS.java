package pt.foundthat.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import pt.foundthat.controller.FoundThat;
import pt.foundthat.controller.ManagerIS;
import pt.foundthat.model.Instituicao;
import pt.foundthat.model.ModelStrategy;

public class FrmConfigIS extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIS;
	static DefaultListModel<String> dlm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmConfigIS frame = new FrmConfigIS();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmConfigIS() {
		setResizable(false);
		FoundThat.managerEntity = new ManagerIS();
		//OPCOES MESSAGEBOX(SIM/N�O)
		String[] opcoes = new String[2];
		opcoes[0] = new String("Sim");
		opcoes[1] = new String("N�o");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmConfigSala.class.getResource("/pt/foundthat/resources/lupa.png")));
		setTitle("Gest\u00E3o de Institui��es - FoundThat");
		setBounds(100, 100, 462, 300);
		this.setLocationRelativeTo(null);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int selectedOption = JOptionPane.showOptionDialog(null, "Deseja sair da aplica��o?", "AVISO! - FoundThat", 0, JOptionPane.INFORMATION_MESSAGE, null, opcoes, null); 
				if (selectedOption == JOptionPane.YES_OPTION) {
					try {
						FoundThat.gravarFicheiro();
						dispose();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				else {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				}

			}
		});
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtIS = new JTextField();
		txtIS.setForeground(new Color(25, 25, 112));
		txtIS.setBackground(new Color(220, 220, 220));
		txtIS.setBounds(295, 76, 106, 23);
		contentPane.add(txtIS);
		txtIS.setColumns(10);

		//LISTA COM INSTITUI��ES
		dlm = new DefaultListModel<String>();
		refresh();
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JList list = new JList(dlm);
		list.setForeground(new Color(25, 25, 112));
		list.setSelectedIndex(0);
		txtIS.setText(list.getSelectedValue().toString().substring(0, 1).toUpperCase() + list.getSelectedValue().toString().substring(1).toLowerCase());
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtIS.setText(list.getSelectedValue().toString().substring(0, 1).toUpperCase() + list.getSelectedValue().toString().substring(1).toLowerCase());
			}
		});
		JScrollPane listScroller = new JScrollPane(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBorder(null);
		list.setBackground(new Color(220, 220, 220));
		listScroller.setBounds(58, 37, 118, 228);
		contentPane.add(listScroller);

		//ADICIONAR INSTITUI��O
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBorder(null);
		btnAdicionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAdicionar.setBackground(new Color(210, 105, 30));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAdicionar.setBackground(new Color(25, 25, 112));
			}
		});
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtIS.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Introduza uma institui��o!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
				else {
					ModelStrategy is = new Instituicao(-1, txtIS.getText().toLowerCase());
					if (!FoundThat.managerEntity.adicionarEntity(is)) {
						JOptionPane.showMessageDialog(null, "A institui��o " + txtIS.getText().substring(0, 1).toUpperCase() + txtIS.getText().substring(1).toLowerCase() + " j� existe!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
					}
					else {
						try {
							FoundThat.gravarFicheiro();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "A institui��o " + txtIS.getText().substring(0, 1).toUpperCase() + txtIS.getText().substring(1).toLowerCase() + " foi adicionada!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;	
						refresh();
					}
				}
			}
		});
		btnAdicionar.setForeground(Color.WHITE);
		btnAdicionar.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnAdicionar.setBackground(new Color(25, 25, 112));
		btnAdicionar.setBounds(295, 110, 106, 23);
		contentPane.add(btnAdicionar);

		//ALTERAR INSTITUI��O
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBorder(null);
		btnAlterar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAlterar.setBackground(new Color(210, 105, 30));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAlterar.setBackground(new Color(25, 25, 112));
			}
		});
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndex() != -1) {
					if (list.getSelectedValue().equals(txtIS.getText().toUpperCase())) {
						JOptionPane.showMessageDialog(null, "A institui��o " + txtIS.getText().toUpperCase() + " j� existe!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
					}
					else if (txtIS.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Introduza uma institui��o!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
					}
					else {
						int selectedOption = JOptionPane.showOptionDialog(null, "Deseja alterar a institui��o " + list.getSelectedValue().toString() + " pela institui��o " + txtIS.getText() + "?", "AVISO! - FoundThat", 0, JOptionPane.INFORMATION_MESSAGE, null, opcoes, null); 
						if (selectedOption == JOptionPane.YES_OPTION) {
						    ModelStrategy isNova = new Instituicao(-1, txtIS.getText().toLowerCase());
						    ModelStrategy isAntiga = new Instituicao(-1, list.getSelectedValue().toString().toLowerCase());
							if (((ManagerIS)FoundThat.managerEntity).alterarEntity(isNova, isAntiga)) {
								JOptionPane.showMessageDialog(null, "A institui��o foi alterada!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
								refresh();
							}
							else {
								JOptionPane.showMessageDialog(null, "A institui��o " + txtIS.getText().substring(0, 1).toUpperCase() + txtIS.getText().substring(1).toLowerCase() + " j� existe!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
							}

						}
					}

				}
				else {
					JOptionPane.showMessageDialog(null, "Selecione uma institui��o!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
			}
		});
		btnAlterar.setForeground(Color.WHITE);
		btnAlterar.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnAlterar.setBackground(new Color(25, 25, 112));
		btnAlterar.setBounds(295, 144, 106, 23);
		contentPane.add(btnAlterar);

		//REMOVER INSTITUI��O
		JButton btnRemover = new JButton("Remover");
		btnRemover.setBorder(null);
		btnRemover.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRemover.setBackground(new Color(210, 105, 30));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnRemover.setBackground(new Color(25, 25, 112));
			}
		});
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (list.getSelectedIndex() != -1) {
					int selectedOption = JOptionPane.showOptionDialog(null, "Deseja remover a institui��o " + list.getSelectedValue().toString().substring(0, 1).toUpperCase() + list.getSelectedValue().toString().substring(1).toLowerCase() + "?", "AVISO! - FoundThat", 0, JOptionPane.INFORMATION_MESSAGE, null, opcoes, null); 
					if (selectedOption == JOptionPane.YES_OPTION) {
						String isRemovida = list.getSelectedValue().toString().substring(0, 1).toUpperCase() + list.getSelectedValue().toString().substring(1).toLowerCase();
						if (!((ManagerIS)FoundThat.managerEntity).isAtribuida(list.getSelectedValue().toString().toLowerCase())) {
							if (FoundThat.managerEntity.removerEntity(list.getSelectedValue().toString().toLowerCase())) {
								JOptionPane.showMessageDialog(null, "A institui��o " + isRemovida + " foi removida!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);
								refresh();
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "A institui��o " + isRemovida + " tem objetos associados, elimine esses objetos primeiro!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Selecione uma institui��o!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
			}
		});
		btnRemover.setForeground(Color.WHITE);
		btnRemover.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnRemover.setBackground(new Color(25, 25, 112));
		btnRemover.setBounds(295, 178, 106, 23);
		contentPane.add(btnRemover);

		//SAIR
		JButton btnSair = new JButton("Sair");
		btnSair.setBorder(null);
		btnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSair.setBackground(new Color(210, 105, 30));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSair.setBackground(new Color(25, 25, 112));
			}
		});
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				this.setVisible(false);
				dispose();
				new FrmMain().setVisible(true);
			}

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub

			}
		});
		btnSair.setForeground(Color.WHITE);
		btnSair.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnSair.setBackground(new Color(25, 25, 112));
		btnSair.setBounds(295, 211, 106, 23);
		contentPane.add(btnSair);

		JLabel lblInstituicao = new JLabel("Institui\u00E7\u00E3o");
		lblInstituicao.setForeground(new Color(25, 25, 112));
		lblInstituicao.setFont(new Font("Myanmar Text", Font.PLAIN, 16));
		lblInstituicao.setBounds(214, 76, 118, 26);
		contentPane.add(lblInstituicao);

		JLabel lblInstituicoes = new JLabel("Institui\u00E7\u00F5es");
		lblInstituicoes.setForeground(new Color(25, 25, 112));
		lblInstituicoes.setFont(new Font("Myanmar Text", Font.PLAIN, 16));
		lblInstituicoes.setBounds(79, 11, 108, 34);
		contentPane.add(lblInstituicoes);

	}

	public static void refresh() {
		//C�PIA DO ARRAY ORIGINAL DE INSTITUICOES, PARA ORDEN�-LO NA LIST!
		ArrayList <Instituicao> instituicoesOrdenado = new ArrayList<>();
		for(ModelStrategy is : FoundThat.instituicoes) {
		    instituicoesOrdenado.add((Instituicao) is);
        }
		Collections.sort(instituicoesOrdenado);
		dlm.clear();
		for (Instituicao is : instituicoesOrdenado) {
			dlm.addElement(is.toString().substring(0, 1).toUpperCase() + is.toString().substring(1).toLowerCase());
		}

	}
}
