
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

public class EmployeeWindow extends JFrame {

	private JPanel contentPane;


	public EmployeeWindow(String username) { //Parathiro gia to menu twn employee ( h klash auth pairnei ws orisma to username me to opoio kaname log in ) 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 425, 238);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("Click");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Koumpi gia na epeksergazetai ta job request pou exei kanei h kai na prosthetei kainourgia  
				
				
				AvJobs av = new AvJobs(username); //Dhimourgia kai klhsh parathirou pou sxetizetai me ta job request tou employee pou kaname log in 
				av.setVisible(true);
				
			}
		});
		btnNewButton_2.setBounds(275, 106, 89, 30);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Click");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Koumpi gia na emfanizei tis plhrofories tou employee
				Employee_Information ei = new Employee_Information(username); //Dhmiourgia kai klhsh parathirou pou emfanizei tis plhrofories tou employee
				ei.setVisible(true);
				
				
				
			}
		});
		btnNewButton_3.setBounds(275, 50, 89, 30);
		contentPane.add(btnNewButton_3);
		
		JLabel Employee_Details = new JLabel("Information");
		Employee_Details.setFont(new Font("Book Antiqua", Font.PLAIN, 17));
		Employee_Details.setBounds(10, 54, 103, 19);
		contentPane.add(Employee_Details);
		
		JLabel lblNewLabel_1 = new JLabel("View/Insert/Delete Job Request");
		lblNewLabel_1.setFont(new Font("Book Antiqua", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(10, 106, 264, 26);
		contentPane.add(lblNewLabel_1);
	}
}
