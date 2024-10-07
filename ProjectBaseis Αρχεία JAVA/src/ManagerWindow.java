import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class ManagerWindow extends JFrame {

	private JPanel contentPane;
	private JTextField getName;
	private JTextField getSurname;
	private static String man_comp_afm;


	public ManagerWindow(String username) {  //Parathiro gia to menu twn Manager
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 744, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("EDIT Company");
		lblNewLabel.setFont(new Font("Book Antiqua", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 0, 154, 75);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("CLICK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Koumpi gia na kanei edit tin etaireia stin opoia einai manager
				Company c1 = new Company(username); //Dhmiourgia kai khsh ths klashs company pou einai ypeythini gia to edit ths etaireias
				c1.setVisible(true);
			}
		});
		btnNewButton.setBounds(184, 28, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblInformation = new JLabel("Your Information");
		lblInformation.setFont(new Font("Book Antiqua", Font.PLAIN, 17));
		lblInformation.setBounds(10, 70, 154, 75);
		contentPane.add(lblInformation);
		
		JLabel lblSeeEvaluationResults = new JLabel("See evaluation results");
		lblSeeEvaluationResults.setFont(new Font("Book Antiqua", Font.PLAIN, 17));
		lblSeeEvaluationResults.setBounds(10, 140, 209, 75);
		contentPane.add(lblSeeEvaluationResults);
		
		JButton btnNewButton_1 = new JButton("CLICK");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Koumpi gia emfanish twn plhroforiwn tou manager
				ManagerInformation m1 = new ManagerInformation(username); //Dhmiourgia kai klhsh tou parathirou pou einai ypeuthino gia tin emfanish twn plhroforiwn tou manager
				m1.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(184, 96, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("CLICK");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Koumpi gia emfanish twn aksiologisewn pou exoun oloklrw8ei gia tis theseis tis etairias tou 
				EvaluationResult er = new EvaluationResult(username); //Dhimourgia kai klhsh tou parathirou pou einai upeuthino gia thn emfanish twn aksiologisewn 
				er.setVisible(true);
				
			}
		});
		btnNewButton_2.setBounds(184, 166, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblSeeEvaluatorsAverage = new JLabel("See evaluators average grading");
		lblSeeEvaluatorsAverage.setFont(new Font("Book Antiqua", Font.PLAIN, 17));
		lblSeeEvaluatorsAverage.setBounds(321, 0, 263, 75);
		contentPane.add(lblSeeEvaluatorsAverage);
		
		JButton btnNewButton_3 = new JButton("CLICK");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Koumpi gia emfanisi m.o alla den m doulevei swsta opote to vazw se comments kai auto kai tin klash AvgEvGrade
			//	AvgEvGrade ev1 = new AvgEvGrade(String username);
			//	ev1.setVisible(true);
			}
		});
		btnNewButton_3.setBounds(594, 28, 89, 23);
		contentPane.add(btnNewButton_3);
		
		JLabel lblEmployeesInformation = new JLabel("Employees Information");
		lblEmployeesInformation.setFont(new Font("Book Antiqua", Font.PLAIN, 17));
		lblEmployeesInformation.setBounds(321, 70, 232, 75);
		contentPane.add(lblEmployeesInformation);
		
		JButton btnNewButton_1_1 = new JButton("CLICK");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Koumpi gia na emfanizei tous employee tis etairias pou einai manager autos p ekane log in 
				CompanyEmployee ce1 = new CompanyEmployee(username); //Dhimourgia kai klhsh parathirou pou einai ypeuthino gia tin emfanisi twn employee ths etairias tou manager p ekane log in 
				ce1.setVisible(true);
			}
		});
		btnNewButton_1_1.setBounds(594, 98, 89, 23);
		contentPane.add(btnNewButton_1_1);
		
		getName = new JTextField();
		getName.setBounds(45, 320, 86, 20);
		contentPane.add(getName);
		getName.setColumns(10);
		
		getSurname = new JTextField();
		getSurname.setBounds(45, 367, 86, 20);
		contentPane.add(getSurname);
		getSurname.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("Find ");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Koumpi gia euresi twn aksiologisewn  enos employee ths etairias pou einai manager autos pou ekane log in ( pairnontas 3 orismata,to username tou manager,  to name kai to surname tou employee pou thelw na psaksw mesw twn textfield getName kai getSurname)
				
				FindEmp fe1 = new FindEmp(username,getName.getText(),getSurname.getText()); //Dhmiourgia kai klhsh parathirou pou einai upeuthino gia tin euresi tou employee
				fe1.setVisible(true);
			}
			
		});
		btnNewButton_4.setBounds(173, 338, 123, 23);
		contentPane.add(btnNewButton_4);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(72, 302, 90, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Surname");
		lblNewLabel_2.setBounds(60, 351, 98, 14);
		contentPane.add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(0, 242, 728, 175);
		contentPane.add(panel);
		
		JLabel lblNewLabel_3 = new JLabel("Enter Name,Surname of Employee you want to see his/her evaluation requests");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblNewLabel_3);
		
		JLabel lblSeeeditJobsInformation = new JLabel("See/Edit Company's Jobs");
		lblSeeeditJobsInformation.setFont(new Font("Book Antiqua", Font.PLAIN, 17));
		lblSeeeditJobsInformation.setBounds(321, 140, 232, 75);
		contentPane.add(lblSeeeditJobsInformation);
		
		JButton btnNewButton_1_1_1 = new JButton("CLICK");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Koumpi pou emfanizei ta jobs tis etairias stin opoia einai manager autos p ekane log in 
				CompanyJobs c1 = new CompanyJobs(username); //dhimourgia kai klhsh parathirou pou emfanizei ta jobs tis etairias stin opoia einai manager autos p ekane log in 
				c1.setVisible(true);
			
			
			}
		
		});
		
		btnNewButton_1_1_1.setBounds(594, 168, 89, 23);
		contentPane.add(btnNewButton_1_1_1);
	}
}

