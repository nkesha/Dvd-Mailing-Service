/*
 * ListItem.java
 *
 * Class hierarchy
 * Superclass: ListItem (abstract)
 * Subclasses: Dvd
 *             Customer
 */

public abstract class ListItem
{
    protected String name;
    protected int quantity;
 
    public ListItem (String name, int quantity)
    {
     this.name = name;
     this.quantity = quantity;
 }
 
    public String getName()
    {
        return name;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int newQuantity)
    {
        this.quantity = newQuantity;
    }

    public void decrQuantity()
    {
        assert quantity >= 0 : "quantity should not be negative";
        if (quantity > 0)
        {
            this.quantity--;
        }
    };

    public int sizeWaitingCustomer ()
    {
        return 0;
    }

    public ListItem randWaitingCustomer ()
    {
        return null;
    };

    public void addToWaitingList (ListItem element){};

    public boolean checkLoanList (String title){return true;};

    public int getLoanListSize()
    {
        return 0;
    }

    public boolean checkWishList (String title){return true;};

    public boolean isLoanListEmpty(){return false;}

    public void addToWishList (ListItem element){}

    public void addToLoanList (ListItem element){}

    public void remFromWishList(String title){}

    public void remFromLoanList(String title){}

    public void remFromWaitingCustomer(ListItem element){}

    public String toString(){ return  "Name: "+ name; }


}

/***
  Dvd class
 ***/

class Dvd extends ListItem
{
    private int quantity;
    OrderedList waitingCustomer;

      //constructor
    public Dvd (String name, int quantity)
    {
        super(name, quantity);
        this.waitingCustomer = new OrderedList();
    }

    public int sizeWaitingCustomer ()
    {
        return this.waitingCustomer.getSize();
    }

    public void addToWaitingList (ListItem element)
    {
        this.waitingCustomer.add(element);
    }

    public ListItem randWaitingCustomer ()
    {
        return this.waitingCustomer.randomAccess();
    }

    public void remFromWaitingCustomer(ListItem element)
    {
        waitingCustomer.remove(element.getName());
    }

    public String toString()
    {
        return "Title: " + name ;
    }
}//end Dvd class

/***
  Customer class
 ***/

class Customer extends ListItem
{
    OrderedList wishList; //As queue
    OrderedList loanList;
  
    public Customer (String name, int quantity)
    {
        super(name, quantity);
        this.wishList = new OrderedList();
        this.loanList = new OrderedList();
    }

    public boolean isLoanListEmpty()
    {
        boolean result = false;
        if (loanList.getSize() == 0)
        {
            result = true;
        }
        return result;
    }

    public boolean checkLoanList (String title)
    {
        boolean result = false;
        if (loanList.search(title) != null)
        {
            result = true;
        }
        return result;
    }

    public boolean checkWishList (String title)
    {
        boolean result = false;
        if (wishList.search(title) != null)
        {
            result = true;
        }
        return result;
    }

    public int getLoanListSize()
    {
        return this.loanList.getSize();
    }

    public void addToWishList (ListItem element)
    {
        this.wishList.push(element);
    }

    public void addToLoanList (ListItem element)
    {
        this.loanList.add(element);
    }

    public void remFromWishList(String title)
    {
        this.wishList.remove(title);
    };

    public void remFromLoanList(String title){this.loanList.remove(title);};

    public String toString()
    {
        System.out.println("CUSTOMER NAME: " + name );
        System.out.println("-----------------------");
        System.out.println("DVD's ON LOAN");
        loanList.printList();
        System.out.println("QUEUE");
        wishList.printList();

        return "";
    }

}//end customer




