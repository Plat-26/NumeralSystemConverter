package converter;
import java.util.*;

public class NumeralSystemConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            
            try {
                
                int sourceRadix = Integer.valueOf(scanner.nextLine());
                String input = scanner.nextLine();
                int targetRadix = Integer.valueOf(scanner.nextLine());
                String value = "";
            
                if (targetRadix <= 0 || sourceRadix <= 0) {
                    System.out.println("Error");
                    break;
                }
            
                if (targetRadix > 36 || sourceRadix > 36) {
                    System.out.println("Error");
                    break;
                }       
        
                //If the number to be converted has no fractional parts
                if (!input.contains(".")) {
                    int sourceNum = Integer.valueOf(input);
                
                    if (sourceRadix != 10 && sourceRadix != 1) {
                        sourceNum = Integer.parseInt(Integer.toString(sourceNum), sourceRadix); //if initial num is not in base 10
                    }
                    if (sourceRadix == 1) {
                        String num = Integer.toString(sourceNum);
                        sourceNum = num.length();
                    }
                    if (targetRadix == 1) {
                        for (int i = 0; i < sourceNum; i++) {
                            value += "1";
                        }
                    } else if (targetRadix <= 36 && targetRadix != 1) {
                        value = Integer.toString(sourceNum, targetRadix);
            
                    }
                    System.out.println(value);
                    break;
            
                    //if number to be converted has fractional parts
                } else if (input.contains(".")) {
            
                    String[] arry = input.split("\\.");   //split number into two sides and convert both to decimal x.y
                    StringBuilder val = new StringBuilder();
                    double decimalVal = 0; //fractional parts
                    int toDecimal = 0;  //whole parts
            
                    if (sourceRadix != 10) {
                
                        for (int i = 0; i < arry[0].length(); i++) {    //convert non-fractional parts to base10
                            int x = Character.getNumericValue(arry[0].charAt(i));
                            toDecimal += x * Math.pow(sourceRadix, arry[0].length() - i - 1);
                        }
                
                        for (int i = 0; i < arry[1].length(); i++) {    //convert fractional parts to base10
                            int x = Character.getNumericValue(arry[1].charAt(i));
                            decimalVal += (double) (x / Math.pow( (double) sourceRadix, i + 1));
                        }
                
                    } else {
                        toDecimal = Integer.valueOf(arry[0]);
                        decimalVal = Double.valueOf(input) % 1;
                    }
            
                    val.append(Integer.toString(toDecimal, targetRadix));    //convert decimal parts to target radix
                    val.append(".");
            
                    //convert fractional parts to new base
                    double x = decimalVal; 
                    int y;
                    for (int i = 0; i < 5; i++) {
                        x *= targetRadix;
                    
                        y = (int) x;
                        if (y > 9) {
                            val.append(Character.forDigit(y, targetRadix));
                        } else {
                            val.append(y);
                        }
                        x -= y;

                    }
                    System.out.println(val.toString()); 
                    break; 
                }
                
            } catch (Exception e) {
                    System.out.println("Error");
                    break;
            }    
        }
    }
}
