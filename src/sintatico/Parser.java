package sintatico;

import enums.*;
import utils.Token;
import utils.Util;

import java.util.List;

import static utils.JavaRunner.javaCode;

public class Parser {

    List<Token> tokens;
    Token token;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Tree main() {
        token = getNextToken();
        Node node = new Node("PROGRAMA");
        Tree tree = new Tree(node);
        System.out.println("Programa traduzido para JAVA:\n");
        System.out.println("--------------------------------------------");
        traduz(Util.INICIO_JAVA);
        if (PROGRAMA(node)) {
            if (token.type.equals("EOF")) {
                traduz(Util.FIM_JAVA);
                System.out.println("--------------------------------------------");
                System.out.println("\nSintaticamente correta\n\n");
            } else {
                System.out.println("\nSintaticamente INCORRETA XX");
            }
        }

        return tree;
    }

    public Token getNextToken() {
        if (tokens.size() > 0) {
            return tokens.remove(0);
        } else
            return null;
    }

    private void erro(String node) {
        System.out.println("\nErro na regra: " + node);
        System.out.println("Token inválido: " + token.lexema);
        System.exit(0);
    }

    private boolean PROGRAMA(Node node) {
        if (matchL(ReservedWords.BEGIN.getWord(), node)) {
            if (BLOCO(node) && matchL(ReservedWords.END.getWord(), node)) {
                return true;
            }
        }
        erro("PROGRAMA");
        return false;
    }

    private boolean BLOCO(Node node) {
        Node nodeBloco = node.addNode("BLOCO");

        if (TIPO_COMANDO(nodeBloco) &&
                BLOCO(nodeBloco)
        )
            return true;


        return true;
    }

    private boolean TIPO_COMANDO(Node node) {
//        Node nodeTipoComando = node.addNode("TIPO_COMANDO");

        if ("STRING".equals(token.type) ||
                "INT".equals(token.type) ||
                "FLOAT".equals(token.type) ||
                "BOOLEAN".equals(token.type)
        ) {
            if (DECLARACAO(node)) {
                return true;
            }
        }

        if ("ID".equals(token.type)) {
            if (ATRIBUICAO(node)) {
                return true;
            }
        }

        if ("IF".equals(token.type)) {
            if (CONDICIONAL(node)) {
                return true;
            }
        }

        if ("WHILE".equals(token.type)) {
            if (ENQUANTO(node)) {
                return true;
            }
        }

        if ("FOR".equals(token.type)) {
            if (PARA(node)) {
                return true;
            }
        }

        if ("PRINT".equals(token.type)) {
            if (ESCRITA(node)) {
                return true;
            }
        }

        if ("SCAN".equals(token.type)) {
            if (LEITURA(node)) {
                return true;
            }
        }

        if ("COMMENT".equals(token.type)) {
            if (matchT("COMMENT", node, token.lexema)) {
                return true;
            }        }

        return false;
    }

    private boolean ESCRITA(Node node) {
        Node nodeEscrita = node.addNode("ESCRITA");

        if (matchL(ReservedWords.PRINT.getWord(), nodeEscrita, "System.out.print") &&
                EXP_PAREN(nodeEscrita) &&
                matchL(DelimitersEnum.SEMICOLON.getDelimiter(), nodeEscrita, ";\n")) {
            return true;
        }
        erro("ESCRITA");
        return false;
    }

