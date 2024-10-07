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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManagerInformation extends JFrame {

	private JPanel contentPane;
	private final JScrollPane scrollPane = new JScrollPane();
	private JTable table;
	private JTextField newEmail;
	private JTextField newPassword;
	private JButton btnNewButton;
	private JButton btnNewButton_1;


	public ManagerInformation(String username) { //Klash pou emfanizei tis plhrofories tou manager pou ekane log in (pairnei san orisma to username pou ekane log in ) 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 961, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		scrollPane.setBounds(0, 0, 945, 91);
		contentPane.add(scrollPane);
		try {
		Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!");
		String query1 = "Select * FROM manager INNER JOIN user ON man_username = username WHERE man_username ='" + username + "'"; //Query gia na emfanizei oles tis plhrofories tou manager pou kaname log in 
		PreparedStatement myStmt = con.prepareStatement(query1);
		ResultSet rs = myStmt.executeQuery();
		
		table = new JTable(); //Dhimourgia pinaka gia apothikeusi apotelesmatwn tou query1
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column"
			}
		));
		scrollPane.setViewportView(table);
		table.setModel(DbUtils.resultSetToTableModel(rs)); //Apothikeusi apotelesmatwn tou query1 ston pinaka pou dhmiourgisame
		
		newEmail = new JTextField(); //textField gia to newEmail ean thelw na allakzw to email tou manager
		newEmail.setBounds(10, 185, 154, 20);
		contentPane.add(newEmail);
		newEmail.setColumns(10);
		
		newPassword = new JTextField(); //textField gia to newPassword ean thelw na allaksw to password tou manager
		newPassword.setBounds(10, 254, 154, 20);
		contentPane.add(newPassword);
		newPassword.setColumns(10);
		
		btnNewButton = new JButton("Update Email");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //koumpi pou kanei update to email tou manager, to neo email to pairnei apo to textField newEmail 
				try {
					Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!");
					String query1 = "UPDATE USER SET USER.email = '" + newEmail.getText() + "' WHERE username ='" + username + "'";
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
		btnNewButton.setBounds(174, 184, 131, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Update Password");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //koumpi pou kanei update to password tou manager, to neo password to pairnei apo to textField newPassword 
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
		btnNewButton_1.setBounds(174, 253, 131, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("CLOSE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
		btnNewButton_2.setBounds(762, 202, 108, 47);
		contentPane.add(btnNewButton_2);
		}
		catch(Exception Ex1) {
			Error er1 = new Error();
			er1.setVisible(true);
		}
	
	}
}
