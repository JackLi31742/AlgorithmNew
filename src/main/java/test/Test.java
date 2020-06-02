package test;

import design.MyHashSet;

public class Test {

	public static void main(String[] args) {
//		int a=-897;
//		System.out.println(a%100);
//		System.out.println(a%100/10);
		
		MyHashSet hashSet = new MyHashSet();
		hashSet.add(1); 
		hashSet.add(2); 
		System.out.println(hashSet.contains(1));;
		System.out.println(hashSet.contains(3));;
		hashSet.add(2);
		System.out.println(hashSet.contains(2));;
		hashSet.remove(2);
		System.out.println(hashSet.contains(2));;
		
		System.out.println(hashSet);
	}
}