    private boolean LEITURA(Node node) {
        Node nodeLeitura = node.addNode("LEITURA");

        String id;
        String tipo;
        if (matchL(ReservedWords.SCAN.getWord(), nodeLeitura) &&
                matchL(DelimitersEnum.OPEN_PAREN.getDelimiter(), nodeLeitura)) {
            if (ValidaTipoLeitura()) {
                tipo = token.lexema;
                if (TIPO_LEITURA(nodeLeitura)) {
                    if ("ID".equals(token.type)) {
                        id = token.lexema;
                        if (matchT("ID", nodeLeitura) &&
                                matchL(DelimitersEnum.CLOSE_PAREN.getDelimiter(), nodeLeitura) &&
                                matchL(DelimitersEnum.SEMICOLON.getDelimiter(), nodeLeitura)
                        ) {
                            if (tipo.equals(ReservedWords.STRING.getWord()))
                                traduz(String.format("String %s = scanner.nextLine();\n", id));
                            if (tipo.equals(ReservedWords.INT.getWord()))
                                traduz(String.format("int %s = scanner.nextInt();\n", id));
                            if (tipo.equals(ReservedWords.FLOAT.getWord()))
                                traduz(String.format("float %s = scanner.nextFloat();\n", id));
                            return true;
                        }
                    }
                }
            }
        }
        erro("LEITURA");
        return false;
    }

    private boolean ValidaTipoLeitura(){
        return "STRING".equals(token.type) || "INT".equals(token.type) || "FLOAT".equals(token.type);
    }

    private boolean TIPO_LEITURA(Node node) {
        Node nodeTipo = node.addNode("TIPO");

        return matchL(ReservedWords.STRING.getWord(), nodeTipo) ||
                matchL(ReservedWords.INT.getWord(), nodeTipo) ||
                matchL(ReservedWords.FLOAT.getWord(), nodeTipo);
    }

    private boolean DECLARACAO(Node node) {
        Node nodeDeclaracao = node.addNode("DECLARACAO");

        if (TIPO(nodeDeclaracao) &&
                matchT("ID", nodeDeclaracao, token.lexema + " ") &&
                matchL(DelimitersEnum.SEMICOLON.getDelimiter(), nodeDeclaracao, ";\n")) {
            return true;
        }
        erro("DECLARACAO");
        return false;
    }

    private boolean ATRIBUICAO(Node node) {
        Node nodeAtribuicao = node.addNode("ATRIBUICAO");

        if (matchT("ID", nodeAtribuicao, token.lexema) &&
                matchL(AttributionOperatorEnum.ATTRIBUTION.getOperator(), nodeAtribuicao, "= ") &&
                EXPRESSAO(nodeAtribuicao) &&
                matchL(DelimitersEnum.SEMICOLON.getDelimiter(), nodeAtribuicao, ";\n")) {
            return true;
        }
        erro("ATRIBUICAO");
        return false;
    }

    private boolean CONDICIONAL(Node node) {
        Node nodeCondicional = node.addNode("CONDICIONAL");
        if (matchL(ReservedWords.IF.getWord(), nodeCondicional, "if") &&
                matchL(DelimitersEnum.OPEN_PAREN.getDelimiter(), nodeCondicional, "(") &&
                CONDICAO(nodeCondicional) &&
                matchL(DelimitersEnum.CLOSE_PAREN.getDelimiter(), nodeCondicional, ")")) {
            if (matchL(DelimitersEnum.OPEN_BRACE.getDelimiter(), nodeCondicional, "{\n") &&
                    BLOCO(nodeCondicional) &&
                    matchL(DelimitersEnum.CLOSE_BRACE.getDelimiter(), nodeCondicional, "}")) {
                ELSE(nodeCondicional);
                return true;
            }
        }
        erro("CONDICIONAL");
        return false;
    }

    private boolean ELSE(Node node) {
        Node nodeElse = node.addNode("ELSE");
        if (matchL(ReservedWords.ELSE.getWord(), nodeElse, "else")) {
            if (matchL(DelimitersEnum.OPEN_BRACE.getDelimiter(), nodeElse, "{\n") &&
                    BLOCO(nodeElse) &&
                    matchL(DelimitersEnum.CLOSE_BRACE.getDelimiter(), nodeElse, "}")) {
                return true;
            }
        }
        return true;
    }

