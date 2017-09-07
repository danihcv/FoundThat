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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import pt.foundthat.controller.FoundThat;
import pt.foundthat.controller.ManagerTipoUser;
import pt.foundthat.model.ModelPrototype;
import pt.foundthat.model.TipoUser;

public class FrmPerfilUser extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	static DefaultListModel<String> dlm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPerfilUser frame = new FrmPerfilUser();
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
	public FrmPerfilUser() {
		FoundThat.managerEntity = new ManagerTipoUser();
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmPerfilUser.class.getResource("/pt/foundthat/resources/lupa.png")));
		setTitle("Gerir perfil - FoundThat");
		setResizable(false);
		//OPCOES MESSAGEBOX(SIM/N�O)
		String[] opcoes = new String[2];
		opcoes[0] = new String("Sim");
		opcoes[1] = new String("N�o");

		setBounds(100, 100, 450, 300);
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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JRadioButton radioRegisto = new JRadioButton("Registo");
		radioRegisto.setFont(new Font("Myanmar Text", Font.PLAIN, 12));
		radioRegisto.setForeground(new Color(25, 25, 112));
		radioRegisto.setBounds(184, 80, 65, 23);
		contentPane.add(radioRegisto);

		JRadioButton radioReclamacao = new JRadioButton("Reclama\u00E7\u00E3o");
		radioReclamacao.setFont(new Font("Myanmar Text", Font.PLAIN, 12));
		radioReclamacao.setForeground(new Color(25, 25, 112));
		radioReclamacao.setBounds(271, 80, 99, 23);
		contentPane.add(radioReclamacao);

		JRadioButton radioImportacao = new JRadioButton("Importa\u00E7\u00E3o");
		radioImportacao.setFont(new Font("Myanmar Text", Font.PLAIN, 12));
		radioImportacao.setForeground(new Color(25, 25, 112));
		radioImportacao.setBounds(184, 106, 89, 23);
		contentPane.add(radioImportacao);

		JRadioButton radioListagens = new JRadioButton("Listagens");
		radioListagens.setFont(new Font("Myanmar Text", Font.PLAIN, 12));
		radioListagens.setForeground(new Color(25, 25, 112));
		radioListagens.setBounds(271, 106, 74, 23);
		contentPane.add(radioListagens);

		JRadioButton radioDoacoes = new JRadioButton("Doa\u00E7\u00F5es");
		radioDoacoes.setFont(new Font("Myanmar Text", Font.PLAIN, 12));
		radioDoacoes.setForeground(new Color(25, 25, 112));
		radioDoacoes.setBounds(184, 132, 74, 23);
		contentPane.add(radioDoacoes);

		JRadioButton radioConfiguracoes = new JRadioButton("Configura\u00E7\u00F5es");
		radioConfiguracoes.setFont(new Font("Myanmar Text", Font.PLAIN, 12));
		radioConfiguracoes.setForeground(new Color(25, 25, 112));
		radioConfiguracoes.setBounds(271, 132, 110, 23);
		contentPane.add(radioConfiguracoes);

		txtNome = new JTextField();
		txtNome.setForeground(new Color(25, 25, 112));
		txtNome.setBackground(new Color(220, 220, 220));
		txtNome.setBounds(256, 50, 89, 23);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		//LISTA COM PERFIS
		dlm = new DefaultListModel<String>();
		refreshUser();
		JScrollPane listScroller = new JScrollPane();
		listScroller.setBounds(44, 32, 80, 228);
		contentPane.add(listScroller);
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JList list = new JList(dlm);
		listScroller.setViewportView(list);
		list.setForeground(new Color(25, 25, 112));
		list.setSelectedIndex(0);
		txtNome.setText(list.getSelectedValue().toString());
		//ASSOCIAR USER (LIST) AO TIPOUSER (COMBOBOX) ANTERIORMENTE ASSOCIADA
		for (ModelPrototype t : FoundThat.tipoUsers) {
			TipoUser tu = (TipoUser)t;
			if (tu.getNome().equals(list.getSelectedValue().toString().toLowerCase())) {
				radioRegisto.setSelected(tu.isRegisto());
				radioReclamacao.setSelected(tu.isReclamacao());
				radioImportacao.setSelected(tu.isImportacao());
				radioListagens.setSelected(tu.isListagens());
				radioDoacoes.setSelected(tu.isDoacoes());
				radioConfiguracoes.setSelected(tu.isConfiguracoes());
			}
		}
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtNome.setText(list.getSelectedValue().toString());
				//ASSOCIAR USER (LIST) AO TIPOUSER (COMBOBOX) ANTERIORMENTE ASSOCIADA
				for (ModelPrototype t : FoundThat.tipoUsers) {
					TipoUser tu = (TipoUser)t;
					if (tu.getNome().equals(list.getSelectedValue().toString().toLowerCase())) {
						radioRegisto.setSelected(tu.isRegisto());
						radioReclamacao.setSelected(tu.isReclamacao());
						radioImportacao.setSelected(tu.isImportacao());
						radioListagens.setSelected(tu.isListagens());
						radioDoacoes.setSelected(tu.isDoacoes());
						radioConfiguracoes.setSelected(tu.isConfiguracoes());
					}
				}
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBorder(null);
		list.setBackground(new Color(220, 220, 220));

		//ADICIONAR PERFIL
		JButton btnCriar = new JButton("Criar");
		btnCriar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnCriar.setBackground(new Color(210, 105, 30));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnCriar.setBackground(new Color(25, 25, 112));
			}
		});
		btnCriar.setForeground(new Color(255, 255, 255));
		btnCriar.setBackground(new Color(25, 25, 112));
		btnCriar.setBorder(null);
		btnCriar.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtNome.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Introduza um nome!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
				else {
					TipoUser tu = (TipoUser) FoundThat.prototypeTipoUser.clone();
					tu.setNome(txtNome.getText().toLowerCase());
					tu.setRegisto(radioRegisto.isSelected());
					tu.setReclamacao(radioReclamacao.isSelected());
					tu.setImportacao(radioImportacao.isSelected());
					tu.setListagens(radioListagens.isSelected());
					tu.setDoacoes(radioDoacoes.isSelected());
					tu.setConfiguracoes(radioConfiguracoes.isSelected());

					if (!FoundThat.managerEntity.adicionarEntity(tu)) {
						JOptionPane.showMessageDialog(null, "O perfil " + txtNome.getText() + " j� existe!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;				
					}
					else {
						dlm.addElement(txtNome.getText());
						JOptionPane.showMessageDialog(null, "O perfil " + txtNome.getText() + " foi adicionado!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;	
						refreshUser();
						try {
							FoundThat.gravarFicheiro();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}

		});
		btnCriar.setBounds(184, 162, 89, 23);
		contentPane.add(btnCriar);
		//REMOVER PERFIL
		JButton btnRemover = new JButton("Remover");
		btnRemover.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnRemover.setBackground(new Color(210, 105, 30));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnRemover.setBackground(new Color(25, 25, 112));
			}
		});
		btnRemover.setForeground(new Color(255, 255, 255));
		btnRemover.setBackground(new Color(25, 25, 112));
		btnRemover.setBorder(null);
		btnRemover.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (list.getSelectedIndex() != -1) {
					int selectedOption = JOptionPane.showOptionDialog(null, "Deseja remover o perfil " + list.getSelectedValue().toString() + "?", "AVISO! - FoundThat", 0, JOptionPane.INFORMATION_MESSAGE, null, opcoes, null); 
					if (selectedOption == JOptionPane.YES_OPTION) {
						if (FoundThat.managerEntity.removerEntity(list.getSelectedValue().toString().toLowerCase())) {
							String perfilRemovido = list.getSelectedValue().toString();
							dlm.removeElement(perfilRemovido);
							JOptionPane.showMessageDialog(null, "O perfil " + perfilRemovido + " foi removido!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
							try {
								FoundThat.gravarFicheiro();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Selecione um perfil!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
			}
		});
		btnRemover.setBounds(184, 196, 89, 23);
		contentPane.add(btnRemover);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndex() != -1) {
					if (txtNome.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Introduza um nome!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
					}
					else {
						int selectedOption = JOptionPane.showOptionDialog(null, "Deseja alterar o perfil " + list.getSelectedValue().toString() + " pelo perfil " + txtNome.getText().substring(0, 1).toUpperCase() + txtNome.getText().substring(1).toLowerCase()+ "?", "AVISO! - FoundThat", 0, JOptionPane.INFORMATION_MESSAGE, null, opcoes, null); 
						if (selectedOption == JOptionPane.YES_OPTION) {
							TipoUser tuNovo = (TipoUser) FoundThat.prototypeTipoUser.clone();
							tuNovo.setNome(txtNome.getText().toLowerCase());
							tuNovo.setRegisto(radioRegisto.isSelected());
							tuNovo.setReclamacao(radioReclamacao.isSelected());
							tuNovo.setImportacao(radioImportacao.isSelected());
							tuNovo.setListagens(radioListagens.isSelected());
							tuNovo.setDoacoes(radioDoacoes.isSelected());
							tuNovo.setConfiguracoes(radioConfiguracoes.isSelected());

							TipoUser tuAntigo = (TipoUser) FoundThat.prototypeTipoUser.clone();
							tuAntigo.setNome(list.getSelectedValue().toString().toLowerCase());
							tuAntigo.setRegisto(radioRegisto.isSelected());
							tuAntigo.setReclamacao(radioReclamacao.isSelected());
							tuAntigo.setImportacao(radioImportacao.isSelected());
							tuAntigo.setListagens(radioListagens.isSelected());
							tuAntigo.setDoacoes(radioDoacoes.isSelected());
							tuAntigo.setConfiguracoes(radioConfiguracoes.isSelected());

							if (FoundThat.managerEntity.alterarEntity(tuNovo, tuAntigo)) {
								refreshUser();
								JOptionPane.showMessageDialog(null, "O perfil foi alterado!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
							}
							else {
								JOptionPane.showMessageDialog(null, "O perfil " + txtNome.getText() + " j� existe!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
							}

						}
					}

				}
				else {
					JOptionPane.showMessageDialog(null, "Selecione um perfil!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
			}
		});
		btnAlterar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnAlterar.setBackground(new Color(210, 105, 30));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAlterar.setBackground(new Color(25, 25, 112));
			}
		});
		btnAlterar.setForeground(new Color(255, 255, 255));
		btnAlterar.setBackground(new Color(25, 25, 112));
		btnAlterar.setBorder(null);
		btnAlterar.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnAlterar.setBounds(281, 162, 89, 23);
		contentPane.add(btnAlterar);

		JLabel lblPerfil = new JLabel("Perfil");
		lblPerfil.setForeground(new Color(25, 25, 112));
		lblPerfil.setFont(new Font("Myanmar Text", Font.PLAIN, 16));
		lblPerfil.setBounds(204, 49, 42, 28);
		contentPane.add(lblPerfil);

		JButton btnSair = new JButton("Sair");
		btnSair.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnSair.setForeground(new Color(255, 255, 255));
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
			public void actionPerformed(ActionEvent e) {
				this.setVisible(false);
				dispose();
				new FrmMain().setVisible(true);
			}

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub

			}
		});
		btnSair.setBackground(new Color(25, 25, 112));
		btnSair.setBounds(281, 197, 89, 23);
		contentPane.add(btnSair);

		JLabel lblPerfis = new JLabel("Perfis");
		lblPerfis.setForeground(new Color(25, 25, 112));
		lblPerfis.setFont(new Font("Myanmar Text", Font.PLAIN, 16));
		lblPerfis.setBounds(61, 8, 42, 28);
		contentPane.add(lblPerfis);
	}

	public static void refreshUser() {
		//C�PIA DO ARRAY ORIGINAL DE TIPO USERS, PARA ORDEN�-LO NA LIST!
		ArrayList <TipoUser> tipoUsersOrdenado = new ArrayList<>();
		for (ModelPrototype t : FoundThat.tipoUsers) {
			tipoUsersOrdenado.add((TipoUser) t);
		}
		Collections.sort(tipoUsersOrdenado);
		dlm.clear();
		for (TipoUser tu : tipoUsersOrdenado) {
			dlm.addElement(tu.toString().substring(0, 1).toUpperCase() + tu.toString().substring(1).toLowerCase());
		}
	}
}

