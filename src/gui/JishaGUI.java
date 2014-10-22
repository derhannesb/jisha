package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.Delegator;

public class JishaGUI extends JFrame implements Observer{

	public static boolean COM_CONNECTED = false;
	private static final long serialVersionUID = -7158032709033162513L;
	private ImageLabel imageLabel = null;
	private JScrollPane imageScrollPane = null;
	private JPanel leftMenu = null;
	private JButton btNew = null;
	private JButton btConnect = null;
	private JButton btSend = null;
	private JPanel bottomPanel = null;
	private JTextArea tfLog = null;
	private JTextArea tfSend = null;
	private Delegator delegator = null;
	
	public JishaGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setTitle("Jisha v0.01");
		setBounds(100, 100, 1024, 768);
		
		delegator = new Delegator();
		
		imageLabel = new ImageLabel();
		imageScrollPane = new JScrollPane(imageLabel);
		
		leftMenu = new JPanel();
		btNew = new JButton("New");
		btConnect = new JButton("Connect");
		btConnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btConnect.setText("Connecting..");
				JishaGUI.COM_CONNECTED = false;
				if (delegator.verbinden())
					{
						btConnect.setText("Connected!");
						JishaGUI.COM_CONNECTED = true;
					}
				else btConnect.setText("Connect");
			}
		});
		
		leftMenu.add(btNew);
		leftMenu.add(btConnect);
		getContentPane().setLayout(new BorderLayout(5,4));
	
		bottomPanel = new JPanel(new BorderLayout(5,4));
		
		tfLog = new JTextArea();
		tfLog.setPreferredSize(new Dimension(500,200));
		bottomPanel.add(tfLog, BorderLayout.WEST);
		
		tfSend = new JTextArea();
		tfSend.setPreferredSize(new Dimension(200,200));
		bottomPanel.add(tfSend, BorderLayout.CENTER);
		
		btSend = new JButton("Send");
		btSend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JishaGUI.COM_CONNECTED)
				{
					String[] lines = tfSend.getText().split(System.getProperty("line.separator"));
					for (String line: lines)
					{
						delegator.senden(line);	
					}
					tfSend.setText("");
				}
					
			}
		});
		bottomPanel.add(btSend, BorderLayout.EAST);
		
		getContentPane().add(leftMenu, BorderLayout.WEST);
		getContentPane().add(imageScrollPane, BorderLayout.CENTER);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new JishaGUI();
	}

	@Override
	public void update(Observable obs, Object obj) {
		tfLog.append(obj.toString()+"\n");
	}

}
