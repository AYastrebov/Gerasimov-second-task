package parser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;


public class MyFileReader 
{
	private static String removeExtraSpaces(String s) 
	{
		return s.replaceAll("\\s+", " ");
	}
	
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
		
		return words;
	}
	
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
				words.add(result);
			}
		}
		
		return words;
	}
		
}
