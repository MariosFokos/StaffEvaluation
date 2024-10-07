import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Company extends JFrame {

	private JPanel contentPane;
	private final JScrollPane scrollPane = new JScrollPane();
	private JTable table;
	private JTextField newPhone;
	private JTextField newStreet;
	private JTextField newStNumber;
	private JTextField newCity;
	private JTextField newCountry;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton newCityb;
	private JButton btnNewButton_4;
	private JPanel panel;
	private JLabel lblNewLabel;
	static String man_comp_afm;
	private JButton btnNewButton_3;

	public Company(String username) {  //Se kathe klash pairnw panta to username me to opoio ekana log in giati to xreiazomai pantou
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1001, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		scrollPane.setBounds(0, 0, 1001, 67);
		contentPane.add(scrollPane);
		try {
			Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!"); //Syndesh se vash opws panta stin arxi
			String query1 = "Select afm,doy,name,phone,street,numbr,city,country,group_name FROM company INNER JOIN manager ON company.afm = manager.company_afm WHERE man_username ='" + username + "'"; //query1 gia emfanish twn plhroforiwn ths etairias stin opoia einai manager autos pou ekane log in 
			PreparedStatement myStmt = con.prepareStatement(query1);
			ResultSet rs = myStmt.executeQuery();
		table = new JTable(); //Dhmiourgia pinaka gia apothikeusi apotelesmatwn tou query1
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column"
			}
		));
		scrollPane.setViewportView(table);
		table.setModel(DbUtils.resultSetToTableModel(rs)); //Apothikeusi twn apotelesmatwn tou query1 ston pinaka pou ftiaksame parapanw 
		
		newPhone = new JTextField(); //textField gia to newPhone ean thelw na allaksw to thlefwno ths etairias
		newPhone.setBounds(10, 221, 86, 20);
		contentPane.add(newPhone);
		newPhone.setColumns(10);
		
		newStreet = new JTextField(); //textField gia to newStreet ean thelw na allaksw thn odo ths etairias
		newStreet.setBounds(10, 281, 86, 20);
		contentPane.add(newStreet);
		newStreet.setColumns(10);
		
		newStNumber = new JTextField(); //textField gia to newStNumber ean thelw na allaksw ton arithmo ths odou ths etairias
		newStNumber.setBounds(10, 341, 86, 20);
		contentPane.add(newStNumber);
		newStNumber.setColumns(10);
		
		newCity = new JTextField();  //textField gia to newCity ean thelw na allaksw thn polh ths etairias
		newCity.setColumns(10);
		newCity.setBounds(319, 221, 86, 20);
		contentPane.add(newCity);
		
		newCountry = new JTextField();  //textField gia to newCountry ean thelw na allaksw thn xwra ths etairias
		newCountry.setColumns(10);
		newCountry.setBounds(319, 281, 86, 20);
		contentPane.add(newCountry);
		
		btnNewButton = new JButton("Update Phone");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Koumpi pou kanei update to thlefwno ths etairias,opou to neo thlefwno to pairnei apo to textField newPhone kai allazei to thlefwno ths etairias tou manager pou kaname log in 
				try {
					
					Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!");
					String queryManAfm ="SELECT company.afm FROM company INNER JOIN manager ON company.afm = manager.company_afm WHERE manager.man_username = '" + username + "'"; //Pairnw to afm ths etairias pou einai manager autos p ekane log in 
					PreparedStatement getAFM = con.prepareStatement(queryManAfm);
					ResultSet rs = getAFM.executeQuery();
					while(rs.next()) {
				    man_comp_afm = rs.getString("afm"); //Apothikeusi tou afm ths etairias stin public metavlhth man_comp_afm
					}
					String query1 = "UPDATE company SET company.phone = " + newPhone.getText() + " WHERE company.afm ='" + man_comp_afm + "'"; //query1 gia na kanei update to thlefwno ths etairias stin opoia einai manager autos p ekane log in 
					PreparedStatement myStmt = con.prepareStatement(query1);
					
				    myStmt.executeUpdate();
				    dispose();
				    
				  LogOut lo = new LogOut(); //Afou kanw tin allagh vgazei to logout parathiro 
				    lo.setVisible(true);
					}
					catch(Exception exc13) {
						Error er1 = new Error(); //Exception handling me parathiro error
						er1.setVisible(true);
					}
				
			}
		});
		btnNewButton.setBounds(125, 220, 129, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Update Street");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //paromoia logikh me to update tou thlefwnou ths etairias mono pou edw allazw tin odo
try {
					
					Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!");
					String queryManAfm ="SELECT company.afm FROM company INNER JOIN manager ON company.afm = manager.company_afm WHERE manager.man_username = '" + username + "'";
					PreparedStatement getAFM = con.prepareStatement(queryManAfm);
					ResultSet rs = getAFM.executeQuery();
					while(rs.next()) {
				    man_comp_afm = rs.getString("afm");
					System.out.println(man_comp_afm);
					}
					String query1 = "UPDATE company SET company.street = '" + newStreet.getText() + "' WHERE company.afm ='" + man_comp_afm + "'";
					PreparedStatement myStmt = con.prepareStatement(query1);
					
				    myStmt.executeUpdate();
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
		btnNewButton_1.setBounds(125, 280, 129, 23);
		contentPane.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Update ST.Number");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  //paromoia logikh me to update tou thlefwnou ths etairias mono pou edw allazw ton arithmo ths odou
				try {
					
					Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!");
					String queryManAfm ="SELECT company.afm FROM company INNER JOIN manager ON company.afm = manager.company_afm WHERE manager.man_username = '" + username + "'";
					PreparedStatement getAFM = con.prepareStatement(queryManAfm);
					ResultSet rs = getAFM.executeQuery();
					while(rs.next()) {
				    man_comp_afm = rs.getString("afm");
					System.out.println(man_comp_afm);
					}
					String query1 = "UPDATE company SET company.numbr = " + newStNumber.getText() + " WHERE company.afm ='" + man_comp_afm + "'";
					PreparedStatement myStmt = con.prepareStatement(query1);
					
				    myStmt.executeUpdate();
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
		btnNewButton_2.setBounds(125, 340, 129, 23);
		contentPane.add(btnNewButton_2);
		
		newCityb = new JButton("Update City");
		newCityb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  //paromoia logikh me to update tou thlefwnou ths etairias mono pou edw allazw tin polh
				try {
					
					Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!");
					String queryManAfm ="SELECT company.afm FROM company INNER JOIN manager ON company.afm = manager.company_afm WHERE manager.man_username = '" + username + "'";
					PreparedStatement getAFM = con.prepareStatement(queryManAfm);
					ResultSet rs = getAFM.executeQuery();
					while(rs.next()) {
				    man_comp_afm = rs.getString("afm");
					System.out.println(man_comp_afm);
					}
					String query1 = "UPDATE company SET company.city = '" + newCity.getText() + "' WHERE company.afm ='" + man_comp_afm + "'";
					PreparedStatement myStmt = con.prepareStatement(query1);
					
				    myStmt.executeUpdate();
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
		newCityb.setBounds(440, 220, 129, 23);
		contentPane.add(newCityb);
		
		btnNewButton_4 = new JButton("Update Country");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  //paromoia logikh me to update tou thlefwnou ths etairias mono pou edw allazw tin xwra
				try {
					
					Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!");
					String queryManAfm ="SELECT company.afm FROM company INNER JOIN manager ON company.afm = manager.company_afm WHERE manager.man_username = '" + username + "'";
					PreparedStatement getAFM = con.prepareStatement(queryManAfm);
					ResultSet rs = getAFM.executeQuery();
					while(rs.next()) {
				    man_comp_afm = rs.getString("afm");
					System.out.println(man_comp_afm);
					}
					String query1 = "UPDATE company SET company.country = '" + newCountry.getText() + "' WHERE company.afm ='" + man_comp_afm + "'";
					PreparedStatement myStmt = con.prepareStatement(query1);
					
				    myStmt.executeUpdate();
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
		btnNewButton_4.setBounds(440, 280, 129, 23);
		contentPane.add(btnNewButton_4);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 67, 1001, 320);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("Update Company's Information");
		lblNewLabel.setBounds(315, 6, 276, 25);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblNewLabel);
		
		btnNewButton_3 = new JButton("CLOSE");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_3.setBounds(816, 218, 115, 56);
		panel.add(btnNewButton_3);
		}
		catch(Exception Ex1) {
			Error er1 = new Error();
			er1.setVisible(true);
		}
	

}
}
