package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Scanner;


public class Run {
	public static void main(String args[])
	{
		Scanner in =new Scanner (System.in);
		System.out.println("Enter the path");
		String path;
		Double total=0.0;
		long start,end;
		//path=in.next();
		//AVLTree avlt=new AVLTree();
		HMap HM=new HMap();
		TMap TM=new TMap();
		int cnt=0;
		NumberFormat formatter = NumberFormat.getNumberInstance();
		formatter.setMaximumFractionDigits(2);
		//Implementation impl=new Implementation();
		BufferedReader reader;
		try {
			//reader = new BufferedReader(new FileReader(path));

			reader = new BufferedReader(new FileReader("product.txt"));

			String line = null;
			start=System.currentTimeMillis();
			while ((line = reader.readLine()) != null)
			{
				//System.out.println(++cnt);
				String[] parts = line.split(" ");
				if (line.trim().contains("FindMinPrice 7153")) {
					System.out.println();
				}
				int ch=0;
				//Snippet is executed when Insert keyword is access from the file
				if(parts[0].equalsIgnoreCase("Insert"))
				{
					
					Dictionary data=new Dictionary();
					data=TM.InsertStrtoData(line);
					TMValue vData=TM.exist(data.ID);
					if(vData==null)
					{
						HM.insert(data);
						total=total+TM.insert(data);
					}
					else
					{
						if(data.name.isEmpty())
						{
							data.name=vData.name;
							HM.delete(data.ID,vData);
							HM.insert(data);
							TM.insert(data);
						}
						else
						{
							HM.delete(data.ID,vData);
							//HM.insertdelete(data,vData);
							HM.insert(data);
							TM.insert(data);
						}
					}
				}
				else if(parts[0].equalsIgnoreCase("Find"))
				{
					Dictionary data=new Dictionary();
					long ID=Long.parseLong(parts[1]);
					total=total+TM.Find(ID);
				}
				else if(parts[0].equalsIgnoreCase("Delete"))
				{
					Dictionary data=new Dictionary();
					long ID=Long.parseLong(parts[1]);
					TMValue vData=TM.exist(ID);
					//System.out.println("Vdata:"+vData.name);
					if(vData!=null)
					{
						//HM.delete(ID,vData);
						HM.delete(ID,vData);
						
						total=total+TM.delete(ID);
					}
					else
					{
						System.out.println(ID+" is not present");
					}
				}
				else if(parts[0].equalsIgnoreCase("FindMinPrice"))
				{
					long n=Long.parseLong(parts[1]);
					//System.out.println("inside find");
					//System.out.println("Min value :"+avlt.FindMinPrice(str));
					total=total+HM.FindMinPrice(n);
				}
				else if(parts[0].equalsIgnoreCase("FindMaxPrice"))
				{
					long n=Long.parseLong(parts[1]);
					//System.out.println("inside find");
					//System.out.println("Min value :"+avlt.FindMinPrice(str));
					total=total+HM.FindMaxPrice(n);
				}
				else if(parts[0].equalsIgnoreCase("FindPriceRange"))
				{
					long n=Long.parseLong(parts[1]);
					float low=Float.parseFloat(parts[2]);
					float high=Float.parseFloat(parts[3]);
					//System.out.println("The price range :"+ avlt.FindPriceRange(parts[1],low,high));
					total=total+HM.FindPriceRange(n,low,high);
					
				}
				else if(parts[0].equalsIgnoreCase("PriceHike"))
				{
					long l= Long.parseLong(parts[2]);
					long h=Long.parseLong(parts[3]);
					float r=Integer.parseInt(parts[4]);
					total=total+TM.PriceHike(l,h,r,HM);
					//total=total%997;
					//total=Double.parseDouble(formatter.format(total));
							
				}
				
				System.out.println(line + "->" + total);
				
			}
			end=System.currentTimeMillis();
			
			System.out.println("The total is :"+ total);
			System.out.print("The time is ");
			System.out.println(end-start);
			/*System.out.println("Treemap");
			TM.print();
			System.out.println("Hashmap");
			HM.print();*/
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}
