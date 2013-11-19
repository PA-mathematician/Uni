load 'datMatrix.rb'
load 'contract.rb'
load 'fastcut.rb'

m = DatMatrix.new 5

# Setup the following matrix:
#   0 5 0 3 0
#   0 0 3 0 1
#   0 0 0 0 2
#   0 0 0 0 0
#   0 0 0 0 0
m.set_w 1, 2, 5
m.set_w 1, 4, 3
m.set_w 2, 3, 3
m.set_w 2, 5, 1
m.set_w 3, 5, 2

puts "============="
puts "Before:"
m.print


r = n_fastcut m

puts "============="
puts "After:"
puts " capacity: #{r[0]}"
puts " v_0: #{r[1].get_v 1}"
puts " v_1 is the rest :)"