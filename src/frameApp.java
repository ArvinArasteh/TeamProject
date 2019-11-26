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
import java.io.PrintWriter;
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
		frame.setBounds(100, 100, 866, 524);
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
				
				JTextArea textAreaErrors = new JTextArea();
				textAreaErrors.setBounds(10, 432, 869, 66);
				frame.getContentPane().add(textAreaErrors);
				
					
												
				JFileChooser chooser = new JFileChooser();   //choosing patch and opening dialog
				chooser.showOpenDialog(null);
				File f  = chooser.getSelectedFile();
				
				String filename = f.getAbsolutePath();
				String file_name = f.getName();	
				
				txtpnFileName.setText(file_name);
				
				
				String error = "error.txt";
				File error_file = new File(error);
				
				String fileName_to_save= "output.txt"; //new file name
				File file_to_save = new File(fileName_to_save); // creates a File to save later
				
				
						
				try
				{
					FileReader reader = new FileReader(filename);
					BufferedReader br = new BufferedReader(reader);
					String line = br.readLine();
					
					Writer error_output = new BufferedWriter(new FileWriter(error_file, false));
					FileReader error_reader = new FileReader(error_file);
					BufferedReader error_buffer = new BufferedReader(error_reader);
					
					
					FileWriter output = new FileWriter(file_to_save, true); //create a file to save
					PrintWriter output_print = new PrintWriter(output);
					
					//output_print.println(line); //addes the line to the new file
					FileReader reader_file = new FileReader(file_to_save);
					BufferedReader file_buffer = new BufferedReader(reader_file);
					
					char flag = 'l';
					while(line != null) {
						if(line.charAt(0) == '-') {
							flag = line.charAt(1);
						} else {
							switch(flag) {
							//left alignment
							case 'l':
								while(line.length() != 0){
								    if(line.length() > 80){
								        output_print.println(line.substring(0, 80));
								        line = line.substring(80, line.length());
								    }else{
								        output_print.println(line);
								        break;
								    }
								}
								break;
							//center alignment
							case 'c':
								while(line.length() != 0){
								    if(line.length() > 80){
								        output_print.println(line.substring(0, 80));
								        line = line.substring(80, line.length());
								    }else{
								    	int num = (80 - line.length()) / 2;
								        output_print.println(blankString(num) + line);
								        break;
								    }
								}
								break;
							//right alignment
							case 'r':
								while(line.length() != 0){
								    if(line.length() > 80){
								        output_print.println(line.substring(0, 80));
								        line = line.substring(80, line.length());
								    }else{
								    	int num = 80 - line.length();
								        output_print.println(blankString(num) + line);
								        line = "";
								    }
								}
								break;
							//makes it a title, centered, with no justification
							case 't':
								while(line.length() != 0){
								    if(line.length() > 80){
								        output_print.println(line.substring(0, 80));
								        line = line.substring(80, line.length());
								    }else{
								    	int num = (80 - line.length()) / 2;
								        output_print.println(blankString(num) + line);
								        break;
								    }
								}
								break;
							//double spaces lines
							case 'd':
								while(line.length() != 0){
								    if(line.length() > 80){
								        output_print.println(line.substring(0, 80));
								        output_print.println("");
								        line = line.substring(80, line.length());
								    }else{
								        output_print.println(line);
								        output_print.println("");
								        break;
								    }
								}
								break;
							//single spaces lines
							case 's':
								while(line.length() != 0){
								    if(line.length() > 80){
								        output_print.println(line.substring(0, 80));
								        line = line.substring(80, line.length());
								    }else{
								        output_print.println(line);
								        break;
								    }
								}
								break;
							//indents the first line by 5 spaces
							case 'i':
								int count = 0;
								while(line.length() != 0){
									if(count == 0) {
										if(line.length() > 75){
									        output_print.println(blankString(5) + line.substring(0, 75));
									        line = line.substring(75, line.length());
									    }else{
									        output_print.println(blankString(5) + line);
									        break;
									    }
									}else {
										if(line.length() > 80){
									        output_print.println(line.substring(0, 80));
									        line = line.substring(80, line.length());
									    }else{
									        output_print.println(line);
									        break;
									    }
									}
								}
								break;
							//indents all the lines 10 spaces
							case 'b':
								if(line.length() > 70){
							        output_print.println(blankString(10) + line.substring(0, 70));
							        line = line.substring(70, line.length());
							    }else{
							        output_print.println(blankString(10) + line);
							        break;
							    }
								break;
							//removes indentation
							case 'n':
								while(line.length() != 0){
								    if(line.length() > 80){
								        output_print.println(line.substring(0, 80));
								        line = line.substring(80, line.length());
								    }else{
								        output_print.println(line);
								        break;
								    }
								}
								break;
							//single column formatting
							case '1':
								while(line.length() != 0){
								    if(line.length() > 80){
								        output_print.println(line.substring(0, 80));
								        line = line.substring(80, line.length());
								    }else{
								        output_print.println(line);
								        break;
								    }
								}
								break;
							//double column formatting
							case '2':
								//divides the line into an array of length 35 strings
								int size = (int) Math.ceil(line.length() / 35);
								String[] arr = new String[size];
								String subLine = line;
								for(int i = 0; i < arr.length; i++) {
									if(subLine.length() > 35) {
										arr[i] = subLine.substring(0, 35);
										subLine = line.substring(35, subLine.length());
									}else {
										arr[i] = subLine;
									}
								}
								
								//prints all of the arrays in two columns, so that both columns can be read
								for(int i = 0; i < (int) Math.floor(arr.length) / 2; i++){
									output_print.println(arr[i] + blankString(10) + arr[(int) (i + Math.ceil(arr.length / 2))]);
								}
								
								//if the array is odd, then it prints the final array that didn't get printed
								if(arr.length % 2 == 1) {
									output_print.println(arr[(int) Math.floor(arr.length/2)]);
								}
								break;
							//blank line
							case 'e':
								output_print.println("");
								break;
							default:
								error_output.write("While Processing Your File The Following Errors Happened: ");
								error_output.write("Invalid Flag Error");
								textAreaErrors.write(error_output);			
							}
						}
						line = br.readLine();					
					}

					
					jTextArea1.read(file_buffer, null);
					textAreaErrors.read(error_buffer, null);
					//jTextArea1.write(output);
					output_print.close();
					br.close();
					//output.close();
					error_output.close();
					jTextArea1.requestFocus();
					textAreaErrors.requestFocus();
										
				}
				
				catch (Exception e){		
					//textAreaErrors.write();
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
		
		JLabel label = new JLabel("Error Log");
		label.setFont(new Font("Ink Free", Font.BOLD, 17));
		label.setBounds(10, 403, 89, 18);
		frame.getContentPane().add(label);
		
		JLabel lblSaved = new JLabel("Saved");
		lblSaved.setFont(new Font("Ink Free", Font.BOLD, 18));
		lblSaved.setBounds(155, 41, 67, 26);
		frame.getContentPane().add(lblSaved);
		
	}
	
	public String blankString(int num) {
		String newString = "";
		for(int i = 0; i < num; i++) {
			newString += " ";
		}
		return newString;
	}
}
