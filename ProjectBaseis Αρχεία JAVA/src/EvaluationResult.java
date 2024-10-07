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
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EvaluationResult extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JButton btnNewButton;

	
	public EvaluationResult(String username) { //Parathiro pou emfanizei ths oloklhromenes aksilogiseis gia tis theseis pou einai manager autos p ekane log in ( pairnw to username tou mesw orismatos)
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1092, 291);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		try {
			Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!");
			String query1 = "Select * from evaluationresult WHERE total_grade IS NOT NULL"; //query1 pou epilegei tis teleiwmenes aksilogiseis ( to katafernw epilegontas autes p to total_grade den einai 0 ,kathws an einai diaforo tou 0 simainei oti einai oloklhrwmenh kai uparxei va8mos)
			PreparedStatement myStmt = con.prepareStatement(query1);
			ResultSet rS = myStmt.executeQuery();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 27, 1076, 176);
		contentPane.add(scrollPane);
		
		table = new JTable(); //Pinakas gia apothikeusi apotelesmatwn tou query1
		scrollPane.setViewportView(table);
		table.setModel(DbUtils.resultSetToTableModel(rS)); //Apothikeusi apotelesmatwn tou query1 ston pinaka
		
		lblNewLabel = new JLabel("Completed Evaluations");
		lblNewLabel.setFont(new Font("Book Antiqua", Font.PLAIN, 17));
		lblNewLabel.setBounds(418, 11, 235, 14);
		contentPane.add(lblNewLabel);
		
		btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Koumpi gia kleisimo parathirou 
				dispose();
			}
		});
		btnNewButton.setBounds(941, 214, 106, 27);
		contentPane.add(btnNewButton);
		}
		catch(Exception e) {
			Error er1 = new Error();
			er1.setVisible(true);
		}
	}
}
