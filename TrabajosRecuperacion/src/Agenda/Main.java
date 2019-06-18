package Agenda;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Map;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

public class Main extends JFrame implements ActionListener, KeyListener, WindowListener{

	private static final long serialVersionUID = 1L;
	
	private Contactos contacts = new Contactos();
	static JButton  b;
	static JButton b2;
	JPanel panel;
	 JToolBar toolBar = new JToolBar();
	JButton load;
	JButton save;
	JButton saveas;
	private String root;
	private JTextField cmd;
	private JTextArea textArea;
	private JFileChooser fileChooser = new JFileChooser();
	private Color fondoTexto=new Color(254,235,245);
	private Color fondoplay=new Color(255,184,221);
	private Color fondocmd=new Color(246,211,234);
	public Main() throws IOException {
		super("Agenda Intefaz");
		JFrame ventana = new JFrame();

		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel=new JPanel ();
		ventana.setBounds(370,370,370,370);
		ventana.add(panel);
		panel.setBounds(370,370,370,370);
		b=new JButton("Buscar/Borrar Contacto");
	    b.setActionCommand("EXEC");
		b.addActionListener(this);


	    b2=new JButton("Ingresar Contacto");
	    b2.setBounds(50,50,50,50);
	    b2.setActionCommand("EXEC");
		b2.addActionListener(this);
		panel.add(b);
		panel.add(b2);
				
		ventana.setVisible(true);
		
	
		setIconImage(ImageIO.read(getClass().getResource("/open.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		toolBar.setBackground(fondoplay);
		
		load = new JButton(new ImageIcon(getClass().getResource("/open.png")));
		load.setActionCommand("LOAD");
		load.addActionListener(this);
		load.setBackground(fondoplay);
		toolBar.add(load);
		
		save = new JButton(new ImageIcon(getClass().getResource("/save.png")));
		save.setActionCommand("SAVE");
		save.addActionListener(this);
		save.setBackground(fondoplay);
		toolBar.add(save);
		
		saveas = new JButton(new ImageIcon(getClass().getResource("/saveas.png")));
		saveas.setActionCommand("SAVEAS");
		saveas.addActionListener(this);
		saveas.setBackground(fondoplay);
		toolBar.add(saveas);
		
		add(toolBar, BorderLayout.NORTH);
		
		textArea = new JTextArea(30, 80);
		textArea.setEditable(false);
		textArea.setFocusable(false);
		add(textArea, BorderLayout.CENTER);
		textArea.setBackground(fondoTexto);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		cmd = new JTextField();
		cmd.addKeyListener(this);	
		cmd.setBackground(fondocmd);
		panel.add(cmd, BorderLayout.CENTER);
		
		JButton exec = new JButton(new ImageIcon(getClass().getResource("/play.png")));
		exec.setActionCommand("EXEC");
		exec.addActionListener(this);
		exec.setBackground(fondoplay);
		panel.add(exec, BorderLayout.EAST);
		
		add(panel, BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(null);
		addWindowListener(this);
	}
	
	
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
			try {
				new Main().setVisible(true);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		});
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		cmd.requestFocus();
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
	
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		int respuesta = JOptionPane.showConfirmDialog(Main.this, "¿Estás Seguro?", "Cierre de la aplicación", JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION)
			System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
	
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
            
		}
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		 if(e.getKeyCode()==KeyEvent.VK_ENTER){
			 try {
				exec();
			
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object objeto=e.getSource();
		if(objeto==b2 || objeto==b )  {
			
			  b2.setBackground(fondocmd);
			  b.setVisible(false);
			  b2.setVisible(false);
			  textArea = new JTextArea(15,30);
			  cmd=new  JTextField (30) ;
			  cmd.addKeyListener(this);
			  textArea.setEditable(false);
			  textArea.setFocusable(false);				    
		
		 JLabel etiqueta=new JLabel("menu");
		 toolBar.add(etiqueta);		
		
		panel.add(toolBar, BorderLayout.NORTH);
		toolBar.setFloatable(false);
		panel.add(textArea,BorderLayout.LINE_END);
		panel.add(cmd,BorderLayout.LINE_END);
		
		pack();
		setLocationRelativeTo(null);
		addWindowListener(this);
	}
		if(objeto==load) {
			load.setBackground(fondocmd);
			
			int seleccion = fileChooser.showOpenDialog(null);
			if (seleccion == JFileChooser.APPROVE_OPTION)
			{
			   File fichero = fileChooser.getSelectedFile();
			 
			try {
				root = fichero.getCanonicalPath();
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
			contacts.leer(root);
			  System.out.println(root);
			}
			
}
		if(objeto==saveas) {
			saveas.setBackground(Color.lightGray);
			fileChooser.showSaveDialog(null);
			fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		    File fichero = fileChooser.getSelectedFile();
		   
			try {
				root = fichero.getCanonicalPath();
			} catch (IOException e1) {
				e1.printStackTrace();
			} 
			contacts.escribir(root);
			  System.out.println(root);
		
			
		   
		}if(objeto==save) {
			save.setBackground(Color.lightGray);
			
			Contactos.escribir(root);
		}
	}
	
	private void exec() throws IOException {
		String result = contacts.exec(cmd.getText());
		 textArea.setFocusable(true);
		 if (result != null) {
		textArea.append(result +"\n");
		}
		cmd.setText("");
	}

		
	
	


	
	
}