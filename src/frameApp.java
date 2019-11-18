import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class frameApp {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frameApp window = new frameApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frameApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 905, 548);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.setForeground(Color.BLACK);
		btnUpload.setFont(new Font("Ink Free", Font.BOLD, 15));
		btnUpload.setBounds(10, 11, 89, 23);
		frame.getContentPane().add(btnUpload);
		
		JButton btnNewButton = new JButton("Save As");
		btnNewButton.setFont(new Font("Ink Free", Font.BOLD, 14));
		btnNewButton.setBounds(10, 45, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblPreview = new JLabel("Preview");
		lblPreview.setFont(new Font("Ink Free", Font.BOLD, 17));
		lblPreview.setBounds(10, 84, 79, 14);
		frame.getContentPane().add(lblPreview);
		
		JTextPane txtpnFileName = new JTextPane();
		txtpnFileName.setText("Dr_Calliss_Syllabus_Example");
		txtpnFileName.setBounds(128, 11, 150, 23);
		frame.getContentPane().add(txtpnFileName);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("File Name");
		textPane.setBounds(128, 45, 94, 23);
		frame.getContentPane().add(textPane);
		
		JLabel label = new JLabel("Error Log");
		label.setFont(new Font("Ink Free", Font.BOLD, 17));
		label.setBounds(10, 403, 89, 18);
		frame.getContentPane().add(label);
		
		JTextPane txtpnWhileProcessingYour = new JTextPane();
		txtpnWhileProcessingYour.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnWhileProcessingYour.setText("While processing your file the following errors happened:\r\nInvalidFlag Error: In line 67 / there was an invalid flag\r\n");
		txtpnWhileProcessingYour.setBounds(10, 432, 869, 66);
		frame.getContentPane().add(txtpnWhileProcessingYour);
		
		JTextArea txtrSd = new JTextArea();
		txtrSd.setText("                                                 This is an Example\r\n     Hello we are team number 9 and this is how we are going to do this project.\r\n                                      Oh did you just use -c command? Interesting");
		txtrSd.setBounds(10, 109, 869, 283);
		frame.getContentPane().add(txtrSd);
	}
}
