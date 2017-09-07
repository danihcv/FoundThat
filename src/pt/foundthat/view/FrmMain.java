package pt.foundthat.view;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pt.foundthat.controller.FoundThat;
import pt.foundthat.model.ModelPrototype;
import pt.foundthat.model.TipoUser;
import pt.foundthat.model.User;


public class FrmMain extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static boolean res;
	static JMenuBar menuPrincipal;
	static JMenu menuRegisto;
	static JMenu menuReclamacao;
	static JMenu menuImportacao;
	static JMenu menuListagens;
	static JMenu menuDoacoes;
	static JMenu menuConfiguracoes;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */


	public FrmMain() {
		setResizable(false);
		//OPCOES MESSAGEBOX(SIM/N�O)
		String[] opcoes = new String[2];
		opcoes[0] = new String("Sim");
		opcoes[1] = new String("N�o");

		setFont(new Font("Myriad Pro", Font.PLAIN, 12));
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmMain.class.getResource("/pt/foundthat/resources/lupa.png")));
		setTitle("FoundThat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 300);
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

		menuPrincipal = new JMenuBar();
		menuPrincipal.setBorderPainted(false);
		menuPrincipal.setBackground(new Color(255, 255, 255));
		setJMenuBar(menuPrincipal);
		//REGISTO
		menuRegisto = new JMenu("Registo");
		menuRegisto.setFont(new Font("Myanmar Text", Font.PLAIN, 18));
		menuRegisto.setForeground(new Color(0, 0, 128));
		menuRegisto.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (FoundThat.salas.isEmpty()) {
					JOptionPane.showMessageDialog(getParent(), "N�o tem salas adicionadas!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
				else if (FoundThat.tipoObjetos.isEmpty()) {
					JOptionPane.showMessageDialog(getParent(), "N�o tem objetos adicionados!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
				else if (FoundThat.salas.isEmpty() && FoundThat.tipoObjetos.isEmpty()) {
					JOptionPane.showMessageDialog(getParent(), "N�o tem salas, nem objetos adicionados!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
				else {
					dispose();
					this.setVisible(false);
					new FrmRegisto().setVisible(true);
				}
			}

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuRegisto.setSelected(false);
				menuRegisto.setForeground(new Color(210, 105, 30));
				menuRegisto.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuRegisto.setForeground(new Color(0, 0, 128));
			}

		});
		menuPrincipal.add(menuRegisto);

		//RECLAMA��O
		menuReclamacao = new JMenu("Reclama\u00E7\u00E3o");
		menuReclamacao.setBackground(new Color(25, 25, 112));
		menuReclamacao.setFont(new Font("Myanmar Text", Font.PLAIN, 18));
		menuReclamacao.setForeground(new Color(192, 192, 192));
		menuReclamacao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//VERIFICA SE O ARRAY SALAS E O ARRAY OBJETOS EST�O VAZIOS, CASO ESTEJAM N�O PERMITEM A ENTRADA NO RESPETIVO MENU
				if (FoundThat.salas.isEmpty() || FoundThat.tipoObjetos.isEmpty()) {
					JOptionPane.showMessageDialog(getParent(), "N�o tem salas e/ou objetos adicionados!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
				else {
					dispose();
					this.setVisible(false);
					new FrmReclamacao().setVisible(true);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuReclamacao.setSelected(false);
				menuReclamacao.setForeground(new Color(0, 0, 128));
				menuReclamacao.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuReclamacao.setForeground(new Color(192, 192, 192));
			}

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub

			}
		});
		menuPrincipal.add(menuReclamacao);

		//IMPORTA��O
		menuImportacao = new JMenu("Importa\u00E7\u00E3o");
		menuImportacao.setBackground(new Color(25, 25, 112));
		menuImportacao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//VERIFICA SE O ARRAY SALAS E O ARRAY OBJETOS EST�O VAZIOS, CASO ESTEJAM N�O PERMITEM A ENTRADA NO RESPETIVO MENU
				if (FoundThat.salas.isEmpty() || FoundThat.tipoObjetos.isEmpty()) {
					JOptionPane.showMessageDialog(getParent(), "N�o tem salas e/ou objetos adicionados!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
				else {
					dispose();
					this.setVisible(false);
					new FrmImportacao().setVisible(true);
				}
			}
			private void setVisible(boolean b) {
				// TODO Auto-generated method stub

			}
			@Override
			public void mouseEntered(MouseEvent e) {
				menuImportacao.setSelected(false);
				menuImportacao.setForeground(new Color(0, 0, 128));
				menuImportacao.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuImportacao.setForeground(new Color(192, 192, 192));
			}
		});
		menuImportacao.setFont(new Font("Myanmar Text", Font.PLAIN, 18));
		menuImportacao.setForeground(new Color(192, 192, 192));

		menuPrincipal.add(menuImportacao);

		//LISTAGENS
		menuListagens = new JMenu("Listagens");
		menuListagens.setBackground(new Color(25, 25, 112));
		menuListagens.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//VERIFICA SE O ARRAY SALAS E O ARRAY OBJETOS EST�O VAZIOS, CASO ESTEJAM N�O PERMITEM A ENTRADA NO RESPETIVO MENU
				if (FoundThat.salas.isEmpty() || FoundThat.tipoObjetos.isEmpty()) {
					JOptionPane.showMessageDialog(getParent(), "N�o tem salas e/ou objetos adicionados!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				} 
				else {
					try {
						if (!FoundThat.checkPerfil(FrmLogin.txtUser.getText())) {
							dispose();
							this.setVisible(false);
							new FrmListagens().setVisible(true);
						}
						else {
							if (FoundThat.registoDoacoes.size() == 0) {
								JOptionPane.showMessageDialog(getParent(), "A institui��o " + FrmLogin.txtUser.getText() + " n�o tem objetos doados!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;	
							}
							else {
								dispose();
								this.setVisible(false);
								new FrmListagensIS().setVisible(true);
							}
						}
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub

			}
			@Override
			public void mouseEntered(MouseEvent e) {
				menuListagens.setSelected(false);
				menuListagens.setForeground(new Color(0, 0, 128));
				menuListagens.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuListagens.setForeground(new Color(192, 192, 192));
			}
		});
		menuListagens.setFont(new Font("Myanmar Text", Font.PLAIN, 18));
		menuListagens.setForeground(new Color(192, 192, 192));
		menuPrincipal.add(menuListagens);

		//DOA��ES
		menuDoacoes = new JMenu("Doa\u00E7\u00F5es");
		menuDoacoes.setBackground(new Color(255, 255, 255));
		menuDoacoes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//VERIFICA SE O ARRAY SALAS E O ARRAY OBJETOS EST�O VAZIOS, CASO ESTEJAM N�O PERMITEM A ENTRADA NO RESPETIVO MENU
				if (FoundThat.salas.isEmpty() || FoundThat.tipoObjetos.isEmpty()) {
					JOptionPane.showMessageDialog(getParent(), "N�o tem salas e/ou objetos adicionados!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
				else {
					try {
						dispose();
						this.setVisible(false);
						new FrmDoacoes().setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
			private void setVisible(boolean b) {
				// TODO Auto-generated method stub

			}
			@Override
			public void mouseEntered(MouseEvent e) {
				menuDoacoes.setSelected(false);
				menuDoacoes.setForeground(new Color(0, 0, 128));
				menuDoacoes.setBackground(new Color(192, 192, 192));
				menuDoacoes.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuDoacoes.setBackground(new Color(255, 255, 255));
				menuDoacoes.setForeground(new Color(192, 192, 192));
			}
		});
		menuDoacoes.setFont(new Font("Myanmar Text", Font.PLAIN, 18));
		menuDoacoes.setForeground(new Color(192, 192, 192));
		menuPrincipal.add(menuDoacoes);

		//CONFIGURA��ES
		menuConfiguracoes = new JMenu("Configura\u00E7\u00F5es");
		JMenuItem menuSalas = new JMenuItem("Gest\u00E3o de salas");
		menuConfiguracoes.setSelected(false);
		menuConfiguracoes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menuConfiguracoes.setSelected(false);
				menuConfiguracoes.setForeground(new Color(0, 0, 128));
				menuConfiguracoes.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuConfiguracoes.setForeground(new Color(192, 192, 192));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				menuSalas.setBackground(new Color(255, 255, 255));
			}
		});
		menuConfiguracoes.setBackground(new Color(255, 255, 255));
		menuConfiguracoes.setFont(new Font("Myanmar Text", Font.PLAIN, 18));
		menuConfiguracoes.setForeground(new Color(192, 192, 192));
		menuPrincipal.add(menuConfiguracoes);


		menuSalas.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {
				menuSalas.setForeground(new Color(192, 192, 192));
			}
		});
		menuSalas.setBackground(Color.WHITE);
		menuSalas.setFont(new Font("Myanmar Text", Font.PLAIN, 18));
		menuSalas.setForeground(new Color(192, 192, 192));
		menuSalas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				this.setVisible(false);
				new FrmConfigSala().setVisible(true);	
			}

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub

			}
		});
		menuConfiguracoes.add(menuSalas);

		JMenuItem menuIS = new JMenuItem("Gest\u00E3o de institui\u00E7\u00F5es de solidariedade");
		menuIS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				this.setVisible(false);
				new FrmConfigIS().setVisible(true);
			}

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub

			}
		});
		menuIS.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menuIS.setForeground(new Color(0, 0, 128));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuIS.setForeground(new Color(192, 192, 192));
			}
		});
		menuIS.setBackground(Color.WHITE);
		menuIS.setFont(new Font("Myanmar Text", Font.PLAIN, 18));
		menuIS.setForeground(new Color(192, 192, 192));
		menuConfiguracoes.add(menuIS);

		JMenuItem menuObjetos = new JMenuItem("Gest\u00E3o de tipos de objetos");
		menuObjetos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				this.setVisible(false);
				new FrmConfigTipoObjeto().setVisible(true);
			}

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub

			}
		});
		menuObjetos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menuObjetos.setForeground(new Color(0, 0, 128));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuObjetos.setForeground(new Color(192, 192, 192));
			}
		});
		menuObjetos.setBackground(Color.WHITE);
		menuObjetos.setFont(new Font("Myanmar Text", Font.PLAIN, 18));
		menuObjetos.setForeground(new Color(192, 192, 192));
		menuConfiguracoes.add(menuObjetos);

		JMenu mnGestaoDeUtilizadores = new JMenu("Gest\u00E3o de utilizadores");
		mnGestaoDeUtilizadores.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {
				mnGestaoDeUtilizadores.setForeground(new Color(192, 192, 192));
			}
		});
		mnGestaoDeUtilizadores.setFont(new Font("Myanmar Text", Font.PLAIN, 18));
		mnGestaoDeUtilizadores.setBackground(Color.WHITE);
		mnGestaoDeUtilizadores.setForeground(new Color(25, 25, 112));
		menuConfiguracoes.add(mnGestaoDeUtilizadores);

		JMenuItem mntmGerirUtilizador = new JMenuItem("Gerir utilizador");
		mntmGerirUtilizador.setForeground(new Color(192, 192, 192));
		mntmGerirUtilizador.setBackground(Color.WHITE);
		mntmGerirUtilizador.setFont(new Font("Gisha", Font.PLAIN, 18));
		mntmGerirUtilizador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				this.setVisible(false);
				new FrmGerirUser().setVisible(true);	
			}

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub

			}
		});
		mnGestaoDeUtilizadores.add(mntmGerirUtilizador);

		JMenuItem mntmGerirPerfil = new JMenuItem("Gerir perfil de utilizador");
		mntmGerirPerfil.setForeground(new Color(192, 192, 192));
		mntmGerirPerfil.setBackground(Color.WHITE);
		mntmGerirPerfil.setFont(new Font("Gisha", Font.PLAIN, 18));
		mntmGerirPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				this.setVisible(false);
				new FrmPerfilUser().setVisible(true);	
			}

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub

			}
		});
		mnGestaoDeUtilizadores.add(mntmGerirPerfil);
		mnGestaoDeUtilizadores.setBackground(new Color(255, 255, 255));
		mnGestaoDeUtilizadores.setForeground(new Color(192, 192, 192));

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//MENSAGEM BEM-VINDO, utilizador
		JLabel lblBemvindo = new JLabel("Bem-vindo, " + FrmLogin.txtUser.getText());
		lblBemvindo.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		lblBemvindo.setBounds(10, 15, 223, 23);
		contentPane.add(lblBemvindo);

		JLabel lblHora = new JLabel(FoundThat.horaRegisto);
		lblHora.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		lblHora.setBounds(402, 11, 42, 27);
		contentPane.add(lblHora);

		JLabel lblDia = new JLabel(FoundThat.dataRegisto);
		lblDia.setFont(new Font("Myanmar Text", Font.PLAIN, 14));
		lblDia.setBounds(449, 11, 94, 27);
		contentPane.add(lblDia);

		//BOT�O TERMINAR SESS�O
		JButton btnTerminarSessao = new JButton("TERMINAR SESS\u00C3O");
		btnTerminarSessao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnTerminarSessao.setForeground(new Color(255, 165, 0));
				btnTerminarSessao.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnTerminarSessao.setForeground(new Color(210, 105, 30));
			}
		});
		btnTerminarSessao.setForeground(new Color(210, 105, 30));
		btnTerminarSessao.setBackground(new Color(255, 255, 255));
		btnTerminarSessao.setFont(new Font("Gisha", Font.PLAIN, 14));
		btnTerminarSessao.setOpaque(false);
		btnTerminarSessao.setContentAreaFilled(false);
		btnTerminarSessao.setBorderPainted(false);
		btnTerminarSessao.setFocusPainted(false);
		btnTerminarSessao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				FrmLogin login = new FrmLogin();
				login.setVisible(true);
			}
		});
		btnTerminarSessao.setBounds(378, 33, 175, 23);
		contentPane.add(btnTerminarSessao);

		checkUser();


	}

	public static  void checkUser() {
		for (User us : FoundThat.users) {
			if (FrmLogin.txtUser.getText().equals(us.getUser())) {
				for (ModelPrototype t : FoundThat.tipoUsers) {
					TipoUser tu = (TipoUser) t;
					if (us.getTipo().getCodigo() == tu.getCodigo()) {
						menuRegisto.setVisible(tu.isRegisto());
						menuReclamacao.setVisible(tu.isReclamacao());
						menuImportacao.setVisible(tu.isImportacao());
						menuListagens.setVisible(tu.isListagens());
						menuDoacoes.setVisible(tu.isDoacoes());
						menuConfiguracoes.setVisible(tu.isConfiguracoes());
					}
				}
			}

		}
	}

}
