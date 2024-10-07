import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CompanyEmployee extends JFrame {

	private JPanel contentPane;
	private static String man_comp_afm;
	private JTable table;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JTextField newCertificates;
	private JTextField newSistatikes;
	private JTextField newAwards;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JLabel asda;
	private JLabel ad;
	private JLabel newAwardsdad;
	private JTextField em_user;
	private JLabel lblNewLabel_2;

	public CompanyEmployee(String username) { //Klash gia na emfanizei tous employee mias etairias 
		
		try {
			Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!");
			String queryManAfm ="SELECT company.afm FROM company INNER JOIN manager ON company.afm = manager.company_afm WHERE manager.man_username = '" + username + "'"; //Pairnw to afm ths etairias stin opoia einai manager autos p ekane log in 
			PreparedStatement getAFM = con.prepareStatement(queryManAfm);
			ResultSet rs = getAFM.executeQuery();
			while(rs.next()) {
				man_comp_afm = rs.getString("afm"); //Apothikeusi afm etairias stin public metavliti man_comp_afm
			}
			String query1 = "select * from employee WHERE c_afm = '" + man_comp_afm + "'"; //query1 gia epilogh twn employee tis etairias me to afm ths etairias p einai manager autos p ekane log in 
			PreparedStatement myStmt = con.prepareStatement(query1);
			ResultSet rS = myStmt.executeQuery();
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1331, 723);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1331, 376);
		contentPane.add(scrollPane);
		
		table = new JTable(); //pinakas gia emfanish twn employee
		scrollPane.setViewportView(table);
		table.setModel(DbUtils.resultSetToTableModel(rS)); //emfanish twn employee ston pinaka 
		
		panel = new JPanel();
		panel.setBounds(0, 376, 485, 297);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("Update employee information");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(118, 11, 229, 39);
		panel.add(lblNewLabel);
		
		newCertificates = new JTextField(); //textField gia ta nea Certificate, ean theloume na kanoume update se enan employee
		newCertificates.setBounds(10, 207, 321, 20);
		panel.add(newCertificates);
		newCertificates.setColumns(10);
		
		newSistatikes = new JTextField();  //textField gia tis nees sistatikes, ean theloume na kanoume update se enan employee
		newSistatikes.setBounds(10, 142, 321, 20);
		panel.add(newSistatikes);
		newSistatikes.setColumns(10);
		
		newAwards = new JTextField();  //textField gia ta nea Awards, ean theloume na kanoume update se enan employee
		newAwards.setBounds(10, 260, 321, 20);
		panel.add(newAwards);
		newAwards.setColumns(10);
		
		btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Koumpi gia na kanoume update tis sistatikes , tis nees sistatikes tis pairnw apo to newSistatikes textField kai EPILEGW SE POION EMPLOYEE THA KANW TA UPDATE GRAFONTAS TO USERNAME TOU STO textField em_user
				
				try {
					Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!");
					String query1 = "UPDATE employee SET employee.sistatikes = '" + newSistatikes.getText() + "' WHERE employee.c_afm ='" + man_comp_afm + "' AND employee.e_username = '" + em_user.getText() + "'";
					PreparedStatement myStmt = con.prepareStatement(query1);
				    myStmt.executeUpdate();
				    dispose(); 
				    
				    LogOut lo = new LogOut();
				    lo.setVisible(true);
				}
				catch(Exception exc) {
					Error er1 = new Error();
					er1.setVisible(true);
				}
			}
		});
		btnNewButton.setBounds(376, 141, 89, 23);
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Koumpi gia na kanoume update ta certificate,paromoio me to koumpi gia tis sistatikes
				try {
					Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!");
					String query1 = "UPDATE employee SET employee.certificates = '" + newCertificates.getText() + "' WHERE employee.c_afm ='" + man_comp_afm + "' AND employee.e_username = '" + em_user.getText() + "'";
					PreparedStatement myStmt = con.prepareStatement(query1);
				    myStmt.executeUpdate();
				    dispose();
				    
				    LogOut lo = new LogOut();
				    lo.setVisible(true);
				}
				catch(Exception exc) {
					Error er1 = new Error();
					er1.setVisible(true);
				}
			}
		});
		btnNewButton_1.setBounds(376, 206, 89, 23);
		panel.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Update");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Koumpi gia na kanw update ta awards enos employee,paromoio me to koumpi gia tis sistatikes
				try {
					Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!");
					String query1 = "UPDATE employee SET employee.awards = '" + newAwards.getText() + "' WHERE employee.c_afm ='" + man_comp_afm + "' AND employee.e_username = '" + em_user.getText() + "'";
					PreparedStatement myStmt = con.prepareStatement(query1);
				    myStmt.executeUpdate();
				    dispose();
				    
				    LogOut lo = new LogOut();
				    lo.setVisible(true);
				}
				catch(Exception exc) {
					Error er1 = new Error();
					er1.setVisible(true);
				}
			}
		});
		btnNewButton_2.setBounds(376, 259, 89, 23);
		panel.add(btnNewButton_2);
		
		asda = new JLabel("Sistatikes");
		asda.setFont(new Font("Tahoma", Font.PLAIN, 13));
		asda.setBounds(152, 108, 75, 23);
		panel.add(asda);
		
		ad = new JLabel("Certificates");
		ad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ad.setBounds(150, 173, 77, 23);
		panel.add(ad);
		
		newAwardsdad = new JLabel("Awards");
		newAwardsdad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		newAwardsdad.setBounds(160, 228, 75, 20);
		panel.add(newAwardsdad);
		
		em_user = new JTextField(); //textField gia eisodo tou username tou employee pou thelw na kanw update ta stoixeia tou 
		em_user.setBounds(93, 77, 181, 20);
		panel.add(em_user);
		em_user.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Employee Username");
		lblNewLabel_2.setBounds(140, 46, 157, 29);
		panel.add(lblNewLabel_2);
		}
		catch(Exception exc) {
			Error er1 = new Error();
			er1.setVisible(true);
		}
		}

}
