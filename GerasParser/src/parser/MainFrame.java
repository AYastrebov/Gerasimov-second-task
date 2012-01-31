package parser;

import java.awt.BorderLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame implements ActionListener
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
	
	public MainFrame(String title)
	{	
		super(title);
		
		this.fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		this.add(new JScrollPane(TableManager.getInstance().getTable()), BorderLayout.CENTER);
			
		//Создаем меню
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
		menuPreview.addActionListener(this);
	
			
		this.setSize(600,600);
			
		this.setLocation(250, 150);
			
		this.setVisible(true);
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		String actionComand = arg0.getActionCommand();
		if (actionComand.equals(OPEN_MENU)) 
		{
			int returnVal = fc.showOpenDialog(MainFrame.this);
			
			if (returnVal == JFileChooser.APPROVE_OPTION) 
			{
				//Открываем файл
                File file = fc.getSelectedFile();
                System.out.println("Opening: " + file.getName());
                //Заполням DataManager информацией
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
			//Предпросмотр исключаемых символов
			new SymbolsPreview();
		}
		else if (actionComand.equals(PREVIEW_MENU))
		{
			//Предпросмотр исходного файла
			new SourcePreview();
		}
		
	}

}
