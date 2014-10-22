package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class JishaGUI extends JFrame{

	private static final long serialVersionUID = -7158032709033162513L;
	ImageLabel imageLabel = null;
	JScrollPane imageScrollPane = null;
	JPanel leftMenu = null;
	JButton btNew = null;
	JPanel bottomPanel = null;
	JTextField tfLog = null;
	
	public JishaGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Jisha v0.01");
		setBounds(100, 100, 1024, 768);
		
		
		imageLabel = new ImageLabel();
		imageScrollPane = new JScrollPane(imageLabel);
		//imageScrollPane.setPreferredSize(new Dimension(500,500));
		
		leftMenu = new JPanel();
		btNew = new JButton("New");
		leftMenu.add(btNew);
		getContentPane().setLayout(new BorderLayout(5,4));
	
		
		bottomPanel = new JPanel(new BorderLayout());
		tfLog = new JTextField();
		bottomPanel.add(tfLog, BorderLayout.CENTER);
		
		
		getContentPane().add(leftMenu, BorderLayout.WEST);
		getContentPane().add(imageScrollPane, BorderLayout.CENTER);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new JishaGUI();
	}

}
