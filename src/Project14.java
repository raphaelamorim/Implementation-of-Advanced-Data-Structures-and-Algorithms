import java.util.Scanner;
import java.util.TreeMap;

public class Project14 
{
	static Vertex[] vt;
	static int next;
	static TreeMap<Integer,Vertex> top_order=new TreeMap<Integer,Vertex>();
	static Vertex[] vt_new;
	static int level=0;
	static int[][] grp;
	public static void main(String args[])
	{
		Scanner in=new Scanner(System.in);
		
		vt=new Vertex[in.nextInt()];
		vt_new=new Vertex[vt.length];
		grp=new int[vt.length][vt.length];
		int[][] grp_new=new int[vt.length][vt.length];
		
		for(int i=0;i<vt.length;i++)
		{
			for(int j=0;j<vt.length;j++)
			{
				if(i==j)
				{
					grp[i][j]=0;
					continue;
				}
				grp[i][j]=100000;
			}
		}
		int no_edge=in.nextInt();
		level=no_edge;
		int src=in.nextInt();
		int dst=in.nextInt();
		for(int i=0;i<no_edge;i++)
		{
			int u=in.nextInt();
			int v=in.nextInt();
			int w=in.nextInt();
			
			grp[u-1][v-1]=w;
		}
		//System.out.println("before");
		grp_new=grp.clone();
		//print(grp);
		//setting the d for the source as zero
		
		//checking the assignment
		int x=1;
		int min=100000;
		
		System.out.println("0 INF");
		long start=System.currentTimeMillis();
		do
		{
			if(true)
			{
			System.out.println(x+" "+grp[0][4]);
//			min=grp[0][4];
			}
			grp=Matrix_MinAdd(grp,grp_new);
			x=x+1;
			
		}while(x<=vt.length);
		long end=System.currentTimeMillis();
		System.out.println(end-start);
	}
	
	private static void print(int[][] grp2) {
		// TODO Auto-generated method stub
		for(int i=0;i<grp2.length;i++)
		{
			for(int j=0;j<grp2.length;j++)
				System.out.print(grp2[i][j]+"\t");
			System.out.println();
		}
	}

	private static int[][] Matrix_MinAdd(int[][] A, int[][] B) {
		// TODO Auto-generated method stub
		int n=A.length;
		int[][] C=new int[n][n];
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				C[i][j]=100000;
				for(int k=0;k<n;k++)
				{
					int val=A[i][k]+B[k][j];
					if(C[i][j]>val)
						C[i][j]=val;
				}
			}
		}
		return C;
	}
}