package parser;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SourcePreview extends PreviewFrame implements ActionListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1969989945970568007L;

	public SourcePreview() 
	{		
		super(DataManager.getInstance().getFileName(), 
				DataManager.getInstance().getStringRepresentation());
		
		JButton updateButton = new JButton("Обновить");
		updateButton.addActionListener(this);
		
		this.add(updateButton, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		updateFrame(DataManager.getInstance().getFileName(), 
				DataManager.getInstance().getStringRepresentation());
	}
}
