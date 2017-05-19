package DaeguLibCounter;

import org.apache.hadoop.io.Text;

public class DaeguLibParse {
    private String bookName;
    private int borrowCount;
	/* [0]:단행본	[1]:도서명	[2]:저자
     * [3]:출판사	[4]:코드		[5]:년도
     * [6]:URL	[7]:도서수	[8]:현재 대출건수
     */
    public void Parsing(Text text) {
        try{
            String[] columns = text.toString().split(",");
            
            bookName = columns[1];
            borrowCount = Integer.parseInt(columns[8]);
            System.out.println("pars :: " + bookName + borrowCount);

        }catch(Exception e){
            System.out.println("error parsing a record : " + e.getMessage());
        }
    }
    
    public int getBorrowCount(){
    	return borrowCount;
    }
    public String getName(){
    	return bookName;
    }
}