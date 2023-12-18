package AIs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.Pair;



public class Memorization {

	Set<String> noPath;;
	Set<String> blockTable;;
	HashMap<String, Integer> IntTable;
	HashMap<String, Boolean> boolTable = new HashMap<>();
	public Memorization() {
		// TODO Auto-generated constructor stub
		this.noPath = new HashSet<String>();
		this.blockTable = new HashSet<String>();
		this.IntTable = new HashMap<>();
		this.boolTable = new HashMap<>();
	}
	
	
	public boolean ContainsBool(String key) {
		
		return this.boolTable.containsKey(key);
	}
	
	
	public boolean getBool(String key) {
		
		return this.boolTable.get(key);
	}
	
	public void setBool(String key,boolean b) {
		
		 this.boolTable.put(key,b);
	}
	
	public boolean ContainsInt(String key) {
		
		return this.IntTable.containsKey(key);
	}
	
	public int getInt(String key) {
		return this.IntTable.get(key);
	}
	
	public void plusInt(String key, int i) {
		if(this.ContainsInt(key)) {
			
			this.IntTable.put(key, this.getInt(key)+i);
		}else {
			
			this.IntTable.put(key, i);
		}
		 
	}
	
	
	public void setInt(String key, int i) {
		 this.IntTable.put(key, i);
		
		
	}
	
	
	public void setNoPath(List<Pair<Integer,Integer>> visited, int x_target, int y_target) {
		for(Pair<Integer,Integer> u : visited) {
			String s= u.m_a+";"+u.m_b+";"+x_target+";"+y_target;
			this.noPath.add(s);
		}
		
	}
	
	public boolean thereIsPath(int x1, int y1, int x2, int y2) {
		String s= x1+";"+y1+";"+x2+";"+y2;
		return !this.noPath.contains(s);
	
		
	}

	public void setblockTable(String s) {
		this.blockTable.add(s);
	}
	
	public boolean containblockTable(String s) {
		return this.blockTable.contains(s);
	}
		
	public static void testNoPath() {
		Memorization me = new Memorization();
		List list = new ArrayList<>();
		list.add(new Pair(1,1));
		list.add(new Pair(3,1));
		list.add(new Pair(1,3));
		me.setNoPath(list,30,30);
		System.out.println(me.thereIsPath(1, 1, 30, 30));
		System.out.println(me.thereIsPath(1, 3, 30, 30));
		System.out.println(me.thereIsPath(3, 3, 30, 30));
		
		
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		testNoPath();
	}

}
