public class Main {

    public static String intToRoman(int num) {//going reversely without loop (highest 4 chars so hardcoding) --> O(1) --> bad practice!!!
        String roman = "";
        //1st digit
        int currentDigit = num % 10;
        if(currentDigit < 4){
            for(int i = 0; i < currentDigit; i++){
                roman = roman + "I";
            }
        }
        else if(currentDigit == 4){
            roman = "IV";
        }
        else if(currentDigit < 9 ){
            roman = "V";
            for(int i = 0; i < currentDigit-5; i++){
                roman = roman + "I";
            }
        }
        else{//9
            roman = "IX";
        }
        //2nd digit
        num = num / 10;
        if(num == 0){
            return roman;
        }
        currentDigit = num % 10;
        if(currentDigit < 4){
            for(int i = 0; i < currentDigit; i++){
                roman = "X" + roman;
            }
        }
        else if(currentDigit == 4){
            roman = "XL" + roman;
        }
        else if(currentDigit < 9 ){//79 --> (LXX)IX
            for(int i = 0; i < currentDigit-5; i++){
                roman = "X" + roman;
            }
            roman = "L" + roman;
        }
        else{//9
            roman = "XC" + roman;
        }
        //3rd digit
        num = num / 10;
        if(num == 0){
            return roman;
        }
        currentDigit = num % 10;
        if(currentDigit < 4){
            for(int i = 0; i < currentDigit; i++){
                roman = "C" + roman;
            }
        }
        else if(currentDigit == 4){
            roman = "CD" + roman;
        }
        else if(currentDigit < 9 ){
            for(int i = 0; i < currentDigit-5; i++){
                roman = "C" + roman;
            }
            roman = "D" + roman;
        }
        else{//9
            roman = "CM" + roman;
        }
        //4th digit
        num = num / 10;
        if(num == 0){
            return roman;
        }
        currentDigit = num % 10;
        for(int i = 0; i < currentDigit; i++){
            roman = "M" + roman;
        }
        return roman;
    }

    //helper method for intToRoman2
    public static String toAdd(int size, int currDigit){//what to concatenate with the roman string
        String add = "";
        String unitLetter, midLetter, endLetter;//they all have this pattern -> unit, mid, end letter
        if(size == 1){//for units (1 digit number --> 1-9)
            unitLetter = "I";
            midLetter = "V";
            endLetter = "X";
        }
        else if(size == 2){//for tens (2 digits number --> 10-99)
            unitLetter = "X";
            midLetter = "L";
            endLetter = "C";
        }
        else if(size == 3){//for hundreds (3 digits number --> 100-999)
            unitLetter = "C";
            midLetter = "D";
            endLetter = "M";
        }
        else{//for thousands (4 digits number --> 1000-9999)
            unitLetter = "M";
            midLetter = "";
            endLetter = "";
        }
        //particular pattern --> Previous digits of 4 (1-3), then for 4, then for previous digits of 9 (5-8), then for 9
        if(currDigit < 4){
            for(int i = 0; i < currDigit; i++){
                add = add + unitLetter;
            }
        }
        else if(currDigit == 4){
            add = unitLetter + midLetter;
        }
        else if(currDigit < 9){
            add = midLetter;
            for(int i = 0; i < currDigit-5; i++){
                add = add + unitLetter;
            }
        }
        else{
            add = unitLetter + endLetter;
        }
        return add;
    }

    public static String intToRoman2(int num) {//concatenate string following pattern - (unit, mid, end)
        String roman = "";   //from 1 to 9 --> unit = I, mid = V, end = X;; 10 to 99 --> unit = X, mid = L, end = C so on
        int size = 1;//unit - 1 digit, tens - 2 digits, hundreds - 3 digits, thousands - 4 digits
        while(num > 0){//until extracting all the digits
            int currDigit = num % 10;//extracting the last digit
            roman = toAdd(size++, currDigit) + roman;//concatenating the roman string
            num = num / 10;//cut the last digit
        }
        return roman;
    }

    public static void main(String[] args) {
        System.out.println(intToRoman(2000));
        System.out.println(intToRoman2(2000));
    }
}