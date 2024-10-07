
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
public class Employee_Information extends JFrame {

	private JPanel bioInsert;
	private JTable tableUsers;
	private JScrollPane scrollPane_1;
	private JTable table_1;
	private JTextField newPassword;
	private JTextField bio;


	 
	public Employee_Information(String username) { //Parathiro gia na emfanizei tis plhrofories tou employee pou ekane log in ( pairnei san orisma to username pou kaname to log in )
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1336, 478);
		bioInsert = new JPanel();
		bioInsert.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(bioInsert);
		bioInsert.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 955, 43);
		bioInsert.add(scrollPane);
		try {
			Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!"); //Syndesh se vash ( den xreiazetai na to eksigw kathe fora nomizw)
			String query1 = "Select * from USER WHERE username ='" + username + "'"; //Entolh gia na emfanizei ta stoixeia tou user pou kaname log in 
			PreparedStatement myStmt = con.prepareStatement(query1);
			ResultSet rs = myStmt.executeQuery();
			
			tableUsers = new JTable(); //Pinakas gia na apothikeuw ta dedomena tou query1
			tableUsers.setModel(new DefaultTableModel(
				new Object[][] {
					{null,null,null,null,null,null},
				},
				new String[] {
					"Onoma Xrhsth", "Kwdikos Xrhsth", "Onoma", "Epitheto", "Hmeromhnia Eggrafhs", "email"
				}
			));
			scrollPane.setViewportView(tableUsers);
			tableUsers.setModel(DbUtils.resultSetToTableModel(rs)); //APothikeusi apotelesmatwn tou query1 ston pinaka pou eftiaksa
		}
		catch(Exception Ex1) { //Exception handling me parathiro error pou dhmiourgisa 
			Error er1 = new Error();
			er1.setVisible(true);
		}
		
		
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 54, 1320, 43);
		bioInsert.add(scrollPane_1);
		try {
			
			Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!");
			CallableStatement statement = con.prepareCall("{call employee_informations(?)}"); //Procedure employee_information
			statement.setString(1, username); //1o orisma ths procedure = username
			ResultSet rs = statement.executeQuery();
			
			
			table_1 = new JTable(); //Dhmiourgia pinaka gia apothikeusi apotelesmatwn ths procedure employee_information
		
			table_1.setModel(new DefaultTableModel(
			
					new Object[][] {
				
						{null, null, null, null, null, null, null, null, null, null, null, null, null},
			
					},
			
					new String[] {
				
							"Biografiko", "Certificates", "Systatikes", "Awards", "Afm Etaireias", "AM", "Xronia Empeirias", "Glwsses", "Titlos", "Idryma", "Vathmida", "Xronologia", "Vathmos"
			
					}
		
					));
		
			scrollPane_1.setViewportView(table_1);
			table_1.setModel(DbUtils.resultSetToTableModel(rs)); //Apothikeusi apotelesmatwn ths procedure ston pinaka
			
			JButton UpdatePasswordButton = new JButton("Update Password");
			UpdatePasswordButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) { //Koumpi gia enhmerwsh tou password
					try {
					Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!");
					String query1 = "UPDATE USER SET USER.password = '" + newPassword.getText() + "' WHERE username ='" + username + "'"; //query1 pou kanei update ton kwdiko tou user pou ekane log in kai pairnei to kainorgio password apo to newPassword textfield pou exoume ftiaksei
					PreparedStatement myStmt = con.prepareStatement(query1);
				    myStmt.executeUpdate(); //Klhsh ths query 1
				    dispose();
				    
				    LogOut lo = new LogOut(); //Afou allakse to password vgainei parathiro log out
				    lo.setVisible(true);
					}
					catch(Exception exc13) { //Exception handling me mhnyma stin konsola
						System.out.println("Trww error");
					}
				}
			});
			UpdatePasswordButton.setBounds(159, 341, 153, 43);
			bioInsert.add(UpdatePasswordButton);
			
			newPassword = new JTextField(); //to textfield gia to kainourgio password
			newPassword.setBorder(new LineBorder(new Color(171, 173, 179)));
			newPassword.setBounds(20, 355, 119, 29);
			bioInsert.add(newPassword);
			newPassword.setColumns(10);
			
			JButton btnNewButton_1 = new JButton("Update Bio");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) { //Paromoies energeies me to update tou password ,mono pou edw kanoume update to bio kai pairnoume to new bio apo to textfild bio
					try {
						Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!");
						String query1 = "UPDATE employee SET employee.bio = '" + bio.getText() + "' WHERE e_username ='" + username + "'";
						PreparedStatement myStmt = con.prepareStatement(query1);
					    myStmt.executeUpdate();
					    dispose();
					    
					    LogOut lo = new LogOut();
					    lo.setVisible(true);
						}
						catch(Exception exc13) {
							System.out.println("Trww error");
						}
					
					
				}
			});
			btnNewButton_1.setBounds(648, 169, 119, 37);
			bioInsert.add(btnNewButton_1);
			
			JLabel lblNewLabel = new JLabel("Set your new Password here");
			lblNewLabel.setBounds(10, 326, 203, 26);
			bioInsert.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Bio Update");
			lblNewLabel_1.setBounds(10, 122, 153, 22);
			bioInsert.add(lblNewLabel_1);
			
			bio = new JTextField();
			bio.setBounds(10, 140, 618, 175);
			bioInsert.add(bio);
			bio.setColumns(10);

		}
	
		catch(Exception exc2) {
		
	}
}
}
