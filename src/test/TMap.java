package test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;


public class TMap {

	
	TreeMap<Long,TMValue> TM=new TreeMap<Long,TMValue>();
	
	public static Dictionary InsertStrtoData(String str)
	{
		Dictionary d=new Dictionary();
		
		//System.out.println("String before split:"+ str);
		String[] parts=str.split(" ");
		String s;
		d.ID=Long.parseLong(parts[1]);
		d.price=Float.parseFloat(parts[2]);
		int i=3;
		while(!parts[i].equals("0"))
		{
			d.name.add(Long.parseLong(parts[i]));
			i++;
			
		}
		return d;
	}
	public TMValue exist(Long key)
	{
		if(TM.containsKey(key))
			return TM.get(key);
		return null;
	}
	public long insert(Dictionary d)
	{
		TMValue value=new TMValue();
		value.price=d.price;
		value.name=d.name;
		if(!TM.containsKey(d.ID))
		{
			TM.put(d.ID, value);
			return 1;
		}
		else
			if(!value.name.isEmpty())
				TM.put(d.ID, value);
			else
			{
				value.name=TM.get(d.ID).name;
				TM.put(d.ID, value);
			}
		return 0;
		
	}
	public float Find(long key)
	{
		if(TM.containsKey(key))
			return TM.get(key).price;
		else
			return 0;
	}
	public long delete(long key)
	{
		ArrayList<Long> namelist=new ArrayList<Long>();
		long val=0;
			namelist=TM.get(key).name;
			for(long i : namelist)
				val=val+i;
			TM.remove(key);
			return val;
		
	}
	public void print()
	{
		Set<Long> s=TM.keySet();
		Iterator it=s.iterator();
		while(it.hasNext())
		{
			long id=(Long) it.next();
			TMValue val=TM.get(id);
			System.out.println(id+" "+val.name+" "+val.price);
		}
	}
	public float PriceHike(long l,long h, float r,HMap hmap)
	{
		SortedMap<Long,TMValue> subtree=TM.subMap(l,h+1);
		
		
		float sum=0;
		Iterator it=subtree.keySet().iterator();
		//HMap hmap=new HMap();
		
		//NumberFormat formatter = NumberFormat.getNumberInstance();
		//formatter.setMaximumFractionDigits(2);
		while(it.hasNext())
		{
			long id=(Long) it.next();
			if(id>=l && id<=h)
			{
				TMValue val=subtree.get(id);
				float oldprice=subtree.get(id).price;
				Dictionary d=new Dictionary();
				d.ID=id;
				d.name=val.name;
				d.price=oldprice;
				float newprice=(oldprice+(oldprice*(r/100)));
				
				val.price=newprice;
				
				float diff=(newprice-oldprice);
				/*if(diff > 998)
				{	
					
					float rem=diff%1000;
					int q=(int) (diff/1000);
					sum=sum+(q*1000);
					//diff=rem;
					diff=Math.abs(rem);
				}*/
				//System.out.println("diff before formating:"+ diff);
					//String di=formatter.format(diff);
					DecimalFormat df = new DecimalFormat("#.00");
					float dif = Float.parseFloat(df.format(diff));
					//System.out.println("Difference is :" +dif);
					//diff=Float.parseFloat(di);
				//System.out.println("ID:"+id+"old price:"+oldprice+"\tnew price:"+newprice+"hike :"+ diff);
				sum=sum+dif;
				//System.out.println("hike :"+ );
				TM.put(id,val);
				hmap.modify(d,newprice);
			}
			
		}
		return sum;
	}
}
