package com.li.DN_HashMap;

import java.util.HashMap;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        DNMap<String,String> dnmap= new DNHashMap<String,String>();
        
        Long t1=System.currentTimeMillis();
        for(int i=0;i<500;i++){
        	dnmap.put("key"+i, "value"+i);
        }
        
        for(int i=0;i<500;i++){
        	System.out.println("key: "+i+"	value:"+dnmap.get("key"+i));
        }
        Long t2=System.currentTimeMillis();
        System.out.println("耗时"+(t2-t1));
        System.out.print(dnmap.size());
//        System.out.println("-----------------------HashMap------------------------------");
//        Map<String,String> map= new HashMap<String,String>();
//        Long t3=System.currentTimeMillis();
//        for(int i=0;i<10000;i++){
//        	map.put("key"+i, "value"+i);
//        }
//        for(int i=0;i<10000;i++){
//        	System.out.println("key: "+i+"	value:"+map.get("key"+i));
//        }
//        Long t4=System.currentTimeMillis();
//        System.out.println("耗时"+(t4-t3));
	}

}
