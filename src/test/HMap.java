package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;


public class HMap {

	TreeMap<Long,ArrayList<HMValue>> HM=new TreeMap<Long,ArrayList<HMValue>>();
	
	public void insert(Dictionary d)
	{
		HMValue val=new HMValue();
		val.id=d.ID;
		val.price=d.price;
		ArrayList<Long> list=d.name;
		for(long i :list)
		{
			if(HM.containsKey(i))
			{
				ArrayList<HMValue> hlist=HM.get(i);
				if(!hlist.contains(val))
				{
					hlist.add(val);
					HM.put(i,hlist);
				}
			}
			else
			{
				ArrayList<HMValue> hlist=new ArrayList<HMValue>();
				hlist.add(val);
				HM.put(i,hlist);
			}
				
		}
	}
	public int modify(Dictionary olddata,float newprice)
	{
		//flag to check whether the delet function is done
		int flag=0;
		//Iterator for the old name
		
		Iterator<Long> it=olddata.name.iterator();
			
		//to save the list of each name key in the hash map
		
		while(it.hasNext())
		{
			long n=it.next();
			
			
			ArrayList<HMValue> vlist=HM.get(n);
			
			for(HMValue h:vlist)
			{
				if(h.id==olddata.ID)
				{
					h.price=newprice;
					//vlist.add(h);
					break;
				}
			}
			HM.put(n, vlist);
		}
		
		if(flag==1)
		{
			return 1;
			
		}return 0;
	}

	public long delete(long key, TMValue data)
	{
		Iterator<Long> it=data.name.iterator();
		long sum=0;
		
	//	System.out.println("Old id and price"+val.id+" "+val.price);
		
		//to save the list of each name key in the hash map
		
		while(it.hasNext())
		{
			int index=0,flag=0;
			ArrayList<HMValue> vlist=new ArrayList<HMValue>();
			long n=it.next();
			//System.out.println("key is "+n);
			sum=sum+n;
			vlist=HM.get(n);
			if(vlist!=null)
			{
			for(HMValue h:vlist)
			{
				//System.out.println("inst :"+h.id+" "+h.price);
				if(h.id==key)
				{
					flag=1;
					break;
				}
				index++;
			}
			if(flag==1)
				vlist.remove(index);
			if(vlist.size()!=0)
			{
				HM.put(n, vlist);
			}
			else
			{
				HM.remove(n);
			}
			}
			//HM.put(n, vlist);
		}
		return sum;
	}
	
	public float FindMinPrice(long n)
	{
		float min=Float.MAX_VALUE;
		if(HM.containsKey(n))
		{
		ArrayList<HMValue> vlist=HM.get(n);
		
		HMValue val=new HMValue();
		
		Iterator it=vlist.iterator();
		while(it.hasNext())
		{
			val=(HMValue) it.next();
			if(min>val.price)
				min=val.price;
		}
		}
		if(min==Float.MAX_VALUE)
			return 0;
		else
			return min;
	}
	public float FindMaxPrice(long n)
	{
		float min=0;
	
		if(HM.containsKey(n))
		{
		ArrayList<HMValue> vlist=HM.get(n);
		
		HMValue val=new HMValue();
		
		Iterator it=vlist.iterator();
		while(it.hasNext())
		{
			val=(HMValue) it.next();
			if(min<val.price)
				min=val.price;
		}
		}
		return min;
	}
	public int FindPriceRange(long n,float low,float high)
	{
		int sum=0;
		if(HM.containsKey(n))
		{
		ArrayList<HMValue> vlist=HM.get(n);
		
		HMValue val=new HMValue();
		
		Iterator it=vlist.iterator();
		while(it.hasNext())
		{
			val=(HMValue) it.next();
			if((val.price>=low) && (val.price<=high))
				sum=sum+1;
		}
		}
		return sum;
	}
				
				
	
	public void print()
	{
		System.out.println("in side print");
		Set str=HM.keySet();
		//String[] s=(String[]) str.toArray();
		Iterator it=str.iterator();
		while(it.hasNext())
		{
			long s=(Long) it.next();
			ArrayList l=(ArrayList) HM.get(s);
			Iterator i=l.iterator();
			System.out.print(s);
			while(i.hasNext())
			{
				HMValue n=(HMValue) i.next();
				System.out.print(":"+n.id+" "+n.price);
			}
			System.out.println();
			
		}
	}
	
	public void print(ArrayList<HMValue> list)
	{
		Iterator it=list.iterator();
		while(it.hasNext())
		{
			HMValue val=(HMValue) it.next();
			System.out.println(val.id+" "+val.price);
		}
	}
	
}
