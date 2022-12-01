package model;

import tests.TreeNode;
import tests.Expression;
import tests.Operand;
import tests.Operator;
import tests.SLLNode;

public class TreeUtilities {

	/* Method 1: getInfixTree() */
	public TreeNode<Expression> getInfixTree(SLLNode<Expression> expression) {
		
		SLLNode<TreeNode<Expression>> stack = new SLLNode<TreeNode<Expression>>(new TreeNode<Expression>(expression.getElement()), null);
		TreeNode<Expression> root = getInfixTreeHelper(expression, stack);
		return root;
		
	}
	
	private TreeNode<Expression> getInfixTreeHelper(SLLNode<Expression> expression, SLLNode<TreeNode<Expression>> stack) {
		
		SLLNode<TreeNode<Expression>> head = stack;
		SLLNode<TreeNode<Expression>> temp = null;
		TreeNode<Expression> root = head.getElement();
		
		if (expression != null) {
			
			if (expression.getElement() instanceof Operand) {
				temp = head;
				head = new SLLNode<TreeNode<Expression>>(new TreeNode<Expression>(expression.getElement()), temp);

			}
			else {
				root = new TreeNode<Expression>(expression.getElement());
				
				TreeNode<Expression> operand2 = head.getElement(); // pop first element out of stack
				temp = head;
				head = head.getNext();
				temp.setElement(null);
				
				TreeNode<Expression> operand1 = head.getElement(); // pop first element out of stack
				temp = head;
				head = head.getNext();
				temp.setElement(null);
				
				root.addChild(operand1);
				root.addChild(operand2);
				operand1.setParent(root);
				operand2.setParent(root);
				
				temp = head;
				head = new SLLNode<TreeNode<Expression>>(root, temp);
			}
			root = getInfixTreeHelper(expression.getNext(), head);
		}
		return root;
		
	}
	
	/* Method 2: getInfixSequence() */
	public String getInfixSequence(SLLNode<Expression> expression) {
		String result = "";
		TreeNode<Expression> expressionTree = getInfixTree(expression);
		
		result = getInfixSequenceHelper(expressionTree);
		
		return result;
	}
	
	private String getInfixSequenceHelper(TreeNode<Expression> root) {
		String result = "";
		String temp = "";
		if (root.getElement() instanceof Operand) {
			result = "" + ((Operand) root.getElement()).getValue();
		}
		else {
			result = "" + ((Operator) root.getElement()).getOperator();
		}
		
		temp = result;
		
		if (root.getChildren() != null) {
			result = "(" + getInfixSequenceHelper(root.getChildren().getElement()) + " ";
			result += temp;
			result = result + " " + getInfixSequenceHelper(root.getChildren().getNext().getElement()) + ")";
		}
		
		return result;
	}
}
