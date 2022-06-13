import java.io.*;
import java.util.Scanner;
import java.util.HashMap;

class disasm{

    public static void main(String[] args){
        if (args.length == 0)
        {
            return;
        }
        
        String filename = args[0];
        
        try ( InputStream inputStream = new FileInputStream(filename); )
        {
            String line;
            StringBuilder sb = new StringBuilder();
            for (int ch; (ch = inputStream.read()) != -1; ) {
                sb.append((char) ch);
            }
            line = sb.toString().replaceAll(" ", "").replaceAll("\n","");
            String[] ln = line.split("(?<=\\G.{4})");
            for (String ls: ln) {
                findOpcode(hexToBinary(ls.substring(0, 1)));
                System.out.println(" "+ls.substring(1, 4));
            }

            // Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(filename)));
            // String line = scanner.nextLine();
            // while(scanner.hasNextLine()){
            //         line = line.replaceAll(" ", "");
            //         String[] ln = line.split("(?<=\\G.{4})");
            //         for (String ls: ln) {
            //             findOpcode(hexToBinary(ls.substring(0, 1)));
            //             System.out.println(" "+ls.substring(1, 4));
            //         }
            // }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void findOpcode(String op){
        switch(op){
            case "0000":{ // JnS
                System.out.print("jns ");
                break;
            }
            case "0001":{ // Load
                System.out.print("load ");
                break;
            }
            case "0010":{ // Store
                System.out.print("store ");
                break;
            }
            case "1010":{ // Clear
                System.out.print("clear ");
                break;
            }
            case "0011":{ // Add
                System.out.print("add ");
                break;
            }
            case "1011":{ // AddI
                System.out.print("addi ");
                break;
            }
            case "0100":{ // Subt
                System.out.print("subt ");
                break;
            }
            case "0101":{ // Input
                System.out.print("input ");
                break;
            }
            case "0110":{ // Output
                System.out.print("output ");
                break;
            }
            case "0111":{ // Halt
                System.out.print("halt ");
                break;
            }
            case "1000":{ // Skipcond
                System.out.print("skipcond ");
                break;
            }
            case "1001":{ // Jump
                System.out.print("jump ");
                break;
            }
            case "1100":{ // JumpI
                System.out.print("jumpi ");
                break;
            }
            case "1101":{ // LoadI
                System.out.print("loadi ");
                break;
            }
            case "1110":{ // StoreI
                System.out.print("storei ");
                break;
            }
        }
    }

    public static String hexToBinary(String hex){
        String binary = "";
        hex = hex.toUpperCase();
        HashMap<Character, String> hashMap = new HashMap<Character, String>();
        hashMap.put('0', "0000");
        hashMap.put('1', "0001");
        hashMap.put('2', "0010");
        hashMap.put('3', "0011");
        hashMap.put('4', "0100");
        hashMap.put('5', "0101");
        hashMap.put('6', "0110");
        hashMap.put('7', "0111");
        hashMap.put('8', "1000");
        hashMap.put('9', "1001");
        hashMap.put('A', "1010");
        hashMap.put('B', "1011");
        hashMap.put('C', "1100");
        hashMap.put('D', "1101");
        hashMap.put('E', "1110");
        hashMap.put('F', "1111");

        int i;
        char ch;
        for (i = 0; i < hex.length(); i++) {
            ch = hex.charAt(i);
            if (hashMap.containsKey(ch))
 
                binary += hashMap.get(ch);
            else {
                binary = "Invalid Hexadecimal String";
                return binary;
            }
        }
        return binary;
    }
}
