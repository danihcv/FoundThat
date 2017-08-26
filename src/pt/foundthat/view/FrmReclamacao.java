package pt.foundthat.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import pt.foundthat.controller.FoundThat;
//import pt.foundthat.controller.ManagerReclamacao;
import pt.foundthat.controller.ManagerReclamacao;
import pt.foundthat.model.Registo;
import pt.foundthat.model.TipoObjeto;


class FrmReclamacao extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	static DefaultTableModel dtm = new DefaultTableModel();
	@SuppressWarnings("rawtypes")
	private static JComboBox cmbObjeto;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	FrmReclamacao() {
		setResizable(false);
        FoundThat.managerAction = new ManagerReclamacao();
		//OPCOES MESSAGEBOX(SIM/N�O)
		String[] opcoes = new String[2];
		opcoes[0] = new String("Sim");
		opcoes[1] = new String("N�o");

		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmReclamacao.class.getResource("/pt/foundthat/resources/lupa.png")));
		setTitle("Reclama\u00E7\u00E3o - FoundThat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		JLabel lblObjeto = new JLabel("Objeto: ");
		lblObjeto.setForeground(new Color(25, 25, 112));
		lblObjeto.setFont(new Font("Myanmar Text", Font.PLAIN, 18));
		lblObjeto.setBounds(35, 59, 85, 23);
		contentPane.add(lblObjeto);

		//COMBOBOX OBJETO
		//associar tipo de objetos � combobox
		cmbObjeto = new JComboBox();
		cmbObjeto.setBackground(new Color(220, 220, 220));
		for (TipoObjeto to : FoundThat.tipoObjetos) {
			cmbObjeto.addItem(to.toString().substring(0, 1).toUpperCase() + to.toString().substring(1).toLowerCase());	
		}	
		cmbObjeto.setSelectedIndex(-1);
		//ao selecionar combobox, aparecer registo daquele tipo de objeto
		cmbObjeto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//cont serve para verificar numero de registos daquele tipo de objeto
				int cont = 0;
				if (cmbObjeto.getSelectedIndex() != -1) {
					dtm.setRowCount(0);
					dtm.setColumnCount(0);

					String objeto = cmbObjeto.getSelectedItem().toString().toLowerCase();
					for (int i = 0; i < FoundThat.registos.size(); i++) {
						if (FoundThat.registos.get(i).getObjeto().getNome().equals(objeto)) {
							cont++;	
						}
					}

					String[][] regs = new String[cont][5];
					String[] nomeColunas = {"C�digo", "Objeto", "Cor", "Estado", "Sala"};

					int c = 0;
					for (int i = 0; i < FoundThat.registos.size(); i++) {
						if (FoundThat.registos.get(i).getObjeto().getNome().equals(objeto)) {
							regs[c][0] = Integer.toString(FoundThat.registos.get(i).getCodigo());
							regs[c][1] = FoundThat.registos.get(i).getObjeto().getNome().substring(0, 1).toUpperCase() + FoundThat.registos.get(i).getObjeto().getNome().substring(1).toLowerCase();
							regs[c][2] = FoundThat.registos.get(i).getCor().substring(0, 1).toUpperCase() + FoundThat.registos.get(i).getCor().substring(1).toLowerCase();
							regs[c][3] = FoundThat.registos.get(i).getEstado().substring(0, 1).toUpperCase() + FoundThat.registos.get(i).getEstado().substring(1).toLowerCase();
							regs[c][4] = FoundThat.registos.get(i).getSala().toString();
							c++;
						}
					}

					for (int j = 0; j < nomeColunas.length; j++) {
						dtm.addColumn(nomeColunas[j]);
					}
					for (int i = 0; i < regs.length; i++) {
						dtm.addRow(regs[i]);
					}


				}
			}
		});


		cmbObjeto.setBounds(21, 93, 85, 20);
		contentPane.add(cmbObjeto);

		//JTABLE
		String[] nomeColunas = {"C�digo", "Objeto", "Cor", "Estado", "Sala"};
		@SuppressWarnings("unused")
		int cont = 0;
		String[][] regs = new String[FoundThat.registos.size()][5];
		System.out.println(FoundThat.registos.size());
		for (int i = 0; i < FoundThat.registos.size(); i++) {
			regs[i][0] = Integer.toString(FoundThat.registos.get(i).getCodigo());
			regs[i][1] = FoundThat.registos.get(i).getObjeto().getNome().substring(0, 1).toUpperCase() + FoundThat.registos.get(i).getObjeto().getNome().substring(1).toLowerCase();
			regs[i][2] = FoundThat.registos.get(i).getCor().substring(0, 1).toUpperCase() + FoundThat.registos.get(i).getCor().substring(1).toLowerCase();
			regs[i][3] = FoundThat.registos.get(i).getEstado().substring(0, 1).toUpperCase() + FoundThat.registos.get(i).getEstado().substring(1).toLowerCase();
			regs[i][4] = FoundThat.registos.get(i).getSala().toString();
			cont++;
		}

		dtm = new DefaultTableModel(regs, nomeColunas);
		table = new JTable(dtm){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable ( int row, int column )
			{
				return false;
			}
		};
		table.setForeground(new Color(25, 25, 112));
		table.setBackground(new Color(220, 220, 220));
		table.getTableHeader().setEnabled(false);
		table.setBounds(32, 77, 269, 97);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setPreferredScrollableViewportSize(new Dimension(300, 60));
		table.setFillsViewportHeight(true);
		table.setSelectionBackground(new Color(210, 105, 30));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setEnabled(false);
		scrollPane.setBounds(119, 45, 302, 87);	
		contentPane.add(scrollPane);	


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
			public void actionPerformed(ActionEvent e) {
				this.setVisible(false);
				dispose();
				new FrmMain().setVisible(true);
			}

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub

			}
		});
		btnSair.setForeground(new Color(255, 255, 255));
		btnSair.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnSair.setBackground(new Color(25, 25, 112));
		btnSair.setBounds(297, 212, 116, 23);
		contentPane.add(btnSair);

		//ENTREGAR OBJETO
		JButton btnDevolver = new JButton("Devolver");
		btnDevolver.setBorder(null);
		btnDevolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDevolver.setBackground(new Color(210, 105, 30));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnDevolver.setBackground(new Color(25, 25, 112));
			}
		});
		btnDevolver.setForeground(new Color(255, 255, 255));
		btnDevolver.setBackground(new Color(25, 25, 112));
		btnDevolver.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnDevolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um objeto!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
				else {
					int cod = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					if (FoundThat.managerAction.execute(cod) != -1) {
						try {
							FoundThat.gravarFicheiro();
							JOptionPane.showMessageDialog(null, "Devolvido com sucesso!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
							refresh();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});

		btnDevolver.setBounds(44, 178, 116, 23);
		contentPane.add(btnDevolver);

		//BOT�O +INFO
		JButton btnInfo = new JButton("+Info");
		btnInfo.setBorder(null);
		btnInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnInfo.setBackground(new Color(210, 105, 30));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnInfo.setBackground(new Color(25, 25, 112));
			}
		});
		btnInfo.setForeground(new Color(255, 255, 255));
		btnInfo.setBackground(new Color(25, 25, 112));
		btnInfo.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um objeto!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
				else {
                    int cod = (Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
					int idx = -1;
					for (int i = 0; i < FoundThat.registos.size() && idx == -1; i++) {
                        if (FoundThat.registos.get(i).getCodigo() == cod) {
					        idx = i;
                        }
                    }
                    String info = "Nome: " + FoundThat.registos.get(idx).getNome() + "\n" + "E-mail: " + FoundThat.registos.get(idx).getEmail() +
							"\n" + "Sala: " + FoundThat.registos.get(idx).getSala() + "\n" + "Dia: " + FoundThat.formatoDataRegisto.format(FoundThat.registos.get(idx).getData()) +
							"\n" + "Hora: " + FoundThat.registos.get(idx).getHora() + "\n" + "Tipo de objeto: " + FoundThat.registos.get(idx).getObjeto().getNome().substring(0, 1).toUpperCase() +
							FoundThat.registos.get(idx).getObjeto().getNome().substring(1).toLowerCase() + "\n" + "Cor: " + FoundThat.registos.get(idx).getCor().substring(0, 1).toUpperCase() +
							FoundThat.registos.get(idx).getCor().substring(1).toLowerCase() + "\n" + "Estado: " + FoundThat.registos.get(idx).getEstado().substring(0, 1).toUpperCase() +
							FoundThat.registos.get(idx).getEstado().substring(1).toLowerCase() + "\n" + "Descri��o: " + FoundThat.registos.get(idx).getDescricao() ;
					JOptionPane.showMessageDialog(null, info, "FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
			}
		});
		btnInfo.setBounds(170, 178, 116, 23);
		contentPane.add(btnInfo);

		//BOT�O LIMPAR
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setBorder(null);
		btnLimpar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLimpar.setBackground(new Color(210, 105, 30));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnLimpar.setBackground(new Color(25, 25, 112));
			}
		});
		btnLimpar.setForeground(new Color(255, 255, 255));
		btnLimpar.setBackground(new Color(25, 25, 112));
		btnLimpar.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refresh();
			}
		});
		btnLimpar.setBounds(297, 178, 116, 23);
		contentPane.add(btnLimpar);




	}	 

	public static void refresh() {
		cmbObjeto.setSelectedIndex(-1);
		dtm.setRowCount(0);
		dtm.setColumnCount(0);
		String[] nomeColunas = {"C�digo", "Objeto", "Cor", "Estado", "Sala"};
		@SuppressWarnings("unused")
		int cont = 0;
		String[][] regs = new String[FoundThat.registos.size()][5];
		for (int i = 0; i < FoundThat.registos.size(); i++) {
			regs[i][0] = Integer.toString(FoundThat.registos.get(i).getCodigo());
			regs[i][1] = FoundThat.registos.get(i).getObjeto().getNome().substring(0, 1).toUpperCase() + FoundThat.registos.get(i).getObjeto().getNome().substring(1).toLowerCase();
			regs[i][2] = FoundThat.registos.get(i).getCor().substring(0, 1).toUpperCase() + FoundThat.registos.get(i).getCor().substring(1).toLowerCase();
			regs[i][3] = FoundThat.registos.get(i).getEstado().substring(0, 1).toUpperCase() + FoundThat.registos.get(i).getEstado().substring(1).toLowerCase();
			regs[i][4] = FoundThat.registos.get(i).getSala().toString();
			cont++;
		}

		for (int j = 0; j < nomeColunas.length; j++) {
			dtm.addColumn(nomeColunas[j]);
		}
		for (int i = 0; i < regs.length; i++) {
			dtm.addRow(regs[i]);
		}
	}

}







