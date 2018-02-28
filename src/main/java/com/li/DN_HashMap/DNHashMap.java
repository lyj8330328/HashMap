package com.li.DN_HashMap;

import java.util.ArrayList;
import java.util.List;

public class DNHashMap<K,V> implements DNMap<K, V> {

	private static int defaultLength=16;
	
	private static double defaultLoader=0.75;
	
	private Entry<K,V>[] table=null;
	
	private int size=0;
	
	@SuppressWarnings("unchecked")
	public DNHashMap(int Length,double  Loader){
		defaultLength=Length;
		defaultLoader=Loader;
		table=new Entry[defaultLength];
	}
	
	public DNHashMap(){
		this(defaultLength,defaultLoader);
	}
	
	public V put(K k, V v) {
		//要判断size是否达到了一个扩容的标准
		if(size>=defaultLength*defaultLoader){
			up2size();
		}
		//1.创建一个hash函数，根据key和hash函数算出数组下标
		int index=getIndex(k);
		Entry<K,V> entery=table[index];
		
		if(entery==null){
			//如果为null,说明table的index位置上没有元素
			table[index]=newEntery(k,v,null);
			size++;
		}
		else{
			//如果index位置不为空，说明有元素，要进行替换。然后next指针指向老数据
			table[index]=newEntery(k,v,entery);
		}
		return table[index].getValue();
	}
	
	private Entry<K,V> newEntery(K k,V v, Entry<K,V> next){
		return new Entry<K, V>(k,v,next);
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	private void up2size(){
		Entry<K,V>[] newTable=new Entry[2*defaultLength];
	  	
	  	//创建的新数组要包含原数组的元素
	}
	
	@SuppressWarnings("unused")
	private void againHash(Entry<K,V>[] newTable){
		List <Entry<K,V>> list=new ArrayList<Entry<K,V>>();
		for(int i=0;i<table.length;i++){
			if(table[i]==null){
				continue;
			}
			findEntryByNext(table[i],list);
		}
		if(list.size()>0){
			//要进行一个新数组的再散列
			size=0;
			defaultLength=defaultLength*2;
			table=newTable;
			
			for(Entry<K,V> entry :list){
				if(entry.next!=null){
					entry.next=null;
				}
				put(entry.getKey(),entry.getValue());
			}
			
		}
	}
	
	private void findEntryByNext(Entry<K,V> entry,List <Entry<K,V>> list){
		if(entry!=null&&entry.next!=null){
			list.add(entry);
			findEntryByNext(entry.next,list);//重点
		}
		else{
			list.add(entry);
		}
	}
    private int getIndex(K k){
    	int m=defaultLength;
    	
    	int index=k.hashCode()%m;
    	return index>0?index:-index;
    }
	public V get(K k) {
		//1.创建一个hash函数，根据key和hash函数算出数组下标
		int index=getIndex(k);
		if(table[index]==null){
			return null;
		}
		// TODO Auto-generated method stub
		return findValueByEqualKey(k,table[index]);
	}
	
	public V findValueByEqualKey(K k,Entry<K,V> entry){
		if(k==entry.getKey()||k.equals(entry.getKey())){
			return entry.getValue();
		}
		else{
			if(entry.next!=null){
			  return findValueByEqualKey(k,entry.next);
			}
		}
		return null;
	}
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
	
	class Entry<K,V> implements com.li.DN_HashMap.DNMap.Entry<K,V>{

		K k;
		
		V v;
		
		Entry<K,V> next;
		
		public Entry(K k,V v, Entry<K,V> next){
			this.k=k;
			this.v=v;
			this.next=next;
		}
		public K getKey() {
			// TODO Auto-generated method stub
			return k;
		}

		public V getValue() {
			// TODO Auto-generated method stub
			return v;
		}
		
	}

}
