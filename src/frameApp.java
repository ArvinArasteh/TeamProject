import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.print.DocFlavor.URL;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
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
import java.util.Scanner;
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
				// JScrollPane sp = new JScrollPane(jTextArea1); // Added for the Scroll but
				// didnt work
				jTextArea1.setBounds(10, 109, 869, 285);
				frame.getContentPane().add(jTextArea1);
				// frame.getContentPane().add(sp); // Added for the Scroll but didnt work

				JTextArea textAreaErrors = new JTextArea();
				textAreaErrors.setBounds(10, 432, 869, 66);
				frame.getContentPane().add(textAreaErrors);

				JFileChooser chooser = new JFileChooser(); // choosing patch and opening dialog
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();

				String filename = f.getAbsolutePath();
				String file_name = f.getName();

				txtpnFileName.setText(file_name);
				
				String error = "error.txt";
				File error_file = new File(error);

				String userHomeFolder = System.getProperty("user.home"); // The user's home directory
				
				String fileName_to_save = "output.txt"; // new file name
				File file_to_save = new File(userHomeFolder,fileName_to_save); // creates a File to save later
				

				try {
					FileReader reader = new FileReader(filename);
					BufferedReader br = new BufferedReader(reader);
					String line = br.readLine();

					/*
					Writer error_output = new BufferedWriter(new FileWriter(error_file, false));
					FileReader error_reader = new FileReader(error_file);
					BufferedReader error_buffer = new BufferedReader(error_reader);
					*/
					FileWriter error_output = new FileWriter(error_file, false); // create a file for error
					PrintWriter error_print = new PrintWriter(error_output);

					FileWriter output = new FileWriter(file_to_save, false); // create a file to save
					PrintWriter output_print = new PrintWriter(output);

					// output_print.println(line); //addes the line to the new file
					//FileReader reader_file = new FileReader(file_to_save);
					//BufferedReader file_buffer = new BufferedReader(reader_file);

					/*
					 * FileReader reader_text = new FileReader("output.txt"); BufferedReader br_text
					 * = new BufferedReader(reader_text); jTextArea1.read(br_text, null);
					 * br_text.close(); jTextArea1.requestFocus();
					 * 
					 */

					char flag = 'l';
					while (line != null) {
						if (line.charAt(0) == '-') {
							flag = line.charAt(1);
						} else {
							switch (flag) {
							// left alignment
							case 'l':
								while (line.length() != 0) {
									if (line.length() > 80) {
										output_print.println(line.substring(0, 80));
										line = line.substring(80, line.length());
									} else {
										output_print.println(line);
										break;
									}
								}
								break;
							// center alignment
							case 'c':
								while (line.length() != 0) {
									if (line.length() > 80) {
										output_print.println(line.substring(0, 80));
										line = line.substring(80, line.length());
									} else {
										int num = (80 - line.length()) / 2;
										output_print.println(blankString(num) + line);
										break;
									}
								}
								break;
							// right alignment
							case 'r':
								while (line.length() != 0) {
									if (line.length() > 80) {
										output_print.println(line.substring(0, 80));
										line = line.substring(80, line.length());
									} else {
										int num = 80 - line.length();
										output_print.println(blankString(num) + line);
										break;
									}
								}
								break;
							// makes it a title, centered, with no justification
							case 't':
								while (line.length() != 0) {
									if (line.length() > 80) {
										output_print.println(line.substring(0, 80));
										line = line.substring(80, line.length());
									} else {
										int num = (80 - line.length()) / 2;
										output_print.println(blankString(num) + line);
										break;
									}
								}
								break;
							// double spaces lines
							case 'd':
								while (line.length() != 0) {
									if (line.length() > 80) {
										output_print.println(line.substring(0, 80) + "\n\n");
										line = line.substring(80, line.length());
									} else {
										output_print.println(line + "\n\n");
										break;
									}
								}
								break;
							// single spaces lines
							case 's':
								while (line.length() != 0) {
									if (line.length() > 80) {
										output_print.println(line.substring(0, 80) + "\n");
										line = line.substring(80, line.length());
									} else {
										output_print.println(line + "\n");
										break;
									}
								}
								break;
							// indents the first line by 5 spaces
							case 'i':
								int count = 0;
								while (line.length() != 0) {
									if (count == 0) {
										if (line.length() > 75) {
											output_print.println(blankString(5) + line.substring(0, 75));
											line = line.substring(75, line.length());
										} else {
											output_print.println(blankString(5) + line);
											break;
										}
									} else {
										if (line.length() > 80) {
											output_print.println(line.substring(0, 80));
											line = line.substring(80, line.length());
										} else {
											output_print.println(line);
											break;
										}
									}
								}
								break;
							// indents all the lines 10 spaces
							case 'b':
								while (line.length() != 0) {
									if (line.length() > 70) {
										output_print.println(blankString(10) + line.substring(0, 70));
										line = line.substring(70, line.length());
									} else {
										output_print.println(blankString(10) + line);
										break;
									}
								}
								break;
							// removes indentation
							case 'n':
								while (line.length() != 0) {
									if (line.length() > 80) {
										output_print.println(line.substring(0, 80));
										line = line.substring(80, line.length());
									} else {
										output_print.println(line);
										break;
									}
								}
								break;
							// single column formatting
							case '1':
								while (line.length() != 0) {
									if (line.length() > 80) {
										output_print.println(line.substring(0, 80));
										line = line.substring(80, line.length());
									} else {
										output_print.println(line);
										break;
									}
								}
								break;
							// double column formatting
							case '2':
								// divides the line into an array of length 35 strings
								int size = line.length() / 35 + 1;
								String[] arr = new String[size];
								String subLine = line;
								for (int i = 0; i < arr.length; i++) {
									if (subLine.length() > 35) {
										arr[i] = subLine.substring(0, 35);
										subLine = subLine.substring(35, subLine.length());
									} else {
										arr[i] = subLine;
									}
								}

								// prints all of the arrays in two columns, so that both columns can be read
								int addVal = arr.length / 2 + 1;
								String newString = "";
								for (int i = 0; i < arr.length / 2; i++) {
									newString += arr[i] + blankString(10) + arr[i + addVal] + "\n";
								}

								// if the array is odd, then it prints the final array that didn't get printed
								if (arr.length % 2 == 1) {
									newString += arr[arr.length / 2];
								}

								output_print.println(newString);
								break;
							// blank line
							case 'e':
								output_print.println("");
								break;
							default:
								error_print.println("While Processing Your File The Following Errors Happened: ");
								error_print.println("Invalid Flag Error");
								textAreaErrors.write(error_output);
							}
						}
						line = br.readLine();
					}

					//textAreaErrors.read(error_buffer, null);
					// jTextArea1.write(output);
					// jTextArea1.read(file_buffer, null);
					// file_buffer.close();
					// jTextArea1.requestFocus();
					br.close();
					output_print.close();
					error_output.close();
					output_print.flush();
					textAreaErrors.requestFocus();

					Scanner scan_output = new Scanner(new File(file_to_save.getAbsolutePath()));
					String preview = "";
					while (scan_output.hasNextLine()) {
						preview += scan_output.nextLine() + "\n";

					}
					jTextArea1.setText(preview);
					scan_output.close();
					
					Scanner scan_error = new Scanner(new File(error_file.getAbsolutePath()));
					String error_log = "";
					while (scan_error.hasNextLine()) {
						error_log += scan_error.nextLine() + "\n";

					}
					textAreaErrors.setText(error_log);
					scan_error.close();

				}
				

				catch (Exception e) {
					// textAreaErrors.write();
					System.err.println("Something Went Wrong... Try Again!");
					JOptionPane.showMessageDialog(null, e);
				}

			}

		});
		btnUpload.setForeground(Color.BLACK);
		btnUpload.setFont(new Font("Ink Free", Font.BOLD, 15));
		btnUpload.setBounds(10, 11, 89, 23);
		frame.getContentPane().add(btnUpload);

		JButton btnSave = new JButton("Save As");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, 
                        "The output.txt file is saved to your HOME directory", 
                        "SAVED", 
                        JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnSave.setFont(new Font("Ink Free", Font.BOLD, 14));
		btnSave.setBounds(10, 45, 89, 23);
		frame.getContentPane().add(btnSave);

		JLabel lblPreview = new JLabel("Preview");
		lblPreview.setFont(new Font("Ink Free", Font.BOLD, 17));
		lblPreview.setBounds(10, 84, 79, 14);
		frame.getContentPane().add(lblPreview);

		JLabel label = new JLabel("Error Log");
		label.setFont(new Font("Ink Free", Font.BOLD, 17));
		label.setBounds(10, 403, 89, 18);
		frame.getContentPane().add(label);

	}

	public String blankString(int num) {
		String newString = "";
		for (int i = 0; i < num; i++) {
			newString += " ";
		}
		return newString;
	}
}
