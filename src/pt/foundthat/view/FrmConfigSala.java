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
import javax.swing.border.LineBorder;

import pt.foundthat.controller.FoundThat;
import pt.foundthat.controller.ManagerSala;
import pt.foundthat.model.Instituicao;
import pt.foundthat.model.ModelStrategy;
import pt.foundthat.model.Sala;

public class FrmConfigSala extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSala;
	@SuppressWarnings("unused")
	private JTextField textField;
	static DefaultListModel<String> dlm;
	@SuppressWarnings("rawtypes")
	static JList list;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmConfigSala frame = new FrmConfigSala();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FrmConfigSala() {
		FoundThat.managerEntity = new ManagerSala();
		setResizable(false);
		//OPCOES MESSAGEBOX(SIM/N�O)
		String[] opcoes = new String[2];
		opcoes[0] = new String("Sim");
		opcoes[1] = new String("N�o");

		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmConfigSala.class.getResource("/pt/foundthat/resources/lupa.png")));
		setTitle("Gest\u00E3o de Salas - FoundThat");
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

		txtSala = new JTextField();
		txtSala.setForeground(new Color(25, 25, 112));
		txtSala.setBorder(new LineBorder(new Color(128, 128, 128)));
		txtSala.setBackground(new Color(220, 220, 220));
		txtSala.setBounds(247, 79, 106, 23);
		contentPane.add(txtSala);
		txtSala.setColumns(10);

		//LISTA COM SALAS
		dlm = new DefaultListModel<String>();
		refresh();
		list = new JList(dlm);
		list.setForeground(new Color(25, 25, 112));
		list.setSelectedIndex(0);
		if (list.getSelectedIndex() != -1) {
			txtSala.setText(list.getSelectedValue().toString());	
		}
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (list.getSelectedIndex() != -1) {
					txtSala.setText(list.getSelectedValue().toString());
				}
			}
		});
		JScrollPane listScroller = new JScrollPane(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBorder(null);
		list.setBackground(new Color(220, 220, 220));
		listScroller.setBounds(109, 36, 60, 228);
		contentPane.add(listScroller);



		//ADICIONAR SALA
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
		btnAdicionar.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnAdicionar.setForeground(new Color(255, 255, 255));
		btnAdicionar.setBackground(new Color(25, 25, 112));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtSala.getText().toUpperCase().equals("")) {
					JOptionPane.showMessageDialog(null, "Introduza uma sala!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
				else {
					ModelStrategy s = new Sala(txtSala.getText().toUpperCase());
					if (!FoundThat.managerEntity.adicionarEntity(s)) {
						JOptionPane.showMessageDialog(null, "A sala " + txtSala.getText().toUpperCase() + " j� existe!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;				
					}
					else {
						JOptionPane.showMessageDialog(null, "A sala " + txtSala.getText().toUpperCase() + " foi adicionada!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;	
						refresh();
					}
				}
			}

		});
		btnAdicionar.setBounds(247, 113, 106, 23);
		contentPane.add(btnAdicionar);

		//ALTERAR SALA
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
		btnAlterar.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnAlterar.setForeground(new Color(255, 255, 255));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndex() != -1) {
					if (list.getSelectedValue().equals(txtSala.getText().toUpperCase())) {
						JOptionPane.showMessageDialog(null, "A sala " + txtSala.getText().toUpperCase() + " j� existe!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
					}
					else if (txtSala.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Introduza uma sala!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
					}
					else {
						int selectedOption = JOptionPane.showOptionDialog(null, "Deseja alterar a sala " + list.getSelectedValue().toString() + " pela sala " + txtSala.getText() + "?", "AVISO! - FoundThat", 0, JOptionPane.INFORMATION_MESSAGE, null, opcoes, null); 
						if (selectedOption == JOptionPane.YES_OPTION) {
						    ModelStrategy sAntiga = new Sala(list.getSelectedValue().toString());
						    ModelStrategy sNova = new Sala(txtSala.getText().toUpperCase());

							if (FoundThat.managerEntity.alterarEntity(sNova, sAntiga)) {
								JOptionPane.showMessageDialog(null, "A sala foi alterada!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
								refresh();
							}
							else {
								JOptionPane.showMessageDialog(null, "A sala " + txtSala.getText().toUpperCase() + " j� existe!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
							}
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Selecione uma sala!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}	
			}
		});

		btnAlterar.setBackground(new Color(25, 25, 112));
		btnAlterar.setBounds(247, 143, 106, 23);
		contentPane.add(btnAlterar);

		//REMOVER SALA
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
		btnRemover.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnRemover.setForeground(new Color(255, 255, 255));
		btnRemover.setBackground(new Color(25, 25, 112));
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndex() != -1) {
					int selectedOption = JOptionPane.showOptionDialog(null, "Deseja remover a sala " + list.getSelectedValue().toString() + "?", "AVISO! - FoundThat", 0, JOptionPane.INFORMATION_MESSAGE, null, opcoes, null); 
					if (selectedOption == JOptionPane.YES_OPTION) {
						if (FoundThat.managerEntity.removerEntity(txtSala.getText().toUpperCase())) {
							String salaRemovida = list.getSelectedValue().toString();
							JOptionPane.showMessageDialog(null, "A sala " + salaRemovida + " foi removida!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
							refresh();
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Selecione uma sala!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
			}


		});
		btnRemover.setBounds(247, 171, 106, 23);
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
		btnSair.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnSair.setForeground(new Color(255, 255, 255));
		btnSair.setBackground(new Color(25, 25, 112));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					FoundThat.gravarFicheiro();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.setVisible(false);
				dispose();
				new FrmMain().setVisible(true);
			}

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub

			}
		});
		btnSair.setBounds(247, 200, 106, 23);
		contentPane.add(btnSair);

		JLabel lblSala = new JLabel("Sala");
		lblSala.setForeground(new Color(25, 25, 112));
		lblSala.setFont(new Font("Myanmar Text", Font.PLAIN, 16));
		lblSala.setBounds(206, 83, 46, 19);
		contentPane.add(lblSala);

		JLabel lblSalas = new JLabel("Salas");
		lblSalas.setForeground(new Color(25, 25, 112));
		lblSalas.setFont(new Font("Myanmar Text", Font.PLAIN, 16));
		lblSalas.setBounds(121, 11, 46, 34);
		contentPane.add(lblSalas);

	}

	public static void refresh() {
        ArrayList<Sala> salasOrdenado = new ArrayList<>();
        for (ModelStrategy sala : FoundThat.salas) {
            salasOrdenado.add((Sala) sala);
        }
		Collections.sort(salasOrdenado);
		dlm.clear();
		for (Sala s : salasOrdenado) {
			dlm.addElement(s.toString());
		}
	}
}



