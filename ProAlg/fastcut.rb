def n_fastcut w
	smallest = -1
	smallest_w = nil

	no = Math.log2(w.n)**2
	no.ceil.times do |n|
		cur_w = w.copy
		cur = fastcut cur_w
		if cur < smallest || smallest == -1
			smallest_w = cur_w
			smallest = cur
		end
	end
	[smallest, smallest_w]
end

def fastcut w

	if w.m <= 3
		return enum_edge w
	end

	w1 = w
	w2 = w.copy

	p = (w.m / Math.sqrt(2)).ceil

	k_contract w1, p
	k_contract w2, p

	r1 = fastcut w1
	r2 = fastcut w2

	return r1 < r2 ? r1 : r2
end


def enum_edge w
	# we call the remaining nodes a, b and c
	# by construction we know, that one of them has index 1
	a = 1
	b = 0
	c = 0

	(1..w.n).each do |j|
		if b == 0 and w.get_w(1,j) > 0
			b = j
			next
		elsif c == 0 and w.get_w(1,j) > 0
			c = j
			break
		end
	end

	# if we have no c yet, the graph might look like
	#      b
	#    /   \
	#  a      c
	# and we have to look at node b
	(1..w.n).each do |j|
		if w.get_w(b,j) > 0
			c = j
			break
		end
	end

	# if still no c is found, the graph looks like
	#   a - b
	# and therefore there's just one split
	if c == 0
		return w.get_w(a,b)
	end

	ab = w.get_w(a,b)
	bc = w.get_w(b,c)
	ac = w.get_w(a,c)

	puts "----"
	puts "Intermediate we have a: #{a}, b: #{b}, c: #{c}"


	# shall we cut of a ?
	if ab + ac <= ab + bc and ab + ac <= ac + bc
		puts "cut of a"
		return ab + ac
	# shall we cut of b ?
	elsif ab + bc <= ab + ac and ab + bc <= ac + bc
		puts "cut of b"
		return ab + bc
	# shall we cut of c ?
	elsif ac + bc <= ab + bc and ac + bc <= ab + ac
		puts "cut of c"
		return ac + bc
	end

end