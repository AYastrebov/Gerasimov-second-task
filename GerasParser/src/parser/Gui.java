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

public class Gui extends JFrame implements ActionListener, WindowListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1370235564110989246L;
	private final Menu menuOptions = new Menu("Опции");
	private JFileChooser fc = new JFileChooser();
	private static final String OPEN_MENU = "Открыть";
	private static final String EXIT_MENU = "Выход";
	
	private static final String EXCEPTIONS_MENU = "Исключения";
	private static final String PREVIEW_MENU = "Источник";
	
	public Gui(String title)
	{	
		super(title);
				
		//tableManager = TableManager.getInstance();
		
		this.fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		this.add(new JScrollPane(TableManager.getInstance().getTable()), BorderLayout.CENTER);
			
		MenuBar menuBar = new MenuBar();
		this.setMenuBar(menuBar);
		
		final Menu menuFile = new Menu("Файл");
		MenuItem menuOpen = new MenuItem(OPEN_MENU, new MenuShortcut(KeyEvent.VK_O));
		MenuItem menuExit = new MenuItem(EXIT_MENU, new MenuShortcut(KeyEvent.VK_Q));
		
		MenuItem menuExceptions = new MenuItem(EXCEPTIONS_MENU, new MenuShortcut(KeyEvent.VK_E));
		MenuItem menuPreview = new MenuItem(PREVIEW_MENU, new MenuShortcut(KeyEvent.VK_P));

		menuBar.add(menuFile);
		
		menuFile.add(menuOpen);			
		menuFile.add(menuExit);
		
		menuBar.add(menuOptions);
		
		menuOptions.add(menuExceptions);
		menuOptions.add(menuPreview);
		menuOptions.setEnabled(false);
		
		menuExit.addActionListener(this);
		menuOpen.addActionListener(this);
		
		menuExceptions.addActionListener(this);
	
			
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
                DataManager.getInstance().fillWithData(file);
                menuOptions.setEnabled(true);
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
		else if (actionComand.equals(EXCEPTIONS_MENU))
		{
			
		}
		else if (actionComand.equals(PREVIEW_MENU))
		{
			
		}
		
	}

}
