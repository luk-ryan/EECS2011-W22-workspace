package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.TreeNode;
import model.TreeUtilities;

public class TestGeneralTrees {
	
	@Test
	public void test_general_trees_construction() {
		TreeNode<String> agnarr = new TreeNode<>("Agnarr");
		TreeNode<String> elsa = new TreeNode<>("Elsa");
		TreeNode<String> anna = new TreeNode<>("Anna");
		
		agnarr.addChild(elsa);
		agnarr.addChild(anna);
		elsa.setParent(agnarr);
		anna.setParent(agnarr);
		
		assertNull(agnarr.getParent());
		assertTrue(agnarr == elsa.getParent());
		assertTrue(agnarr == anna.getParent());
		assertTrue(agnarr.getChildren().length == 2);
		assertTrue(agnarr.getChildren()[0] == elsa);
		assertTrue(agnarr.getChildren()[1] == anna);
	}

	@Test
	public void test_general_trees_construction_2() {
		TreeNode<String> agnarr = new TreeNode<>("Agnarr");
		TreeNode<String> elsa = new TreeNode<>("Elsa");
		TreeNode<String> anna = new TreeNode<>("Anna");
		
		agnarr.addChild(elsa);
		agnarr.addChild(anna);
		elsa.setParent(agnarr);
		anna.setParent(agnarr);
		agnarr.removeChildAt(0);
		
		assertNull(agnarr.getParent());
		assertNull(elsa.getParent());
		assertTrue(agnarr == anna.getParent());
		assertTrue(agnarr.getChildren().length == 1);
		assertTrue(agnarr.getChildren()[0] == anna);
	}
	
	@Test
	public void test_general_trees_depths() {
		TreeNode<String> david = new TreeNode<>("David");
		TreeNode<String> ernesto = new TreeNode<>("Ernesto");
		TreeNode<String> chris = new TreeNode<>("Chris");
		TreeNode<String> elsa = new TreeNode<>("Elsa");
		TreeNode<String> anna = new TreeNode<>("Anna");
		TreeNode<String> shirley = new TreeNode<>("Shirley");
		TreeNode<String> vanessa = new TreeNode<>("Vanessa");
		TreeNode<String> peter = new TreeNode<>("Peter");
		
		/* Nodes with depth 1 */
		ernesto.setParent(david); david.addChild(ernesto);
		chris.setParent(david); david.addChild(chris);
		
		/* Nodes with depth 2 */
		elsa.setParent(chris); chris.addChild(elsa);
		anna.setParent(chris); chris.addChild(anna);
		
		/* Nodes with depth 3 */
		shirley.setParent(elsa); elsa.addChild(shirley);
		vanessa.setParent(elsa); elsa.addChild(vanessa);
		peter.setParent(elsa); elsa.addChild(peter);
		
		TreeUtilities<String> u = new TreeUtilities<>();
		assertEquals(0, u.depth(david));
		assertEquals(1, u.depth(ernesto));
		assertEquals(1, u.depth(chris));
		assertEquals(2, u.depth(elsa));
		assertEquals(2, u.depth(anna));
		assertEquals(3, u.depth(shirley));
		assertEquals(3, u.depth(vanessa));
		assertEquals(3, u.depth(peter));
	}

	@Test
	public void test_general_trees_heights() {
		TreeNode<String> david = new TreeNode<>("David");
		TreeNode<String> ernesto = new TreeNode<>("Ernesto");
		TreeNode<String> chris = new TreeNode<>("Chris");
		TreeNode<String> elsa = new TreeNode<>("Elsa");
		TreeNode<String> anna = new TreeNode<>("Anna");
		TreeNode<String> shirley = new TreeNode<>("Shirley");
		TreeNode<String> vanessa = new TreeNode<>("Vanessa");
		TreeNode<String> peter = new TreeNode<>("Peter");
		
		/* Nodes with depth 1 */
		ernesto.setParent(david); david.addChild(ernesto);
		chris.setParent(david); david.addChild(chris);
		
		/* Nodes with depth 2 */
		elsa.setParent(chris); chris.addChild(elsa);
		anna.setParent(chris); chris.addChild(anna);
		
		/* Nodes with depth 3 */
		shirley.setParent(elsa); elsa.addChild(shirley);
		vanessa.setParent(elsa); elsa.addChild(vanessa);
		peter.setParent(elsa); elsa.addChild(peter);
		
		TreeUtilities<String> u = new TreeUtilities<>();
		
		/* internal nodes */
		assertEquals(3, u.height(david));
		assertEquals(2, u.height(chris));
		assertEquals(1, u.height(elsa));
		
		/* external nodes */
		assertEquals(0, u.height(ernesto));
		assertEquals(0, u.height(anna));
		assertEquals(0, u.height(shirley));
		assertEquals(0, u.height(vanessa));
		assertEquals(0, u.height(peter));
	}
	
	@Test
	public void test_general_trees_descendants() {
		TreeNode<String> david = new TreeNode<>("David");
		TreeNode<String> ernesto = new TreeNode<>("Ernesto");
		TreeNode<String> chris = new TreeNode<>("Chris");
		TreeNode<String> elsa = new TreeNode<>("Elsa");
		TreeNode<String> anna = new TreeNode<>("Anna");
		TreeNode<String> shirley = new TreeNode<>("Shirley");
		TreeNode<String> vanessa = new TreeNode<>("Vanessa");
		TreeNode<String> peter = new TreeNode<>("Peter");
		
		/* Nodes with depth 1 */
		ernesto.setParent(david); david.addChild(ernesto);
		chris.setParent(david); david.addChild(chris);
		
		/* Nodes with depth 2 */
		elsa.setParent(chris); chris.addChild(elsa);
		anna.setParent(chris); chris.addChild(anna);
		
		/* Nodes with depth 3 */
		shirley.setParent(elsa); elsa.addChild(shirley);
		vanessa.setParent(elsa); elsa.addChild(vanessa);
		peter.setParent(elsa); elsa.addChild(peter);
		
		TreeUtilities<String> u = new TreeUtilities<>();
		TreeNode<String>[] output = u.descendants(david);
		TreeNode<String>[] output1 = u.descendants(chris);
		TreeNode<String>[] output2 = u.descendants(elsa);
		TreeNode<String>[] output3 = u.descendants(anna);

	}
}
