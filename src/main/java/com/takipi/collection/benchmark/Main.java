package com.takipi.collection.benchmark;

import static com.takipi.collection.benchmark.CsvReport.buildAggregateReport;
import static com.takipi.collection.benchmark.FileUtils.clearFile;
import static com.takipi.collection.benchmark.JavaAntLauncher.testClass;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		if (args.length >= 1)
		{
			Properties.MAX_MEMORY = args[0];
		}
		
		clearFile(Properties.allTestsFile);
		clearFile(Properties.csvReport);
		runAllTests();
		buildAggregateReport();
	}
	
	public static void runAllTests()
	{
		for (int i = 0; i < 5; i++)
		{
			testClass("java.util.ArrayList");
			testClass("java.util.TreeSet");
			testClass("java.util.HashSet");
			testClass("java.util.HashMap");
			
			//            testClass("scala.collection.mutable.ArrayBuffer");
			
			//            testClass("com.google.common.collect.TreeMultimap");
			//            testClass("com.google.common.collect.TreeMultiset");
			
			testClass("gnu.trove.list.array.TIntArrayList");
			testClass("gnu.trove.list.linked.TIntLinkedList");
			testClass("gnu.trove.map.hash.TIntIntHashMap");
			testClass("gnu.trove.set.hash.TIntHashSet");
		}
	}
}
