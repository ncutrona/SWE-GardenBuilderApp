package pkgMain;

public class PentagonShape {
	
	Coordinates top;
	Coordinates left;
	Coordinates leftdown;
	Coordinates right;
	Coordinates rightdown;
	
	public PentagonShape(int height, int width) {
		top = new Coordinates(0,0);
		left = new Coordinates(0,0);
		leftdown = new Coordinates(0,0);
		right = new Coordinates(0,0);
		rightdown = new Coordinates(0,0);
	}
	
	public Coordinates getTop() {
		return this.top;
	}
	public Coordinates getLeft() {
		return this.left;
	}
	public Coordinates getLeftdown() {
		return this.leftdown;
	}
	public Coordinates getRight() {
		return this.right;
	}
	public Coordinates getRightdown() {
		return this.rightdown;
	}

	public void setTop(Coordinates top) {
		this.top = top;
	}

	public void setLeft(Coordinates left) {
		this.left = left;
	}

	public void setLeftdown(Coordinates leftdown) {
		this.leftdown = leftdown;
	}

	public void setRight(Coordinates right) {
		this.right = right;
	}

	public void setRightdown(Coordinates rightdown) {
		this.rightdown = rightdown;
	}

}
