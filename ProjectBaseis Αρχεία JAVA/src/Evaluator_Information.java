import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Evaluator_Information extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField newEmail;
	private JTextField newPassword;
	private JLabel lblNewLabel_1;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;


	public Evaluator_Information(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1064, 79);
		contentPane.add(scrollPane);
	
			try {
				Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!");
				String query1 = "Select * from USER WHERE username ='" + username + "'"; //query1 gia emfanish twn stoixeiwn tou user pou ekane log in ( kai sugkekrimena tou evaluator gia auth thn periptwsh)
				PreparedStatement myStmt = con.prepareStatement(query1);
				ResultSet rs = myStmt.executeQuery();
				table = new JTable(); //Dhimourgia pinaka gia tin apothikeusi twn plhroforiwn tou evaluator
				table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column"
			}
		));
		scrollPane.setViewportView(table);
		table.setModel(DbUtils.resultSetToTableModel(rs)); //Apothikeusi plhroforiwn tou evaluator ston pinaka pou ftiaksame
		
		newEmail = new JTextField(); //textField gia to neo email
		newEmail.setBounds(10, 161, 173, 29);
		contentPane.add(newEmail);
		newEmail.setColumns(10);
		
		newPassword = new JTextField(); //textField gia to neo password
		newPassword.setBounds(10, 231, 173, 20);
		contentPane.add(newPassword);
		newPassword.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Enter your new email here");
		lblNewLabel.setBounds(32, 141, 167, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Update Email");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  //Koumpi pou kanei update to email 
				try {
					Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!");
					String query1 = "UPDATE USER SET USER.email = '" + newEmail.getText() + "' WHERE username ='" + username + "'"; //Query1 pou kanei update to email tou evaluator pou ekane log in ,  vazontas ws neo email to email apo to newEmail textField
					PreparedStatement myStmt = con.prepareStatement(query1);
				    myStmt.executeUpdate(); //Klhsh tou query1 ara kai pragmatopoiountai allages
				    dispose();
				    
				    LogOut lo = new LogOut(); //afou kanw allages panta kalw to log out screen pou exw ftiaksei 
				    lo.setVisible(true);
					} 
					catch(Exception exc13) { //Exception handling me mhnyma stin konsola
						System.out.println("Trww error");
					}
			}
		});
		btnNewButton.setBounds(223, 164, 152, 23);
		contentPane.add(btnNewButton);
		
		lblNewLabel_1 = new JLabel("Enter your new password here");
		lblNewLabel_1.setBounds(20, 206, 179, 14);
		contentPane.add(lblNewLabel_1);
		
		btnNewButton_1 = new JButton("Update password");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Paromoia douleia me to update tou email,mono pou edw allazw password
				try {
					Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!");
					String query1 = "UPDATE USER SET USER.password = '" + newPassword.getText() + "' WHERE username ='" + username + "'";
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
		btnNewButton_1.setBounds(223, 231, 152, 20);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Close");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Koumpi gia na kleinw auto to parathiro 
				dispose();
			}
		});
		btnNewButton_2.setBounds(824, 297, 113, 42);
		contentPane.add(btnNewButton_2);
		}
		catch(Exception exc) 
		{
			Error er1 = new Error();
			er1.setVisible(true);
		}
	}
}

