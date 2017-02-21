/*
*ADT: OrderedList
* Implemented as a Linked List
* Could also be used as a queue with added push and pop fuctionality
 */

import java.util.Random;

class OrderedList
{
  private Node head, tail;
  private int size;
  
  //constructor
  public OrderedList ()
  {
    head = null;
    tail = null;
    size = 0;
  }

  public Node getFirstElement ()
  {
    return head;
  }

  public Node getTailElement ()
  {
    return tail;
  }

  public int getSize () {
    return size;
  }

  public ListItem randomAccess ()
  {
    ListItem result = null;
    Random rand = new Random();
    int randomNumber = rand.nextInt(size);

    assert size != 0: "Cannot random an empty waiting customer List";
    if (size != 0)
    {
      Node currNode = head;
      int n = 0;

      while (n < randomNumber)
      {
        currNode = currNode.getNextNode();
        n++;
      }
      if (currNode != null)
      {
        result = currNode.getElement();
      }
    }
    return result;
  }

  //adding here for dvd or customerData
  public void add (ListItem newElement)
  {
    Node newNode, prevNode, currNode;
   
    assert newElement != null : "new dvd / new customer is null";
    if (newElement != null)
    {
      newNode = new Node (newElement, null);
      
      assert newNode != null : "Node_Dvd/ Node_customer not allocated";
      if (newNode != null)
      {
        if (head == null)
        {
          head = newNode;     
        }
        else 
        {
          currNode = head;
          prevNode = null;
   
          while (currNode != null && newElement.getName().compareTo(currNode.getElement().getName()) > 0 )
          {
            prevNode = currNode;
            currNode = currNode.getNextNode();
          }
          if (prevNode == null)
          {
            newNode.setNextNode(currNode);
            head = newNode;
          }
          else
          {
            newNode.setNextNode(currNode);
            prevNode.setNextNode(newNode);
          }          
        }
        size++;
      }
    }
  }
  
  //remove here for dvd or customerData
  public void remove (String element)
  {
    Node currNode = head;
    Node prevNode = null;
    
    if (element != null)
    {
      if (search(element) == null) {
        System.out.println ("Item not present. Could not remove!");
      }
      else
      {
        while (currNode.getElement().getName().compareTo(element)!= 0)
        {
          prevNode = currNode;
          currNode = currNode.getNextNode();
        }
        if (prevNode == null)
        {
          head = currNode.getNextNode();
        }
        else
        {
          prevNode.setNextNode(currNode.getNextNode());
        }
        size--;
      }
    }
  }
  
  //Work as push for a queue (FIFO)_for wishList.
  public void push (ListItem newElement)
  {
    Node newNode;
    
    assert newElement != null : "Dvd for your wishList not created";
    if (newElement != null)
    {
      newNode = new Node (newElement, null);
      
      assert newNode != null : "Node_Dvd not allocated for wishList";
      if (newNode != null)
      {
        if (head == null)
        {
          head = newNode;
        }
        else
        {
          tail.setNextNode(newNode);
        }
        tail = newNode;
        size++;
      }
    }
  }//end push
  
  //Work as pop for a queue (FIFO)_for wishList.
  public Node pop ()
  {
    Node result = null;
    assert size != 0: "empty queue cannot pop func";
    if (size != 0)
    {
      result = head;
      head = head.getNextNode();
      size--;
    } 
    return result;
  }//end pop

  //Search functionality for either a queue or ordered list.
  public ListItem search (String element)
  {   
    Node currNode = null;
    ListItem result = null;
    
    assert element != null && size != 0: "cannot seach if list is empty";
    if (element != null && size != 0)
    {
      currNode = head;
      
      while(currNode != null)
      {
        if (currNode.getElement().getName().equals(element))
        {
          result = currNode.getElement();
          currNode = null;
        }
        else 
        {
          currNode = currNode.getNextNode();
        }
      }
    }
    return result;  
  }//search

  public void printList()
  {
    if (size > 0)
    {
      Node curr = head;
      
      while (curr != null)
      {
        System.out.println(curr.getElement().toString());

        curr = curr.getNextNode();
      }
    } 
  }
  
}//end OrderedList class

class Node 
{
  private ListItem element;
  private Node nextNode;
  
  public Node (ListItem element, Node nextNode)
  {
    this.element = element;
    this.nextNode = nextNode;
  }
  public ListItem getElement ()
  {
    return element;
  }

  public Node getNextNode ()
  {
      return nextNode;
  }

  public void setNextNode (Node nextNode)
  {
    this.nextNode = nextNode;
  }    

}// end Node class







