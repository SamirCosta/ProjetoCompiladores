
import java.util.Scanner;

public class GeneratedCode {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
		int contador;
		String mensagem;
		float taxa;
		boolean ativo;
		contador= 0;
		taxa= 0;
		ativo= true;
		if(contador==0){
		mensagem= "Contador inicializado";
		System.out.println(mensagem);
		}else{
		mensagem= "Contador já possui valor";
		System.out.println(mensagem);
		}
		while(contador<10){
		System.out.println("Contagem: "+contador);
		contador= contador+1;
		}
		int i;
		for(i= 0;i<5;i=i+1)		{
		System.out.println("Iteração do loop for: "+i);
		}
		
int cont = scanner.nextInt();		
String mens = scanner.nextLine();		
float tax = scanner.nextFloat();		if(ativo==true){
		System.out.println("O sistema está ativo.");
		}else{
		System.out.println("O sistema está inativo.");
		}
		
    
	}
}
