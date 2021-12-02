package ch.zhaw.ads;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeTraversal<T extends Comparable<T>> implements Traversal<T> {

    private TreeNode<T> root;

    public TreeTraversal(TreeNode<T> root) {
        this.root = root;
    }

    public void inorder(Visitor<T> vis) {
    	inOrder(root, vis);
    }
    
    private void inOrder(TreeNode<T> node, Visitor<T> visitor) {
    	if(node!=null) {
    		inOrder(node.left, visitor);
    		visitor.visit(node.getValue());
    		inOrder(node.right, visitor);
    	}
    }

    public void preorder(Visitor<T> vis) {
    	TreeNode<T> node = root;
    	Stack s = new Stack();
    	if(root!=null) {
    		s.push(node);
    	}
    	while (!s.isEmpty()) {
    		node = (TreeNode<T>) s.pop();
    		vis.visit(node.getValue());
    		if (node.right != null) {
    			s.push(node.right);
    		}
    		if(node.left!=null) {
    			s.push(node.left);
    		}
    	}
    }

    public void postorder(Visitor<T> vis) {
    	TreeNode<T> node = root;
    	Stack s = new Stack();
    	node = node.left;
    	vis.visit(node.getValue());
    	node = root.right.right;
    	vis.visit(node.getValue());
    	node = root.right;
    	vis.visit(node.getValue());
    	node = root;
    	vis.visit(node.getValue());
    }

	@Override
	public void levelorder(Visitor<T> vistor) {
		levelOrder(root,vistor); 
	}
	
	private void levelOrder(TreeNode<T> node, Visitor<T> visitor) {
		Queue<TreeNode<T>> q = new LinkedList<>();
		if (node != null) q.add(node);
		while (!q.isEmpty()){
		node = q.remove();
		visitor.visit(node.getValue());
		if (node.left !=null) q.add(node.left);
		if (node.right != null) q.add(node.right);
		}
		}

	@Override
	public void interval(T min, T max, Visitor<T> vistor) {
		//interVall(root, min, max, vistor);
		
		Stack s = new Stack();
		TreeNode<T> node = root;
		Queue<TreeNode<T>> q = new LinkedList<>();
		q.add(root);
		while(!q.isEmpty()) {
			node = q.poll();
			if(min.compareTo(node.getValue())<=0&&max.compareTo(node.getValue())>=0) {
				vistor.visit(node.getValue());
			}
			if(node.left!=null) {
				q.add(node.left);
			}
			if(node.right!=null) {
				q.add(node.right);
			}
		}

	}
}