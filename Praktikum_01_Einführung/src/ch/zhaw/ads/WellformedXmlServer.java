package ch.zhaw.ads;

import java.util.ArrayList;

public class WellformedXmlServer implements CommandExecutor {
	private ListStack stack;
	private ArrayList<String> xmlToCheck = new ArrayList<>();

	public boolean checkWellformed(String command) {
		if (command.startsWith("</")) {
			return false;
		}
		stack = new ListStack();
		createTokens(command);
		for (String token : xmlToCheck) {
			if (xmlToCheck.size() == 1 && token.endsWith("/")) {
				return true;
			}
			if (token.startsWith("<")) {
				stack.push(token);
			}

			if (token.startsWith("</")) {
				stack.pop();
				token = token.substring(2);
				String closingToken = stack.peek().toString().substring(1);
				if (token.equals(closingToken)) {
					stack.pop();
				}
			}

		}
		return stack.isEmpty();
	}

	private void createTokens(String xmlText) {
		xmlToCheck.clear();
		String[] textSplitted = xmlText.split("[ >]+");
		for (String text : textSplitted) {
			if (text.startsWith("<")) {
				xmlToCheck.add(text);
			}
		}
	}

	@Override
	public String execute(String command) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
