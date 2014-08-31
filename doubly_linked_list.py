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


class DoublyLinkedListIndex(object):
	begin = None
	end = None
	aux = None
	main = None

	def insert_on_begin(self, person):
		new = List(person=person)
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

	def insert_on_end(self, person):
		new = List(person=person)
		if self.begin is None:
			print 'empty'
			self.begin = new
			self.end = new
			new.next = None
			new.prev = None
		else:
			self.end.next = new
			new.prev = self.end
			new.next = None
			self.end = new


	def show_list(self):
		if self.begin is None:
			print 'empty list'
		else:
			self.aux = self.begin
			while self.aux is not None:
				print self.aux.person
				self.aux = self.aux.next


class DoublyLinkedList(object):
	begin = None
	end = None
	aux = None
	index = DoublyLinkedListIndex()


	def insert_on_begin(self, person):
		new = List(person=person)
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
		self.index.insert_on_begin(self.begin.person)

	def insert_on_end(self, person):
		new = List(person=person)
		if self.begin is None:
			print 'empty'
			self.begin = new
			self.end = new
			new.next = None
			new.prev = None
		else:
			self.end.next = new
			new.prev = self.end
			new.next = None
			self.end = new
		self.index.insert_on_begin(self.end.person)


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
			comparisons = 0


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
	map(dll.insert_on_end, people)
	print 'done'

	print '######### Menu #########'
	print '1 - Insert on the begin'
	print '2 - Insert in the end'
	print '3 - Remove value'
	print '4 - List entries'
	print '5 - Exit'
	option = ''
	while option != '5':
		option = raw_input("Please enter the option: ")
		if option == '1':
			dll.insert_on_begin(read_person())
		if option == '2':
			dll.insert_on_end(read_person())
		if option == '3':
			value = raw_input("Please enter the value to be removed: ")
			dll.remove(value)
		if option == '4':
			dll.show_list()
	print 'Exit'

if __name__ == '__main__':
	main()
