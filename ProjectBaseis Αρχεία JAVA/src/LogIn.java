
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import java.sql.*;

public class LogIn extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;
	private JLabel username1;
	private JLabel password1;
	private Statement st=null;
	private ResultSet rs=null;
	public static String currentUsername;


	

	public LogIn() { //Dhimourgia Parathirou gia to Log In 
		setTitle("Evaluation_Test");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 691, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Koumpi gia to Log IN 
				try {
					Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!");  //Syndesi stin vash dedomenwn mas 
					
					PreparedStatement myStmt = con.prepareStatement("Select * FROM user WHERE username='" + username.getText() + "' AND password='" + password.getText() +"'"); //Entolh gia na dialeksei ton user apo thn vash me ta stoixeia pou evale o xrhsths
					
					String currentUsername = username.getText(); //Apothikeusi tou periexomenou tou textField username stin metavliti currentUsername giati tha to xreiastw argotera
					
					ResultSet myRs = myStmt.executeQuery();
					
					System.out.println(username.getText() + "           " + password.getText()); //Aplh ektypwsh gia diko mou elegxo (elegxw ta stoixeia me ta opoia ekane log in ) 
					
					if(myRs.next() ) { //Ean h ektelesh tis parapanw entolhs vrei kapoia antistoixisi stin vash mas,shmainei oti uparxei tetoios xrhsths ara mpainoume edw
						
						JOptionPane.showMessageDialog(contentPane, "Succesfully loged in");
						
						PreparedStatement myStmt2 = con.prepareStatement("Select * from employee WHERE e_username='" + username.getText() + "'");  //Entolh gia na dw an o user einai employee
						
						PreparedStatement myStmt3 = con.prepareStatement("Select * from evaluator WHERE ev_username ='" + username.getText() + "'"); //Entolh gia na dw an o user einai evaluator
						
						PreparedStatement myStmt4 = con.prepareStatement("Select * from manager WHERE man_username = '" + username.getText() +"'"); //Entolh gia na dw an o user einai manager 
						
						ResultSet myRs2 = myStmt2.executeQuery();
						ResultSet myRs3 = myStmt3.executeQuery();
						ResultSet myRs4 = myStmt4.executeQuery();
						
						
							if(myRs2.next()) {
							// Edw eimaste stous employee
								dispose();
								EmployeeWindow ew = new EmployeeWindow(username.getText()); //Dhmiourgia kai klhsh parathirou gia to menu twn employee
								ew.setVisible(true);
							}
								
							else if(myRs3.next()) {
								//Edw eimaste stous evaluator
								EvaluatorWindow ew1 = new EvaluatorWindow(username.getText()); //Dhmiourgia kai klhsh parathirou gia to menu twn evaluator
								ew1.setVisible(true);
							}
							
							else if(myRs4.next()) {
								//Edw eimaste stous manager
								dispose();
								ManagerWindow mw1 = new ManagerWindow(username.getText());  //Dhmiourgia kai klhsh parathirou gia to menu twn manager
								mw1.setVisible(true);
							}
					}
							
					else 
						JOptionPane.showMessageDialog(contentPane, "Failed to log in"); //den upirxe antistoixisi stin vash me ta stoixeia pou dwsame opote petaei mhnyma failed to log in 
					
				}
				catch(Exception ex) { //Exception handling gia to ean den mporesa na sundethw stin vash (emfanizei mhnyma stin konsola)
					System.out.println("Couldnt connect to database");
					
				}
		
			}
		});
		btnNewButton.setBounds(220, 209, 101, 34);
		contentPane.add(btnNewButton);
		
		username = new JTextField(); //textField pou eisagei to username
		username.setBounds(241, 125, 171, 20);
		contentPane.add(username);
		username.setColumns(10);
		
	    username1 = new JLabel("Username");
		username1.setBounds(167, 113, 94, 44);
		contentPane.add(username1);
		
		
		password1 = new JLabel("Password");
		password1.setBounds(167, 154, 65, 44);
		contentPane.add(password1);
		
		password = new JPasswordField(); //passwordField pou eisagei to password
		password.setBounds(241, 166, 171, 20);
		contentPane.add(password);
		
		JButton btnNewButton_1 = new JButton("Close");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setActionCommand("Close");
		btnNewButton_1.setBounds(342, 209, 101, 34);
		contentPane.add(btnNewButton_1);
	}
}


