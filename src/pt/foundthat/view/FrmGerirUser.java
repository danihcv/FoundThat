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
import javax.swing.JComboBox;
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
import pt.foundthat.controller.ManagerUser;
import pt.foundthat.model.ModelPrototype;
import pt.foundthat.model.TipoUser;
import pt.foundthat.model.User;

public class FrmGerirUser extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUser;
	private JTextField txtPassword;
	private JTextField txtConfirmarPassword;
	@SuppressWarnings("rawtypes")
	static JComboBox cmbPerfil;
	static DefaultListModel<String> dlm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmGerirUser frame = new FrmGerirUser();
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
	@SuppressWarnings("rawtypes")
	public FrmGerirUser() {
		setResizable(false);
		setTitle("Gerir Utilizador - FoundThat");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmGerirUser.class.getResource("/pt/foundthat/resources/lupa.png")));
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
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtUser = new JTextField();
		txtUser.setForeground(new Color(25, 25, 112));
		txtUser.setBackground(new Color(220, 220, 220));
		txtUser.setBounds(215, 87, 86, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);

		txtPassword = new JTextField();
		txtPassword.setForeground(new Color(25, 25, 112));
		txtPassword.setBackground(new Color(220, 220, 220));
		txtPassword.setBounds(215, 116, 86, 20);
		contentPane.add(txtPassword);

		JLabel lblUtilizador = new JLabel("Utilizador");
		lblUtilizador.setForeground(new Color(25, 25, 112));
		lblUtilizador.setFont(new Font("Myanmar Text", Font.PLAIN, 16));
		lblUtilizador.setBounds(135, 88, 69, 23);
		contentPane.add(lblUtilizador);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(25, 25, 112));
		lblPassword.setFont(new Font("Myanmar Text", Font.PLAIN, 16));
		lblPassword.setBounds(135, 118, 77, 21);
		contentPane.add(lblPassword);

		txtConfirmarPassword = new JTextField();
		txtConfirmarPassword.setForeground(new Color(25, 25, 112));
		txtConfirmarPassword.setBackground(new Color(220, 220, 220));
		txtConfirmarPassword.setBounds(215, 147, 86, 20);
		contentPane.add(txtConfirmarPassword);

		cmbPerfil = new JComboBox();
		cmbPerfil.setForeground(new Color(25, 25, 112));
		cmbPerfil.setBackground(new Color(220, 220, 220));
		refreshTipoUser();
		cmbPerfil.setSelectedIndex(0);
		cmbPerfil.setBounds(215, 178, 86, 20);
		contentPane.add(cmbPerfil);

		//LISTA COM UTILIZADORES
		dlm = new DefaultListModel<String>();
		refreshUser();
		@SuppressWarnings("unchecked")
		JList list = new JList(dlm);
		list.setForeground(new Color(25, 25, 112));
		list.setSelectedIndex(0);
		txtUser.setText(list.getSelectedValue().toString());
		//ASSOCIAR USER (LIST) AO TIPOUSER (COMBOBOX) ANTERIORMENTE ASSOCIADA
		for (User us : FoundThat.users) {
			if (us.getUser().equals(list.getSelectedValue().toString())) {
				cmbPerfil.setSelectedItem(us.getTipo().getNome().substring(0, 1).toUpperCase() + us.getTipo().getNome().substring(1).toLowerCase());
				txtPassword.setText(us.getPassword());
				txtConfirmarPassword.setText(us.getPassword());
			}
		}
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtUser.setText(list.getSelectedValue().toString());
				//ASSOCIAR USER (LIST) AO TIPOUSER (COMBOBOX) ANTERIORMENTE ASSOCIADA
				for (User us : FoundThat.users) {
					if (us.getUser().equals(list.getSelectedValue().toString())) {
						cmbPerfil.setSelectedItem(us.getTipo().getNome().substring(0, 1).toUpperCase() + us.getTipo().getNome().substring(1).toLowerCase());
						txtPassword.setText(us.getPassword());
						txtConfirmarPassword.setText(us.getPassword());
					}
				}
			}
		});
		JScrollPane listScroller = new JScrollPane(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBorder(null);
		list.setBackground(new Color(220, 220, 220));
		listScroller.setBounds(10, 32, 118, 228);
		contentPane.add(listScroller);

		JLabel lblUtilizadores = new JLabel("Utilizadores");
		lblUtilizadores.setForeground(new Color(25, 25, 112));
		lblUtilizadores.setFont(new Font("Myanmar Text", Font.PLAIN, 16));
		lblUtilizadores.setBounds(26, 8, 108, 34);
		contentPane.add(lblUtilizadores);

		JButton btnGravar = new JButton("Adicionar");
		btnGravar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnGravar.setBackground(new Color(210, 105, 30));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnGravar.setBackground(new Color(25, 25, 112));
			}
		});
		btnGravar.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnGravar.setBorder(null);
		btnGravar.setBackground(new Color(25, 25, 112));
		btnGravar.setForeground(new Color(255, 255, 255));
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (txtUser.getText().equals("") || txtPassword.getText().equals("") || txtConfirmarPassword.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Insira os dados!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
				else if (!(txtPassword.getText().equals(txtConfirmarPassword.getText()))) {
					JOptionPane.showMessageDialog(null, "As passwords n�o coincididem!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
					clear();
				}
				else {
					if (!ManagerUser.adicionarUser(txtUser.getText(), txtPassword.getText(), (cmbPerfil.getSelectedItem().toString().toLowerCase()))) {
						JOptionPane.showMessageDialog(null, "O utilizador " + txtUser.getText() + " j� existe!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
						clear();
					}
					else {
						try {
							FoundThat.gravarFicheiro();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "O utilizador " + txtUser.getText() + " foi adicionado!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
						refreshUser();
						clear();
					}
				}
			}
		});
		btnGravar.setBounds(328, 84, 106, 23);
		contentPane.add(btnGravar);



		JLabel lblConfirmePassword = new JLabel("Confirmar");
		lblConfirmePassword.setForeground(new Color(25, 25, 112));
		lblConfirmePassword.setFont(new Font("Myanmar Text", Font.PLAIN, 16));
		lblConfirmePassword.setBounds(135, 144, 148, 31);
		contentPane.add(lblConfirmePassword);

		//SAIR
		JButton btnSair = new JButton("Sair");
		btnSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnSair.setBackground(new Color(210, 105, 30));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSair.setBackground(new Color(25, 25, 112));
			}
		});
		btnSair.setBorder(null);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					FoundThat.gravarFicheiro();
					this.setVisible(false);
					dispose();
					new FrmMain().setVisible(true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub

			}
		});
		btnSair.setForeground(new Color(255, 255, 255));
		btnSair.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnSair.setBackground(new Color(25, 25, 112));
		btnSair.setBounds(328, 175, 106, 23);
		contentPane.add(btnSair);

		JLabel lblPerfil = new JLabel("Perfil");
		lblPerfil.setForeground(new Color(25, 25, 112));
		lblPerfil.setFont(new Font("Myanmar Text", Font.PLAIN, 16));
		lblPerfil.setBounds(135, 179, 69, 23);
		contentPane.add(lblPerfil);

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndex() != -1) {
					if (txtUser.getText().equals("") || txtPassword.getText().equals("") || txtConfirmarPassword.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Introduza um utilizador!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
					}
					else if (!(txtPassword.getText().equals(txtConfirmarPassword.getText()))) {
						JOptionPane.showMessageDialog(null, "As passwords n�o coincididem!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
						clear();
					}
					else {
						int selectedOption = JOptionPane.showOptionDialog(null, "Deseja alterar o utilizador " + list.getSelectedValue().toString() + " pelo utilizador " + txtUser.getText() + "?", "AVISO! - FoundThat", 0, JOptionPane.INFORMATION_MESSAGE, null, opcoes, null); 
						if (selectedOption == JOptionPane.YES_OPTION) {
							if (ManagerUser.alterarUser(txtUser.getText(), list.getSelectedValue().toString(), txtPassword.getText(), cmbPerfil.getSelectedItem().toString().toLowerCase()) == true) {
								refreshUser();
								JOptionPane.showMessageDialog(null, "O utilizador foi alterado!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
								clear();
							}
							else {
								JOptionPane.showMessageDialog(null, "O utilizador " + txtUser.getText() + " j� existe!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
							}

						}
					}

				}
				else {
					JOptionPane.showMessageDialog(null, "Selecione um utilizador!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
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
		btnAlterar.setForeground(Color.WHITE);
		btnAlterar.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnAlterar.setBorder(null);
		btnAlterar.setBackground(new Color(25, 25, 112));
		btnAlterar.setBounds(328, 113, 106, 23);
		contentPane.add(btnAlterar);

		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (list.getSelectedIndex() != -1) {
					int selectedOption = JOptionPane.showOptionDialog(null, "Deseja remover o utilizador " + list.getSelectedValue().toString() + "?", "AVISO! - FoundThat", 0, JOptionPane.INFORMATION_MESSAGE, null, opcoes, null); 
					if (selectedOption == JOptionPane.YES_OPTION) {
						if (ManagerUser.removerUser(list.getSelectedValue().toString()) == false) {
							JOptionPane.showMessageDialog(null, "O utilizador " + list.getSelectedValue().toString() + " foi removido!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
							refreshUser();
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Selecione um utilizador!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
			}


		});
		btnRemover.setForeground(Color.WHITE);
		btnRemover.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnRemover.setBorder(null);
		btnRemover.setBackground(new Color(25, 25, 112));
		btnRemover.setBounds(328, 144, 106, 23);
		contentPane.add(btnRemover);

	}

	private void clear() {
		txtUser.setText("");
		txtPassword.setText("");
		txtConfirmarPassword.setText("");
		cmbPerfil.setSelectedIndex(0);
		txtUser.requestFocusInWindow();
	}

	@SuppressWarnings("unchecked")
	public static void refreshTipoUser() {
		//C�PIA DO ARRAY ORIGINAL DE TIPOUSERS, PARA ORDEN�-LO NA COMBOBOX!
		ArrayList <TipoUser> tipoUsersOrdenado = new ArrayList<TipoUser>();
		for (ModelPrototype t : FoundThat.tipoUsers) {
		    tipoUsersOrdenado.add((TipoUser) t);
        }
		Collections.sort(tipoUsersOrdenado);
		cmbPerfil.removeAllItems();
		for (TipoUser us : tipoUsersOrdenado) {
			cmbPerfil.addItem(us.toString().substring(0, 1).toUpperCase() + us.toString().substring(1).toLowerCase());
		}
	}

	public static void refreshUser() {
		//C�PIA DO ARRAY ORIGINAL DE USERS, PARA ORDEN�-LO NA LIST!
		ArrayList <User> usersOrdenado = new ArrayList<User>(FoundThat.users);
		Collections.sort(usersOrdenado);
		dlm.clear();
		for (User us : usersOrdenado) {
			dlm.addElement(us.getUser());
		}

	}
}