public class Main{
    public static int subString(String mainString, String secondString){
        for(int i=0,j=0;i<mainString.length();){
            if(mainString.charAt(i)==secondString.charAt(j)){
                i++;
                j++;
                if (j == secondString.length()) {
                    return i - j;
                }
            }else{
                i = i - j + 1;
                j= 0;
            }
        }
        return -1;
    }
    public static void main(String[] args){
        System.out.println(subString("wildbotiscoolbot", "bot"));
    }//end main
}//end Main