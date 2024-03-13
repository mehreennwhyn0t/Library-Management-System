import java.util.Scanner; //importing Scanner class to get user input

public class Duke_Humfreys_Library 
{
    public static final int SIZE=5;

    public static Scanner takeInput = new Scanner(System.in); //creating Scanner object to gain user input

    public static void addNewBooks() //method to add all books information
    {
        int i = -1; //checking for the next available index
        for(int j=0;j<SIZE;j++)
        {
            if(Books.title[j]==null)
            {
                i=j;
                break;
            }
        }
        if(i == -1) //exception handling
        {
            System.out.println("Library capacity reached. Cant add more books, sorry.");
            return;
        }

        System.out.println("Enter Book "+(1+i)+" Title:");
        Books.title[i]=takeInput.nextLine();
        System.out.println("Enter Book ID:");
        Books.book_ID[i]=takeInput.nextDouble();
        takeInput.nextLine(); // Consume newline

        boolean idExists = false;
        for (int k = 0; k < SIZE; k++) {
            if (Books.book_ID[k] == Books.book_ID[i] && k != i && Books.book_ID[k] != 0) {
                idExists = true;
                System.out.println("Multiple books cant have the same ID. Please make sure youre entering the correct ID. Try again");
                System.out.println("Enter Book ID:");
                Books.book_ID[i]=takeInput.nextDouble();
                takeInput.nextLine(); // Consume newline
                break;
            }
        }

        System.out.println("Enter the Author's name:");
        Books.author[i]=takeInput.nextLine();
        System.out.println("Enter Book Genre:");
        Books.genre[i]=takeInput.nextLine();
        System.out.println("Enter Book's Availability Status (true/false):");
        Books.availabilityStat[i]=takeInput.nextBoolean();
        takeInput.nextLine(); // Consume newline
    }

    public static void addNewUsers() //method to add all users information
    {
        int i = -1; //checking for the next available index
        for(int j=0;j<SIZE;j++)
        {
            if(Users.name[j]==null)
            {
                i=j;
                break;
            }
        }
        if(i == -1) //exception handling
        {
            System.out.println("Library capacity reached. Cant add more users at the moment, sorry.");
            return;
        }
        
         System.out.println("Enter User Name:");
         Users.name[i]=takeInput.nextLine();
         System.out.println("Enter User ID:");
         Users.userID[i]=takeInput.nextDouble();
         takeInput.nextLine(); // Consume newline

         boolean idExists = false;
         for (int k = 0; k < SIZE; k++) {
             if (Users.userID[k] == Users.userID[i] && k != i && Users.userID[k] != 0) {
                 idExists = true;
                 System.out.println("Multiple users cant have the same ID. Please make sure youre entering the correct ID. Try again");
                 System.out.println("Enter User ID:");
                 Users.userID[i]=takeInput.nextDouble();
                 takeInput.nextLine(); // Consume newline
                 break;
             }
         }

         System.out.println("Enter the User's contact number:");
         Users.contact[i]=takeInput.nextDouble();
         takeInput.nextLine(); // Consume newline
         System.out.println("Enter the books borrowed by the user:");
         Users.booksBorrowed[i]=takeInput.nextLine();
        
    }

    public static void borrow()
    {
        String ans;
        boolean continueBorrowing = true;
        while (continueBorrowing) {
            System.out.println("Enter your ID: ");
            double borrowID = takeInput.nextDouble();
            takeInput.nextLine(); // Consume newline
            System.out.println("Which book do you wish to borrow? Enter 0 to exit");
            ans = takeInput.nextLine();

            if (ans.equals("0")) {
                break; // Exit if the user enters 0
            }
    
            // Check the availability of the book and borrow if available
            boolean bookFound = false;
            for (int i = 0; i < SIZE; i++) {
                if (ans.equalsIgnoreCase(Books.title[i]) && Books.availabilityStat[i]==true) {
                    System.out.println("Enjoy reading your book!");
                    Books.availabilityStat[i] = false; // Change availability status
                    for(int x=0;x<SIZE;x++)
                    {
                        if(Users.userID[x]==borrowID)
                        {
                            Users.booksBorrowed[x] = ans; // Add the book to the user's borrowed books
                            bookFound = true;
                            break;
                        }
                    }
                    break;
                }
            }
    
            // If the book is not available or not found, prompt the user to borrow another book
            if (!bookFound) {
                System.out.println("The book is not available. Do you want to borrow another book? (Y/N)");
                ans = takeInput.nextLine();
                if (!ans.equalsIgnoreCase("Y")) {
                    continueBorrowing = false;
                }
            } 
        }
          
    }

