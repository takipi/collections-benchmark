package com.takipi.collection.benchmark;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileUtils
{
	public static void appendStringToFile(String fileName, String string)
	{
		try
		{
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
			out.println(string);
			out.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void clearFile(String fileName)
	{
		File file = new File(fileName);
		PrintWriter writer = null;
		
		try
		{
			writer = new PrintWriter(file);
			writer.print("");
			writer.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
}
