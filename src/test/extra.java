package test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;


public class extra {

	/*public static void main(String arg[])
	{
		ArrayList<HMValue> list=new ArrayList<HMValue>();
		
		for(long i=0;i<5;i++)
		{
			HMValue val=new HMValue();
			val.id=i;
			val.price=i*10;
			list.add(val);
		}
		
		System.out.println("index");
		Iterator it=list.iterator();
		while(it.hasNext())
		{
			HMValue val=new HMValue();
			val=(HMValue) it.next();
			System.out.print(val.id+"'s index is ");
			System.out.println(val);
			//System.out.println(list.indexOf(val));
		}
	}*/
	public static void main(String arg[])
	{
		float diff=(float) 1000.1233534543;
		
		DecimalFormat df = new DecimalFormat("#.00");
		float dif = Float.parseFloat(df.format(diff));
		System.out.println(dif);
	}
	
}
