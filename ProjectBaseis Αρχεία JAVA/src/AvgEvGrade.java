/*import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AvgEvGrade extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public AvgEvGrade(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1030, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		try {
			Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Omgkai3lol!");
			String queryManAfm ="SELECT company.afm FROM company INNER JOIN manager ON company.afm = manager.company_afm WHERE manager.man_username = '" + username + "'";
			PreparedStatement getAFM = con.prepareStatement(queryManAfm);
			ResultSet rs = getAFM.executeQuery();
			while(rs.next()) {
				man_comp_afm = rs.getString("afm");
			}
			CallableStatement statement = con.prepareCall("{call AithshYpallhlou1(?,?,?)}");
			statement.setString(1, name);
			statement.setString(2, surname);
			statement.setString(3,man_comp_afm);
			ResultSet rS = statement.executeQuery();
			
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 44, 1014, 352);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column"
			}
		));
		scrollPane.setViewportView(table);
		table.setModel(DbUtils.resultSetToTableModel(rS));
		}
		catch (Exception ex) 
		{
			
		}
		JLabel lblNewLabel = new JLabel("Evaluators and their average evaluation grade");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(318, 11, 499, 37);
		contentPane.add(lblNewLabel);
	}
}
*/
