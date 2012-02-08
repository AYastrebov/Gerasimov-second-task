package parser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Vector;
import java.util.regex.PatternSyntaxException;


public class MyFileReader 
{
	//Убираем лишние пробелы
	private static String removeExtraSpaces(String s) 
	{
		return s.replaceAll("\\s+", " ");
	}
	
	//Получаем содержание файла
	public static Vector<String> getFileContent(File file)
	{
		Vector<String> content = new Vector<String>();
		
		BufferedReader bufferedReader = null;
		String line = null;
        
		try 
		{
			bufferedReader = new BufferedReader(new FileReader(file));
			
			while ((line = bufferedReader.readLine()) != null) 
	        {
				content.add(line);
	        }
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
            try 
            {
                if (bufferedReader != null)
                    bufferedReader.close();
            } 
            catch (IOException ex) 
            {
                ex.printStackTrace();
            }
		}
		
		return content;
	}
	
	//Весь файл в виде строки
	public static String getFileStringRepresentation(Vector<String> fileContent)
	{
		StringBuilder builder = new StringBuilder();
		
		for (String string : fileContent) 
		{
			builder.append(string);
			builder.append(System.getProperty("line.separator"));
		}
		
		return builder.toString();
	}
	
	public static String getFileStringRepresentation(File file)
	{
		return getFileStringRepresentation(getFileContent(file));
	}
	
	//Получаем отформатированные слова
	public static Vector<String> getFormatedWords(File file)
	{
		return getFormatedWords(getStandAloneWords(getFileContent(file)));
	}
	
	public static Vector<String> getFormatedWords(Vector<String> standAloneWords)
	{				
		Vector<String> words = new Vector<String>();
		
		for (String word : standAloneWords) 
		{
			if (!DataManager.isSpecialSymbol(word)) 
			{
				words.add(word.toLowerCase());
			}
		}
		
		words = removeSpecialSymbolsInWords(words);
		words = getInfinitiWordValues(words);
		
		return words;
	}
	
	//Приводим слова к начальной форме
	private static Vector<String> getInfinitiWordValues(Vector<String> words) 
	{
		Vector<String> result = new Vector<String>(words.size());
		MorphAnalyzer mAnalyzer = MorphAnalyzer.getInstance();
		
		for (String word : words) 
		{
			List<String> wordForms = mAnalyzer.getWordBaseForms(word);
			result.add(wordForms.get(0));
		}
		
		return result;
	}

	//Убираем из слов исключаемые символы
	private static Vector<String> removeSpecialSymbolsInWords(
			Vector<String> input) 
			{
		
		Vector<String> words = new Vector<String>(input.size());
		
		for (String word : input) 
		{
			for (String symbol : DataManager.getSpecial()) 
			{
				if (word.contains(symbol)); 
				{
					try 
					{
						word = word.replace(symbol, "");
					} 
					catch (PatternSyntaxException e) 
					{
						word = word.replace("\\" + symbol, "");
					}
				}
				
			}
			if (word.length() > 0) 
			{
				words.add(word);
			}
			
		}
		
		return words;
	}

	//Получаем набор слов
	public static Vector<String> getStandAloneWords(File file)
	{
		return getStandAloneWords(getFileContent(file));
	}
	
	public static Vector<String> getStandAloneWords(Vector<String> fileContent)
	{
		Vector<String> words = new Vector<String>();
		
		for (String line : fileContent) 
		{
			String[] results = removeExtraSpaces(line).split(" ");
			
			for (String result : results) 
			{
				if (result.contains(" ")) 
				{
					result = result.replace(" ", "");
				}
				words.add(result);
			}
		}
		
		return words;
	}
		
}
