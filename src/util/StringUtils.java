package util;

public class StringUtils {
    //count the frequency of "containstr" appearance in inputstr
    public static int count(String inputstr, String containstr){
        int count = 0;
        if(inputstr==null){
            return -1;
        }
        while (inputstr.contains(containstr)){
            count++;
            inputstr = inputstr.substring(inputstr.indexOf(containstr)+containstr.length());
        }
        return count;
    }
}
