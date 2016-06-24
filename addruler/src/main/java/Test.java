import java.text.NumberFormat;
import java.text.ParseException;


public class Test {
	public static void main(String[] args) throws ParseException {
		String a = "";
		NumberFormat format = NumberFormat.getInstance();
		System.out.println(format.parse(a).doubleValue());
	}
}
