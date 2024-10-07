import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EvaluatorWindow extends JFrame {

	private JPanel contentPane;

	
	public EvaluatorWindow(String username) { //Parathiro gia to menu twn evaluator ( h klash pairnei ws orisma to username pou kaname log in ) 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1136, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Your information");
		lblNewLabel.setFont(new Font("Book Antiqua", Font.PLAIN, 17));
		lblNewLabel.setBounds(10, 52, 154, 38);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Click");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Koumpi gia emfanish plhroforiwn tou evaluator
				Evaluator_Information ei1 = new Evaluator_Information(username); //Dhimourgia kai klhsh tou parathirou pou einai upeuthino gia tin emfanish twn plhroforiwn tou evaluator
				ei1.setVisible(true);
			}
		});
		btnNewButton.setBounds(174, 56, 88, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblSeeeditJobs = new JLabel("See/Edit Jobs");
		lblSeeeditJobs.setFont(new Font("Book Antiqua", Font.PLAIN, 17));
		lblSeeeditJobs.setBounds(10, 163, 154, 38);
		contentPane.add(lblSeeeditJobs);
		
		JButton btnNewButton_1 = new JButton("Click");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //Koumpi gia emfanish twn thesewn proagwgis ths etairias me thn opoia sunergazetai o evaluator kai epeksergasia twn thesewn proagwgis pou ekane o idios 
				SeeEvaluatorJobs se1 = new SeeEvaluatorJobs(username); //Dhmiourgia kai klhsh parathirou gia emfanish twn thesewn proagwgis ths etairias pou sunergazetai o evaluator kai epeksergasia twn thesewn proagwgis pou ekane o idios 
				se1.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(174, 167, 88, 29);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Click");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EvaluationCheck ec1 = new EvaluationCheck(username);
				ec1.setVisible(true);
			}
		});
		btnNewButton_1_1.setBounds(174, 265, 88, 29);
		contentPane.add(btnNewButton_1_1);
		
		JLabel lblEvaluationCheck = new JLabel("Evaluation Check");
		lblEvaluationCheck.setFont(new Font("Book Antiqua", Font.PLAIN, 17));
		lblEvaluationCheck.setBounds(10, 258, 154, 38);
		contentPane.add(lblEvaluationCheck);
	}
}
