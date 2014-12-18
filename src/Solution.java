import java.io.*;
import java.lang.*;
import java.util.*;
public class Solution {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int size = 0;
        int q = 0;
        int i = 0;
        int m = 0;
        int l = 0;
        List<Integer> inputList = new ArrayList<Integer>();
        boolean run = true;
        while (run){
           if (i == 0) {
               q = Integer.parseInt(line);
           } else if (i == 1) {
               m = Integer.parseInt(line);
           } else {
               String[] split = line.split(" ");
               int count = Integer.parseInt(split[1]);
               int val = Integer.parseInt(split[0]);
               for (int j=0; j < count; j++) {
                   inputList.add(val);
               }
               l++;
               if (l == m) {
            	   run = false;
               }
           }
            
           i++;
           if (run)
           line = br.readLine();
        }
        
        int n = inputList.size();
        List<Integer> outputList = new ArrayList<Integer>();
        for (int k = 1; k <n; k++) {
            int quant = (n*k)/q;
            int index = (int)Math.ceil(quant);
            if (index < n) {
            outputList.add(inputList.get(index-1));
            }
        }
        
        Collections.sort(outputList);
        
        for(int out : outputList) {
            System.out.println(out);
        }
        
    }
}