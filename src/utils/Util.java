package utils;

import java.text.CharacterIterator;

public class Util {

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
