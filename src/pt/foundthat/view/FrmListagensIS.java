package pt.foundthat.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
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
import pt.foundthat.model.ModelStrategy;
import pt.foundthat.model.Registo;
import pt.foundthat.model.Sala;
import pt.foundthat.model.TipoObjeto;

public class FrmListagensIS extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTable table;
	static DefaultTableModel dtm = new DefaultTableModel();
	private JFormattedTextField txtInicial;
	private JFormattedTextField txtFinal;
	private JButton btnFiltrar;
	@SuppressWarnings("unused")
	private static JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmListagensIS frame = new FrmListagensIS();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public FrmListagensIS() throws Exception {
		//OPCOES MESSAGEBOX(SIM/N�O)
		String[] opcoes = new String[2];
		opcoes[0] = new String("Sim");
		opcoes[1] = new String("N�o");

		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmListagensIS.class.getResource("/pt/foundthat/resources/lupa.png")));
		setTitle("Listagens - FoundThat");
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

		DateFormat formato = new SimpleDateFormat("dd-MM-yyyy");

		txtInicial = new JFormattedTextField(formato);
		txtInicial.setForeground(new Color(25, 25, 112));
		txtInicial.setBackground(new Color(220, 220, 220));
		txtInicial.setBounds(160, 60, 92, 20);
		contentPane.add(txtInicial);
		txtInicial.setColumns(10);

		txtFinal = new JFormattedTextField(formato);
		txtFinal.setForeground(new Color(25, 25, 112));
		txtFinal.setBackground(new Color(220, 220, 220));
		txtFinal.setBounds(160, 97, 92, 20);
		contentPane.add(txtFinal);
		txtFinal.setColumns(10);

		//COMBOBOX SALA
		@SuppressWarnings("rawtypes")
		JComboBox cmbSala = new JComboBox();
		cmbSala.setForeground(new Color(25, 25, 112));
		cmbSala.setBackground(new Color(220, 220, 220));

		for (ModelStrategy s : FoundThat.salas) {
			cmbSala.addItem(s);
		}

		cmbSala.setBounds(24, 29, 102, 20);
		contentPane.add(cmbSala);

		//COMBOBOX OBJETO
		@SuppressWarnings("rawtypes")
		JComboBox cmbObjeto = new JComboBox();
		cmbObjeto.setForeground(new Color(25, 25, 112));
		cmbObjeto.setBackground(new Color(220, 220, 220));
		for (TipoObjeto to : FoundThat.tipoObjetos) {
			cmbObjeto.addItem(to.toString().substring(0, 1).toUpperCase() + to.toString().substring(1).toLowerCase());	
		}	


		cmbObjeto.setBounds(126, 29, 102, 20);
		contentPane.add(cmbObjeto);

		//COMBOBOX ESTADO
		@SuppressWarnings("rawtypes")
		JComboBox cmbEstado = new JComboBox();
		cmbEstado.setForeground(new Color(25, 25, 112));
		cmbEstado.setBackground(new Color(220, 220, 220));

		cmbEstado.addItem("Bom");
		cmbEstado.addItem("Mau");
		cmbEstado.addItem("Razo�vel");

		cmbEstado.setBounds(332, 29, 102, 20);
		contentPane.add(cmbEstado);

		//COMBOBOX COR
		@SuppressWarnings("rawtypes")
		JComboBox cmbCor = new JComboBox();
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

		cmbCor.setBounds(229, 29, 102, 20);
		contentPane.add(cmbCor);

		//JTABLE
		dtm.setRowCount(0);
		dtm.setColumnCount(0);
		String[] nomeColunas = {"C�digo", "Objeto", "Cor", "Estado", "Sala"};
		@SuppressWarnings("unused")
		int cont = 0;
		String[][] regs = null;
		//C�PIA DO ARRAY ORIGINAL DE REGISTOS DAS DOACOES, PARA ORDEN�-LO NA JTABLE POR TIPO DE OBJETO!
		ArrayList <Registo> registoDoacoesOrdenado = new ArrayList<Registo>(FoundThat.registoDoacoes);
		Collections.sort(registoDoacoesOrdenado);
		regs = new String[registoDoacoesOrdenado.size()][5];
		for (int i = 0; i < registoDoacoesOrdenado.size(); i++) {
			regs[i][0] = Integer.toString(registoDoacoesOrdenado.get(i).getCodigo());
			regs[i][1] = registoDoacoesOrdenado.get(i).getObjeto().getNome().substring(0, 1).toUpperCase() + registoDoacoesOrdenado.get(i).getObjeto().getNome().substring(1).toLowerCase();
			regs[i][2] = registoDoacoesOrdenado.get(i).getCor().substring(0, 1).toUpperCase() + registoDoacoesOrdenado.get(i).getCor().substring(1).toLowerCase();
			regs[i][3] = registoDoacoesOrdenado.get(i).getEstado().substring(0, 1).toUpperCase() + registoDoacoesOrdenado.get(i).getEstado().substring(1).toLowerCase();
			regs[i][4] = registoDoacoesOrdenado.get(i).getSala().toString();
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
		scrollPane.setBounds(72, 128, 302, 87);	
		contentPane.add(scrollPane);	


		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnFiltrar.setBackground(new Color(210, 105, 30));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnFiltrar.setBackground(new Color(25, 25, 112));
			}
		});
		btnFiltrar.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnFiltrar.setBackground(new Color(25, 25, 112));
		btnFiltrar.setForeground(new Color(255, 255, 255));
		btnFiltrar.setBorder(null);
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtm.setRowCount(0);
				dtm.setColumnCount(0);

				Date dataInicial = null;
				Date dataFinal = null;
				int cont = 0;
				if (txtInicial.getText().equals("") || (txtFinal.getText().equals(""))) {
					JOptionPane.showMessageDialog(getParent(), "Insira as datas no formato dia-m�s-ano!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
					txtInicial.setText("");
					txtFinal.setText("");
					refresh();
				} else
					try {
						if ( FoundThat.formatoDataRegisto.parse(txtInicial.getText()).after(FoundThat.formatoDataRegisto.parse(txtFinal.getText()))                          ) {
							JOptionPane.showMessageDialog(getParent(), "A data final tem de ser superior � data inicial!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
							refresh();
						}
						else {
							try {
								dataInicial = FoundThat.formatoDataRegisto.parse(txtInicial.getText());
								dataFinal = FoundThat.formatoDataRegisto.parse(txtFinal.getText());
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							//C�PIA DO ARRAY ORIGINAL DE REGISTOS, PARA ORDEN�-LO NA JTABLE POR TIPO DE OBJETO!
							ArrayList <Registo> registoDoacoesOrdenado = new ArrayList<Registo>(FoundThat.registoDoacoes);
							Collections.sort(registoDoacoesOrdenado);
							for (int i = 0; i < registoDoacoesOrdenado.size(); i++) {
								if ((registoDoacoesOrdenado.get(i).getData().after(dataInicial) || registoDoacoesOrdenado.get(i).getData().equals(dataInicial)) && (registoDoacoesOrdenado.get(i).getData().before(dataFinal) || registoDoacoesOrdenado.get(i).getData().equals(dataFinal))) {
									cont++;	
								}
							}
							dtm.setRowCount(0);
							dtm.setColumnCount(0);

							String[][] regs = new String[cont][5];
							String[] nomeColunas = {"C�digo", "Objeto", "Cor", "Estado", "Sala"};

							int c = 0;
							for (int i = 0; i < registoDoacoesOrdenado.size(); i++) {
								if ( (registoDoacoesOrdenado.get(i).getData().after(dataInicial) || registoDoacoesOrdenado.get(i).getData().equals(dataInicial)) && 
										(registoDoacoesOrdenado.get(i).getData().before(dataFinal) || registoDoacoesOrdenado.get(i).getData().equals(dataFinal)) && 
										registoDoacoesOrdenado.get(i).getCor().equals(cmbCor.getSelectedItem().toString().toLowerCase()) && 
										registoDoacoesOrdenado.get(i).getEstado().equals(cmbEstado.getSelectedItem().toString().toLowerCase()) &&
										registoDoacoesOrdenado.get(i).getSala().getNome().equals(cmbSala.getSelectedItem().toString()) &&
										registoDoacoesOrdenado.get(i).getObjeto().getNome().equals(cmbObjeto.getSelectedItem().toString().toLowerCase())) {

									regs[c][0] = Integer.toString(registoDoacoesOrdenado.get(i).getCodigo());
									regs[c][1] = registoDoacoesOrdenado.get(i).getObjeto().getNome().substring(0, 1).toUpperCase() + registoDoacoesOrdenado.get(i).getObjeto().getNome().substring(1).toLowerCase();
									regs[c][2] = registoDoacoesOrdenado.get(i).getCor().substring(0, 1).toUpperCase() + registoDoacoesOrdenado.get(i).getCor().substring(1).toLowerCase();
									regs[c][3] = registoDoacoesOrdenado.get(i).getEstado().substring(0, 1).toUpperCase() + registoDoacoesOrdenado.get(i).getEstado().substring(1).toLowerCase();
									regs[c][4] = registoDoacoesOrdenado.get(i).getSala().toString();
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
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		btnFiltrar.setBounds(273, 95, 101, 23);
		contentPane.add(btnFiltrar);

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
			public void actionPerformed(ActionEvent e) {
				FoundThat.registoDoacoes.clear();
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
		btnSair.setBounds(273, 226, 106, 23);
		contentPane.add(btnSair);

		//BOT�O LIMPAR
		JButton btnLimpar = new JButton("Limpar");
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
		btnLimpar.setBackground(new Color(25, 25, 112));
		btnLimpar.setBorder(null);
		btnLimpar.setForeground(new Color(255, 255, 255));
		btnLimpar.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtInicial.setText("");
				txtFinal.setText("");
				cmbSala.setSelectedIndex(0);
				cmbObjeto.setSelectedIndex(0);
				cmbCor.setSelectedIndex(0);
				cmbEstado.setSelectedIndex(0);
				refresh();

			}
		});
		btnLimpar.setBounds(174, 227, 89, 23);
		contentPane.add(btnLimpar);
		
		//BOT�O +INFO
		JButton btnInfo = new JButton("+Info");
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um objeto!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
				else {
					int cod = table.getSelectedRow();
					String info = "Nome: " + FoundThat.registoDoacoes.get(cod).getNome() + "\n" + "E-mail: " + FoundThat.registoDoacoes.get(cod).getEmail() + 
							"\n" + "Sala: " + FoundThat.registoDoacoes.get(cod).getSala() + "\n" + "Dia: " + FoundThat.formatoDataRegisto.format(FoundThat.registoDoacoes.get(cod).getData()) + 
							"\n" + "Hora: " + FoundThat.registoDoacoes.get(cod).getHora() + "\n" + "Tipo de objeto: " + FoundThat.registoDoacoes.get(cod).getObjeto().getNome().substring(0, 1).toUpperCase() + 
							FoundThat.registoDoacoes.get(cod).getObjeto().getNome().substring(1).toLowerCase() + "\n" + "Cor: " + FoundThat.registoDoacoes.get(cod).getCor().substring(0, 1).toUpperCase() +  
							FoundThat.registoDoacoes.get(cod).getCor().substring(1).toLowerCase() + "\n" + "Estado: " + FoundThat.registoDoacoes.get(cod).getEstado().substring(0, 1).toUpperCase() + 
							FoundThat.registoDoacoes.get(cod).getEstado().substring(1).toLowerCase() + "\n" + "Descri��o: " + FoundThat.registoDoacoes.get(cod).getDescricao() ;
					JOptionPane.showMessageDialog(null, info, "FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
			}
		});
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
		btnInfo.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnInfo.setBorder(null);
		btnInfo.setBackground(new Color(25, 25, 112));
		btnInfo.setBounds(72, 227, 89, 23);
		contentPane.add(btnInfo);

		JLabel lblDataInicial = new JLabel("Data Inicial:");
		lblDataInicial.setForeground(new Color(25, 25, 112));
		lblDataInicial.setFont(new Font("Myanmar Text", Font.PLAIN, 16));
		lblDataInicial.setBounds(72, 64, 106, 23);
		contentPane.add(lblDataInicial);

		JLabel lblDataFinal = new JLabel("Data Final:");
		lblDataFinal.setForeground(new Color(25, 25, 112));
		lblDataFinal.setFont(new Font("Myanmar Text", Font.PLAIN, 16));
		lblDataFinal.setBounds(72, 98, 89, 23);
		contentPane.add(lblDataFinal);


	}

	public static void refresh() {
		dtm.setRowCount(0);
		dtm.setColumnCount(0);
		String[] nomeColunas = {"C�digo", "Objeto", "Cor", "Estado", "Sala"};
		@SuppressWarnings("unused")
		int cont = 0;
		//C�PIA DO ARRAY ORIGINAL DE REGISTOS, PARA ORDEN�-LO NA JTABLE POR TIPO DE OBJETO!
		ArrayList <Registo> registoDoacoesOrdenado = new ArrayList<Registo>(FoundThat.registoDoacoes);
		Collections.sort(registoDoacoesOrdenado);
		String[][] regs = new String[registoDoacoesOrdenado.size()][5];
		for (int i = 0; i < registoDoacoesOrdenado.size(); i++) {
			regs[i][0] = Integer.toString(registoDoacoesOrdenado.get(i).getCodigo());
			regs[i][1] = registoDoacoesOrdenado.get(i).getObjeto().getNome().substring(0, 1).toUpperCase() + registoDoacoesOrdenado.get(i).getObjeto().getNome().substring(1).toLowerCase();
			regs[i][2] = registoDoacoesOrdenado.get(i).getCor().substring(0, 1).toUpperCase() + registoDoacoesOrdenado.get(i).getCor().substring(1).toLowerCase();
			regs[i][3] = registoDoacoesOrdenado.get(i).getEstado().substring(0, 1).toUpperCase() + registoDoacoesOrdenado.get(i).getEstado().substring(1).toLowerCase();
			regs[i][4] = registoDoacoesOrdenado.get(i).getSala().toString();
			cont++;
		}		cont++;


		for (int j = 0; j < nomeColunas.length; j++) {
			dtm.addColumn(nomeColunas[j]);
		}
		for (int i = 0; i < regs.length; i++) {
			dtm.addRow(regs[i]);
		}
	}	
}
