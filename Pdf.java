package pdf1.com.pdf1;

import java.io.*;
import java.text.AttributedCharacterIterator.Attribute;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import java.util.jar.JarFile;
import javax.swing.*;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfDocument;
import com.lowagie.text.pdf.PdfWriter;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;

public class Pdf extends JFrame implements ActionListener {
	JLabel l1, l2;
	JFileChooser fs;
	JTextArea ar, ar1;
	JMenuBar mb;
	JMenu m;
	JMenuItem jmt;
	JButton b1, b2;

	public Pdf() {
		setTitle("Converter");
		JFrame f1 = new JFrame();
		f1.setDefaultCloseOperation(EXIT_ON_CLOSE);

		l1 = new JLabel("Online PDF Converter");
		l1.setBounds(40, 20, 500, 40);
		l1.setFont(new Font("Arial", Font.BOLD, 45));
		l1.setForeground(Color.gray);
		add(l1);

		l2 = new JLabel("Easily convert to and from PDF in seconds.");
		l2.setBounds(100, 100, 500, 40);
		l2.setFont(new Font("Arial", Font.BOLD, 15));
		l2.setForeground(Color.gray);
		add(l2);

		b2 = new JButton("+          File Chooser      ");
		b2.setFont(new Font("Arial", Font.BOLD, 25));
		b2.setForeground(Color.white);
		b2.setBackground(Color.red);
		b2.setBounds(100, 150, 320, 55);
		add(b2);

		b2.addActionListener(this);

		ar = new JTextArea();
		ar.setBounds(90, 220, 330, 40);
		ar.setFont(new Font("Arial", Font.BOLD, 19));
		add(ar);

		ar1 = new JTextArea();
		ar1.setBounds(80, 360, 380, 45);
		ar1.setFont(new Font("Arial", Font.BOLD, 19));
		add(ar1);

		b1 = new JButton("Submit");
		b1.setFont(new Font("Arial", Font.BOLD, 25));
		b1.setBackground(new Color(43, 173, 73));
		b1.setForeground(Color.white);
		b1.setBounds(150, 290, 200, 55);
		add(b1);

		fs = new JFileChooser();
		add(fs);

		setLayout(null);
		setBounds(300, 30, 550, 600);
		setVisible(true);

		b1.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equalsIgnoreCase("+          File Chooser      ")) {
			int x = fs.showDialog(null, "my file");
			if (x == fs.APPROVE_OPTION) {
				ar.setText(fs.getSelectedFile().getPath());
			} else {
				ar.setText("Not Selected File");
			}
		}

		else if (ae.getSource() == b1) {
			String s5 = "";
			int index = fs.getSelectedFile().getPath().lastIndexOf('.');

			if (index > 0) {
				s5 = fs.getSelectedFile().getPath().substring(index + 1);

			}
			System.out.println(s5);
			if (s5.equals("docx")) {

				try {

					FileInputStream fc = new FileInputStream(ar.getText());
					XWPFDocument doc = new XWPFDocument(fc);
					PdfOptions pd1 = PdfOptions.create();
					FileOutputStream fs = new FileOutputStream("C:\\Users\\pande\\Desktop\\ac.pdf");
					PdfConverter.getInstance().convert(doc, fs, pd1);
					fc.close();
					fs.close();
					ar1.setText("succesfully created\n" + pd1);

				} catch (Exception e) {
					System.err.println(e);
				}
			}    else if (s5.equalsIgnoreCase("txt")) {

				try {

					FileInputStream fc = new FileInputStream(ar.getText());
					FileOutputStream fs = new FileOutputStream("C:\\Users\\pande\\Desktop\\aac.pdf");
                    byte b[]=new byte[ar.getText().length()];
                    
                    String s3=new String(b);
                   
					fc.close();
					fs.close();
					ar1.setText("successfully"+s3);

				} catch (Exception e) {
					System.err.println(e);
				}
			} else if (s5.equalsIgnoreCase("jpg")) {

			} else if (s5.equalsIgnoreCase("png")) {

			} else if (s5.equalsIgnoreCase("xlsx")) {

			}
		}
	}

	public static void main(String[] args) throws Exception {
		new Pdf();
	}
}
