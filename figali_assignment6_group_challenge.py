MOVE = 'move '
TO = ' to '
NEWLINE = '\n'

def fill_dict(list_):
	pos_dict = {}
	for i in range(len(list_)):
		pos_dict[list_[i]] = i
	return pos_dict

# returns position of car_number
def where_is(position_dict, car_number):
	return position_dict[car_number]

def arrange_cars(initial_car_list, final_car_list):
	# first make sure initial_car_list and final_car_list are same length
	assert(len(initial_car_list) == len(final_car_list)), 'The two lists must be same length!'
	parking_spaces = len(initial_car_list)

	# then fill dictionaries of item->index_position (faster access time)
	position_dict = fill_dict(initial_car_list)

	# fix the target car_number in its corresponding position, starting from 0-th index till the end
	for car_index in range(parking_spaces):

		if(initial_car_list[car_index] == final_car_list[car_index]):
			# nothing to do here
			continue
		
		# swap logic
		if (initial_car_list[car_index] == 0 or final_car_list[car_index] == 0):
			# if one of initial or target is 0, straightforward swap
			print(swap_with_zero(car_index, position_dict, initial_car_list, final_car_list))
		else:
			print(swap_twice(car_index, position_dict, initial_car_list, final_car_list))
	return initial_car_list, final_car_list

def swap_with_zero(car_index, position_dict, initial_car_list, final_car_list):
	
	index_of_initial = where_is(position_dict, initial_car_list[car_index])
	index_of_target = where_is(position_dict, final_car_list[car_index])

	# change the indexes in the position dictionary before swapping accordingly
	position_dict[initial_car_list[car_index]] = index_of_target
	position_dict[final_car_list[car_index]] = index_of_initial
	
	# straightforward swap
	temp = initial_car_list[index_of_initial]
	initial_car_list[index_of_initial] = initial_car_list[index_of_target]
	initial_car_list[index_of_target] = temp

	return MOVE + str(index_of_target) + TO + str(index_of_initial)

def swap_twice(car_index, position_dict, initial_car_list, final_car_list):
	result = ''

	# keep the replaced nonzero var
	temp = initial_car_list[car_index]

	# first, swap 0 with initial value since we want value from final list at car_index
	index_zero = where_is(position_dict, 0)
	initial_car_list[index_zero] = initial_car_list[car_index] 
	initial_car_list[car_index] = 0
	
	# change indexes in possition dict accordingly
	position_dict[0] = car_index
	position_dict[temp] = index_zero
	
	result += MOVE + str(car_index) + TO + str(index_zero)
	# second, perform just a straightforward swapping with zero in order to get value of final list in car_index
	return result + NEWLINE + swap_with_zero(car_index, position_dict, initial_car_list, final_car_list)

###########
# TESTING #
###########
import unittest
import random

class test_assignment_six(unittest.TestCase):
	def __init__(self, *args, **kwargs):
		super(test_assignment_six, self).__init__(*args, **kwargs)
		
		# used in tests
		self.test_initial = [0, 3, 2, 1]
		self.test_final = [1, 2, 3, 0]
		self.test_pos_dict = {0:0, 3:1, 2:2, 1:3}

	# create a random array and a random final configuration
	def create_test_data(self):
		answer_to_life_the_universe_and_everything = 42
		list_1 = list(range(answer_to_life_the_universe_and_everything))
		return list_1, random.sample(list_1, answer_to_life_the_universe_and_everything)

	def test_arrange_cars_random_input(self):
		init, final = self.create_test_data()
		init_test, final_test = arrange_cars(init, final)
		assert(init_test == final_test)

	def test_swap_with_zero(self):
		res = MOVE + str(3) + TO + str(0)
		assert(res == swap_with_zero(0, self.test_pos_dict, self.test_initial, self.test_final))
	
	def test_swap_twice(self):
		# for init = [0, 3, 2, 1] and final = [1, 2, 3, 0] and index 1
		res = MOVE + str(1) + TO + str(0) + NEWLINE + MOVE + str(2) + TO + str(1)
		assert(res == swap_twice(1, self.test_pos_dict, self.test_initial, self.test_final))

	def test_fill_dict(self):
		# assert value at dict key is same as index of key in initial list
		_dict = fill_dict(self.test_initial)
		for i in range(len(self.test_initial)):
			assert(i == _dict[self.test_initial[i]]) 

	def test_where_is(self):
		assert(where_is(self.test_pos_dict, 3) == self.test_pos_dict[3])

if __name__ == '__main__':
	unittest.main()