    public static void returnn()
    {
        System.out.println("Enter your ID: ");
        double userID = takeInput.nextDouble();
        takeInput.nextLine(); // Consume newline

        System.out.println("Which book are you returning?");
        String ans = takeInput.nextLine();

        boolean found = false;
        for (int i = 0; i < SIZE; i++) {
            if (Users.userID[i] == userID && Users.booksBorrowed[i] != null && Users.booksBorrowed[i].equalsIgnoreCase(ans)) {
                System.out.println("Thank you for returning the book: " + ans);
                Users.booksBorrowed[i] = null;
                for (int j = 0; j < SIZE; j++) {
                    if (Books.title[j].equalsIgnoreCase(ans)) {
                        Books.availabilityStat[j] = true; // Change availability status
                        found = true;
                        break;
                    }
                }
                break;
            }
        }
        //exception handling
        if (!found) {
            System.out.println("You have not borrowed this book or have entered the wrong ID.");
        }

        System.out.println("Thanks for visiting Duke Humphrey's Library!");
    }

    public static void search()
    {
        System.out.println("Enter the ID of the user to know what book they have borrowed ");
        double ans =takeInput.nextDouble();

        //checking if the book is available in the library
        for(int i=0;i<SIZE;i++)
        {
            if ((ans==Users.userID[i]) && (Users.booksBorrowed[i] != "0"))
            { 
                System.out.println("The user "+Users.name[i]+" with ID "+Users.userID[i]+" has borrowed the book "+Users.booksBorrowed[i]);
                break;
            }
            else if(Users.booksBorrowed[i]== null)
            {
                System.out.println("The user"+Users.name[i]+" with ID "+Users.userID[i]+" hasnt borrowed any books at the moment");
                break;
            }
            else
            {
                System.out.println("Seems like you have entered a wrong book id.");
                break;
            }
        }
    }


    //main method
    public static void main(String[] args) 
    {
        System.out.println("Welcome to Duke Humphrey's Library!");
        boolean continueExecution = true;
        while (continueExecution) 
        {
            System.out.println("Are you a Librarian or a User?");
            String ans = takeInput.nextLine();
            if(ans.equalsIgnoreCase("librarian"))
            {
                System.out.println("What would you like to do?\n(1) Add Books\n(2) Add Users\n(3) Display Books\n(4) Search a Book?\nPlease enter the number of the option you want to accesss");
                int a = takeInput.nextInt();
                takeInput.nextLine(); // Consume newline

                switch (a) {
                    case 1:
                        addNewBooks();
                        break;

                    case 2:
                        addNewUsers();
                        break;

                    case 3:
                        new DisplayBooks();
                        break;

                    case 4:
                        search();
                        break;

                    default:
                        System.out.println("Your request is out of our reach.");
                        break;
                }
                continue;
            }
            else if(ans.equalsIgnoreCase("user"))
            {
                System.out.println("Would youlike to:\n(1) Borrrow a book\n(2) Return a Book?");
                int b = takeInput.nextInt();
                takeInput.nextLine(); // Consume newline
                switch (b) {
                    case 1:
                        borrow();
                        break;
                    case 2:
                        returnn();
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
                continue;
            }
            else {
                    System.out.println("Invalid input. Please enter 'librarian' or 'user'.");
            }

            System.out.println("Do you want to continue performing another task? (Y/N)");
            ans = takeInput.nextLine();
            if (!ans.equalsIgnoreCase("Y")) {
                continueExecution = false;
            }
        }
        
    }

}