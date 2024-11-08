import lexico.Lexer;
import sintatico.Parser;
import sintatico.Tree;
import utils.JavaRunner;
import utils.Token;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        List<Token> tokens = null;
        String caminhoArquivo = "C:/Users/samir/Desktop/ProjetoCompiladores/src/script.txt";
        StringBuilder code = new StringBuilder();
        BufferedReader leitor = null;

        try {
            leitor = new BufferedReader(new FileReader(caminhoArquivo));
            String linha;

            while ((linha = leitor.readLine()) != null) {
                code.append(linha).append(System.lineSeparator());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (leitor != null) {
                    leitor.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Lexer lexer = new Lexer(code.toString());
        tokens = lexer.getTokens();

        System.out.println("Lista de tokens:");
        for (Token t : tokens) {
            System.out.println(t);
        }
        System.out.println("\n\n");

        Parser parser = new Parser(tokens);
        Tree tree = parser.main();
        tree.printTree();

        JavaRunner.run();

    }
}
