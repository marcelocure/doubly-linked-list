package com.senacrs;

import java.util.Scanner;

public class ListaDuplamenteEncadeadaComOutrasOptions {
    // a List está vazia, logo, objeto inicio têm o valor null, o objeto inicio conterá o endereço do primeiro elemento da List
    static List inicio = null;
    // o objeto fim conterá o endereço do último elemento da List
    static List fim = null;
    // o objeto aux é um objeto auxiliar
    static List aux;
    // o objeto anterior é um objeto auxiliar
    static List anterior;
    static Scanner entrada = new Scanner(System.in);

	
	private static class List {
        public int num;
        public List prox;
        public List ant;
    }
    
    public static void main(String[] args) {
    	CSVReader reader = new CSVReader();
    	java.util.List<Person> people = reader.read("C:/Temp/people.csv");
    	
        int op;
        do {
            printOptions();
            System.out.print("Digite sua opção: ");
            op = entrada.nextInt();
            if (op == 1) insertOnBeggining();
            if (op == 2) insertOnEnd();
            if (op == 3) listPeople();
            if (op == 4) removePerson();
            if (op == 5) cleanList();
        } while (op != 0);
    }

	private static void cleanList() {
		if (inicio == null) System.out.println("List vazia!!");
		else {
		    inicio = null;
		    System.out.println("List esvaziada");
		}
	}

	private static void listPeople() {
		if (inicio == null) {
		    // a List está vazia
		    System.out.println("List vazia!!");
		} else {
		    // a List contém elementos e estes serão mostrados do início ao fim
		    System.out.println("\nConsultando a List do início ao fim\n");
		    aux = inicio;
		    while (aux != null) {
		        System.out.print(aux.num + " ");
		        aux = aux.prox;
		    }
		}
	}

	private static void removePerson() {
		int numero;
		int achou;
		if (inicio == null) {
		    // a List está vazia
		    System.out.println("List vazia!!");
		} else {
		    // a List contém elementos e o elmento a ser removido deve ser digitado
		    System.out.print("\nDigite o elemento a ser removido: ");
		    numero = entrada.nextInt();
		    // todos as ocorrências da List, iguais ao número digitado serão removidas
		    aux = inicio;
		    achou = 0;
		    while (aux != null) {
		        if (aux.num == numero) {
		            // o número digitado foi encontrado na List e será removido
		            achou = achou + 1;
		            if (aux == inicio) {
		                // o número a ser removido é o primeiro da List
		                inicio = aux.prox;
		                if (inicio != null) {
		                    inicio.ant = null;
		                }
		                aux = inicio;
		            } else if (aux == fim) {
		                // o número a ser removido é o último da List
		                fim = fim.ant;
		                fim.prox = null;
		                aux = null;
		            } else {
		                // o número a ser removido está no meio da List
		                aux.ant.prox = aux.prox;
		                aux.prox.ant = aux.ant;
		                aux = aux.prox;
		            }
		        } else {
		            aux = aux.prox;
		        }
		    }
		    if (achou == 0) {
		        System.out.println("Número não encontrado");
		    } else if (achou == 1) {
		        System.out.println("Número removido 1 vez");
		    } else {
		        System.out.println("Numero removido " + achou + " vezes");
		    }
		}
	}

	private static void insertOnEnd() {
		System.out.println("Digite o número a ser inserido no fim da List: ");
		List novo = new List();
		novo.num = entrada.nextInt();
		if (inicio == null) {
		    // a List estava vazia e o elemento inserido será o primeiro e o último
		    inicio = novo;
		    fim = novo;
		    novo.prox = null;
		    novo.ant = null;
		} else {
		    // a List já contém elementos e o novo elemento será inserido no fim da List
		    fim.prox = novo;
		    novo.ant = fim;
		    novo.prox = null;
		    fim = novo;
		}
		System.out.println("Número inserido no fim da List!!");
	}

	private static void insertOnBeggining() {
		System.out.println("Digite o número a ser inserido no início da List");
		List novo = new List();
		novo.num = entrada.nextInt();
		if (inicio == null) {
		    // a Lista esta vazia e o elemento inserido será o primeiro e o último da List
		    inicio = novo;
		    fim = novo;
		    novo.prox = null;
		    novo.ant = null;
		} else {
		    // a Lista já contém elementos e o novo elemento será inserido no início da List
		    novo.prox = inicio;
		    inicio.ant = novo;
		    novo.ant = null;
		    inicio = novo;
		}
		System.out.println("Número inserido no início da List!!");
	}

	private static void printOptions() {
		System.out.println("\n");
		System.out.println("0 - Sair");
		System.out.println("1 - Inserir no início da lista");
		System.out.println("2 - Inserir no fim da lista");
		System.out.println("3 - Consultar a lista do início ao fim");
		System.out.println("4 - Remover da lista");
		System.out.println("5 - Esvaziar a lista");
	}
}