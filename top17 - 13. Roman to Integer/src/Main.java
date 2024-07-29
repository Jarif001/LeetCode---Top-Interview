public class Main {

    public static int romanToInt(String s) {//look forward approach: looks forwards letters to count
        int number = 0;
        int count = 0;
        while(count < s.length()){
            if(s.charAt(count) == 'I'){//current letter is 'I'
                if(count + 1 < s.length()){//After 'I'
                    if(s.charAt(count+1) == 'V'){//'V' after 'I' --> 'IV'
                        //4
                        number += 4;
                        count++;
                    }
                    else if(s.charAt(count+1) == 'X'){//'X' after 'I' --> 'IX'
                        //9
                        number += 9;
                        count++;
                    }
                    else{//will be treated as two --> neither 'V' nor 'X' then it must be 'II'. so now increment one then into the loop
                        number ++;           // the loop again to increment another one (because count wasn't incremented)
                    }
                }
                else{
                    //one and end
                    number++;
                }
            }
            else if(s.charAt(count) == 'V'){
                //after V none can have extra meaning
                number += 5;
            }
            else if(s.charAt(count) == 'X'){
                if(count + 1 < s.length()){
                    if(s.charAt(count+1) == 'L'){
                        //40
                        number += 40;
                        count++;
                    }
                    else if(s.charAt(count+1) == 'C'){
                        //90
                        number += 90;
                        count++;
                    }
                    else{//treated as 10
                        number += 10;
                    }
                }
                else{
                    //10 and done
                    number += 10;
                }
            }
            else if(s.charAt(count) == 'L'){
                //after L none can have extra meaning
                number += 50;
            }
            else if(s.charAt(count) == 'C'){
                if(count + 1 < s.length()){
                    if(s.charAt(count+1) == 'D'){
                        //400
                        number += 400;
                        count++;
                    }
                    else if(s.charAt(count+1) == 'M'){
                        //900
                        number += 900;
                        count++;
                    }
                    else{//treated as 100
                        number += 100;
                    }
                }
                else{
                    //100 and done
                    number += 100;
                }
            }
            else if(s.charAt(count) == 'D'){
                //after D none can have extra meaning
                number += 500;
            }
            else{
                //It must be m
                number += 1000;
            }
            count++;
        }
        return number;
    }

    public static int romanToInt2(String s) {//Checks previous letter to count
        int number = 0;
        int prevNum = 0;//keeps track of the previous letter (1 means previous letter was 'I')
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == 'I'){
                prevNum = 1;
                number += 1;
            }
            else if(s.charAt(i) == 'V'){
                if(prevNum == 1){//previous letter was an 'I'. so it is 'IV' --> 4
                    prevNum = 4;
                    number += 3;//1 was added for the previous letter. but it is 4. So, now add 3
                }
                else{//previous letter was not an 'I'. so it is 5
                    prevNum = 5;
                    number += 5;
                }
            }
            else if(s.charAt(i) == 'X'){
                if(prevNum == 1){//previous letter was an 'I'. so it is 'IX' --> 9
                    prevNum = 9;
                    number += 8;//1 was added for the previous letter 'I'
                }
                else{
                    prevNum = 10;
                    number += 10;//it is not 'IX', rather 'X' --> 10
                }
            }
            else if(s.charAt(i) == 'L'){
                if(prevNum == 10){//previous letter was an 'X'. so it is 'XL' --> 40
                    prevNum = 40;
                    number += 30;//10 was added for the previous letter
                }
                else{
                    prevNum = 50;
                    number += 50;//it is not 'XL', rather 'L' --> 50
                }
            }
            else if(s.charAt(i) == 'C'){
                if(prevNum == 10){//previous letter was an 'X'. so it is 'XC' --> 90
                    prevNum = 90;
                    number += 80;//10 was added for the previous letter
                }
                else{
                    prevNum = 100;
                    number += 100;
                }
            }
            else if(s.charAt(i) == 'D'){
                if(prevNum == 100){//previous letter was an 'C'. so it is 'CD' --> 400
                    prevNum = 400;
                    number += 300;//100 was added for the previous letter
                }
                else{
                    prevNum = 500;
                    number += 500;
                }
            }
            else {//must be an 'M'
                if(prevNum == 100){//previous letter was an 'C'. so it is 'CM' --> 900
                    prevNum = 900;
                    number += 800;//100 was added for the previous letter
                }
                else{
                    prevNum = 1000;
                    number += 1000;
                }
            }
        }
        return number;
    }

    

    public static void main(String[] args) {
        String s = "MCDLIX";
        System.out.println(romanToInt2(s));
    }
}