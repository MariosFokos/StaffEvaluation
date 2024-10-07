import java.awt.*;
import net.proteanit.sql.DbUtils;


import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;

public class FindEmp extends JFrame {

	private JPanel contentPane;
	private static String man_comp_afm;
	private JTable table;

	public FindEmp(String username,String name,String surname) { //Parathiro gia tin euresi twn aksiologisewn enos employee ths etairias pou einai manager autos p ekane log in 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 975, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 959, 317);
		contentPane.add(scrollPane);
		try {
			Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!"); //Syndei stin vash klassika
			String queryManAfm ="SELECT company.afm FROM company INNER JOIN manager ON company.afm = manager.company_afm WHERE manager.man_username = '" + username + "'"; //pairnw to afm tis etairias stin opoia einai manager autos p ekane log in 
			PreparedStatement getAFM = con.prepareStatement(queryManAfm);
			ResultSet rs = getAFM.executeQuery();
			while(rs.next()) {
				man_comp_afm = rs.getString("afm"); //Apothikeusi tou afm tis etairias stin metavliti man_comp_afm
			}
			CallableStatement statement = con.prepareCall("{call AithshYpallhlou1(?,?,?)}"); //Klhsh procedure pou emfanizei tis aksiologiseis tou employee pairnontas 3 orismata
			statement.setString(1, name); //1o orisma to onoma tou employee
			statement.setString(2, surname); // 2o orisma to epitheto tou employee
			statement.setString(3,man_comp_afm); //3o orisma to afm tis etairias 
			ResultSet rS = statement.executeQuery();
			
		    table = new JTable(); //dhmiourgia pinaka gia apothikeusi apotelesmatwn ths procedure
		    table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column"
			}
		));
		scrollPane.setViewportView(table);
		table.setModel(DbUtils.resultSetToTableModel(rS)); //apothikeusi apotelesmatwn ths procedure ston pinaka pou ftiaksame
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(763, 348, 102, 33);
		contentPane.add(btnNewButton);
		}
		catch(Exception exc) {
			
		}
	}
}
