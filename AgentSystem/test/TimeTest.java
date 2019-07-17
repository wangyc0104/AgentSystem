import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeTest {
	
	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String odt = sdf.format(new Date());
		System.out.println(odt);
		
	}
}
