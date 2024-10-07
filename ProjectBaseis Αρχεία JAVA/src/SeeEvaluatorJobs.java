import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

import net.proteanit.sql.DbUtils;

public class SeeEvaluatorJobs extends JFrame {

	private JPanel contentPane;
	private JTextField newPosition;
	private JTextField newEdra;
	private JTextField newSalary;
	private JTextField jobID;
	private JTextField newSubmissionDate;
	private JTable table;
	private static String man_comp_afm;
	
	public SeeEvaluatorJobs(String username) { //Exw eksigisei tin douleia auth ths klashs stin klhsh ths apo to evaluator window ( pairnei orisma to username p kaname log in )
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 421);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		try {
			
			Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!");
			String queryManAfm ="SELECT company.afm FROM company INNER JOIN evaluator ON company.afm = evaluator.com_afm WHERE evaluator.ev_username = '" + username + "'"; //Pairnw to afm ths etairias pou sunergazetai o evaluator
			PreparedStatement getAFM = con.prepareStatement(queryManAfm);
			ResultSet rs = getAFM.executeQuery();
			while(rs.next()) {
		    man_comp_afm = rs.getString("afm"); //Apothikeusi tou afm ths etairias pou sunergazetai o evaluator stin public metavliti man_comp_afm
			}
			String query = "Select job.id,job.position,job.edra,job.salary,job.evaluator,job.announce_date,job.submission_date from job INNER JOIN evaluator ON job.evaluator = evaluator.ev_username  WHERE evaluator.com_afm = '" + man_comp_afm +"'"; //Query gia emfanisi job ths etairias pou sunergazetai o evaluator 
			PreparedStatement myStmt = con.prepareStatement(query);
			ResultSet rS = myStmt.executeQuery();
			
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1084, 119);
		contentPane.add(scrollPane);
		
