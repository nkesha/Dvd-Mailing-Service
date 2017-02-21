/*
 * Event.java
 *
 * Class hierarchy
 * Superclass: Event (abstract)
 * Subclasses: acn Event
 *             acq Event
 *             addEvent
 *             remEvent
 *             retEvent
 *             lostEvent
 *             rcnEvent
 */



public abstract class Event
{
    protected String name;
    public Event (String name)
    {
        this.name = name;
    }

    public void processEvent (Netflix account){}
}
class acnEvent extends Event
{
    private int maxNum;
    public acnEvent (String name, int maxNum)
    {
        super(name);
        this.maxNum = maxNum;
    }

    public void processEvent (Netflix account)
    {
        assert account != null : "Error acn process";
        if(account.addCustomer(this.name, this.maxNum))
        {
            System.out.println("Customer: "+name+" added to our service.");
        }
    }
}

class acqEvent extends Event
{
    private int numCopies;

    public acqEvent (String name, int numCopies)
    {
        super(name);
        this.numCopies = numCopies;
    }

    public void processEvent (Netflix account)
    {
        assert account != null : "Error acq process";
        if(account.addDvd(name, numCopies))
        {
            System.out.println ("Adding "+numCopies+" copies of "+name+" to inventory");
        }
    }

}

class addEvent extends Event
{
    private String title;

    public addEvent (String name, String title)
    {
        super(name);
        this.title = title;
    }

    public void processEvent (Netflix account)
    {
        assert account != null : "Error add process";
        if(account.addTitleCustomer(name, title))
        {
            System.out.println ("Adding : "+this.title+ " to "+this.name + "Lists.");
        }
    }

}

class remEvent extends Event
{
    private String title;

    public remEvent (String name, String title)
    {
        super(name);
        this.title = title;
    }

    public void processEvent (Netflix account)
    {
        assert account != null : "Error: REM process";
        if(account.removeTitleCustomer(name, title))
        {
            System.out.println ("Removed "+ title + " from" + name);
        }
    }

}

class retEvent extends Event
{
    private String title;

    public retEvent (String name, String title)
    {
        super(name);
        this.title = title;
    }

    public void processEvent (Netflix account)
    {
        assert account != null : "Error RET process";
        if(account.returnDvd(title, name))
        {
            System.out.println ("Title: "+this.title+ " returned by "+this.name);
        }
    }
}

class lostEvent extends Event
{
    private String title;

    public lostEvent (String name, String title)
    {
        super(name);
        this.title = title;
    }
    public void processEvent(Netflix account)
    {
        assert account != null : "Error LOS process";
        if(account.lostDvd(title, name))
        {
            System.out.println ("One copy of: "+this.title+ " lost by "+this.name);
        }
    }
}

class rcnEvent extends Event
{
    public rcnEvent (String name)
    {
        super(name);
    }

    public void processEvent (Netflix account)
    {
        assert account != null : "Error RCN process";
        if(account.cancelCustomer(name))
        {
            System.out.println ("Closing: "+this.name+ " account.");
        }
    }

}
