/*
 * Class Netflix
 */
public class Netflix {
    private OrderedList dvdList;
    private OrderedList customerList;
    int MAX_DVD = 10;

    public Netflix()
    {
        dvdList =  new OrderedList();
        customerList = new OrderedList();
    }

    public OrderedList getCustomerList()
    {
        return customerList;
    }

    public boolean addCustomer(String name, int max)//Cool
    {
        boolean result = false;
        assert name != null && max > 0 && max <= 10 : "Invalid Input";
        if (name != null && max > 0 && max <= 10)
        {
            Customer newCustomer = new Customer(name, max);

            if (customerList.search(newCustomer.getName()) == null)
            {
                customerList.add(newCustomer);
                result = true;
            }
            else
            {
                System.out.println ("ACN Error: "+ name + " already exists.");
            }

        }
        return result;
    };

    //need to increase quantity of particular title
    public boolean addDvd(String title, int numCopies) //Cool
    {
        boolean result = false;

        assert title != null && numCopies > 0 && dvdList != null : "Invalid Input ACQ adding DVD";
        if (title != null && numCopies > 0 && dvdList != null)
        {
            Dvd newDvd = new Dvd(title, numCopies);

            assert newDvd != null : "ACQ error allocation";
            if (newDvd != null)
            {
                ListItem currDvd = dvdList.search(title);

                if (currDvd == null)
                {
                    dvdList.add(newDvd);
                }
                else
                {
                    if (currDvd.sizeWaitingCustomer() > 0)
                    {
                        ListItem customer = currDvd.randWaitingCustomer();

                        if (customer!= null)
                        {
                            customer.addToLoanList(currDvd);
                            customer.remFromWishList(currDvd.getName());
                        }
                    }

                    currDvd.setQuantity(currDvd.getQuantity()+numCopies);
                    newDvd = null;
                }
                result = true;
            }
        }
        return result;
    };

    //customer adds dvd title to their wishList (Queue) or LoanList based on Availability of Dvds.
    public boolean addTitleCustomer(String name, String titleDvd)//Cool
    {
        boolean result = false;
        assert (name != null) && (titleDvd != null) : "Error Input ADD to CUSTOMER";
        if (name != null && titleDvd != null)
        {
            ListItem currCustomer = customerList.search(name);
            if (currCustomer != null)
            {
                if (!currCustomer.checkWishList(titleDvd) && !currCustomer.checkLoanList(titleDvd))
                {
                    ListItem currDvd = dvdList.search(titleDvd);
                    if (currDvd != null)
                    {
                        //check quantity remaining
                        if ((currDvd.getQuantity() > 0) &&
                                (currCustomer.getLoanListSize() < currCustomer.getQuantity()))
                        {
                            currCustomer.addToLoanList(currDvd);
                            currDvd.decrQuantity();
                        }
                        else
                        {
                            currCustomer.addToWishList(currDvd);
                            //add the customer to waiting List of a particular movie.
                            currDvd.addToWaitingList(currCustomer);
                        }
                        result = true;
                    }
                }
                else
                {
                    System.out.println("Error: " + name + " already has " + titleDvd + " in their list");
                }
            }
        }
        return result;
    };

    public boolean removeTitleCustomer(String name, String title) //Cool
    {
        boolean result = false;
        assert name != null && title != null: "Error Input REM";
        if (name != null && title != null)
        {
            ListItem currCustomer = customerList.search(name);

            if (currCustomer != null)
            {
                ListItem currDvd = dvdList.search(title);

                if (currDvd != null)
                {
                    //currCustomer.remFromWishList(title);

                    currDvd.remFromWaitingCustomer(currCustomer);

                    if (currCustomer.checkLoanList(title))
                    {
                        currCustomer.remFromLoanList(title);
                        result = true;
                    }
                    else if(currCustomer.checkWishList(title))
                    {
                        currCustomer.remFromWishList(title);
                        result = true;
                    }
                    else
                    {
                        System.out.println("REM Error "+ title + " not present in "+name);
                    }

                }
                else
                    System.out.println("Error: [" + title + "] can't be removed, not present in customer database");
            }
            else
                System.out.println("Error: [" + name + "] can't be removed, not present in database");
        }
        return result;
    };

    //double check: del from Netflix and Customer Acc (check if it was on loan)
    public boolean lostDvd (String title, String name)//cool
    {
        boolean result = false;
        assert title != null && name != null :"Error Input Lost";

        if (title != null && name != null)
        {
            ListItem currCustomer = customerList.search(name);

            if (currCustomer != null)
            {
                if (currCustomer.checkLoanList(title))
                {
                    currCustomer.remFromLoanList(title);
                    dvdList.remove(title);
                    result = true;
                }
                else
                {
                    System.out.println("Error: " + name + " tried to declare " + title + " lost, not loaned to them.");
                }
            }
        }
        return result;
    };

    public boolean cancelCustomer (String name)//cool
    {
        boolean result = false;
        assert name != null: "Error Input RCN";
        if (name != null)
        {
            ListItem currCustomer = customerList.search(name);
            if (currCustomer != null)
            {
                if (currCustomer.isLoanListEmpty())
                {
                    customerList.remove(name);
                    result = true;
                }
                else
                {
                    System.out.println("Error: can't close [ "+name+" ]'s account, still has loans.");
                }
            }
            else
            {
                System.out.println("Error: trying to delete "+name+" doen't exist");
            }
        }
        return result;
    };

    public boolean returnDvd (String title, String name)//cool
    {
        boolean result = false;
        assert title != null && name != null :"Error Input";
        if (title != null && name != null)
        {
            ListItem currCustomer = customerList.search(name);

            if (currCustomer != null)
            {
                if (currCustomer.checkLoanList(title))
                {
                    ListItem currDvd = dvdList.search(title);
                    if (currDvd != null)
                    {
                        if (currDvd.sizeWaitingCustomer() > 0)
                        {
                            ListItem WaitingCustomer = currDvd.randWaitingCustomer();

                            if (WaitingCustomer != null)
                            {
                                WaitingCustomer.addToLoanList(currDvd);
                                WaitingCustomer.remFromWishList(title);
                                currDvd.remFromWaitingCustomer(WaitingCustomer);
                            }
                        }
                        //increment the dvd in dvdList Netflix account.
                        else
                        {
                            currDvd.setQuantity(currDvd.getQuantity() + 1);
                        }

                        currCustomer.remFromLoanList(title);
                        result = true;
                    }
                    else
                    {
                        System.out.println("Error RET: "+name+" returned copy of "+title+" , doesn't exist in Inventory.");
                    }
                }
                else
                {
                    System.out.println("Error RET: " +name+ " tried to return "+title +" , not loaned  to them.");
                }
            }
        }
        return result;
    };


}




