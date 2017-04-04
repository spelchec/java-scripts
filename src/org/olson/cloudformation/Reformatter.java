package org.olson.cloudformation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Reformatter {

	private static String getCountOfSpaces(int num) {
		String str = "";
		for (int k = 0; k < num; k++) {
			str = str.concat(" ");
		}
		return str;
	}

	private static boolean isEmptyOrComment(final String line) {
		return (line.length() == 0 || line.startsWith("#"));

	}

	private static String formatLine(int num) {
		String str = "";
		for (int k = 0; k < num; k++) {
			str = str.concat(" ");
		}
		return str;
	}

	public static void main(String[] args) throws IOException {
		String fileName = null;
		if (args != null && args.length > 0) {
			fileName = args[0];
		} else {
			fileName = "C:\\Users\\trolson\\Desktop\\ftpDeploy\\powershell.txt";
		}
		File file = new File(fileName);
		if (!file.exists()) {
			System.err.printf("The file %s doesn't exist!", file.toURI());
		}
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String input = null;
		System.out.println(getCountOfSpaces(20)+"\"<powershell>\\n\",");
		try {
			while (null != (input = br.readLine())) {
				String str = input.trim();
				if (!isEmptyOrComment(str)) {
					str = str.replaceAll("\\\\", "\\\\\\\\");
					str = str.replaceAll("\"", "\\\\\"");
					str = "\"" + str + " \\n\",";
					System.out.println(getCountOfSpaces(24)+str);
				}
			}
		} catch (Exception e) {
			System.err.printf("Exception: %s", e);
		} finally {
			br.close();
			System.out.println(getCountOfSpaces(20)+"\"</powershell>\"");
		}
	}
	
}
