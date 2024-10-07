import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.CallableStatement;
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
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AvJobs extends JFrame {

	private JPanel contentPane;
	private final JScrollPane scrollPane = new JScrollPane();
	private JTable table;
	private JTable table_1;
	private JTextField jobID;

	public AvJobs(String username) { //Parathiro pou sxetizetai me ta job request tou xrhsth 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 896, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		scrollPane.setBounds(0, 0, 870, 215);
		contentPane.add(scrollPane);
		try {
			
			Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!"); //Syndesh stin vash 
			CallableStatement statement = con.prepareCall("{call select_jobs(?)}"); // procedure apo tin vash mas 
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery(); //klhsh procedure apo tin vash 
			
		table = new JTable(); //Dhmiourgia pinaka gia oles tis diathesimes ergasies pou mporei na kanei request o employee
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Job ID", "Job Position", "Base", "Salary", "Evaluator", "Announce Date", "Submittion Date"
			}
		));
		
		scrollPane.setViewportView(table);
		table.setModel(DbUtils.resultSetToTableModel(rs)); //Emfanish olwn twn diathesimwn ergasiwn pou mporei na kanei job request o employee
		
		JLabel lblNewLabel = new JLabel("Jobs you already have requested evaluation");
		lblNewLabel.setBounds(135, 226, 313, 14);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 244, 502, 170);
		contentPane.add(scrollPane_1);
		
		String query1 = "Select jobid,job.position,job.edra,job.salary,job.evaluator,job.announce_date,job.submission_date from requestevaluation INNER JOIN job ON jobid=job.id WHERE employee_user ='" + username + "'"; //query pou emfanizei ta stoixeia twn ergasiwn pou exoume kanei request gia aksiologhsh 
		PreparedStatement myStmt = con.prepareStatement(query1);
		ResultSet rS = myStmt.executeQuery();
		
		table_1 = new JTable(); //Pinakas gia na emfanizei ta apotelesmata tou query1
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Job ID"
			}
		));
		scrollPane_1.setViewportView(table_1);
		table_1.setModel(DbUtils.resultSetToTableModel(rS)); //Apothikeusi apotelesmatwn tou query 1 ston pinaka
		
		jobID = new JTextField();
		jobID.setBounds(637, 248, 89, 20);
		contentPane.add(jobID);
		jobID.setColumns(10);
		
		JButton btnNewButton = new JButton("Insert");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				
					Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!");
					String query1 = "INSERT INTO requestevaluation VALUES ('" + username + "'," + jobID.getText() + ")";  //Entolh gia na eisagei neo job request gia ton employee,opou o xrhsths dinei to jobid tis douleias pou thelei na kanei request mesw tou textfield JobID
					PreparedStatement myStmt = con.prepareStatement(query1);
					myStmt.executeUpdate();
					dispose(); //To dispose() to xrisimopoioume gia na kleinei to parathiro afou egine h energeia
				
				
			}
				catch(Exception exc1) { //Exception handling me parathiro error pou exoume dimiourgisei 
					Error er1=new Error();
					er1.setVisible(true);
					
				}
			}
		});
		
		
		btnNewButton.setBounds(637, 279, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!");
					String query1 = "DELETE FROM requestevaluation WHERE jobid=" + jobID.getText() + " AND employee_user='" + username +"'";
					PreparedStatement myStmt = con.prepareStatement(query1);
					myStmt.executeUpdate();
					dispose();
				
				
			}
				catch(Exception exc1) {
					System.out.println("Trww error");
				}
				
				
			}
		});
		btnNewButton_1.setBounds(637, 313, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Enter the Job ID you want to insert or delete");
		lblNewLabel_1.setBounds(585, 226, 229, 14);
		contentPane.add(lblNewLabel_1);
		}
		catch(Exception exav) {
			
		}
	}
}
