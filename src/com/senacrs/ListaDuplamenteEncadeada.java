package com.senacrs;

import java.util.ArrayList;
import java.util.Scanner;

public class ListaDuplamenteEncadeada {
    static List inicio = null;
    static List fim = null;
    static List aux;
    static Scanner entrada = new Scanner(System.in);

	
	private static class List {
        public Object person;
        public List prox;
        public List ant;
    }
    
    public static void main(String[] args) {
    	CSVReader reader = new CSVReader();
    	java.util.List<Person> people = reader.read("C:/Temp/people.csv");
    	for (Person person: people)
    		insertOnBeggining(person);
    	
        int op;
        do {
            printOptions();
            System.out.print("Digite sua opção: ");
            op = entrada.nextInt();
            if (op == 1) insertOnBeggining(null);
            if (op == 2) insertOnEnd(null);
            if (op == 3) listPeople();
            if (op == 4) removePerson();
        } while (op != 0);
    }

	private static void listPeople() {
		if (inicio == null) {
		    System.out.println("Empty list");
		} else {
		    System.out.println("\nPeople in the list:\n");
		    aux = inicio;
		    while (aux != null) {
		        System.out.print(aux.person);
		        aux = aux.prox;
		    }
		}
	}

	private static void removePerson() {
		int achou;
		if (inicio == null) {
		    // a List está vazia
		    System.out.println("List vazia!!");
		} else {
		    // a List contém elementos e o elmento a ser removido deve ser digitado
		    System.out.print("\nType person's name to be removed: ");
		    String name = entrada.next();
		    // todos as ocorrências da List, iguais ao número digitado serão removidas
		    aux = inicio;
		    achou = 0;
		    while (aux != null) {
		    	Person person = ((Person)aux.person);
		        if (person.getName().equals(name)) {
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

	private static void insertOnEnd(Person person) {
		List novo = new List();
		if (person == null) novo.person = readNewPerson();
		else novo.person = person;
		
		if (inicio == null) {
		    inicio = novo;
		    fim = novo;
		    novo.prox = null;
		    novo.ant = null;
		} else {
		    fim.prox = novo;
		    novo.ant = fim;
		    novo.prox = null;
		    fim = novo;
		}
		System.out.println("Person inserted in the end of the list");
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
	}
}