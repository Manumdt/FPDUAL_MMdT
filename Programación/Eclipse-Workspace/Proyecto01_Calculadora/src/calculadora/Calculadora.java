package calculadora;

import java.util.Scanner;

public class Calculadora {

	public float Suma(float num1, float num2) {
		
		float resultado;
		
		resultado=num1+num2;
		
		return resultado;

	}
	
	public float Resta(float num1, float num2) {
		
		float resultado;
		
		resultado=num1-num2;
		
		return resultado;

	}
	
	public float Producto(float num1, float num2) {
		
		float resultado;
		
		resultado=num1*num2;
		
		return resultado;

	}

	public float Cociente(float num1, float num2) {
		
		float resultado;
		
		resultado=num1/num2;
		
		return resultado;

	}
	
	public static void main(String[] args) {
			
		int opcion;
		float num1, num2;
		
		Scanner sc=new Scanner(System.in);
		Calculadora cal=new Calculadora();
		
		System.out.println("Introduce el primer número");
		num1=sc.nextFloat();
		
		System.out.println("Introduce el segundo número");
		num2=sc.nextFloat();
		
		System.out.println("¿Qué operación quieres realizar?");
		System.out.println("1.- Sumar");
		System.out.println("2.- Restar");
		System.out.println("3.- Multiplicar");
		System.out.println("4.- Dividir");
		opcion=sc.nextInt();
		
		switch(opcion) {
			case 1:{
				if(cal.Suma(num1, num2)<0) {
					System.out.println("NEGATIVO!!!");
				}else {
					System.out.println(num1 + " + " + num2 + " = " + cal.Suma(num1, num2));
				}
				
				break;
			}
			
			case 2:{
				if(cal.Resta(num1, num2)<0) {
					System.out.println("NEGATIVO!!!");
				}else {
					System.out.println(num1 + " - " + num2 + " = " + cal.Resta(num1, num2));
				}
				break;
			}
			
			case 3:{
				if(cal.Producto(num1, num2)<0) {
					System.out.println("NEGATIVO!!!");
				}else {
					System.out.println(num1 + " * " + num2 + " = " + cal.Producto(num1, num2));
				}
				break;
			}
			
			case 4:{
				if(cal.Cociente(num1, num2)<0) {
					System.out.println("NEGATIVO!!!");
				}else {
					System.out.println(num1 + " / " + num2 + " = " + cal.Cociente(num1, num2));
				}
				break;
			}
		}
		
	}

}
