package pt.foundthat.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import pt.foundthat.controller.FoundThat;
import pt.foundthat.controller.ManagerDoacao;

public class FrmDoacoes extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	static DefaultTableModel dtm = new DefaultTableModel();
	JButton btnInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDoacoes frame = new FrmDoacoes();
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
	public FrmDoacoes() throws Exception {
		setResizable(false);
		FoundThat.managerAction = new ManagerDoacao();
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmDoacoes.class.getResource("/pt/foundthat/resources/lupa.png")));
		setTitle("Doa\u00E7\u00F5es - FoundThat");
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

		//JTABLE
		Date temp;
		Date sistema;
		sistema = FoundThat.formatoDataRegisto.parse(FoundThat.dataRegisto);

		String dataTemp;
		int cont = 0;
		for (int i = 0; i < FoundThat.registos.size(); i++) {
			dataTemp = FoundThat.formatoDataRegisto.format(FoundThat.registos.get(i).getData());
			temp = FoundThat.formatoDataRegisto.parse(dataTemp);
			temp.setTime(temp.getTime() + 30L * 24 * 60 * 60 * 1000);
			if ((temp.before(sistema)) && ((FoundThat.registos.get(i).getEstado().equals("razo�vel")) || (FoundThat.registos.get(i).getEstado().equals("bom")))) {
				cont++;
			}
		}

		String[] nomeColunas = {"C�digo", "Data", "Objeto", "Descri��o", "Institui��o"};
		int c = 0;
		String[][] regs = new String[cont][5];
        for (int i = 0; i < FoundThat.registos.size(); i++) {
            dataTemp = FoundThat.formatoDataRegisto.format(FoundThat.registos.get(i).getData());
			temp = FoundThat.formatoDataRegisto.parse(dataTemp);
			temp.setTime(temp.getTime() + 30L * 24 * 60 * 60 * 1000);
			if ((temp.before(sistema)) && ((FoundThat.registos.get(i).getEstado().equals("razo�vel")) || (FoundThat.registos.get(i).getEstado().equals("bom")))) {
				regs[c][0] = Integer.toString(FoundThat.registos.get(i).getCodigo());
				regs[c][1] = FoundThat.formatoDataRegisto.format(FoundThat.registos.get(i).getData());
				regs[c][2] = FoundThat.registos.get(i).getObjeto().getNome().substring(0, 1).toUpperCase() + FoundThat.registos.get(i).getObjeto().getNome().substring(1).toLowerCase();
				regs[c][3] = FoundThat.registos.get(i).getDescricao().toString();
				regs[c][4] = FoundThat.registos.get(i).getObjeto().getCodigoIS().getNome().substring(0, 1).toUpperCase() + FoundThat.registos.get(i).getObjeto().getCodigoIS().getNome().substring(1).toLowerCase();
				c++;
			}
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
		scrollPane.setBounds(49, 38, 343, 87);	
		contentPane.add(scrollPane);


		//BOT�O +INFO
		btnInfo = new JButton("+Info");
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
		btnInfo.setBackground(new Color(25, 25, 112));
		btnInfo.setForeground(new Color(255, 255, 255));
		btnInfo.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um objeto!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
				else {
					int cod = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()) -  1;
					String info = "Nome: " + FoundThat.registos.get(cod).getNome() + "\n" + "E-mail: " + FoundThat.registos.get(cod).getEmail() + 
							"\n" + "Sala: " + FoundThat.registos.get(cod).getSala() + "\n" + "Dia: " + FoundThat.formatoDataRegisto.format(FoundThat.registos.get(cod).getData()) + 
							"\n" + "Hora: " + FoundThat.registos.get(cod).getHora() + "\n" + "Tipo de objeto: " + FoundThat.registos.get(cod).getObjeto().getNome().substring(0, 1).toUpperCase() + 
							FoundThat.registos.get(cod).getObjeto().getNome().substring(1).toLowerCase() + "\n" + "Cor: " + FoundThat.registos.get(cod).getCor().substring(0, 1).toUpperCase() +  
							FoundThat.registos.get(cod).getCor().substring(1).toLowerCase() + "\n" + "Estado: " + FoundThat.registos.get(cod).getEstado().substring(0, 1).toUpperCase() + 
							FoundThat.registos.get(cod).getEstado().substring(1).toLowerCase() + "\n" + "Descri��o: " + FoundThat.registos.get(cod).getDescricao() ;
					JOptionPane.showMessageDialog(null, info, "FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
			}
		});
		btnInfo.setBounds(165, 155, 106, 23);
		contentPane.add(btnInfo);

		//BOT�O SAIR
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
		btnSair.setBackground(new Color(25, 25, 112));
		btnSair.setForeground(new Color(255, 255, 255));
		btnSair.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
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
		btnSair.setBounds(286, 155, 106, 23);
		contentPane.add(btnSair);

		//BOT�O DOAR
		JButton btnDoar = new JButton("Doar");
		btnDoar.setBorder(null);
		btnDoar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnDoar.setBackground(new Color(210, 105, 30));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnDoar.setBackground(new Color(25, 25, 112));
			}
		});
		btnDoar.setBackground(new Color(25, 25, 112));
		btnDoar.setForeground(new Color(255, 255, 255));
		btnDoar.setFont(new Font("Myriad Pro", Font.PLAIN, 18));
		btnDoar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Selecione um objeto!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
				}
				else {
					int cod = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					if (FoundThat.managerAction.execute(cod) != -1) {
						try {
							FoundThat.gravarFicheiro();
							FoundThat.gravarDoacoes();
							FoundThat.gravarDoacoesIS();
							dtm.removeRow(table.getSelectedRow());
							JOptionPane.showMessageDialog(null, "Objeto doado com sucesso!", "AVISO! - FoundThat", JOptionPane.INFORMATION_MESSAGE);;
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnDoar.setBounds(49, 155, 106, 23);
		contentPane.add(btnDoar);

	}
}





