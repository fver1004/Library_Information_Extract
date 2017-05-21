package DaeguLibCounter;

import org.apache.hadoop.io.Text;

public class DaeguLibParse {
    private String bookName;
    private int borrowCount;
    
	/* [0]:단행본[1]:도서명[2]:저자[3]:출판사[4]:코드[5]:년도[6]:URL[7]:도서수[8]:현재 대출건수
	 * [1],[8] 파싱하는 클래스.
	 * 대출건수가 0일 수 있기에 값 반환하지않고 void 사용후 get()으로 이용
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