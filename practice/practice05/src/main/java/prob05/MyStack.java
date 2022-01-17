package prob05;

public class MyStack {
	String[] stack;
	int top = -1;

	public MyStack(int num) {
		stack = new String[num+2];
	}

	public void push(String str) {
		if (!full()) {
			this.stack[++top] = str;
		}
	}
	public boolean full() {
		if(this.top>= this.stack.length-1) {
			return true;
		}
		else return false;
	}

	public boolean isEmpty() {
		if (top >= 0) {
			return false;
		} else {
			return true;
		}
	}

	public String pop() throws MyStackException{
		if (top >= 0) {
			return this.stack[top--];
		}else {
			throw new MyStackException("stack is empty");
		}
	}
}
