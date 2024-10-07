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

public class EvaluationCheck extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JScrollPane scrollPane_4;
	private JTable table_4;

	public EvaluationCheck(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1092, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 417, 70);
		contentPane.add(scrollPane);
		try {
			 Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!"); //Syndesh se vash ( den xreiazetai na to eksigw kathe fora nomizw)
			 String query1 = "select requestevaluation.employee_user,requestevaluation.jobid from requestevaluation inner join job on requestevaluation.jobid=job.id where job.evaluator='" + username + "'"; 
			 PreparedStatement myStmt = con.prepareStatement(query1);
			 ResultSet rs = myStmt.executeQuery();
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
			},
			new String[] {
				"New column"
			}
		));
		scrollPane.setViewportView(table);
		table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception e) {
			Error er1 = new Error();
			er1.setVisible(true);
		}
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 81, 960, 65);
		contentPane.add(scrollPane_1);
		try {	
			 Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!"); 
			 String query1 = "select * from evaluation_1 where evaluation_1.axiologhths='" + username + "'"; 
			 PreparedStatement myStmt = con.prepareStatement(query1);
			 ResultSet rs = myStmt.executeQuery();
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		table_1.setModel(DbUtils.resultSetToTableModel(rs));

		}
		catch(Exception e) {
			Error er2 = new Error();
			er2.setVisible(true);
		}
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 157, 960, 65);
		contentPane.add(scrollPane_2);
		try {
			 Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!"); 
			 String query1 = "select * from evaluation_2 where evaluation_2.axiologhths_ev2='" + username + "'"; 
			 PreparedStatement myStmt = con.prepareStatement(query1);
			 ResultSet rs = myStmt.executeQuery();
			 
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		table_2.setModel(DbUtils.resultSetToTableModel(rs));

		}
		catch(Exception e) {
			Error er3 = new Error();
			er3.setVisible(true);
		}
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(0, 233, 960, 65);
		contentPane.add(scrollPane_3);
		try {
			 Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!"); 
			 String query1 = "select * from evaluation_3 where evaluation_3.axiologhths_ev3='" + username + "'"; 
			 PreparedStatement myStmt = con.prepareStatement(query1);
			 ResultSet rs = myStmt.executeQuery();
			 
		table_3 = new JTable();
		scrollPane_3.setViewportView(table_3);
		table_3.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch(Exception e) {
			Error er4 = new Error();
			er4.setVisible(true);
		}
		
		scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(0, 313, 960, 65);
		contentPane.add(scrollPane_4);
		try {
			 Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!"); 
			 String query1 = "select * from evaluationresult inner join job on evaluationresult.id_job=job.id where job.evaluator='" + username + "'"; 
			 PreparedStatement myStmt = con.prepareStatement(query1);
			 ResultSet rs = myStmt.executeQuery();
			 
		table_4 = new JTable();
		scrollPane_4.setViewportView(table_4);
		table_4.setModel(DbUtils.resultSetToTableModel(rs));

		}
		catch(Exception e) {
			Error er5 = new Error();
			er5.setVisible(true);
		}
		
	}
}
