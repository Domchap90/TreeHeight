package ExamQuestions;

import java.util.ArrayDeque; //double ended queue more efficient than linked list.
import java.util.Queue;

public class Node {
	private Node left = null, right = null;

	public Node(Node left, Node right) {
		this.left = left;
		this.right = right;

	}

	public int getTreeHeightRecur() {
		// recursive method: base case child nodes are null
		if (left == null && right == null) {
			System.out.println("base case");
			return 0;
		} else if (left != null && right != null) {
			System.out.println("both child nodes non zero");
			return 1 + (Math.max(left.getTreeHeightRecur(), right.getTreeHeightRecur()));
		} else if (left == null && right != null) {
			System.out.println("right child node non zero");
			return 1 + right.getTreeHeightRecur();
		} else {
			System.out.println("left child node non zero");
			return 1 + left.getTreeHeightRecur();
		}
	}

	public int getTreeHeightIter(Node node) { // iterative method: using Queue with poll

		if (left == null && right == null) {
			System.out.println("base case");
			return 0;
		}
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(node);

		Node frontQueue = null;
		int height = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				frontQueue = queue.poll();
				if (frontQueue.left != null) {
					queue.add(frontQueue.left);
				}
				if (frontQueue.right != null) {
					queue.add(frontQueue.right);
				}
			}

			height++;
		}
		return height - 1; // 1 is taken away because the height is the number of edges from the root to
							// the leaf NOT number of levels of nodes.

	}
	public static void main(String[] args) {
		// root node has no parent nodes
		// leaf node has no child nodes
		Node leaf1 = new Node(null, null);
		Node leaf2 = new Node(null, null);
		Node branch1 = new Node(leaf1, null);
		Node branch2 = new Node(leaf2, null);
		Node root = new Node(branch1, branch2);
		System.out.println(root.getTreeHeightRecur());
		System.out.println(root.getTreeHeightIter(root));
	}

}
