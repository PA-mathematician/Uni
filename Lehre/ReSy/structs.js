global = new Object()
debug = new Object()
global.drawables = new Object()
global.boxSize = 28

/**
 * Refresh the canvas.
 */
global.draw = function() {
	global.canvas.clearRect (0, 0, 800 , 800);
	for(var d in global.drawables) {
		global.drawables[d].draw();
	}
}

function BitVector(pos, name, length) {
	this.x = pos[0]
	this.y = pos[1]
	this.name = name

	this.cells = new Array()
	// initialize the Cells
	for(var i = 0; i < length; i++) {
		this.cells.push(new Cell([this.x + 30 * i, 30], name+i))
	}

	this.setValue = function(newValue) {
		// TODO check
		for(var i = 0; i < length; i++) {
			this.cells[i].value = newValue[i]
		}
	}

}

/**
 * A cell is a thing that can hold exactly one value. Whoow!
 *
 * pos - an array with 2 elements [x,y] that state the position of the left upper corner
 * name - string used to identify the cell
 */
function Cell(pos, name) {
	this.x = pos[0]
	this.y = pos[1]
	this.name = name

	// a call can be drawn, so we register it
	global.drawables[name] = this


	this.value = 1

	this.draw = function() {
		global.canvas.strokeRect(this.x, this.y, global.boxSize, global.boxSize)

		global.canvas.font = "1em Arial"
		global.canvas.fillText(this.value, this.x + 9, this.y + 19)
	}
}
