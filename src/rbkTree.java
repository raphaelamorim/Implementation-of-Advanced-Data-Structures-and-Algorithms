import java.lang.Math;
import java.io.*;
import java.util.*;

/*** @author rbk ***/
// Program to illustrate effect of calling height/depth recursively from each node of a tree
public class rbkTree {
    static long startTime, endTime, elapsedTime;
    static int phase = 0;

    public class binaryTree {
    	int data, h, d;
    	boolean seen, isLeft;
    	binaryTree left, right, parent;

    	binaryTree(int x) { data = x;  left = null;  right = null;  parent = null; }

	int depth() {
	    int dep = 0;  
	    binaryTree t = this;
	    while(t.parent != null) { 
		dep++; 
		t = t.parent; 
	    }
	    return dep;
	}

	int height(binaryTree t) {  // return stored height
	    if (t == null) { return -1; }
	    else return t.h;
	}

	// Helper function to mark a node as seen and push it into stack
	void push(Stack<binaryTree> S, binaryTree t) {
	    if(t == null) return;
	    t.seen = false;
	    S.push(t);
	}

	/* height is implemented with a stack to avoid stack overflow error
	 * Intended code: 
	       height(t) {
	           if(t == null) return -1;
		   else return Math.max(height(t.left), height(t.right)) + 1;
	       }
	*/

	int height() {  // returns height of node
	    int lh, rh;
	    Stack<binaryTree> S = new Stack<>();
	    seen = false;
	    push(S, left);
	    push(S, right);
	    while(!S.empty()) {
		binaryTree cur = S.peek();
		if(cur.seen) {
		    lh = height(cur.left);
		    rh = height(cur.right);
		    cur.h = Math.max(lh, rh) + 1;
		    S.pop();
		} else {
		    cur.seen = true;
		    push(S, cur.left);
		    push(S, cur.right);
		}
	    }
	    h = Math.max(height(left), height(right)) + 1;
	    return h;	
	}
	
	// The tree is visited in level order, using a Queue. Depth and height of each node is computed recursively
	int worst() { 
	    d = 0;  int sum = 0;
	    Queue<binaryTree> bfsQ = new LinkedList<>();
	    bfsQ.add(left);
	    bfsQ.add(right);
	    while(!bfsQ.isEmpty()) {
		binaryTree cur = bfsQ.remove();
		if(cur != null) {
		    cur.d = cur.depth();
		    cur.h = cur.height();
		    sum = Math.max(sum, cur.d+cur.h);
		    bfsQ.add(cur.left);  bfsQ.add(cur.right);
		}
	    }
		    
	    // Add code to check for the sum value of this node too.
	    sum = Math.max(sum, d + h);
	    return sum;
	}
	
	int best() {  // in one traversal, it stores height and depth of all nodes in a subtree; returns height of node
	    int lh, rh, sum = 0;
	    Stack<binaryTree> S = new Stack<>();
	    seen = false;
	    d = 0;
	    push(S, left);
	    push(S, right);
	    while(!S.empty()) {
		binaryTree cur = S.peek();
		if(cur.seen) {
		    lh = height(cur.left);
		    rh = height(cur.right);
		    cur.h = Math.max(lh, rh) + 1;
		    sum = Math.max(sum, cur.d+cur.h);
		    S.pop();
		} else {
		    cur.seen = true;				    
		    cur.d = cur.parent.d + 1;
		    push(S, cur.left);
		    push(S, cur.right);
		}
	    }
		    
	    // Check sum value for itself too
	    h = Math.max(left.h, right.h) + 1;
	    sum = Math.max(sum, d + h);
	    
	    return sum;	
	}
    } // end of binaryTree class


    // If there is a command line argument, it is used as the depth of the tree generated
    public static void main(String[] args) {
    	int maxDepth = 100000;
    	if(args.length > 0) maxDepth = Integer.parseInt(args[0]);
    	rbkTree x = new rbkTree();
    	x.driver(maxDepth);
    }

    void driver(int dep)
    {
    	int nodeNum = 0;
    	// build a long left chain
    	binaryTree T = new binaryTree(nodeNum++);
    	for(int i=0; i<dep; i++) {
	    binaryTree newT = new binaryTree(nodeNum++);
	    newT.left = T;  T.parent = newT;
	    T = newT;
    	}

    	// build a long right chain
    	binaryTree rightT = new binaryTree(nodeNum++);
    	for(int i=1; i<dep; i++) {
	    binaryTree newT = new binaryTree(nodeNum++);
	    newT.right = rightT;  rightT.parent = newT;
	    rightT = newT;
    	}

    	// connect them up
    	T.right = rightT;  rightT.parent = T;


    	// best() and worst() calculate the same function
    	rbkTimer();
    	int sum = T.best();
    	rbkTimer("Best: " + sum + " ");

    	rbkTimer();
    	sum = T.worst();;
    	rbkTimer("Worst: " + sum + " ");
    }

    void rbkTimer(String s) {
    	if(phase == 0) { startTime = System.currentTimeMillis();  phase = 1; }
    	else {
	    endTime = System.currentTimeMillis();  
	    elapsedTime = endTime-startTime;
	    System.out.println(s + "Time: " + elapsedTime + " msec.");
	    phase = 0;
    	}
    }

    void rbkTimer() { rbkTimer(""); }
}