package pt.foundthat.view;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import pt.foundthat.controller.FoundThat;
import pt.foundthat.controller.ManagerRegisto;
import pt.foundthat.model.ModelStrategy;
import pt.foundthat.model.Sala;
import pt.foundthat.model.TipoObjeto;


public class FrmRegisto extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField txtNome;
	private static JTextField txtEmail;
	@SuppressWarnings("rawtypes")
	private static JComboBox cmbSala;
	@SuppressWarnings("rawtypes")
	private static JComboBox cmbObjeto;
	@SuppressWarnings("rawtypes")
	private static JComboBox cmbCor;
	@SuppressWarnings("rawtypes")
	private static JComboBox cmbEstado;
	private static JTextArea txtDesc;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public FrmRegisto() {
		//OPCOES MESSAGEBOX(SIM/N�O)
		String[] opcoes = new String[2];
		opcoes[0] = new String("Sim");
		opcoes[1] = new String("N�o");

		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmRegisto.class.getResource("/pt/foundthat/resources/lupa.png")));
		setTitle("Registo - FoundThat");
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
		contentPane.setForeground(new Color(25, 25, 112));
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		txtNome = new JTextField();
		txtNome.setForeground(new Color(25, 25, 112));
		txtNome.setBackground(new Color(220, 220, 220));
		txtNome.setBounds(162, 21, 135, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setForeground(new Color(25, 25, 112));
		txtEmail.setBackground(new Color(220, 220, 220));
		txtEmail.setBounds(162, 46, 135, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setForeground(new Color(25, 25, 112));
		lblNome.setFont(new Font("Myanmar Text", Font.PLAIN, 18));
		lblNome.setBounds(80, 21, 64, 23);
		contentPane.add(lblNome);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setForeground(new Color(25, 25, 112));
		lblEmail.setFont(new Font("Myanmar Text", Font.PLAIN, 18));
		lblEmail.setBounds(80, 43, 64, 28);
		contentPane.add(lblEmail);

		JLabel lblSala = new JLabel("Sala:");
		lblSala.setForeground(new Color(25, 25, 112));
		lblSala.setFont(new Font("Myanmar Text", Font.PLAIN, 18));
		lblSala.setBounds(80, 70, 46, 23);
		contentPane.add(lblSala);

		JLabel lblObjeto = new JLabel("Objeto:");
		lblObjeto.setForeground(new Color(25, 25, 112));
		lblObjeto.setFont(new Font("Myanmar Text", Font.PLAIN, 18));
		lblObjeto.setBounds(80, 92, 64, 28);
		contentPane.add(lblObjeto);

		JLabel lblCor = new JLabel("Cor:");
		lblCor.setForeground(new Color(25, 25, 112));
		lblCor.setFont(new Font("Myanmar Text", Font.PLAIN, 18));
		lblCor.setBounds(80, 120, 46, 23);
		contentPane.add(lblCor);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setForeground(new Color(25, 25, 112));
		lblEstado.setFont(new Font("Myanmar Text", Font.PLAIN, 18));
		lblEstado.setBounds(80, 142, 64, 28);
		contentPane.add(lblEstado);

		JLabel lblDesc = new JLabel("Descri\u00E7\u00E3o:");
		lblDesc.setForeground(new Color(25, 25, 112));
		lblDesc.setFont(new Font("Myanmar Text", Font.PLAIN, 18));
		lblDesc.setBounds(80, 172, 89, 23);
		contentPane.add(lblDesc);

		@SuppressWarnings("unused")
		Border border = BorderFactory.createLineBorder(Color.BLACK);

		txtDesc = new JTextArea();
		txtDesc.setForeground(new Color(25, 25, 112));
		txtDesc.setBackground(new Color(220, 220, 220));
		txtDesc.setBorder(new LineBorder(new Color(169, 169, 169)));
		txtDesc.setBounds(162, 173, 194, 53);
		contentPane.add(txtDesc);


		//COMBOBOX SALA
		cmbSala = new JComboBox();
		cmbSala.setBorder(null);
		cmbSala.setForeground(new Color(25, 25, 112));
		cmbSala.setBackground(new Color(220, 220, 220));

		for (ModelStrategy s : FoundThat.salas) {
			cmbSala.addItem(s);
		}
		cmbSala.setSelectedIndex(-1);

		cmbSala.setBounds(162, 71, 102, 20);
		contentPane.add(cmbSala);

		//COMBOBOX OBJETO
		cmbObjeto = new JComboBox();
		cmbObjeto.setBorder(null);
		cmbObjeto.setForeground(new Color(25, 25, 112));
		cmbObjeto.setBackground(new Color(220, 220, 220));
		refresh();
		cmbObjeto.setSelectedIndex(-1);


		cmbObjeto.setBounds(162, 96, 102, 20);
		contentPane.add(cmbObjeto);

		//COMBOBOX ESTADO
		cmbEstado = new JComboBox();
		cmbEstado.setBorder(null);
		cmbEstado.setForeground(new Color(25, 25, 112));
		cmbEstado.setBackground(new Color(220, 220, 220));

		cmbEstado.addItem("Bom");
		cmbEstado.addItem("Mau");
		cmbEstado.addItem("Razo�vel");
		cmbEstado.setSelectedIndex(-1);

		cmbEstado.setBounds(162, 146, 102, 20);
		contentPane.add(cmbEstado);

		//COMBOBOX COR
		cmbCor = new JComboBox();
		cmbCor.setBorder(null);
		cmbCor.setForeground(new Color(25, 25, 112));
		cmbCor.setBackground(new Color(220, 220, 220));

		cmbCor.addItem("Amarelo");	
		cmbCor.addItem("Azul");
		cmbCor.addItem("Branco");
		cmbCor.addItem("Castanho");
		cmbCor.addItem("Cinzento");
		cmbCor.addItem("Laranja");
		cmbCor.addItem("Preto");
		cmbCor.addItem("Rosa");
		cmbCor.addItem("Roxo");
		cmbCor.addItem("Verde");
		cmbCor.addItem("Vermelho");
		cmbCor.setSelectedIndex(-1);

		cmbCor.setBounds(162, 121, 102, 20);
		contentPane.add(cmbCor);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBorder(null);
		btnGuardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnGuardar.setBackground(new Color(210, 105, 30));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnGuardar.setBackground(new Color(25, 25, 112));
			}
		});
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtNome.getText().equals("") || txtEmail.getText().equals("") || cmbSala.getSelectedIndex() == -1 || cmbObjeto.getSelectedIndex() == -1 || cmbCor.getSelectedIndex() == -1 || cmbEstado.getSelectedIndex() == -1 || txtDesc.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Insira os dados!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
					}
					else if (txtNome.getText().length() > 20) {
						JOptionPane.showMessageDialog(null, "O nome tem mais de 20 caracteres!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
						txtNome.setText("");
					}
					/*					else if (txtEmail.getText().length() > 20) {
						JOptionPane.showMessageDialog(null, "O e-mail tem mais de 20 caracteres!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
						txtEmail.setText("");
					}*/
					else if (txtDesc.getText().length() > 50) {
						JOptionPane.showMessageDialog(null, "A descri��o tem mais de 50 caracteres!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
						txtDesc.setText("");
					}
					else {
						if (!FoundThat.isNumber(txtNome.getText())) {
							cmbObjeto.removeAllItems();
							Collections.sort(FoundThat.tipoObjetos);
							for (TipoObjeto to : FoundThat.tipoObjetos) {
								cmbObjeto.addItem(to);	
							}
							if (ManagerRegisto.adicionarRegisto(txtNome.getText(), txtEmail.getText(), cmbSala.getSelectedItem(), cmbObjeto.getSelectedItem(), cmbCor.getSelectedItem().toString().toLowerCase(), cmbEstado.getSelectedItem().toString().toLowerCase(), txtDesc.getText())) {
								FoundThat.gravarFicheiro();
								JOptionPane.showMessageDialog(null, "Registado com sucesso!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
								refresh();
								clear();


							}
						}
						else {
							JOptionPane.showMessageDialog(null, "N�o pode inserir n�meros no nome! ", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
							txtNome.setText("");
						}
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnGuardar.setBackground(new Color(25, 25, 112));
		btnGuardar.setBounds(80, 237, 108, 23);
		contentPane.add(btnGuardar);


		//BOTAO LIMPAR
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBorder(null);
		btnLimpar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnLimpar.setBackground(new Color(210, 105, 30));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnLimpar.setBackground(new Color(25, 25, 112));
			}
		});
		btnLimpar.setForeground(Color.WHITE);
		btnLimpar.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnLimpar.setBackground(new Color(25, 25, 112));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clear();
			}
		});
		btnLimpar.setBounds(191, 237, 89, 23);
		contentPane.add(btnLimpar);

		//BOT�O SAIR (VOLTA AO MENU PRINCIPAL)
		JButton btnSair = new JButton("Sair");
		btnSair.setBorder(null);
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
		btnSair.setForeground(Color.WHITE);
		btnSair.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnSair.setBackground(new Color(25, 25, 112));
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
		btnSair.setBounds(282, 237, 89, 23);
		contentPane.add(btnSair);




	}

	public static void clear() {
		txtNome.setText("");
		txtEmail.setText("");
		cmbSala.setSelectedIndex(-1);
		cmbObjeto.setSelectedIndex(-1);
		cmbCor.setSelectedIndex(-1);
		cmbEstado.setSelectedIndex(-1);
		txtDesc.setText("");
		txtNome.requestFocusInWindow();
	}

	@SuppressWarnings("unchecked")
	public static void refresh () {
		cmbObjeto.removeAllItems();
		Collections.sort(FoundThat.tipoObjetos);
		for (TipoObjeto to : FoundThat.tipoObjetos) {
			cmbObjeto.addItem(to.toString().substring(0, 1).toUpperCase() + to.toString().substring(1).toLowerCase());	
		}
	}
}
