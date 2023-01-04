
import java.util.Scanner;  
  
class RedBlackNode  
{      
    RedBlackNode leftChild, rightChild;  
    int element;  
    int color;  
  
    
    public RedBlackNode(int element)  
    {  
        this( element, null, null );  
    }   
  
    
    public RedBlackNode(int element, RedBlackNode leftChild, RedBlackNode rightChild)  
    {  
        this.element = element;  
        this.leftChild = leftChild;  
        this.rightChild = rightChild;  
        color = 1;  
    }      
}  
  

class CreateRedBlackTree  
{  
    private static RedBlackNode nullNode;   
    private RedBlackNode current;   
    private RedBlackNode parent;    
    private RedBlackNode header;   
    private RedBlackNode grand; 
    private RedBlackNode great; 
  
    
    static final int RED   = 0;  
    static final int BLACK = 1;      
  
    
    static   
    {  
        nullNode = new RedBlackNode(0);  
        nullNode.leftChild = nullNode;  
        nullNode.rightChild = nullNode;  
    }  
  
  
    
    public CreateRedBlackTree(int header)  
    {  
        this.header = new RedBlackNode(header);  
        this.header.leftChild = nullNode;  
        this.header.rightChild = nullNode;  
    }  
  
    
    public void removeAll()  
    {  
        header.rightChild = nullNode;  
    }  
  
    
    public boolean checkEmpty()  
    {  
        return header.rightChild == nullNode;  
    }  
  
    
    public void insertNewNode(int newElement )  
    {  
        current = parent = grand = header;      
        nullNode.element = newElement;          
  
        
        while (current.element != newElement)  
        {              
            great = grand;   
            grand = parent;   
            parent = current;  
  
            
            current = newElement < current.element ? current.leftChild : current.rightChild;   
  
            
            if (current.leftChild.color == RED && current.rightChild.color == RED)  
                handleColors( newElement );  
        }  
              
        
        if (current != nullNode)  
            return;  
  
        
        current = new RedBlackNode(newElement, nullNode, nullNode);  
  
        
        if (newElement < parent.element)  
            parent.leftChild = current;  
        else  
            parent.rightChild = current;          
        handleColors( newElement );  
    }  
  
    
    private void handleColors(int newElement)  
    {  
        
        current.color = RED;    
        current.leftChild.color = BLACK;    
        current.rightChild.color = BLACK;   
  
        
        if (parent.color == RED)     
        {  
            
            grand.color = RED;  
              
            if (newElement < grand.element && grand.element != newElement && newElement < parent.element)  
                parent = performRotation( newElement, grand );  
            current = performRotation(newElement, great );  
            current.color = BLACK;  
        }  
          
        
        header.rightChild.color = BLACK;   
    }  
  
    
    private RedBlackNode performRotation(int newElement, RedBlackNode parent)  
    {  
        
        if(newElement < parent.element)  
            
            return parent.leftChild = newElement < parent.leftChild.element ? rotationWithLeftChild(parent.leftChild) : rotationWithRightChild(parent.leftChild) ;    
        else  
            
            return parent.rightChild = newElement < parent.rightChild.element ? rotationWithLeftChild(parent.rightChild) : rotationWithRightChild(parent.rightChild);    
    }  
  
    
    private RedBlackNode rotationWithLeftChild(RedBlackNode node2)  
    {  
        RedBlackNode node1 = node2.leftChild;  
        node2.leftChild = node1.rightChild;  
        node1.rightChild = node2;  
        return node1;  
    }  
  
    
    private RedBlackNode rotationWithRightChild(RedBlackNode node1)  
    {  
        RedBlackNode node2 = node1.rightChild;  
        node1.rightChild = node2.leftChild;  
        node2.leftChild = node1.leftChild;  
        return node2;  
    }  
  
    
    public int nodesInTree()  
    {  
        return nodesInTree(header.rightChild);  
    }  
    private int nodesInTree(RedBlackNode node)  
    {  
        if (node == nullNode)  
            return 0;  
        else  
        {  
            int size = 1;  
            size = size + nodesInTree(node.leftChild);  
            size = size + nodesInTree(node.rightChild);  
            return size;  
        }  
    }  
    
