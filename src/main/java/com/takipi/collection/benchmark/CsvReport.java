package com.takipi.collection.benchmark;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvReport
{
	public static void buildAggregateReport() throws IOException
	{
		List<Bean> list = readFromFile();
		Map<String, List<Bean>> map = new HashMap<String, List<Bean>>();
		
		groupByName(list, map);
		
		// addReportHeader();
		//  FileUtils.appendStringToFile(Properties.csvReport, "Class,minCnt,maxCnt,avgCnt,minTime,maxTime,avgTime");
		
		saveToFinalReport(map);
	}
	
	private static void saveToFinalReport(Map<String, List<Bean>> map)
	{
		for (String key : map.keySet())
		{
			// name, minCnt, maxCnt, avgCnt, avgTime
			
			int size = map.get(key).size();
			
			if (size < 1)
			{
				return;
			}
			
			long minCnt = map.get(key).get(0).getCnt();
			long maxCnt = minCnt;
			long sumCnt = 0;
			long minTime = map.get(key).get(0).getTime();
			long maxTime = minTime;
			long sumTime = 0;
			
			for (Bean bean : map.get(key))
			{
				sumCnt += bean.getCnt();
				sumTime += bean.getTime();
				
				if (bean.getCnt() <= minCnt)
				{
					minCnt = bean.getCnt();
				}
				
				if (bean.getCnt() >= maxCnt)
				{
					maxCnt = bean.getCnt();
				}
				
				if (bean.getTime() <= minTime)
				{
					minTime = bean.getTime();
				}
				
				if (bean.getTime() >= maxTime)
				{
					maxTime = bean.getTime();
				}
			}
			
			String result =
					String.format("%s,%d,%d,%d,%d,%d,%d", key, minCnt, maxCnt, sumCnt / size, minTime, maxTime,
							sumTime / size);
			FileUtils.appendStringToFile(Properties.csvReport, result);
		}
	}
	
	private static void groupByName(List<Bean> list, Map<String, List<Bean>> map)
	{
		for (Bean bean : list)
		{
			if (map.containsKey(bean.getName()))
			{
				map.get(bean.getName()).add(bean);
			}
			else
			{
				ArrayList<Bean> localList = new ArrayList<Bean>();
				localList.add(bean);
				map.put(bean.getName(), localList);
			}
		}
	}
	
	private static List<Bean> readFromFile() throws IOException
	{
		List<Bean> list = new ArrayList<Bean>();
		
		BufferedReader br = new BufferedReader(new FileReader(Properties.allTestsFile));
		
		try
		{
			String line = br.readLine();
			
			while (line != null)
			{
				list.add(parseLine(line));
				line = br.readLine();
			}
		}
		finally
		{
			br.close();
		}
		
		return list;
	}
	
	private static Bean parseLine(String line)
	{
		Bean bean = new Bean();
		
		String[] strings = line.split(",");
		bean.setName(strings[0]);
		bean.setCnt(Long.parseLong(strings[1]));
		bean.setTime(Long.parseLong(strings[2]));
		
		return bean;
	}
	
	public static class Bean
	{
		private String name;
		long cnt;
		long time;
		
		public String getName()
		{
			return name;
		}
		
		public void setName(String name)
		{
			this.name = name;
		}
		
		public long getCnt()
		{
			return cnt;
		}
		
		public void setCnt(long cnt)
		{
			this.cnt = cnt;
		}
		
		public long getTime()
		{
			return time;
		}
		
		public void setTime(long time)
		{
			this.time = time;
		}
	}
}
