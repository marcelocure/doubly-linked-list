package com.senacrs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
	public List<Person> read(String path) {
		List<Person> people = new ArrayList<Person>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			String line = reader.readLine();;
			while (line != null) {
				String[] line_splitted = line.split("#");
				people.add(createPerson(line_splitted[0], line_splitted[1], buildPhoneNumbers(line_splitted)));
				line = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(people);
		return people;
	}

	private List<String> buildPhoneNumbers(String[] line_splitted) {
		List<String> phone_numbers = new ArrayList<String>();
		if (line_splitted.length > 3) {
			for (int i = 2; i <= line_splitted.length-1; i++) phone_numbers.add(line_splitted[i]);
		} else phone_numbers.add(line_splitted[2]);
		return phone_numbers;
	}

	public Person createPerson(String name, String address,
			List<String> phone_numbers) {
		return new Person(name, address, phone_numbers);
	}
}