    // public boolean searchNode(int value)  
    // {  
    //     return searchNode(header.rightChild, value);  
    // }  
    // private boolean searchNode(RedBlackNode node, int value)  
    // {  
    //     boolean check = false;  
    //     while ((node != nullNode) && check != true)  
    //     {  
    //         int nodeValue = node.element;  
    //         if (value < nodeValue)  
    //             node = node.leftChild;  
    //         else if (value > nodeValue)  
    //             node = node.rightChild;  
    //         else  
    //         {  
    //             check = true;  
    //             break;  
    //         }  
    //         check = searchNode(node, value);  
    //     }  
    //     return check;  
    // }  
  
    
    public void preorderTraversal()  
    {  
        preorderTraversal(header.rightChild);  
    }  
    private void preorderTraversal(RedBlackNode node)  
    {  
        if (node != nullNode)  
        {  
            char c = 'R';  
            if (node.color == 1)  
                c = 'B';  
            System.out.print(node.element +""+c+" ");  
            preorderTraversal(node.leftChild);               
            preorderTraversal(node.rightChild);  
        }  
    }  
  
    
    // public void inorderTraversal()  
    // {  
    //     inorderTraversal(header.rightChild);  
    // }  
    // private void inorderTraversal(RedBlackNode node)  
    // {  
    //     if (node != nullNode)  
    //     {  
    //         inorderTraversal(node.leftChild);  
    //         char c = 'R';  
    //         if (node.color == 1)  
    //             c = 'B';  
    //         System.out.print(node.element +""+c+" ");  
    //         inorderTraversal(node.rightChild);  
    //     }  
    // }  
  
    
//     public void postorderTraversal()  
//     {  
//         postorderTraversal(header.rightChild);  
//     }  
//     private void postorderTraversal(RedBlackNode node)  
//     {  
//         if (node != nullNode)  
//         {  
//             postorderTraversal(node.leftChild);               
//             postorderTraversal(node.rightChild);  
//             char c = 'R';  
//             if (node.color == 1)  
//              c = 'B';  
//             System.out.print(node.element +""+c+" ");  
//         }  
//     }       
// }  
  

class RedBlackTreeExample  
{  
    
    public static void main(String[] args)  
    {    
        
        Scanner scan = new Scanner(System.in);  
  
        
        CreateRedBlackTree obj = new CreateRedBlackTree(Integer.MIN_VALUE);   
        char choice;  
  
        
        do      
        {  
            System.out.println("\nSelect an operation:\n");  
            System.out.println("1. Insert a node ");  
            // System.out.println("2. Search a node");  
            System.out.println("2. Get total number of nodes in Red Black Tree");  
            System.out.println("3. Is Red Black Tree empty?");  
            // System.out.println("5. Remove all nodes from Red Black Tree");  
            // System.out.println("6. Display Red Black Tree in Post order");  
            // System.out.println("7. Display Red Black Tree in Pre order");  
            System.out.println("4. Display Red Black Tree in pre order");  
  
            
            int ch = scan.nextInt();              
            switch (ch)  
            {  
                case 1 :   
                    System.out.println("Please enter an element to insert in Red Black Tree");  
                    obj.insertNewNode( scan.nextInt() );                       
                    break;                            
                // case 2 :   
                //     System.out.println("Enter integer element to search");  
                //     System.out.println(obj.searchNode( scan.nextInt() ));  
                //     break;                                            
                case 2 :   
                    System.out.println(obj.nodesInTree());  
                    break;       
                case 3 :   
                    System.out.println(obj.checkEmpty());  
                    break;       
                // case 5 :   
                //     obj.removeAll();  
                //     System.out.println("\nTree Cleared successfully");  
                //     break;  
                // case 6 :   
                //     System.out.println("\nDisplay Red Black Tree in Post order");  
                //     obj.postorderTraversal();  
                //     break;  
                // case 7 :   
                //     System.out.println("\nDisplay Red Black Tree in Pre order");  
                //     obj.preorderTraversal();  
                    // break;  
                case 4 :   
                    System.out.println("\nDisplay Red Black Tree in In order");  
                    obj.preorderTraversal();  
                    break;  
                default :   
                    System.out.println("\n ");  
                    break;      
            }  
            System.out.println("\nPress 'y' or 'Y' to continue \n");  
            choice = scan.next().charAt(0);                          
        } while (choice == 'Y'|| choice == 'y');                 
    }  
}  

}