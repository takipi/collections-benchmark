package com.takipi.collection.benchmark;

import gnu.trove.list.array.TIntArrayList;
import gnu.trove.list.linked.TIntLinkedList;
import gnu.trove.map.hash.TIntIntHashMap;
import gnu.trove.set.hash.TIntHashSet;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

import scala.collection.mutable.ArrayBuffer;

import com.google.common.collect.TreeMultimap;
import com.google.common.collect.TreeMultiset;

public class TestPerformer
{
	public static void main(String[] args) throws Exception
	{
		if (args.length == 0)
		{
			throw new Exception("Provide collection class name as first argument");
			
		}
		
		String className = args[0];
		
		Class clazz = Class.forName(className);
		Object o = null;
		
		try
		{
			o = clazz.newInstance();
		}
		catch (InstantiationException e) // exception for google collections guava
		{
			Method m = clazz.getMethod("create");
			o = m.invoke(null);
		}
		
		if (o instanceof ArrayList)
		{
			testDecorator(ArrayList.class);
			return;
		}
		else if (o instanceof HashMap)
		{
			testDecorator(HashMap.class);
			return;
		}
		else if (o instanceof TreeSet)
		{
			testDecorator(TreeSet.class);
			return;
		}
		else if (o instanceof HashSet)
		{
			testDecorator(HashSet.class);
			return;
		}
		else if (o instanceof TIntArrayList)
		{
			testDecorator(TIntArrayList.class);
			return;
		}
		else if (o instanceof TIntLinkedList)
		{
			testDecorator(TIntLinkedList.class);
			return;
		}
		else if (o instanceof TIntIntHashMap)
		{
			testDecorator(TIntIntHashMap.class);
			return;
		}
		else if (o instanceof TIntHashSet)
		{
			testDecorator(TIntHashSet.class);
			return;
		}
		else if (o instanceof TreeMultimap)
		{
			testDecorator(TreeMultimap.class);
			return;
		}
		else if (o instanceof TreeMultiset)
		{
			testDecorator(TreeMultiset.class);
			return;
		}
		else if (o instanceof ArrayBuffer)
		{
			testDecorator(ArrayBuffer.class);
			return;
		}
		else
		{
			System.out.println("Don't have test for " + className);
			return;
		}
	}
	
	private static void testDecorator(Class clazz) throws NoSuchMethodException, IllegalAccessException,
			InvocationTargetException
	{
		testDecorator("test" + clazz.getSimpleName(), clazz);
	}
	
	private static void testDecorator(String methodName, Class clazz) throws NoSuchMethodException,
			InvocationTargetException, IllegalAccessException
	{
		long startTime = System.currentTimeMillis();
		TestCase.cnt = 0;
		
		Method method = TestCase.class.getMethod(methodName);
		
		try
		{
			method.invoke(null);
		}
		catch (InvocationTargetException e)
		{
			if (!(e.getCause() instanceof OutOfMemoryError))
			{
				throw e;
			}
		}
		
		long endTime = System.currentTimeMillis();
		long time = endTime - startTime;
		
		String result = String.format("%s,%s,%s", clazz.getCanonicalName(), TestCase.cnt, endTime - startTime);
		
		FileUtils.appendStringToFile(Properties.allTestsFile, result);
		
		System.out.println(result);
	}
}