    private boolean ENQUANTO(Node node) {
        Node nodeEnquanto = node.addNode("ENQUANTO");
        if (matchL(ReservedWords.WHILE.getWord(), nodeEnquanto, "while") &&
                matchL(DelimitersEnum.OPEN_PAREN.getDelimiter(), nodeEnquanto, "(") &&
                CONDICAO(nodeEnquanto) &&
                matchL(DelimitersEnum.CLOSE_PAREN.getDelimiter(), nodeEnquanto, ")")) {
            if (matchL(DelimitersEnum.OPEN_BRACE.getDelimiter(), nodeEnquanto, "{\n") &&
                    BLOCO(nodeEnquanto) &&
                    matchL(DelimitersEnum.CLOSE_BRACE.getDelimiter(), nodeEnquanto, "}")) {
                return true;
            }
        }
        erro("ENQUANTO");
        return false;
    }

    private boolean PARA(Node node) {
        Node nodePara = node.addNode("PARA");
        if (matchL(ReservedWords.FOR.getWord(), nodePara, "for") &&
                matchL(DelimitersEnum.OPEN_PAREN.getDelimiter(), nodePara, "(") &&
                ATRIBUICAO(nodePara) &&
                CONDICAO(nodePara) &&
                matchL(DelimitersEnum.SEMICOLON.getDelimiter(), nodePara, ";") &&
                INCREMENTO(nodePara) &&
                matchL(DelimitersEnum.CLOSE_PAREN.getDelimiter(), nodePara, ")") &&
                BLOCO(nodePara)) {
            if (matchL(DelimitersEnum.OPEN_BRACE.getDelimiter(), nodePara, "{\n") &&
                    BLOCO(nodePara) &&
                    matchL(DelimitersEnum.CLOSE_BRACE.getDelimiter(), nodePara, "}")) {
                return true;
            }
        }
        erro("PARA");
        return false;
    }

    private boolean INCREMENTO(Node node) {
        Node nodeIncremento = node.addNode("INCREMENTO");

        if (matchT("ID", nodeIncremento, token.lexema) &&
                matchL(AttributionOperatorEnum.ATTRIBUTION.getOperator(), nodeIncremento, "=") &&
                matchT("ID", nodeIncremento, token.lexema) &&
                SOMA_SUB(nodeIncremento) &&
                EXPRESSAO(nodeIncremento)) {
            return true;
        }
        erro("INCREMENTO");
        return false;
    }

    private boolean CONDICAO(Node node) {
        Node nodeCondicao = node.addNode("CONDICAO");

        if (EXPRESSAO(nodeCondicao) && OP_COMPARADOR(nodeCondicao) && EXPRESSAO(nodeCondicao)) {
            return true;
        }
        erro("CONDICAO");
        return false;
    }

    private boolean EXPRESSAO(Node node) {
        Node nodeExpressao = node.addNode("EXPRESSAO");

        if (TERMO(nodeExpressao) && EXPRESSAO_OPCIONAL(nodeExpressao)) {
            return true;
        }
        erro("EXPRESSAO");
        return false;
    }

    private boolean EXPRESSAO_OPCIONAL(Node node) {
        Node nodeExpressaoOpcional = node.addNode("EXPRESSAO_OPCIONAL");

        if (SOMA_SUB(nodeExpressaoOpcional) &&
                TERMO(nodeExpressaoOpcional) &&
                EXPRESSAO_OPCIONAL(nodeExpressaoOpcional)
        )
            return true;

        return true;
    }

    private boolean TERMO(Node node) {
        Node nodeTermo = node.addNode("TERMO");

        if (FATOR(nodeTermo) && TERMO_OPCIONAL(nodeTermo)) {
            return true;
        }
        erro("TERMO");
        return false;
    }

    private boolean TERMO_OPCIONAL(Node node) {
        Node nodeTermoOpcional = node.addNode("TERMO_OPCIONAL");

        if (MULT_DIV(nodeTermoOpcional) &&
                FATOR(nodeTermoOpcional) &&
                TERMO_OPCIONAL(nodeTermoOpcional)
        )
            return true;

        return true;
    }

