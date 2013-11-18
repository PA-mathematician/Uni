
# This class represents a matrix like in ex 6:
#
# 0    w_12    ...   w_1n  | W_1 | v_1
# 0     0      ...   w_2n  | W_2 | v_2
#              ...         | ... | ...
# 0            ...    0    | W_n | v_n
#
# It is stored in an array. Hereby w_ij and W_i are integers,
# v_i are arrays themselves.
#
# Every read and write access is done in constant time.
class DatMatrix

	attr_reader :n
	attr_accessor :m

	# We store the following
	#   - the one integer 'n'
	#   - the next integer 'm', intended to count the remaining thingsies
	#   - the array 'array' of size O(n²)
	# Hence we need O(n²)
	def initialize n
		size_of_matrix = (n*(n-1) / 2)
		size_of_vectors = 2 * n
		@n = n
		@m = n
		@array = Array.new size_of_matrix + size_of_vectors, 0
		# initialize names
		(1..n).each do |i|
			@array[v_position(i)] = [i]
		end
	end

	# gets the weight of the coordinate i, j
	def get_w i, j
		return 0 if i >= j
		@array[w_position(i,j)]
	end

	# sets the weight of entry i, j
	def set_w i, j, w
		return if i >= j
		pos = w_position(i,j)
		before = @array[w_position(i,j)]
		@array[w_position(i,j)] = w
		@array[W_position(i)] += w - before
	end

	# gets the sum in line i
	def get_W i
		@array[W_position(i)]
	end

	# gets the nodes of index i
	def get_v i
		@array[v_position(i)]
	end

	def join_v i, j
		vi = v_position i
		vj = v_position j
		a = @array[vi]
		b = @array[vj]
		@array[vi] = a + b
		@array[vj] = []
	end

	# Output to console (max 3 char per entry)
	def print
		# lines
		(1..@n).each do |i|
			line = ""
			# rows of w
			(1..@n).each do |j|
				line << printable(get_w(i,j))
				line << ' '
			end
			line << '| ' + printable(get_W(i))
			line << '| ' + get_v(i).to_s

			puts line
		end
	end

	private

	# Helper that calculates for coordinates i and j the array index.
	def w_position i, j
		(((j - 2)*(j - 1)) / 2) + (i - 1)
	end

	# Helper that calculates for i the array index of W_i
	def W_position i
		((@n * (@n-1)) / 2) + (i - 1)
	end

	# Helper that calculates for i the array index of v_i
	def v_position i
		((@n * (@n-1)) / 2) + @n + (i - 1)
	end

	# Returns a string starting with n and filled up with spaces
	# such that it is 3 chars long
	def printable n
		if n < 10
			return "#{n}  "
		elsif n < 100
			return "#{n} "
		elsif n < 1000
			return "#{n}"
		end
	end
end