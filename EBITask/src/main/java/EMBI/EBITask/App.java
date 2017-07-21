package EMBI.EBITask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter input");
		// String s = "A00000, A0001, ERR000109, ERR000110, ERR000111, ERR000112,
		// ERR000113, ERR000115, ERR000116, ERR100114, ERR200000001, ERR200000002,
		// ERR200000003, DRR2110012, SRR211001, ABCDEFG1";
		// String s = "A00000, A0001, ERR000111, ERR000112, ERR000113";
		String s = sc.nextLine();
		if (s == null || "".equals(s)) {
			sc.close();
			throw new NullPointerException("Invalid input");
		}
		App app = new App();
		List<String> output = app.formatList(s);
		sc.close();
		System.out.println("Output : ");
		System.out.println(output);
	}

	public List<String> formatList(String s) {
		String[] arr = s.split(",");
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i].trim();
		}
		Arrays.sort(arr);
		List<String> finalList = new ArrayList<>();
		boolean[] status = new boolean[arr.length];
		for (int i = 0; i < status.length; i++) {
			status[i] = true;
		}
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == null || "".equals(arr[i])) {
				throw new NullPointerException("Invalid element in input");
			}
			if (status[i]) {
				Integer size = 0;
				String temp = arr[i];
				for (int j = i + 1; j < arr.length; j++) {
					String s2 = arr[j].substring(0, (arr[j].length() * 3) / 4);
					String s1 = temp.substring(0, (temp.length() * 3) / 4);
					String s1Last = temp.substring(((temp.length() * 3) / 4) + 1, temp.length());
					if (s1.equals(s2)) {
						String s2Last = arr[j].substring(((arr[j].length() * 3) / 4) + 1, arr[j].length());
						if (areAllDigits(s1Last) && areAllDigits(s2Last)) {
							Integer i1Last = Integer.valueOf(s1Last);
							Integer i2Last = Integer.valueOf(s2Last);
							if (i1Last - i2Last == 1 || i1Last - i2Last == -1) {
								size++;
								status[i] = false;
								status[j] = false;
								temp = arr[j];
							}
						}
					}
				}
				if (size > 0) {
					int start = 0;
					for (int x = arr[i].length() - 1; x > 0; x--) {
						if (Character.isDigit(arr[i].charAt(x)) && arr[i].charAt(x) != '0') {
							start = x;
						}
					}
					Integer i1Last = Integer.valueOf(arr[i].substring(start, arr[i].length())) + size;
					String range = arr[i] + "-" + arr[i].substring(0, start) + i1Last;
					finalList.add(range);
				} else {
					finalList.add(arr[i]);
				}
			}
		}
		return finalList;
	}

	public static boolean areAllDigits(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isDigit(s.charAt(i))) {
				return false;
			}
		}
		return true;
	}

}
