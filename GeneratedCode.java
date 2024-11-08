import java.util.Scanner;

public class GeneratedCode {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
int contador ;
String mensagem ;
float taxa ;
boolean ativo ;
contador= 0;
taxa= 0;
ativo= true;
if(contador==0){
mensagem= "Contador inicializado";
System.out.print(mensagem);
}else{
mensagem= "Contador já possui valor";
System.out.print(mensagem);
}while(contador<10){
System.out.print("Contagem: "+contador);
contador= contador+1;
}int i ;
for(i= 0;
i<5;i=i+1){
System.out.print("Iteração do loop for: "+i);
}int cont = scanner.nextInt();
String mens = scanner.nextLine();
float tax = scanner.nextFloat();
if(ativo==true){
System.out.print("O sistema está ativo.");
}else{
System.out.print("O sistema está inativo.");
}    
	}
}
