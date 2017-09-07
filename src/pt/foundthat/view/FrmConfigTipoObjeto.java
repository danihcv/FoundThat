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

import javax.swing.DefaultComboBoxModel;
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
import javax.swing.border.LineBorder;

import pt.foundthat.controller.FoundThat;
import pt.foundthat.controller.ManagerTipoObjeto;
import pt.foundthat.model.Instituicao;
import pt.foundthat.model.ModelPrototype;
import pt.foundthat.model.TipoObjeto;

public class FrmConfigTipoObjeto extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtObjeto;
	static DefaultComboBoxModel<String> dlmIS;
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
	@SuppressWarnings("rawtypes")
	public FrmConfigTipoObjeto() {
	    FoundThat.managerEntity = new ManagerTipoObjeto();
		setResizable(false);
		//OPCOES MESSAGEBOX(SIM/N�O)
		String[] opcoes = new String[2];
		opcoes[0] = new String("Sim");
		opcoes[1] = new String("N�o");

		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmConfigTipoObjeto.class.getResource("/pt/foundthat/resources/lupa.png")));
		setTitle("Gest\u00E3o de tipos de objetos - FoundThat");
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
		txtObjeto = new JTextField();
		txtObjeto.setForeground(new Color(25, 25, 112));
		txtObjeto.setBorder(new LineBorder(new Color(169, 169, 169)));
		txtObjeto.setBackground(new Color(220, 220, 220));
		txtObjeto.setBounds(285, 44, 106, 23);
		contentPane.add(txtObjeto);
		txtObjeto.setColumns(10);

		//COMBOBOX COM INSTITUICOES
		dlmIS = new DefaultComboBoxModel<String>();
		refreshIS();		

		@SuppressWarnings("unchecked")
		JComboBox cmbInstituicao = new JComboBox(dlmIS);
		cmbInstituicao.setBackground(new Color(220, 220, 220));
		cmbInstituicao.setForeground(new Color(25, 25, 112));
		cmbInstituicao.setBounds(285, 78, 106, 23);
		contentPane.add(cmbInstituicao);

		JLabel lblInstituicao = new JLabel("Institui\u00E7\u00E3o");
		lblInstituicao.setForeground(new Color(25, 25, 112));
		lblInstituicao.setFont(new Font("Myanmar Text", Font.PLAIN, 16));
		lblInstituicao.setBounds(204, 78, 118, 23);
		contentPane.add(lblInstituicao);

		//LISTA COM OBJETOS
		dlm = new DefaultListModel<String>();
		refreshTO();
		@SuppressWarnings("unchecked")
		JList list = new JList(dlm);
		list.setForeground(new Color(25, 25, 112));
		list.setSelectedIndex(0);
		for (TipoObjeto to : FoundThat.tipoObjetos) {
			if (to.getNome().equals(list.getSelectedValue().toString().toLowerCase())) {
				dlmIS.setSelectedItem(to.getCodigoIS().getNome().substring(0, 1).toUpperCase() + to.getCodigoIS().getNome().substring(1).toLowerCase());
			}
		}
		txtObjeto.setText(list.getSelectedValue().toString().substring(0, 1).toUpperCase() + list.getSelectedValue().toString().substring(1).toLowerCase());
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtObjeto.setText(list.getSelectedValue().toString().substring(0, 1).toUpperCase() + list.getSelectedValue().toString().substring(1).toLowerCase());
				//ASSOCIAR TIPOOBJETO (LIST) � INSTITUICAO (COMBOBOX) ANTERIORMENTE ASSOCIADA
				for (TipoObjeto to : FoundThat.tipoObjetos) {
					if (to.getNome().equals(list.getSelectedValue().toString().toLowerCase())) {
						dlmIS.setSelectedItem(to.getCodigoIS().getNome().substring(0, 1).toUpperCase() + to.getCodigoIS().getNome().substring(1).toLowerCase());
					}
				}

			}
		});
		JScrollPane listScroller = new JScrollPane(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBorder(null);
		list.setBackground(new Color(220, 220, 220));
		listScroller.setBounds(61, 32, 118, 228);
		contentPane.add(listScroller);



		//ADICIONAR TIPO OBJETO
		JButton btnAdicionar = new JButton("Adicionar");
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
		btnAdicionar.setBorder(null);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtObjeto.getText().toLowerCase().equals("")) {
					JOptionPane.showMessageDialog(null, "Introduza um objeto!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
				else {
                    ModelPrototype is = null;
                    String isNome = cmbInstituicao.getSelectedItem().toString().toLowerCase();
                    for(ModelPrototype i : FoundThat.instituicoes) {
                        if (i.getNome().equals(isNome)) {
                            is = i;
                        }
                    }
                    TipoObjeto to = (TipoObjeto) FoundThat.prototypeTipoObjeto.clone();
                    to.setNome(txtObjeto.getText().toLowerCase());
                    to.setCodigoIS((Instituicao) is);
					if (!FoundThat.managerEntity.adicionarEntity(to)) {
						JOptionPane.showMessageDialog(null, "O objeto " + txtObjeto.getText().substring(0, 1).toUpperCase()+txtObjeto.getText().substring(1).toLowerCase() + " j� existe!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
					}
					else {
						refreshTO();
						JOptionPane.showMessageDialog(null, "O objeto " + txtObjeto.getText().substring(0, 1).toUpperCase()+txtObjeto.getText().substring(1).toLowerCase() + " foi adicionado!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
					}
				}
			}
		});
		btnAdicionar.setForeground(Color.WHITE);
		btnAdicionar.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnAdicionar.setBackground(new Color(25, 25, 112));
		btnAdicionar.setBounds(285, 112, 106, 23);
		contentPane.add(btnAdicionar);

		//ALTERAR OBJETO
		JButton btnAlterar = new JButton("Alterar");
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
		btnAlterar.setBorder(null);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedIndex() != -1) {
					if (txtObjeto.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Introduza um objeto!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
					}
					else {
						int selectedOption = JOptionPane.showOptionDialog(null, "Deseja alterar o objeto " + list.getSelectedValue().toString().substring(0, 1).toUpperCase() + list.getSelectedValue().toString().substring(1).toLowerCase() + " pelo objeto " + txtObjeto.getText().substring(0, 1).toUpperCase()+txtObjeto.getText().substring(1).toLowerCase() + "?", "AVISO! - FoundThat", 0, JOptionPane.INFORMATION_MESSAGE, null, opcoes, null); 
						if (selectedOption == JOptionPane.YES_OPTION) {
							ModelPrototype isAntiga = null;
							for (TipoObjeto to : FoundThat.tipoObjetos) {
								if (list.getSelectedValue().toString().toLowerCase().equals(to.getNome())) {
                                    isAntiga = to.getCodigoIS();
								}
							}
							ModelPrototype isNova = null;
							for (ModelPrototype i : FoundThat.instituicoes) {
							    if (cmbInstituicao.getSelectedItem().toString().toLowerCase().equals(i.getNome())) {
							        isNova = i;
                                }
                            }

							TipoObjeto toNovo = (TipoObjeto) FoundThat.prototypeInstituicao.clone();
							toNovo.setNome(txtObjeto.getText().toLowerCase());
							toNovo.setCodigoIS((Instituicao) isNova);
							TipoObjeto toAntigo = (TipoObjeto) FoundThat.prototypeTipoObjeto.clone();
							toAntigo.setNome(list.getSelectedValue().toString().toLowerCase());
							toAntigo.setCodigoIS((Instituicao) isAntiga);
							if (FoundThat.managerEntity.alterarEntity(toNovo, toAntigo)) {
								refreshTO();
								JOptionPane.showMessageDialog(null, "O objeto foi alterado!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
							}
							else {
								JOptionPane.showMessageDialog(null, "O objeto " + txtObjeto.getText().substring(0, 1).toUpperCase() + txtObjeto.getText().substring(1).toLowerCase() + " j� existe!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
							}

						}
					}

				}
				else {
					JOptionPane.showMessageDialog(null, "Selecione um objeto!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
			}
		});
		btnAlterar.setForeground(Color.WHITE);
		btnAlterar.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnAlterar.setBackground(new Color(25, 25, 112));
		btnAlterar.setBounds(285, 146, 106, 23);
		contentPane.add(btnAlterar);

		//REMOVER OBJETO
		JButton btnRemover = new JButton("Remover");
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
		btnRemover.setBorder(null);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (list.getSelectedIndex() != -1) {
					int selectedOption = JOptionPane.showOptionDialog(null, "Deseja remover o objeto " + list.getSelectedValue().toString().substring(0, 1).toUpperCase() + list.getSelectedValue().toString().substring(1).toLowerCase() + "?", "AVISO! - FoundThat", 0, JOptionPane.INFORMATION_MESSAGE, null, opcoes, null); 
					if (selectedOption == JOptionPane.YES_OPTION) {
						if (FoundThat.managerEntity.removerEntity(list.getSelectedValue().toString().toLowerCase())) {
							String objetoRemovido = list.getSelectedValue().toString().substring(0, 1).toUpperCase() + list.getSelectedValue().toString().substring(1).toLowerCase();
							dlm.removeElement(objetoRemovido);
							JOptionPane.showMessageDialog(null, "O objeto " + objetoRemovido + " foi removido!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Selecione um objeto!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
			}
		});
		btnRemover.setForeground(Color.WHITE);
		btnRemover.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnRemover.setBackground(new Color(25, 25, 112));
		btnRemover.setBounds(285, 180, 106, 23);
		contentPane.add(btnRemover);

		//SAIR
		JButton btnSair = new JButton("Sair");
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
		btnSair.setForeground(Color.WHITE);
		btnSair.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnSair.setBackground(new Color(25, 25, 112));
		btnSair.setBounds(285, 214, 106, 23);
		contentPane.add(btnSair);

		JLabel lblObjeto = new JLabel("Objeto");
		lblObjeto.setForeground(new Color(25, 25, 112));
		lblObjeto.setFont(new Font("Myanmar Text", Font.PLAIN, 16));
		lblObjeto.setBounds(204, 41, 118, 32);
		contentPane.add(lblObjeto);		

		JLabel lblObjetos = new JLabel("Objetos");
		lblObjetos.setForeground(new Color(25, 25, 112));
		lblObjetos.setFont(new Font("Myanmar Text", Font.PLAIN, 16));
		lblObjetos.setBounds(95, 11, 87, 23);
		contentPane.add(lblObjetos);

	}

	public static void refreshIS() {
		//C�PIA DO ARRAY ORIGINAL DE INSTITUICOES, PARA ORDEN�-LO NA COMBOBOX!
		ArrayList <Instituicao> instituicoesOrdenado = new ArrayList<>();
		for(ModelPrototype is : FoundThat.instituicoes) {
		    instituicoesOrdenado.add((Instituicao) is);
        }
		Collections.sort(instituicoesOrdenado);
		dlmIS.removeAllElements();
		for (Instituicao is : instituicoesOrdenado) {
			dlmIS.addElement(is.toString().substring(0, 1).toUpperCase() + is.toString().substring(1).toLowerCase());
		}


	}

	public static void refreshTO() {
		//C�PIA DO ARRAY ORIGINAL DE OBJETOS, PARA ORDEN�-LO NA LIST!
		ArrayList <TipoObjeto> objetosOrdenado = new ArrayList<TipoObjeto>(FoundThat.tipoObjetos);
		Collections.sort(objetosOrdenado);
		dlm.clear();
		for (TipoObjeto to : objetosOrdenado) {
			dlm.addElement(to.toString().substring(0, 1).toUpperCase() + to.toString().substring(1).toLowerCase());
		}
	}
}
