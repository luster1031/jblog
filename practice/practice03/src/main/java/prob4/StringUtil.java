package prob4;

public class StringUtil {
	//문자열을 결합하여 리턴 하는 메소드 구현
	public static String concatenate(String[] str) {
        String utilStr = "";
        for(String s : str) {
        	utilStr += s;
        }
		return utilStr;
    }


}
