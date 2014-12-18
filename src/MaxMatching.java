import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class MaxMatching
{    
    private final int NIL = 0;
    private final int INF = Integer.MAX_VALUE;
    private ArrayList<Integer>[] Adj; 
    private int[] Pair;
    private int[] Dist;
    private int cx, cy;
 
    public boolean BFS() 
    {
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int v = 1; v <= cx; ++v) 
            if (Pair[v] == NIL) 
            { 
                Dist[v] = 0; 
                queue.add(v); 
            }
            else 
                Dist[v] = INF;
 
        Dist[NIL] = INF;
 
        while (!queue.isEmpty()) 
        {
            int v = queue.poll();
            if (Dist[v] < Dist[NIL]) 
                for (int u : Adj[v]) 
                    if (Dist[Pair[u]] == INF) 
                    {
                        Dist[Pair[u]] = Dist[v] + 1;
                        queue.add(Pair[u]);
                    }           
        }
        return Dist[NIL] != INF;
    }    
    public boolean DFS(int v) 
    {
        if (v != NIL) 
        {
            for (int u : Adj[v]) 
                if (Dist[Pair[u]] == Dist[v] + 1)
                    if (DFS(Pair[u])) 
                    {
                        Pair[u] = v;
                        Pair[v] = u;
                        return true;
                    }               
 
            Dist[v] = INF;
            return false;
        }
        return true;
    }
    public int FindMatching() 
    {
        Pair = new int[cx + cy + 1];
        Dist = new int[cx + cy + 1];
        int matching = 0;
        while (BFS())
            for (int v = 1; v <= cx; ++v)
                if (Pair[v] == NIL)
                    if (DFS(v))
                        matching = matching + 1;
        return matching;
    }
    public void makeGraph(int[] x, int[] y, int E)
    {
        Adj = new ArrayList[cx + cy + 1];
        for (int i = 0; i < Adj.length; ++i)
            Adj[i] = new ArrayList<Integer>();        
        for (int i = 0; i < E; ++i) 
            addEdge(x[i] + 1, y[i] + 1);    
    }
    public void addEdge(int u, int v) 
    {
        Adj[u].add(cx + v);
        Adj[cx + v].add(u);
    }    
    @SuppressWarnings("resource")
	public static void main (String[] args) throws IOException 
    {
        Scanner scan = new Scanner(System.in);        
        MaxMatching mm= new MaxMatching(); 
        BufferedReader reader = new BufferedReader (new InputStreamReader (System.in));
        //String filename = reader.readLine();
        reader = new BufferedReader (new InputStreamReader (new FileInputStream ("c:/vinodh/inp3.txt")));
        String line = reader.readLine();
        System.out.println("Executing...");
        String[] temp = line.split(" ");
        int E = Integer.parseInt(temp[2]);
        int[] x = new int[E];
        int[] y = new int[E];
        mm.cx = 0;
        mm.cy = 0;
        line = reader.readLine ();
        int count = 0;
        while (line != null) 
        {
        	//System.out.println(line);
       	 	String[] match = line.split(" ");
       	 	x[count] = Integer.parseInt(match[0])-1;
       	 	y[count] = Integer.parseInt(match[1])-1;
       	 	mm.cx = Math.max(mm.cx, x[count]);
       	 	mm.cy = Math.max(mm.cy, y[count]);
       	 	count = count+1;
       	 	line = reader.readLine ();
       	 	//System.out.println(count);
        }
        mm.cx += 1;
        mm.cy += 1;
        mm.makeGraph(x, y, E);
        System.out.println("Matches : "+ mm.FindMatching());            
    }    
}