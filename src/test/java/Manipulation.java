import com.demo.treemanipulation.client.*;
import com.demo.treemanipulation.dao.TreeNode;
import com.demo.treemanipulation.exceptionhandling.InputValidationException;
import com.demo.treemanipulation.exceptionhandling.OperationalException;
import com.demo.treemanipulation.treehandler.BinaryTree;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Manipulation {

    private BinaryTree<Integer> bTree;

    @BeforeEach
    void init() {
        bTree = new BinaryTree<Integer>();
    }

    @AfterEach
    void tearDown() {
        bTree = null;
    }

    public List<Integer> mockData() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(null);
        list.add(6);
        list.add(7);
        return list;
    }

    public List<Integer> mockData1() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(null);
        list.add(7);
        return list;
    }

    @Test()
    public void testPreOrderRemoveNode() throws InputValidationException, OperationalException {

        bTree.insertTreeNode(mockData());
        System.out.println(bTree.traverseLevelOrder(bTree.getRoot()));
        int nodeToBeRemoved =2;
        RemoveNodePreOrder preOrderStrategy = new RemoveNodePreOrder(bTree, nodeToBeRemoved);
        RemoveNodeAndGetRootOfSubTrees remove = new RemoveNodeAndGetRootOfSubTrees(preOrderStrategy);
        Vector<TreeNode> vector = remove.removeNode();
        assertEquals(2,vector.size());
        assertEquals(vector.get(0).getVal(), 1);
        assertEquals(vector.get(1).getVal(), 4);
        Vector<Object> vectorSubTreeRoots = remove.getNodeSubTreeRoots(vector);
        assertEquals(vectorSubTreeRoots.get(0), 1);
        assertEquals(vectorSubTreeRoots.get(1), 4);
    }

    @Test()
    public void testPreOrderRemoveNodeNotInTree() throws InputValidationException, OperationalException {

        bTree.insertTreeNode(mockData());
        int nodeToBeRemoved=8;
        RemoveNodeStrategy preOrder = new RemoveNodePreOrder(bTree, nodeToBeRemoved);
        RemoveNodeAndGetRootOfSubTrees remove = new RemoveNodeAndGetRootOfSubTrees(preOrder);
        Vector<TreeNode> vector = remove.removeNode();
        assertEquals(1,vector.size());
        assertEquals(vector.get(0).getVal(), 1);
    }

    @Test()
    public void testPreOrderRemoveNodeWithInvalidKey() throws InputValidationException {

        bTree.insertTreeNode(mockData());
        String nodeToBeRemoved="2";
        RemoveNodeStrategy preOrder = new RemoveNodePreOrder(bTree, nodeToBeRemoved);
        RemoveNodeAndGetRootOfSubTrees remove = new RemoveNodeAndGetRootOfSubTrees(preOrder);
        assertThrows(OperationalException.class, () -> {
            remove.removeNode(); });
    }

    @Test()
    public void testPreOrderRemoveNodeIfTreeIsEmpty() throws InputValidationException {

        String nodeToBeRemoved="2";
        assertThrows(InputValidationException.class, () -> {
            new RemoveNodePreOrder(bTree, nodeToBeRemoved); });
    }

    //Post-Order

    @Test()
    public void testPostOrderRemoveNode() throws InputValidationException, OperationalException {

        bTree.insertTreeNode(mockData());

        int nodeToBeRemoved =2;
        RemoveNodeAndGetRootOfSubTrees remove = new RemoveNodeAndGetRootOfSubTrees(new RemoveNodePostOrder(bTree, nodeToBeRemoved));
        Vector<TreeNode> vector = remove.removeNode();
        assertEquals(2,vector.size());
        assertEquals(vector.get(0).getVal(), 4);
        assertEquals(vector.get(1).getVal(), 1);
    }

    @Test()
    public void testPostOrderRemoveNodeNotInTree() throws InputValidationException, OperationalException {

        bTree.insertTreeNode(mockData());
        int nodeToBeRemoved=8;
        RemoveNodeAndGetRootOfSubTrees remove = new RemoveNodeAndGetRootOfSubTrees(new RemoveNodePreOrder(bTree, nodeToBeRemoved));
        Vector<TreeNode> vector = remove.removeNode();
        assertEquals(1,vector.size());
        assertEquals(vector.get(0).getVal(), 1);
    }

    @Test()
    public void testPostOrderRemoveNodeWithInvalidKey() throws InputValidationException, OperationalException {

        bTree.insertTreeNode(mockData());
        String nodeToBeRemoved="2";
        RemoveNodeAndGetRootOfSubTrees remove = new RemoveNodeAndGetRootOfSubTrees(new RemoveNodePreOrder(bTree, nodeToBeRemoved));
        assertThrows(OperationalException.class, () -> {
            remove.removeNode(); });
    }

    @Test()
    public void testPostOrderRemoveNodeIfTreeIsEmpty() throws InputValidationException, OperationalException {

        String nodeToBeRemoved="2";
        assertThrows(InputValidationException.class, () -> {
            new RemoveNodeAndGetRootOfSubTrees(new RemoveNodePostOrder(bTree, nodeToBeRemoved)); });
    }

    //In-order

    @Test()
    public void testInOrderRemoveNode() throws InputValidationException, OperationalException {

        bTree.insertTreeNode(mockData());

        int nodeToBeRemoved =2;
        RemoveNodeAndGetRootOfSubTrees remove = new RemoveNodeAndGetRootOfSubTrees(new RemoveNodeInOrder(bTree, 2));
        Vector<TreeNode> vector = remove.removeNode();
        assertEquals(2,vector.size());
        assertEquals(vector.get(0).getVal(), 1);
        assertEquals(vector.get(1).getVal(), 4);
    }

    @Test()
    public void testInOrderRemoveNodeNotInTree() throws InputValidationException, OperationalException {

        bTree.insertTreeNode(mockData());
        int nodeToBeRemoved=8;
        RemoveNodeAndGetRootOfSubTrees remove = new RemoveNodeAndGetRootOfSubTrees(new RemoveNodeInOrder(bTree, nodeToBeRemoved));
        Vector<TreeNode> vector = remove.removeNode();
        assertEquals(1,vector.size());
        assertEquals(vector.get(0).getVal(), 1);
    }

    @Test()
    public void testInOrderRemoveNodeWithInvalidKey() throws InputValidationException, OperationalException {

        bTree.insertTreeNode(mockData());
        String nodeToBeRemoved="2";
        RemoveNodeAndGetRootOfSubTrees remove = new RemoveNodeAndGetRootOfSubTrees(new RemoveNodeInOrder(bTree, nodeToBeRemoved));
        assertThrows(OperationalException.class, () -> {
            remove.removeNode(); });
    }

    @Test()
    public void testInOrderRemoveNodeIfTreeIsEmpty() throws InputValidationException, OperationalException {

        // bTree.insertTreeNode(mockData());
        String nodeToBeRemoved="2";
        assertThrows(InputValidationException.class, () -> {
            new RemoveNodeAndGetRootOfSubTrees(new RemoveNodeInOrder(bTree, nodeToBeRemoved)); });
    }
}
