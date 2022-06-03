//relabel flux converters given their Post Order Traversal labeling within a Perfect Binary Tree
import java.util.Arrays;

public class Solution2b {
	
	//Node implementation
	static class Node {
		int val, label;
		Node lNode, rNode;
		
		public Node() {
			this(0);
		}
		
		public Node(int val) {
			label = -1;
			lNode = null;
			rNode = null;
			this.val = val;
		}
	}
	
	//Perfect Binary Tree implementation
	static class PerfectTree {
		private Node root;
		private int numNodes, labelAcc = 1;
		//an array for keeping track of the parent node for each flux converter label
		private int[] labelAssigns;
		
		public PerfectTree(int height) {
			
			numNodes = (int)Math.pow(2, height) - 1;
			labelAssigns = new int[numNodes];
			
			//root = new Node(1);
			//TraverseCreate(root, 1);
			root = TraverseCreate(1);
			TraverseLabel(root);
		}
		
		//access the array used for solution()
		public int GetLabelAssigns(int label) {
			if(label < numNodes) return labelAssigns[label - 1];
			else return -1;
		}
		
		//Create a perfect binary tree by recursion
		private Node TraverseCreate(int val) {
			Node n = new Node(val);
			
			if(val * 2 <= numNodes) {
				n.lNode = TraverseCreate(val * 2);
				n.rNode = TraverseCreate((val * 2) + 1);
			}
			
			return n;
		}
		
		//Label the nodes in the tree with their Post Order traversal order label, recursively
		private void TraverseLabel(Node n) {
			//first test to see if we're at an end node.
			if(n.lNode != null) {
				//then traverse left node
				TraverseLabel(n.lNode);
				//then traverse right node
				TraverseLabel(n.rNode);
				//and evaluate the current node
				n.label = labelAcc++;
				
				//fill out labelAssigns array, as traversing after creation would be costly and problematic
				labelAssigns[n.lNode.label - 1] = n.label;
				labelAssigns[n.rNode.label - 1] = n.label;
			}
			else {
				n.label = labelAcc++;
			}
		}
	}
	
	//foobar test point
	public static int[] solution(int h, int[] q) {
		PerfectTree tree = new PerfectTree(h);
		int[] labels = new int[q.length];
		
		for(int i = 0; i < labels.length; i++) {
			labels[i] = tree.GetLabelAssigns(q[i]);
		}
		
		return labels;
	}
	
	public static void main(String[] args) {
		int[] input1 = {19, 14, 28};
		int[] output = solution(5, input1);
		System.out.println(Arrays.toString(output));
		int[] input2 = {7, 3, 5, 1};
		output = solution(3, input2);
		System.out.println(Arrays.toString(output));
	}
}