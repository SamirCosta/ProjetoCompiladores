package utils;

import java.text.CharacterIterator;

public class Util {
    public static final String VERMELHO = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String INICIO_JAVA = """
            import java.util.Scanner;
            
            public class GeneratedCode {
                public static void main(String[] args){ 
                    Scanner scanner = new Scanner(System.in);
            """;

    public static final String FIM_JAVA = """
                \n\t}
            }
            """;

}
