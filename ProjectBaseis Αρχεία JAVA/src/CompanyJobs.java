import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class CompanyJobs extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField jobID;
	private JTextField newSalary;
	static String man_comp_afm;



	public CompanyJobs(String username) { //Parathiro gia emfanisi job ths etairias stin opoia einai ypeuthinos autos p ekane log in 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1114, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1098, 224);
		contentPane.add(scrollPane);
		try {
			Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!"); //Syndesh stin vash 
			String queryManAfm ="SELECT company.afm FROM company INNER JOIN manager ON company.afm = manager.company_afm WHERE manager.man_username = '" + username + "'"; //Pairnw to afm tis etairias stin opoia einai manager autos p ekane log in 
			PreparedStatement getAFM = con.prepareStatement(queryManAfm);
			ResultSet rs = getAFM.executeQuery();
			while(rs.next()) {
				man_comp_afm = rs.getString("afm"); //Apothikeusi tou afm sto man_comp_afm
			}
			String query1 = "select job.id,job.edra,job.salary,job.evaluator,job.announce_date,job.submission_date FROM job inner join evaluator on job.evaluator=evaluator.ev_username inner join manager on evaluator.com_afm=manager.company_afm where manager.company_afm='" + man_comp_afm +"'"; //query1 gia emfanish plhroforiwn twn thesewn ergasias ths etairias stin opoia einai manager autos p ekane log in 
			PreparedStatement myStmt = con.prepareStatement(query1);
			ResultSet rS = myStmt.executeQuery();
			
		
		table = new JTable(); //Pinakas gia emfanish thesewn ergasias 
		scrollPane.setViewportView(table);
		table.setModel(DbUtils.resultSetToTableModel(rS)); // apothikeusi apotelesmatwn tou query1 ston pinaka
		
		jobID = new JTextField(); //textField opou tha eisagei o manager to ID ths theshs ergasias pou thelei na kanei modify
		jobID.setBounds(33, 289, 86, 20);
		contentPane.add(jobID);
		jobID.setColumns(10);
		
		newSalary = new JTextField(); //textField gia to neo Salary ean thelei o manager na allaksei to salary kapoias thesis ergasias
		newSalary.setBounds(33, 346, 86, 20);
		contentPane.add(newSalary);
		newSalary.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Job ID");
		lblNewLabel.setBounds(58, 274, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Salary");
		lblNewLabel_1.setBounds(58, 331, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 222, 330, 162);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Modify job's Salary");
		lblNewLabel_2.setBounds(49, 6, 138, 21);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Koumpi pou kanei update to salary mias thesis ergasias me jobid = me auto pou valame sto textField jobID
				try {
					int flag = 0; //xrhsimopoiw flag gia na dw ean tha vrw tetoio job id stis thesis ergasias ths etairias stin opoia einai manager autos p ekane log in 
					Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!");
					String query = "select job.id FROM job inner join evaluator on job.evaluator=evaluator.ev_username inner join manager on evaluator.com_afm=manager.company_afm where manager.company_afm='" + man_comp_afm +"'"; //pairnw ta jobid ths etairias stin opoia einai manager autos p ekane log in 
					PreparedStatement myStmt2 = con.prepareStatement(query);
					ResultSet rs2 = myStmt2.executeQuery();
					while(rs2.next()) {
						String check = rs2.getString(1);
						if(check.equals(jobID.getText()) ) //ta sugkrinw 1-1 ta jobid ths etairias me to jobid pou eisagame sto jobid textField
						{
							flag = 1; //Ean vrw kapoio match,tote kanw to flag 1 
							break ; //kai vgainw apo to while loop
							
						}
					}
					
	
				    if(flag == 1) { //Ean to flag =1 , tote simainei oti uparxei auto to id ara mporw na kanw allages
				    	String query1 = "UPDATE job SET job.salary = '" + newSalary.getText() + "' WHERE job.id ='" + jobID.getText() + "'"; //query1 gia na kanei update to job salary ths thesis me jobid = me to jobid pou valame sto textField
						PreparedStatement myStmt = con.prepareStatement(query1);
						myStmt.executeUpdate(); //Pragmatopoihsh tou update
						dispose();
				    
						LogOut lo = new LogOut();
						lo.setVisible(true);
					}
				    
				    else {
				    	Error er1 = new Error(); //AN to flag einai diaforo tou 1 simainei oti den to vrike ara error gt dn uparxei tetoia thesi me tetoio id stin etairia
				    	er1.setVisible(true);
				    }
				}
					catch(Exception exc13) { // exception handling gia tin vash 
						Error er1 = new Error();
						er1.setVisible(true);
					}
			
			}
		});
		btnNewButton.setBounds(164, 92, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Close");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();				
			}
			
		});
		btnNewButton_1.setBounds(882, 288, 115, 42);
		contentPane.add(btnNewButton_1);
		}
		catch(Exception e) { //Exception handling gia tin vash 
			Error er1 = new Error();
			er1.setVisible(true);
		}
	}

	}

