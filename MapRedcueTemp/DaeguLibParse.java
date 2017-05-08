package DaeguLibCounter;

import org.apache.hadoop.io.Text;

public class DaeguLibParse {
    private String bookName;
    private String bookCount;
    private String borrowCount;

    
    public DaeguLibParse(Text text) {
        try{
            String[] columns = text.toString().split(",");

            bookName = columns[1];
            bookCount = columns[7];
            borrowCount = columns[8];
            
        }catch(Exception e){
            System.out.println("error parsing a record : " + e.getMessage());
        }
    }
}