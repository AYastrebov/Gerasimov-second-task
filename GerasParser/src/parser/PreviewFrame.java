package parser;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PreviewFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3186494520298809478L;
	private JTextArea textArea = new JTextArea();

	public PreviewFrame(String title, String textAreaText) 
	{
		super(title);
		
		this.add(new JScrollPane(textArea), BorderLayout.CENTER);
		textArea.setText(textAreaText);
		
		this.setSize(600,600);
		
		this.setLocation(250, 150);
			
		this.setVisible(true);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void updateFrame(String title, String textAreaText)
	{
		this.setTitle(title);
		textArea.setText(textAreaText);
	}
}
