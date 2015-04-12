package bplustree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Q1 {

	public static void main(String[] args) throws FileNotFoundException {
		int degree = Integer.parseInt(args[0]);
		File file = new File(args[1]);

		BPlusTree<Integer, Integer> tree = new BPlusTree<Integer, Integer>(
				degree);

		Scanner sc = new Scanner(file);
		int i = 1;
		while (sc.hasNext()) {
			String s = sc.nextLine();
			String[] ops = s.split(" ");
			switch (ops[0]) {
			case "PRINT":
				System.out.println("B+tree #" + i + "with order d=" + degree
						+ "\n" + tree);
				break;
			case "INSERT": {
				int key = Integer.parseInt(ops[1]);
				tree.insert(key, key);
			}
				break;
			case "DELETE": {
				int value = Integer.parseInt(ops[1]);
				tree.delete(value);
			}
			default:
				break;
			}
		}
	}

}
