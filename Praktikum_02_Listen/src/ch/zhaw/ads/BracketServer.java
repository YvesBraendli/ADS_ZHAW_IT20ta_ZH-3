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
		if(signs.length<=1) {
			return false;
		}
		int i = 0;
		for (Character symbol : signs) {
			if (Character.toString(symbol).matches("[\\/]")
					&& (signs.length-1>i)) {
				String twoSignBracket = Character.toString(symbol) + Character.toString(signs[i+1]);
				stack.push(twoSignBracket);
			}
			if (Character.toString(symbol).matches("[\\(\\[\\{\\<]")) {
				stack.push(symbol);
			}
			if (Character.toString(symbol).matches("[\\*]")
					&& (signs.length-2==i)) {
				String twoSignClosingBracket = Character.toString(symbol) + Character.toString(signs[i+1]);
				if (twoSignClosingBracket.equals("*/")&&stack.peek().equals("/*")) {
					stack.pop();
				}
				if(stack.isEmpty()) {
					return true;
				}
			}
			if ((Character.toString(symbol).matches("[\\)]") && stack.peek().toString().matches("[\\(]"))
					|| (Character.toString(symbol).matches("[\\]]") && stack.peek().toString().matches("[\\[]"))
					|| (Character.toString(symbol).matches("[>]") && stack.peek().toString().matches("[<]"))
					|| (Character.toString(symbol).matches("[\\}]") && stack.peek().toString().matches("[\\{]"))) {
				stack.pop();
			}
			i++;
		}
		return stack.isEmpty();
	}

}
