package dataStorage;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import graphicDesign.Areas;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class InitializationTest extends JFrame {

	private JPanel contentPane;
	private static HashMap<String, String> initializationDict = new HashMap<String, String>();

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader in; 
        in = new BufferedReader(new FileReader(System.getProperty("user.dir") + "\\src\\ressources\\Initialization"));
        String line = in.readLine();
        
        while (line != null) {
        	if (line.charAt(0) != '#') {
        		String[] keyValue = line.split(" ");
        		initializationDict.put(keyValue[0], keyValue[1]);
        	}
        	
        	line = in.readLine();
        }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InitializationTest frame = new InitializationTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InitializationTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(get("PositionX"), get("PositionY"), 582, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Areas areas = new Areas();
		areas.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				areas.mouseX = e.getX();
				areas.mouseY = e.getY();
				areas.repaint();
			}
		});
		areas.setBounds(10, 10, 500, 300);
		contentPane.add(areas);
	}
	
	public static String set(String key, String value) {
		return initializationDict.put(key, value);
	}
	
	public static int get(String key) {
		String returns = initializationDict.get(key);
		if (returns == null) {
			return Integer.MIN_VALUE;
		}
		return Integer.parseInt(returns);
	}
}
