package Agenda;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

public class Main extends JFrame implements ActionListener, KeyListener, WindowListener{

	private static final long serialVersionUID = 1L;
	
	private Contactos contacts = new Contactos();
	private JTextField cmd;
	private JTextArea textArea;
	private JFileChooser fileChooser = new JFileChooser();
	private Color fondoTexto=new Color(254,235,245);
	private Color fondoplay=new Color(255,184,221);
	private Color fondocmd=new Color(246,211,234);
	public Main() throws IOException {
		super("Prueba de Swing");
		setIconImage(ImageIO.read(getClass().getResource("/open.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(fondoplay);
		
		JButton load = new JButton(new ImageIcon(getClass().getResource("/open.png")));
		load.setActionCommand("LOAD");
		load.addActionListener(this);
		load.setBackground(fondoplay);
		toolBar.add(load);
		
		JButton save = new JButton(new ImageIcon(getClass().getResource("/save.png")));
		save.setActionCommand("SAVE");
		save.addActionListener(this);
		save.setBackground(fondoplay);
		toolBar.add(save);
		
		JButton saveAs = new JButton(new ImageIcon(getClass().getResource("/saveas.png")));
		saveAs.setActionCommand("SAVEAS");
		saveAs.addActionListener(this);
		saveAs.setBackground(fondoplay);
		toolBar.add(saveAs);
		
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
	
	private void exec() {
		String result = contacts.exec(cmd.getText());
		if (result != null) {
			textArea.append(result + "\n");
		}
		cmd.setText("");
//		cmd.requestFocus();
	}
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(() -> {
			try {
				new Main().setVisible(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		int respuesta = JOptionPane.showConfirmDialog(Main.this, "¿Estás Seguro?", "Cierre de la aplicación", JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION)
			System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		exec();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("LOAD")) {
			load();
		}
		else if (e.getActionCommand().equals("SAVE")) {
			save();
		}
		else if (e.getActionCommand().equals("SAVEAS")) {
			saveAs();
		}
		else if (e.getActionCommand().equals("EXEC")) {
			exec();
		}
	}
	
	private void load() {
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
			contacts.load(fileChooser.getSelectedFile());
	}
	
	private void save() {
		
	}
	
	private void saveAs() {
		
	}

}