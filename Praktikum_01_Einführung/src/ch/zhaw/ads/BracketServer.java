package ch.zhaw.ads;

public class BracketServer implements CommandExecutor {
	private ListStack stack;

	@Override
	public String execute(String s) {
		return null;
	}

	public boolean checkBrackets(String command) {
		stack = new ListStack();
		char[] signs = command.toCharArray();
		for (Character symbol : signs) {
			if (Character.toString(symbol).matches("[\\(\\[\\{]")) {
				stack.push(symbol);
			}
			if ((Character.toString(symbol).matches("[\\)]") && stack.peek().toString().matches("[\\(]"))
					|| (Character.toString(symbol).matches("[\\]]") && stack.peek().toString().matches("[\\[]"))
					|| (Character.toString(symbol).matches("[\\}]") && stack.peek().toString().matches("[\\{]"))) {
				stack.pop();
			}
		}
		return stack.isEmpty();
	}

}
