ReadMe

javac A1.java 
java A1 random.txt


Description

The class that is initially called is "A1" which read the input from the given file, contains main and creates our Netflix Account. Then A1 processes the different events using "Event" abstract class. Depending on the type of events, the Event class calls the proper subclass to interact with our Netflix Account. 

Netflix Account is a class that keeps track of our DVD's and customers list. By processing events from "Event" class we can properly interact with our account with the functionality that the "Netflix" class has. 

Customers and Dvd's are represented by ListItem which is an abstract class with customer and DVD as subclasses. Netflix class interacts with "ListItem" class to create customer or DVD's as needed.

Our ADT is  "OrderedList" implemented as a linked List with added queue functionality (push and pop used as needed).