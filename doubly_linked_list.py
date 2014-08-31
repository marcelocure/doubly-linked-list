class Person(object):
	def __init__(self, name, address, phones):
		self.name = name
		self.address = address
		self.phones = phones

	def __str__(self):
		return 'name: {0} address: {1} phones: {2}'.format(self.name, self.address, self.phones)


class List(object):
	def __init__(self, person=None):
		self.person = person
	prev = None
	next = None
	person = None


class ListIndex(object):
	def __init__(self, person=None, main=None):
		self.person = person
		self.main = main
	prev = None
	next = None
	person = None
	main = None


class DoublyLinkedListIndex(object):
	begin = None
	end = None
	aux = None

	def sort_index(self):
		self.aux = self.begin
		changed = False
		while self.aux is not None:
			if self.aux.person.name == self.begin.person.name:
				if self.begin.next.person.name < self.begin.person.name:
					changed = True
					person = self.begin.person
					self.begin.person = self.begin.next.person
					self.begin.next.person = person

					main = self.begin.main
					self.begin.main = self.begin.next.main
					self.begin.next.main = main
			elif self.aux.person.name == self.end.person.name:
				if self.end.prev.person.name > self.end.person.name:
					changed = True
					person = self.end.person
					self.end.person = self.end.prev.person
					self.end.prev.person = person

					main = self.end.main
					self.end.main = self.end.prev.main
					self.end.prev.main = main
			else:
				if self.aux.prev.person.name > self.aux.person.name:
					changed = True
					person = self.aux.prev.person
					self.aux.prev.person = self.aux.person
					self.aux.person = person

					main = self.aux.prev.main
					self.aux.prev.main = self.aux.main
					self.aux.main = main
					
				if self.aux.person.name > self.aux.next.person.name:
					changed = True
					person = self.aux.person
					self.aux.person = self.aux.next.person
					self.aux.next.person = person

					main = self.aux.main
					self.aux.main = self.aux.next.main
					self.aux.next.main = main
					
			if self.aux is not None:
				self.aux = self.aux.next
		if changed:
			self.sort_index()

	def remove(self, name):
		if self.begin is None:
			print 'empty list'
		else:
			self.aux = self.begin
			found = 0
			comparisons = 0
			while self.aux is not None:
				comparisons = comparisons + 1
				if self.aux.person.name == name:
					found = found + 1
					if self.aux == self.begin:
						self.begin = self.aux.next
						if self.begin is not None:
							self.begin.prev = None
						self.aux = None
					elif self.aux == self.end:
						self.end = self.end.prev
						self.end.next = None
						self.aux = None
					else:
						self.aux.prev.next = self.aux.next
						self.aux.next.prev = self.aux.prev
						self.aux = self.aux.next
				else:
					self.aux = self.aux.next
	

	def contains_on_index(self, person):
		self.aux = self.begin
		contains = False
		while self.aux is not None:
			if self.aux.person.name == person.name:
				contains = True
				self.aux = None
			else:
				self.aux = self.aux.next
		return contains

	def insert(self, person, main):
		if not self.contains_on_index(person):
			new = ListIndex(person=person)
			if self.begin is None:
				self.begin = new
				self.end = new
				new.next = None
				new.prev = None
			else:
				new.next = self.begin
				self.begin.prev = new
				new.prev = None
				self.begin = new
			self.begin.main = main
	def show_list(self):
		if self.begin is None:
			print 'empty list'
		else:
			self.aux = self.begin
			while self.aux is not None:
				print self.aux.main.person
				self.aux = self.aux.next

	def search(self, person_name):
		self.aux = self.begin
		found = False
		comparisons = 0
		#print 'comparing on index'
		while self.aux is not None:
			comparisons = comparisons + 1
			if person_name > self.aux.person.name:
				#print 'index value {0} is bigger than {1}'.format(self.aux.person.name, person_name)
				self.aux = self.aux.next
			else:
				#print 'index value {0} is lower than {1}, going back one level'.format(self.aux.person.name, person_name)
				main = self.aux.prev.main
				self.aux = None

		#print 'exit index'
		#print 'searching on main list'
		while main is not None:
			comparisons = comparisons + 1
			#print main.person.name
			if main.person.name == person_name:
				found = True
				main = None
			else:
				main = main.next
		if not found:
			print 'not found'
		else:
			print 'Found'
			print '{0} comparisons made'.format(comparisons)


