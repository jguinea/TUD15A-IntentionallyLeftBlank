package nl.tudelft.tud15a.snake.model;

public enum Speed {
	SPEEDUP(4), SLOWDOWN(5), NOCHANGE(6);

	int index;

	Speed(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
}
