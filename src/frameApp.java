import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class frameApp {

	boolean uploaded = false;
	
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
				
				uploaded = true; // File Uploaded
				
				JTextPane txtpnFileName = new JTextPane();   //File Name GUI
				txtpnFileName.setBounds(128, 11, 150, 23);
				frame.getContentPane().add(txtpnFileName);

				JTextArea jTextArea1 = new JTextArea();   //Preview GUI
				jTextArea1.setBounds(10, 109, 869, 285);
				frame.getContentPane().add(jTextArea1);

				JTextArea textAreaErrors = new JTextArea();  //Error Log GUI
				textAreaErrors.setBounds(10, 432, 869, 66);
				frame.getContentPane().add(textAreaErrors);

				JFileChooser chooser = new JFileChooser(); // choosing patch and opening dialog
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();

				String filename = f.getAbsolutePath();  //Path of the File
				String file_name = f.getName();   //Name

				txtpnFileName.setText(file_name);
				
				String error = "error.txt";
				File error_file = new File(error);

				String userHomeFolder = System.getProperty("user.home") + "/Desktop"; // The user's home directory
				
				String fileName_to_save = "output.txt"; // new file name
				File file_to_save = new File(userHomeFolder,fileName_to_save); // creates a File to save later
				

				try {
					FileReader reader = new FileReader(filename);
					BufferedReader br = new BufferedReader(reader);
					String line = br.readLine();

					FileWriter error_output = new FileWriter(error_file, false); // create a file for error
					PrintWriter error_print = new PrintWriter(error_output);

					FileWriter output = new FileWriter(file_to_save, false); // create a file to save
					PrintWriter output_print = new PrintWriter(output);

					//creating variables to use for multiple flag reading
					boolean right = false, center = false, title = false;
					boolean doublespace = false;
					boolean singleIndent = false, multiIndent = false;
					boolean twoCol = false;
					
					char flag = 'l';
					while (line != null) {
						if (line.charAt(0) == '-') {
							for(i = 1; i < line.length(); i++) {
								if(line.charAt(i-1) == '-') {
									switch(line.charAt(i)) {
									case 'l':
										right = false;
										center = false;
										title = false;
										break;
									case 'c':
										center = true;
										right = false;
										title = false;
										break;
									case 'r':
										right = true;
										center = false;
										title = false;
										break;
									case 't':
										title = true;
										right = false;
										center = false;
										break;
									case 'd':
										doublespace = true;
										break;
									case 's':
										doublespace = false;
										break;
									case 'i':
										singleIndent = true;
										multiIndent = false;
										break;
									case 'b':
										multiIndent = true;
										singleIndent = false;
										break;
									case 'n':
										singleIndent = false;
										multiIndent = false;
										break;
									case '2':
										twoCol = true;
										break;
									case '1':
										twoCol = false;
										break;
									case 'e':
										output_print.println(blankString(80));
										break;
									default:
										error_print.println("While Processing Your File The Following Errors Happened: ");
										error_print.println("Invalid Flag Error");
										textAreaErrors.write(error_output);
									}
								}
							}
						} else {
							//formats the line beneath the flag line
							if(right) {
								if(singleIndent) {
									if(twoCol) {
										int size = line.length() / 35 + 1;
										String[] arr = new String[size];
										String subLine = line;
										for (int i = 0; i < arr.length; i++) {
											if(i == 0) {
												if (subLine.length() > 30) {
													arr[i] = blankString(5) + subLine.substring(0, 30);
													subLine = subLine.substring(30, subLine.length());
												} else {
													arr[i] = blankString(5) + subLine;
												}
											}else {
												if (subLine.length() > 35) {
													arr[i] = subLine.substring(0, 35);
													subLine = subLine.substring(35, subLine.length());
												} else {
													arr[i] = subLine;
												}
											}
										}

										// prints all of the arrays in two columns, so that both columns can be read
										int addVal = arr.length / 2 + 1;
										String newString = "";
										for (int i = 0; i < arr.length / 2; i++) {
											newString += arr[i] + blankString(10) +
													blankString(35-arr[i + addVal].length()) + arr[i + addVal] + "\n";
										}

										// if the array is odd, then it prints the final array that didn't get printed
										if (arr.length % 2 == 1) {
											newString += arr[arr.length / 2];
										}

										output_print.println(newString);
									}else {
										int count = 0;
										while (line.length() != 0) {
											if (count == 0) {
												if (line.length() > 75) {
													output_print.println(blankString(5) + line.substring(0, 75));
													line = line.substring(75, line.length());
													count++;
												} else {
													int num = 80 - line.length();
													output_print.println(blankString(num) + line);
													count++;
													break;
												}
											} else {
												if (line.length() > 80) {
													output_print.println(line.substring(0, 80));
													line = line.substring(80, line.length());
												} else {
													int num = 80 - line.length();
													output_print.println(blankString(num) + line);
													break;
												}
											}
										}
									}
								}else if(multiIndent) {
									if(twoCol) {
										int size = line.length() / 25 + 1;
										String[] arr = new String[size];
										String subLine = line;
										for (int i = 0; i < arr.length; i++) {
											if (subLine.length() > 25) {
												arr[i] = blankString(10) + subLine.substring(0, 25);
												subLine = subLine.substring(25, subLine.length());
											} else {
												arr[i] = blankString(10) + subLine;
											}
										}

										// prints all of the arrays in two columns, so that both columns can be read
										int addVal = arr.length / 2 + 1;
										String newString = "";
										for (int i = 0; i < arr.length / 2; i++) {
											newString += arr[i] + blankString(10) + 
													blankString(35-arr[i + addVal].length()) + arr[i + addVal] + "\n";
										}

										// if the array is odd, then it prints the final array that didn't get printed
										if (arr.length % 2 == 1) {
											newString += arr[arr.length / 2];
										}

										output_print.println(newString);
										
									}else {
										while (line.length() != 0) {
											if (line.length() > 70) {
												output_print.println(line.substring(0, 70));
												line = blankString(10) + line.substring(70, line.length());
											} else {
												int num = 80 - line.length();
												output_print.println(blankString(num) + line);
												break;
											}
										}
									}
								}else {
									if(twoCol) {
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
											newString += arr[i] + blankString(10) +
													blankString(35-arr[i + addVal].length()) + arr[i + addVal] + "\n";
										}

										// if the array is odd, then it prints the final array that didn't get printed
										if (arr.length % 2 == 1) {
											newString += arr[arr.length / 2];
										}

										output_print.println(newString);
										
									}else {
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
									}
								}
							}else if(center) {
								if(singleIndent) {
									if(twoCol) {
										int size = line.length() / 35 + 1;
										String[] arr = new String[size];
										String subLine = line;
										for (int i = 0; i < arr.length; i++) {
											if(i == 0) {
												if (subLine.length() > 30) {
													arr[i] = blankString(5) + subLine.substring(0, 30);
													subLine = subLine.substring(30, subLine.length());
												} else {
													arr[i] = blankString(5) + subLine;
												}
											}else {
												if (subLine.length() > 35) {
													arr[i] = subLine.substring(0, 35);
													subLine = subLine.substring(35, subLine.length());
												} else {
													arr[i] = subLine;
												}
											}
										}

										// prints all of the arrays in two columns, so that both columns can be read
										int addVal = arr.length / 2 + 1;
										String newString = "";
										for (int i = 0; i < arr.length / 2; i++) {
											newString += arr[i] + blankString(10) +
													blankString(35/2 - arr[i + addVal].length()) + arr[i + addVal] + "\n";
										}

										// if the array is odd, then it prints the final array that didn't get printed
										if (arr.length % 2 == 1) {
											newString += arr[arr.length / 2];
										}

										output_print.println(newString);
									}else {
										int count = 0;
										while (line.length() != 0) {
											if (count == 0) {
												if (line.length() > 75) {
													output_print.println(blankString(5) + line.substring(0, 75));
													line = line.substring(75, line.length());
													count++;
												} else {
													int num = (75 - line.length()) / 2;
													output_print.println(blankString(num + 5) + line);
													count++;
													break;
												}
											} else {
												if (line.length() > 80) {
													output_print.println(line.substring(0, 80));
													line = line.substring(80, line.length());
												} else {
													int num = (80 - line.length()) / 2;
													output_print.println(blankString(num) + line);
													break;
												}
											}
										}
									}
								}else if(multiIndent) {
									if(twoCol) {
										int size = line.length() / 25 + 1;
										String[] arr = new String[size];
										String subLine = line;
										for (int i = 0; i < arr.length; i++) {
											if (subLine.length() > 25) {
												arr[i] = blankString(10) + subLine.substring(0, 25);
												subLine = subLine.substring(25, subLine.length());
											} else {
												arr[i] = blankString(10) + subLine;
											}
										}

										// prints all of the arrays in two columns, so that both columns can be read
										int addVal = arr.length / 2 + 1;
										String newString = "";
										for (int i = 0; i < arr.length / 2; i++) {
											newString += arr[i] + blankString(10) +
													blankString(35/2 - arr[i + addVal].length()) + arr[i + addVal] + "\n";
										}

										// if the array is odd, then it prints the final array that didn't get printed
										if (arr.length % 2 == 1) {
											newString += arr[arr.length / 2];
										}

										output_print.println(newString);
									}else {
										if (line.length() > 70) {
											output_print.println(blankString(10) + line.substring(0, 70));
											line = line.substring(70, line.length());
										} else {
											int num = (70 - line.length()) / 2;
											output_print.println(blankString(num + 10) + line);
											break;
										}
									}
								}else {
									if(twoCol) {

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
											newString += arr[i] + blankString(10) +
													blankString(35/2 - arr[i + addVal].length()) + arr[i + addVal] + "\n";
										}

										// if the array is odd, then it prints the final array that didn't get printed
										if (arr.length % 2 == 1) {
											newString += arr[arr.length / 2];
										}

										output_print.println(newString);										
									}else {
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
									}
								}
								
							}else if(title) { //should only make a centered title with no changes, otherwise what's the point
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
							}else {
								if(singleIndent) {
									if(twoCol) {
										int size = line.length() / 35 + 1;
										String[] arr = new String[size];
										String subLine = line;
										for (int i = 0; i < arr.length; i++) {
											if(i == 0) {
												if (subLine.length() > 30) {
													arr[i] = blankString(5) + subLine.substring(0, 30);
													subLine = subLine.substring(30, subLine.length());
												} else {
													arr[i] = blankString(5) + subLine;
												}
											}else {
												if (subLine.length() > 35) {
													arr[i] = subLine.substring(0, 35);
													subLine = subLine.substring(35, subLine.length());
												} else {
													arr[i] = subLine;
												}
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
									}else {
										int count = 0;
										while (line.length() != 0) {
											if (count == 0) {
												if (line.length() > 75) {
													output_print.println(blankString(5) + line.substring(0, 75));
													line = line.substring(75, line.length());
													count++;
												} else {
													output_print.println(blankString(5) + line);
													count++;
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
									}
								}else if(multiIndent) {
									if(twoCol) {
										int size = line.length() / 25 + 1;
										String[] arr = new String[size];
										String subLine = line;
										for (int i = 0; i < arr.length; i++) {
											if (subLine.length() > 25) {
												arr[i] = blankString(10) + subLine.substring(0, 25);
												subLine = subLine.substring(25, subLine.length());
											} else {
												arr[i] = blankString(10) + subLine;
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
									}else {
										if (line.length() > 70) {
											output_print.println(blankString(10) + line.substring(0, 70));
											line = line.substring(70, line.length());
										} else {
											output_print.println(blankString(10) + line);
											break;
										}
									}
								}else {
									if(twoCol) {
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
									}else {
										while (line.length() != 0) {
											if (line.length() > 80) {
												output_print.println(line.substring(0, 80));
												line = line.substring(80, line.length());
											} else {
												output_print.println(line);
												break;
											}
										}
									}
								}
								
							}
							
							if(doubleSpace) {
								output_print.println(blankString(80));
							}
						}
						line = br.readLine();
					}

					br.close();
					output_print.close();
					error_output.close();
					output_print.flush();
					textAreaErrors.requestFocus();
					
					Font previewFont = new Font("MONOSPACED", Font.PLAIN, 12);
					jTextArea1.setFont(previewFont);
					
					// Display Preview
					Scanner scan_output = new Scanner(new File(file_to_save.getAbsolutePath()));
					String preview = "";
					while (scan_output.hasNextLine()) {
						preview += scan_output.nextLine() + "\n";

					}
					jTextArea1.setText(preview);
					scan_output.close();
					jTextArea1.requestFocus();
					
					// Display Error Log
					Scanner scan_error = new Scanner(new File(error_file.getAbsolutePath()));
					String error_log = "";
					while (scan_error.hasNextLine()) {
						error_log += scan_error.nextLine() + "\n";

					}
					textAreaErrors.setText(error_log);
					scan_error.close();
					textAreaErrors.requestFocus();

				}
				

				catch (Exception e) {
					//Error Handling 
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
				if(uploaded) {
					JOptionPane.showMessageDialog(null, 
	                        "The output.txt file is saved to your HOME directory", 
	                        "FILE SAVED", 
	                        JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, 
	                        "You need to upload a file first.",
	                        "NO FILE UPLOADED",
	                        JOptionPane.INFORMATION_MESSAGE);
				}
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
	
} //end