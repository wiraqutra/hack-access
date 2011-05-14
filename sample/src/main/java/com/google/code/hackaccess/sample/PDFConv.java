package com.google.code.hackaccess.sample;

import com.google.code.hackaccess.sample.output.HtmlOutput;
import com.google.code.hackaccess.sample.output.JsonOutput;
import com.google.code.hackaccess.sample.output.TextOutput;

public class PDFConv {
	
	public static void main(String[] args) {

		if (args.length > 0) {

			String command = args[0];
			String[] arguments = new String[args.length - 1];
			System.arraycopy(args, 1, arguments, 0, arguments.length);

			try {
				String url;
				
				if (command.equals("HTML")) {
					url = UrlOption.getUrl(arguments);
					HtmlOutput output = new HtmlOutput("UTF-8");
					output.output(url);
				
				} else if (command.equals("TEXT")) {
					url = UrlOption.getUrl(arguments);
					TextOutput output = new TextOutput("UTF-8");
					output.output(url);
				
				} else if (command.equals("JSON")) {
					url = UrlOption.getUrl(arguments);
					JsonOutput output = new JsonOutput();
					output.output(url);

				}
				else {
					showMessageAndExit();
				}
			} catch (Exception e) {
				System.err.println(command + " failed with the following exception:");
				e.printStackTrace();
				System.exit(1);
			}
		}
		else {
			showMessageAndExit();
		}
	}

	private static void showMessageAndExit() {
		System.err.println("usage: java -jar pdfonv-xx.jar <command> <args..>");
		System.exit(1);
	}
}
