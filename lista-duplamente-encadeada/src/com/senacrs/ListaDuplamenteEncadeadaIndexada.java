package com.senacrs;

import java.util.ArrayList;
import java.util.Scanner;
//http://manfred.com.br/index.php/bsi/estrutura-de-dados-i/132-aula-04-listas-simplesmente-encadeada-duplamente-encadeada-e-circular-em-java
public class ListaDuplamenteEncadeadaIndexada {
    static List inicio = null;
    static List fim = null;
    static List aux;
    
    static ListIndex inicioIndex = null;
    static ListIndex fimIndex = null;
    static ListIndex auxIndex;
    static ListIndex main;
    static Scanner entrada = new Scanner(System.in);

	private static class List {
        public Object person;
        public List prox;
        public List ant;
    }
	
	private static class ListIndex {
        public Object person;
        public ListIndex prox;
        public ListIndex ant;
        public List main;
    }
    
    public static void main(String[] args) {
    	CSVReader reader = new CSVReader();
    	java.util.List<Person> people = reader.read("C:/Temp/people.csv");
    	for (Person person: people) insertOnBeggining(person);
    	
        int op;
        do {
            printOptions();
            System.out.print("Digite sua opção: ");
            op = entrada.nextInt();
            if (op == 1) insertOnBeggining(null);
            if (op == 3) listPeople();
            if (op == 4) removePerson();
            if (op == 5) findPerson();
        } while (op != 0);
    }

	private static void findPerson() {
		System.out.println("Enter the person's name to find:");
		String name = entrada.next();
	    aux = inicio;
	    int comparisons = 0;
	    while (aux != null) {
	    	Person person = ((Person)aux.person);
	    	System.out.println("comparing " + person.getName() + " and " + name);
	    	comparisons++;
	        if (person.getName().equals(name)) {
	        	System.out.println(person);
	        	aux = null;
	        }
	        else aux = aux.prox;
	    }
	    System.out.println(comparisons + " comparisons made");
	}

	private static void listPeople() {
		if (inicio == null)  System.out.println("Lista vazia");
		else {
		    System.out.println("\nPeople in the list:\n");
		    aux = inicio;
		    while (aux != null) {
		        System.out.print(aux.person);
		        aux = aux.prox;
		    }
		}
	}

	private static void removePerson() {
		if (inicio == null) System.out.println("Lista vazia");
        else {
            System.out.print("\nDigite o elemento a ser removido: ");
            String name = entrada.next();
            aux = inicio;
            int achou = 0;
            while (aux != null) {
            	Person person = ((Person)aux.person);
                if (person.getName().equals(name)) {
                    achou++;
                    if (aux == inicio) {
                        inicio = aux.prox;
                        if (inicio != null)  inicio.ant = null;
                        aux = inicio;
                    } else if (aux == fim) {
                        fim = fim.ant;
                        fim.prox = null;
                        aux = null;
                    } else {
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

	private static void insertOnBeggining(Person person) {
		List novo = new List();
		if (person == null) novo.person = readNewPerson();
		else novo.person = person;
			
		if (inicio == null) {
			inicio = novo;
		    fim = novo;
		    novo.prox = null;
		    novo.ant = null;
		    //index
		    inicioIndex.ant = null;
		    inicioIndex.person = novo.person;
		    inicioIndex.prox = null;
		    inicioIndex.main = inicio;
		    
		    fimIndex.ant = null;
		    fimIndex.person = novo.person;
		    fimIndex.prox = null;
		    fimIndex.main = inicio;
		} else {
		    novo.prox = inicio;
		    inicio.ant = novo;
		    novo.ant = null;
		    inicio = novo;
		}
		System.out.println("Person inserted in the beggining of the list");
	}
	
	private static Person readNewPerson() {
		System.out.println("Type the name:");
		String name = entrada.next();
		System.out.println("Type the address:");
		String address = entrada.next();
		return new Person(name, address, readPhoneNumbers());
	}

	private static java.util.List<String> readPhoneNumbers() {
		java.util.List<String> phone_numbers = new ArrayList<String>();
		String newPhone;
		do {
			System.out.println("Type the phone number:");
			phone_numbers.add(entrada.next());
			System.out.println("Add another number?");
			newPhone = entrada.next();
		} while (newPhone == "Y");
		return phone_numbers;
	}

	private static void printOptions() {
		System.out.println("\n");
		System.out.println("0 : Exit");
		System.out.println("1 : Insert person in the beggining of the list");
		System.out.println("2 : Insert person in the end of the list");
		System.out.println("3 : List people");
		System.out.println("4 : Remove person");
		System.out.println("5 : Find person");
	}
}