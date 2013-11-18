
# Computes contract for input DatMatrix W
def contract w
	# 1) If graph has only two vertices,  then stop
    #    output y and cap(y)
	if w.m == 2
		y = w.get_v 1
		cap = w.get_W 1
		return [y, cap]
	end

	# 2) At random contract an edge where the likelihood of
    #    taking an edge is proportional to the edge weight.
    edge = select_edge w
    contract_edge w, edge

    # 3) Recursively apply CONTRACT to the resulting graph
	w.m -= 1
    contract w
end

# As above but without recursion
def k_contract w, k

	k.times do |i|
		if w.m == 2
			y = w.get_v 1
			cap = w.get_W 1
			return [y, cap]
		end

		edge = select_edge w
		contract_edge w, edge

		w.m -= 1
	end
end



# Returns an array [row, col] with indeces.
# Note: row < col holds!
def select_edge w
	# Sum up the partial weights ~ O(n)
	total_w = 0
	(1..w.n).each do |w_i|
		total_w += w.get_W(w_i)
	end

	# Draw random number between 0 and total_w ~ O(1)
	rnd = Random.rand * total_w	

	# search the column where this weight is located ~ O(n)
	sum_up = w.get_W(1)
	row_index = 1
	while sum_up < rnd
		row_index += 1
		sum_up += w.get_W(row_index)
	end

	# now we have the correct row
	col_index = w.n
	while sum_up > rnd
		sum_up -= w.get_w(row_index, col_index)
		col_index -= 1
	end

	# compensate for -1 too much
	[row_index, col_index + 1]
end

def contract_edge w, edge
	# 1) combine the lines of the selected nodes
	(1..w.n).each do |j|
		a = w.get_w(edge[0], j)
		b = w.get_w(edge[1], j)
		w.set_w(edge[0], j, a + b)
		w.set_w(edge[1], j, b - b)
	end
	w.set_w(edge[0], edge[1], 0)

	# 2) combine edges that point to both nodes of the edge
	#    or just reroute them
	(1..w.n).each do |i|
		a = w.get_w(i, edge[0])
		b = w.get_w(i, edge[1])
		w.set_w(i, edge[0], a + b)
		w.set_w(i, edge[1], b - b)
	end

	# 3) write down the names
	w.join_v(edge[0], edge[1])
end