class DoublyLinkedList(object):
	begin = None
	end = None
	aux = None
	index = DoublyLinkedListIndex()

	def sort_list(self):
		self.aux = self.begin
		changed = False
		while self.aux is not None:
			if self.aux.person.name == self.begin.person.name:
				if self.begin.next.person.name < self.begin.person.name:
					changed = True
					person = self.begin.person
					self.begin.person = self.begin.next.person
					self.begin.next.person = person
			elif self.aux.person.name == self.end.person.name:
				if self.end.prev.person.name > self.end.person.name:
					changed = True
					person = self.end.person
					self.end.person = self.end.prev.person
					self.end.prev.person = person
			else:
				if self.aux.prev.person.name > self.aux.person.name:
					person = self.aux.prev.person
					self.aux.prev.person = self.aux.person
					self.aux.person = person
					changed = True
				if self.aux.person.name > self.aux.next.person.name:
					person = self.aux.person
					self.aux.person = self.aux.next.person
					self.aux.next.person = person
					changed = True
				
			if self.aux is not None:
				self.aux = self.aux.next
		if changed:
			self.sort_list()
			
	def insert(self, person, update_index=True):
		new = List(person=person)
		if self.begin is None:
			self.begin = new
			self.end = new
			new.next = None
			new.prev = None
		else:
			self.end.next = new
			new.prev = self.end
			new.next = None
			self.end = new
		if update_index:
			self.update_index()

	def show_list(self):
		if self.begin is None:
			print 'empty list'
		else:
			self.aux = self.begin
			while self.aux is not None:
				print self.aux.person
				self.aux = self.aux.next
		print '####INDEX####'
		self.index.show_list()
		print '####INDEX####'

	def update_index(self):
		self.index = DoublyLinkedListIndex()
		if self.begin is None:
			print 'empty list'
		else:
			self.aux = self.begin
			while self.aux is not None:
				if self.aux.person.name[:1] in ['e','j','o','t','z']:
					self.index.insert(self.aux.person, self.aux)
				self.aux = self.aux.next
			self.index.sort_index()

	def remove(self, name):
		if self.begin is None:
			print 'empty list'
		else:
			self.aux = self.begin
			found = 0
			comparisons = 0
			while self.aux is not None:
				comparisons = comparisons + 1
				if self.aux.person.name == name:
					found = found + 1
					if self.aux == self.begin:
						self.begin = self.aux.next
						if self.begin is not None:
							self.begin.prev = None
						self.aux = None
					elif self.aux == self.end:
						self.end = self.end.prev
						self.end.next = None
						self.aux = None
					else:
						self.aux.prev.next = self.aux.next
						self.aux.next.prev = self.aux.prev
						self.aux = self.aux.next
				else:
					self.aux = self.aux.next
			if found > 0:
				print 'removed '+str(found)+' times'
			print str(comparisons) + ' comparisons made'
			self.index.remove(name)


	def search(self, person_name):
		self.aux = self.begin
		found = False
		comparisons = 0
		#print 'comparing'
		while self.aux is not None:
			comparisons = comparisons + 1
			#print 'comparing {0} to {1}'.format(self.aux.person.name, person_name)
			if person_name == self.aux.person.name:
				found = True
				self.aux = None
			else:
				self.aux = self.aux.next
		if not found:
			print 'not found'
		else:
			print 'found'
			print '{0} comparisons made'.format(comparisons)
	


def read_person():
	name = raw_input("Please enter the name")
	address = raw_input("Please enter the address")
	phones = []
	option = 'yes'

	while option != 'No':
		phones.append(raw_input("Please enter the phone"))
		option = raw_input("Add another phone?")

	return Person(name, address, phones)

def read_csv(path):
	f = open(path, 'r')
	people = []
	for line in f.readlines():
		line_splited = line.split('#')
		name = line_splited[0]
		address = line_splited[1]
		phones = map(lambda idx: line_splited[idx].replace('\n', ''), range(2, len(line_splited)))
		people.append(Person(name, address, phones))
	return people

def main():
	dll = DoublyLinkedList()
	print 'reading csv'
	people = read_csv('C:\Temp\people.csv')
	map(lambda person: dll.insert(person, False), people)
	dll.sort_list()
	dll.update_index()
	print 'done'

	print '######### Menu #########'
	print '1 - Insert'
	print '2 - Remove value'
	print '3 - List entries'
	print '4 - Search person on index'
	print '5 - Search person on list'
	print '6 - Exit'
	option = ''
	while option != '6':
		option = raw_input("Please enter the option: ")
		if option == '1':
			dll.insert(read_person())
		if option == '2':
			value = raw_input("Please enter the name to be removed: ")
			dll.remove(value)
		if option == '3':
			dll.show_list()
		if option == '4':
			name = raw_input("Please enter the name to search: ")
			dll.index.search(name)
		if option == '5':
			name = raw_input("Please enter the name to search: ")
			dll.search(name)
		dll.sort_list()
		dll.update_index()
	print 'Exit'

if __name__ == '__main__':
	main()
