package com.takipi.collection.benchmark;

import gnu.trove.list.array.TIntArrayList;
import gnu.trove.list.linked.TIntLinkedList;
import gnu.trove.map.hash.TIntIntHashMap;
import gnu.trove.set.hash.TIntHashSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import scala.collection.mutable.ArrayBuffer;

import com.google.common.collect.TreeMultimap;
import com.google.common.collect.TreeMultiset;

public class TestCase
{
	
	private static Random random = new Random();
	public static long cnt = 0;
	
	public static void testHashMap()
	{
		HashMap map = new HashMap();
		
		for (;;)
		{
			int key = random.nextInt();
			if (!map.containsKey(key))
			{
				map.put(key, key); //todo double key
				cnt++;
			}
		}
	}
	
	public static void testHashSet()
	{
		testSet(new HashSet());
	}
	
	public static void testTreeSet()
	{
		testSet(new TreeSet());
	}
	
	public static void testArrayList()
	{
		ArrayList list = new ArrayList();
		
		for (;;)
		{
			list.add(random.nextInt());
			cnt++;
		}
	}
	
	public static void testTIntArrayList()
	{
		TIntArrayList list = new TIntArrayList();
		
		for (;;)
		{
			list.add(random.nextInt());
			cnt++;
		}
	}
	
	public static void testTIntLinkedList()
	{
		TIntLinkedList list = new TIntLinkedList();
		
		for (;;)
		{
			list.add(random.nextInt());
			cnt++;
		}
	}
	
	public static void testTIntIntHashMap()
	{
		TIntIntHashMap map = new TIntIntHashMap();
		
		for (;;)
		{
			int key = random.nextInt();
			
			if (!map.containsKey(key))
			{
				map.put(key, key); //todo double key // cannot store null as value!!!
				cnt++;
			}
		}
	}
	
	private static void testSet(Set set)
	{
		for (;;)
		{
			int element = random.nextInt();
			
			if (!set.contains(element))
			{
				set.add(element);
				cnt++;
			}
		}
	}
	
	public static void testTIntHashSet()
	{
		TIntHashSet set = new TIntHashSet();
		
		for (;;)
		{
			int element = random.nextInt();
			
			if (!set.contains(element))
			{
				set.add(element);
				cnt++;
			}
		}
	}
	
	public static void testTreeMultimap()
	{ //todo random list size
		TreeMultimap multimap = TreeMultimap.create();
		
		for (;;)
		{
			int element = random.nextInt();
			
			if (!multimap.containsKey(element))
			{
				multimap.put(element, element); //todo double key
				cnt++;
			}
		}
	}
	
	public static void testTreeMultiset()
	{ //todo random list size
		TreeMultiset multiset = TreeMultiset.create();
		
		for (;;)
		{
			int element = random.nextInt();
			
			if (!multiset.contains(element))
			{
				multiset.add(element);
				cnt++;
			}
		}
	}
	
	public static void testArrayBuffer()
	{ //todo random list size
		ArrayBuffer arrayBuffer = new ArrayBuffer();
		
		for (;;)
		{
			int element = random.nextInt();
			// arrayBuffer
		}
	}
}
