package parser;

import java.awt.BorderLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class Gui extends JFrame implements ActionListener, WindowListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1370235564110989246L;
	private JTable table = null;
	private TableManager tableManager = null;
	private JFileChooser fc = new JFileChooser();
	private static final String OPEN_MENU = "Открыть";
	private static final String EXIT_MENU = "Выход";
	
	public Gui(String title)
	{	
		super(title);
		
		this.table = new JTable();
		
		tableManager = TableManager.getInstance();
		tableManager.setTable(table);
		
		this.fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		this.add(new JScrollPane(table), BorderLayout.CENTER);
			
		MenuBar menuBar = new MenuBar();
		this.setMenuBar(menuBar);
		Menu menuFile = new Menu("Файл");
		MenuItem menuOpen = new MenuItem(OPEN_MENU, new MenuShortcut(KeyEvent.VK_O));
		MenuItem menuExit = new MenuItem(EXIT_MENU, new MenuShortcut(KeyEvent.VK_Q));

		menuBar.add(menuFile);
		menuFile.add(menuOpen);			
		menuFile.add(menuExit);
	
		menuExit.addActionListener(this);
		menuOpen.addActionListener(this);
	
			
		this.setSize(600,600);
			
		this.setLocation(250, 150);
			
		this.setVisible(true);
			
		this.addWindowListener(this);		
	}

	@Override
	public void windowActivated(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void windowClosed(WindowEvent arg0) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void windowClosing(WindowEvent arg0) 
	{
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void windowIconified(WindowEvent arg0) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void windowOpened(WindowEvent arg0) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		String actionComand = arg0.getActionCommand();
		if (actionComand.equals(OPEN_MENU)) 
		{
			int returnVal = fc.showOpenDialog(Gui.this);
			
			if (returnVal == JFileChooser.APPROVE_OPTION) 
			{
                File file = fc.getSelectedFile();
                System.out.println("Opening: " + file.getName());
                tableManager.setTableData(MyFileReader.getStandAloneWords(file));
            } 
			else 
			{
				System.out.println("Open command cancelled by user.");
            }
		} 
		else if (actionComand.equals(EXIT_MENU)) 
		{
			System.exit(0);
		}
		
	}

}
