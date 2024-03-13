import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DisplayBooks 
{
    //frame object
    JFrame f = new JFrame();

    // Data for the table 
    Object[][] data = {
    {Books.book_ID[0], Books.title[0], Books.genre[0], Books.author[0]},
    {Books.book_ID[1], Books.title[1], Books.genre[1], Books.author[1]},
    {Books.book_ID[2], Books.title[2], Books.genre[2], Books.author[2]},
    {Books.book_ID[3], Books.title[3], Books.genre[3], Books.author[3]},
    };

    // Column names
    String[] columnNames = {"ID", "Name", "Genre", "Author"};

    //table object
    JTable t = new JTable(data,columnNames);
    
    //constructor
    DisplayBooks()
    {
        //table name
        f.setTitle("Books");

        // Fetch book data from Books class
        Object[][] data = new Object[Books.book_ID.length][4];
        for (int i = 0; i < Books.book_ID.length; i++) {
            data[i][0] = Books.book_ID[i];
            data[i][1] = Books.title[i];
            data[i][2] = Books.genre[i];
            data[i][3] = Books.author[i];
        }
        JTable t = new JTable(data, columnNames);
        JScrollPane sp = new JScrollPane(t);
        f.add(sp);
        f.setSize(500, 200);
        f.setVisible(true);
    }
}
    
    

