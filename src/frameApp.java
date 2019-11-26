import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.awt.event.ActionEvent;

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
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
						
				JTextPane txtpnFileName = new JTextPane();
				txtpnFileName.setBounds(128, 11, 150, 23);
				frame.getContentPane().add(txtpnFileName);
								
				JTextArea jTextArea1 = new JTextArea();
				jTextArea1.setBounds(10, 109, 869, 285);
				frame.getContentPane().add(jTextArea1);
												
				JFileChooser chooser = new JFileChooser();   //choosing patch and opening dialog
				chooser.showOpenDialog(null);
				File f  = chooser.getSelectedFile();
				
				String filename = f.getAbsolutePath();
				String file_name = f.getName();	
				
				txtpnFileName.setText(file_name);
				
				String fileName_to_save= "newFile.txt";
				
				File file_to_save = new File(fileName_to_save); // creates a File to save later
				
						
				try
				{
					FileReader reader = new FileReader(filename);
					BufferedReader br = new BufferedReader(reader);
					String line = br.readLine();
					
					Writer output = new BufferedWriter(new FileWriter(file_to_save, true)); //create a file to save
					output.append(line); //addes the line to the new file
					
					
					while(line != null) {
						while(line.length() != 0){
						    if(line.length() > 80){
						        System.out.println(line.substring(0, 80));
						        line = line.substring(80, line.length());
						    }else{
						        System.out.println(line);
						        line = "";
						    }
						}

						line = br.readLine();
					}
							
					
					jTextArea1.read(br, null);
					br.close();
					output.close();
					jTextArea1.requestFocus();
										
				}
				
				catch (Exception e){
					System.err.println("Something Went Wrong... Try Again!");
					JOptionPane.showMessageDialog(null, e);
				}				
				
			}
		
		});
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
		
		JTextPane txtpnSaveFileName = new JTextPane();
		txtpnSaveFileName.setText("Dr_Calliss_New_Syllabus_Example");
		txtpnSaveFileName.setBounds(128, 45, 180, 23);
		frame.getContentPane().add(txtpnSaveFileName);
		
		JLabel label = new JLabel("Error Log");
		label.setFont(new Font("Ink Free", Font.BOLD, 17));
		label.setBounds(10, 403, 89, 18);
		frame.getContentPane().add(label);
		
		JTextPane txtpnWhileProcessingYour = new JTextPane();
		txtpnWhileProcessingYour.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnWhileProcessingYour.setBounds(10, 432, 869, 66);
		frame.getContentPane().add(txtpnWhileProcessingYour);
		
		JLabel lblSaved = new JLabel("Saved");
		lblSaved.setFont(new Font("Ink Free", Font.BOLD, 18));
		lblSaved.setBounds(318, 42, 67, 26);
		frame.getContentPane().add(lblSaved);
		
		
	}
}
