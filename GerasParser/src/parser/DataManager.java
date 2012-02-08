package parser;

import java.io.File;
import java.util.Vector;

public class DataManager 
{
	private String stringRepresentation = null;
	private String fileName = null;
	private Vector<String> fileContent = null;
	private Vector<String> formatedWords = null;
	private Vector<String> standAloneWords = null;
	
	private volatile static DataManager instance;

	/** Returns singletone class instance */
	public static DataManager getInstance() {
		if (instance == null) {
			synchronized (DataManager.class) {
				if (instance == null) {
					instance = new DataManager();
				}
			}
		}
		return instance;
	}
		
	private DataManager()
	{
	}
	
	public void fillWithData(File file)
	{
		this.fileContent = MyFileReader.getFileContent(file);
		this.stringRepresentation = MyFileReader.getFileStringRepresentation(fileContent);
		this.standAloneWords = MyFileReader.getStandAloneWords(fileContent);
		this.formatedWords = MyFileReader.getFormatedWords(standAloneWords);
		this.fileName = file.getName();
						
		TableManager.getInstance().setTableData(formatedWords);
	}
	
	public String getFileName() 
	{
		return fileName;
	}

	public Vector<String> getStandAloneWords() 
	{
		return standAloneWords;
	}

	public Vector<String> getFormatedWords()
	{
		return formatedWords;
	}

	public Vector<String> getFileContent() 
	{
		return fileContent;
	}

	public String getStringRepresentation() 
	{
		return stringRepresentation;
	}

	private final static String[] predlogi = 
		{
		"ВОЗЛЕ",
		"ВОКРУГ",
		"ВДОЛЬ",
		"ПОПЕРЕК",
		"БЛИЗ",
		"ИЗ",
		"С",
		"СО",
		"ИЗ-ЗА",
		"ИЗ-ПОД",
		"ОКОЛО",
		"ОТ",
		"У",
		"В",
		"НА",
		"К",
		"ПО",
		"ПЕРЕД",
		"ПОД",
		"НАД",
		"ЗА",
		"МЕЖДУ",
		"ЧЕРЕЗ",		
		"П",
		"Н",
		"О"
		};
	
	private final static String[] particles = 
		{
		"НЕ",
		"НЕТ",
		"ЛИ",
		"РАЗВЕ",
		"НЕУЖЕЛИ",
		"ДАЖЕ",
		"ВЕДЬ",
		"ВСЕ-ТАКИ",
		"ЖЕ",
		"ДАЖЕ",
		"НИ",
		"НУ",
		"ЧТО",
		"КАК",
		"ЗА",
		"ИМЕННО",
		"КАК",
		"РАЗ",
		"ТОЛЬКО",
		"ПОЧТИ",
		"УЖЕ",
		"ВОН",
		"ВОТ",
		"И",
		"А"
		};
	
	private final static String[] special =
		{
			"-",
			"—",
			"+",
			"=",
			",",
			".",
			"!",
			"|",
			">",
			"<",
			"{",
			"}",
			"[",
			"]",
			"(",
			")",
			";",
			":",
			"/",
			"\\",
			"?",
			"«",
			"»"
		};

	public static String[] getSpecial() 
	{
		return special;
	}

	public static String[] getParticles() 
	{
		return particles;
	}

	public static String[] getPredlogi() 
	{
		return predlogi;
	}
	
	public static String[] getAllSpicialSymbols()
	{
		String[] result = new String[predlogi.length  + particles.length + special.length];
		
		System.arraycopy(predlogi, 0, result, 0, predlogi.length);
		System.arraycopy(particles, 0, result, predlogi.length, particles.length);
		System.arraycopy(special, 0, result, predlogi.length + particles.length, special.length);
		
		return result;
	}
	
	public static boolean isSpecialSymbol(String word)
	{
		for (String symbol : getAllSpicialSymbols()) 
		{
			if (word.equalsIgnoreCase(symbol)) 
			{
				return true;
			}
		}
		
		return false;
	}
	
}