		table = new JTable(); //Dhmiourgia pinaka gia emfanish apotelesmatwn tou query
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column"
			}
		));
		scrollPane.setViewportView(table);
		table.setModel(DbUtils.resultSetToTableModel(rS)); //Apothikeusi apotelesmatwn tou query ston pinaka pou ftiaksame

		JPanel panel = new JPanel();
		panel.setBounds(0, 200, 350, 182);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New Position");
		lblNewLabel.setBounds(28, 11, 158, 23);
		panel.add(lblNewLabel);
		
		JLabel newa = new JLabel("New Edra");
		newa.setBounds(28, 64, 158, 23);
		panel.add(newa);
		
		JLabel lblNewSalary = new JLabel("New Salary");
		lblNewSalary.setBounds(28, 114, 158, 23);
		panel.add(lblNewSalary);
		
		newPosition = new JTextField(); //textField gia tin nea thesi ean tha thelisei o evaluator na kanei update se ena job p dhmiourgise o idios
		newPosition.setBounds(10, 31, 106, 20);
		panel.add(newPosition);
		newPosition.setColumns(10);
		
		newEdra = new JTextField();  //textField gia tin nea edra ean tha thelisei o evaluator na kanei update se ena job p dhmiourgise o idios
		newEdra.setBounds(10, 83, 106, 20);
		panel.add(newEdra);
		newEdra.setColumns(10);
		
		newSalary = new JTextField();  //textField gia to neo Salary ean tha thelisei o evaluator na kanei update se ena job p dhmiourgise o idios
		newSalary.setBounds(10, 138, 106, 20);
		panel.add(newSalary);
		newSalary.setColumns(10);
		
		newSubmissionDate = new JTextField(); //textField gia to neo SumbissionDate ean tha thelisei o evaluator na kanei update se ena job p dhmiourgise o idios
		newSubmissionDate.setBounds(163, 83, 155, 20);
		panel.add(newSubmissionDate);
		newSubmissionDate.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("New Submission Date");
		lblNewLabel_2.setBounds(188, 65, 130, 20);
		panel.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(350, 200, 350, 182);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		jobID = new JTextField();  //textField opou o xrhsths tha epilegei se poio jobId thelei na kanei tis allages
		jobID.setBounds(120, 94, 86, 20);
		panel_1.add(jobID);
		jobID.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Job ID you want to update");
		lblNewLabel_1.setBounds(96, 69, 155, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("To insert a job,type values into New Position,New Edra,New Salary,New Submission Date and press Insert Job Button");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(151, 155, 1007, 34);
		contentPane.add(lblNewLabel_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(700, 200, 385, 182);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnNewButton = new JButton("Update Position");
		btnNewButton.setBounds(20, 11, 160, 23);
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update Edra");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Koumpi pou kanei update tin edra , thn opoia nea edra tin pairnei apo to textField newEdra kai ginontai oi allages mono sta job pou evaluator einai autos p ekane log in 
				try {
					
					Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!");
					String query = "UPDATE job SET job.edra = '" + newEdra.getText() + "' WHERE job.id = '" + jobID.getText() + "' AND job.evaluator = '" + username + "'";
					System.out.println(query);
					PreparedStatement myStmt1 = con.prepareStatement(query);
				    myStmt1.executeUpdate();
				    dispose();
				
				    LogOut lo = new LogOut();
				    lo.setVisible(true);
				}
					catch(Exception exc13) {
						Error er1 = new Error();
						er1.setVisible(true);
					}
				
			}
		});
		btnNewButton_1.setBounds(20, 51, 160, 23);
		panel_2.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Update Salary");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Paromoia logikh me to update Edra apla edw kanw update salary
				try {
					
					Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!");
					String query = "UPDATE job SET job.salary = '" + newSalary.getText() + "' WHERE job.id = '" + jobID.getText() + "' AND job.evaluator = '" + username + "'";
					System.out.println(query);
					PreparedStatement myStmt1 = con.prepareStatement(query);
				    myStmt1.executeUpdate();
				    dispose();
				
				    LogOut lo = new LogOut();
				    lo.setVisible(true);
				}
					catch(Exception exc13) {
						Error er1 = new Error();
						er1.setVisible(true);
					}
			}
		});
		btnNewButton_2.setBounds(20, 91, 160, 23);
		panel_2.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("Update Submission Date");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Paromoia logikh me to update Edra apla edw kanw update sumbission date
				try {
					
					Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!");
					String query = "UPDATE job SET job.submission_date = '" + newSubmissionDate.getText() + "' WHERE job.id = '" + jobID.getText() + "' AND job.evaluator = '" + username + "'";
					System.out.println(query);
					PreparedStatement myStmt1 = con.prepareStatement(query);
				    myStmt1.executeUpdate();
				    dispose();
				
				    LogOut lo = new LogOut();
				    lo.setVisible(true);
				}
					catch(Exception exc13) {
						Error er1 = new Error();
						er1.setVisible(true);
					}
			}
		});
		btnNewButton_2_1.setBounds(20, 131, 160, 23);
		panel_2.add(btnNewButton_2_1);
		
		JButton btnInsertJob = new JButton("Insert Job");
		btnInsertJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Koumpi gia na prosthetei mia thesi proagwghs apla den doulevei den kserw giati 
				try {
					
					Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!");
					String query1 = "Insert INTO job VALUES('','ASDAD','ASASDSADAS','1200','alone21','','2050-02-01');"; //Test ena aplo insert gia na dw mhpws ekana lathos stin eisodo 
					PreparedStatement myStmt = con.prepareStatement(query1);
					myStmt.executeUpdate();
					dispose();
				
				
			}
				catch(Exception exc1) {
					Error er1=new Error();
					er1.setVisible(true);
					
				}
			}
		});
		btnInsertJob.setBounds(198, 75, 160, 23);
		panel_2.add(btnInsertJob);
		
		JLabel lblNewLabel_3_1 = new JLabel("To update a job STEP 1 : insert values either to Position,Edra,Salary,Submission Date STEP 2 : Enter the Job's ID you want to Update STEP 3 : Press the right button");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(10, 130, 1064, 34);
		contentPane.add(lblNewLabel_3_1);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Paromoia logikh me to update Edra apla edw kanw update to position
				try {
					
					Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!");
					String query = "UPDATE job SET job.position = '" + newPosition.getText() + "' WHERE job.id = '" + jobID.getText() + "' AND job.evaluator = '" + username + "'";
					System.out.println(query);
					PreparedStatement myStmt1 = con.prepareStatement(query);
				    myStmt1.executeUpdate();
				    dispose();
				
				    LogOut lo = new LogOut();
				    lo.setVisible(true);
				}
					catch(Exception exc13) { //Klassiko exception handling
						Error er1 = new Error();
						er1.setVisible(true);
					}
			}
		});
		}
		catch(Exception exc) { //Exception handling
			Error er1 = new Error();
			er1.setVisible(true);
		}
	}
}