    private boolean FATOR(Node node) {
        Node nodeFator = node.addNode("FATOR");
        if (EXP_PAREN(nodeFator) ||
                LITERAL(nodeFator) ||
                matchT("ID", nodeFator, token.lexema)
        ) {
            return true;
        }
        erro("FATOR");
        return false;
    }

    private boolean EXP_PAREN(Node node) {
        Node nodeExpParen = node.addNode("EXP_PAREN");
        if (matchL(DelimitersEnum.OPEN_PAREN.getDelimiter(), nodeExpParen, "(") &&
                EXPRESSAO(nodeExpParen) &&
                matchL(DelimitersEnum.CLOSE_PAREN.getDelimiter(), nodeExpParen, ")")) {
            return true;
        }

        return false;
    }

    private boolean LITERAL(Node node) {
        Node nodeLiteral = node.addNode("LITERAL");

        return matchT("NUM", nodeLiteral, token.lexema) ||
                matchT("TEXT", nodeLiteral, token.lexema) ||
                matchL(ReservedWords.TRUE.getWord(), nodeLiteral, "true") ||
                matchL(ReservedWords.FALSE.getWord(), nodeLiteral, "false");
    }

    private boolean SOMA_SUB(Node node) {
        Node nodeSomaSub = node.addNode("SOMA_SUB");

        return matchL(MathOperatorEnum.PLUS.getOperator(), nodeSomaSub, "+") ||
                matchL(MathOperatorEnum.MINUS.getOperator(), nodeSomaSub, "-");
    }

    private boolean MULT_DIV(Node node) {
        Node nodeMultDiv = node.addNode("MULT_DIV");

        return matchL(MathOperatorEnum.MULT.getOperator(), nodeMultDiv, "*") ||
                matchL(MathOperatorEnum.DIV.getOperator(), nodeMultDiv, "/");
    }

    private boolean TIPO(Node node) {
        Node nodeTipo = node.addNode("TIPO");

        return matchL(ReservedWords.STRING.getWord(), nodeTipo, "String ") ||
                matchL(ReservedWords.INT.getWord(), nodeTipo, "int ") ||
                matchL(ReservedWords.FLOAT.getWord(), nodeTipo, "float ") ||
                matchL(ReservedWords.BOOLEAN.getWord(), nodeTipo, "boolean ");
    }

    private boolean OP_COMPARADOR(Node node) {
        Node nodeOpComparador = node.addNode("OP_COMPARADOR");

        return matchL(ConditionOperatorEnum.IS_EQUAL.getOperator(), nodeOpComparador, "==") ||
                matchL(ConditionOperatorEnum.IS_DIFFERENT.getOperator(), nodeOpComparador, "!=") ||
                matchL(ConditionOperatorEnum.GREATER_EQUAL.getOperator(), nodeOpComparador, ">=") ||
                matchL(ConditionOperatorEnum.MINOR_EQUAL.getOperator(), nodeOpComparador, "<=") ||
                matchL(ConditionOperatorEnum.GREATER.getOperator(), nodeOpComparador, ">") ||
                matchL(ConditionOperatorEnum.MINOR.getOperator(), nodeOpComparador, "<");
    }

    private boolean matchL(String palavra, Node node) {
        if (token.lexema.equals(palavra)) {
            node.addNode(token.lexema);
            token = getNextToken();
            return true;
        }
        return false;
    }

    private boolean matchL(String palavra, Node node, String newcode) {
        if (token.lexema.equals(palavra)) {
            node.addNode(newcode);
            traduz(newcode);
            token = getNextToken();
            return true;
        }
        return false;
    }

    private boolean matchT(String tipo, Node node) {
        if (token.type.equals(tipo)) {
            node.addNode(token.lexema);
            token = getNextToken();
            return true;
        }
        return false;
    }

    private boolean matchT(String tipo, Node node, String newcode) {
        if (token.type.equals(tipo)) {
            node.addNode(newcode);
            traduz(newcode);
            token = getNextToken();
            return true;
        }
        return false;
    }

    public void traduz(String code) {
        System.out.print(code);
        javaCode.append(code);
    }
}
