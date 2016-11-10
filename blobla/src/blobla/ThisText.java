/**
 * 
 */
package blobla;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author US
 *
 */
public class ThisText extends JFrame {
	JMenuBar mnbBar;
	JMenu mnuFile,mnuFormat;
	JMenuItem mniOpen, mniExit, mniChangeBgColor, mniChangeFontColor,mniSaveAs,mniSave;
	JTextArea txaContent;
	JScrollPane scrPane;
	JFileChooser fchOpenFile= new JFileChooser();
	File f;
	/**
	 * @Constructor
	 */
	public  ThisText(){
		setTitle("Text Editor");
		setPreferredSize(new Dimension(600,500));
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		//goi phuong thuc tao menu
		InitializeMenu();
		//tao text area
		txaContent = new JTextArea();
		scrPane = new JScrollPane(txaContent);
		//them scrollpane vao frame
		getContentPane().add(scrPane);
		txaContent.setLineWrap(true);
		
		
		ActionListener action = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getSource()==mniOpen){
					OpenFile();
				}else if(e.getSource()==mniExit){
					closeApplication();
				}else if(e.getSource()==mniChangeBgColor){
					changeBGColor();
				}else if(e.getSource()==mniChangeFontColor){
					ChangeFontColor();
				}else if(e.getSource()==mniSaveAs){
					saveAs();
				}else if(e.getSource()==mniSave){
					save();
				}
			}
		};
		//them lang nghe su kien cho cac menu item
		mniOpen.addActionListener(action);
		mniExit.addActionListener(action);
		mniChangeBgColor.addActionListener(action);
		mniChangeFontColor.addActionListener(action);
		mniSaveAs.addActionListener(action);
		mniSave.addActionListener(action);
		//filechooser
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", "txt");
		fchOpenFile.setFileFilter(filter);
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				closeApplication();
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	public void InitializeMenu(){
		//tao cac object lien quan den menu
		mnbBar = new JMenuBar();
		mnuFile = new JMenu("File");
		mnuFormat = new JMenu("Format");
		mniOpen = new JMenuItem("Open");
		mniExit = new JMenuItem("Exit");
		mniSave = new JMenuItem("Save");
		mniSaveAs = new JMenuItem("Save as");
		mniSaveAs.setMnemonic(KeyEvent.VK_A);
		mniSave.setMnemonic(KeyEvent.VK_S);
		mniChangeBgColor = new JMenuItem("Change Backgroud Color");
		mniChangeFontColor = new JMenuItem("Change Font Color");
		//them menu item vao menu file
		mnuFile.add(mniOpen);
		mnuFile.addSeparator();
		mnuFile.add(mniExit);
		mnuFile.add(mniSaveAs);
		mnuFile.add(mniSave);
		//them menu item vao menu format
		mnuFormat.add(mniChangeBgColor);
		mnuFormat.addSeparator();
		mnuFormat.add(mniChangeFontColor);
		//them menu file va menu format vao menu bar
		mnbBar.add(mnuFile);
		mnbBar.add(mnuFormat);
		//thiet lap menubar thanh menu chinh cua frame
		setJMenuBar(mnbBar);
	}
	public void OpenFile(){
		
		int result = fchOpenFile.showOpenDialog(this);
		
		if (result==JFileChooser.APPROVE_OPTION){
			f=fchOpenFile.getSelectedFile();
			txaContent.setText("");
			try {
				Scanner scn = new Scanner(fchOpenFile.getSelectedFile());
				while (scn.hasNextLine()){
					txaContent.append(scn.nextLine() +"\n");
					
					
				}
				scn.close();
			} catch (FileNotFoundException e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Selected file is not found");
			}
		}
	}
	public void changeBGColor(){
		Color newColor = JColorChooser.showDialog(this, "Choose new backgroud color", txaContent.getBackground());
		txaContent.setBackground(newColor);
	}
	public void ChangeFontColor(){
		Color fC = JColorChooser.showDialog(this, "Choose new font color", txaContent.getForeground());
		txaContent.getForeground();
	}
	public void closeApplication(){
		int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?","Confirm",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
		if (result==JOptionPane.YES_OPTION){
			System.exit(0);
		}
	}
	public void saveAs(){
	
		int result = fchOpenFile.showSaveDialog(this);
		if(result == JFileChooser.APPROVE_OPTION){
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(fchOpenFile.getSelectedFile()));
				writer.write(txaContent.getText());
				writer.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	public void save(){
		if(f!=null){
			try {
				BufferedWriter wr = new BufferedWriter(new FileWriter(f));
				wr.write(txaContent.getText());
				wr.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}else{
		saveAs();}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThisText mw = new ThisText();
		mw.setVisible(true);
	}

}
