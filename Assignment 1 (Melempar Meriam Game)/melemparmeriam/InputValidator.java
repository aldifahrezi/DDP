package melemparmeriam;

public class InputValidator {
	public static int inputInt(java.util.Scanner in) {
		int value;
		String input;
		while (true) {
			try {
	           	input = in.nextLine();
	           	value = Integer.parseInt(input);
	            break;
	        }
	        catch (Exception e) {
	            System.out.print("Invalid input type. Silahkan input kembali: ");
	        }
		}
		return value;
	}

	public static long inputLong(java.util.Scanner in) {
		long value;
		String input;
		while (true) {
			try {
	           	input = in.nextLine();
	           	value = Long.parseLong(input);
	            break;
	        }
	        catch (Exception e) {
	            System.out.print("Invalid input type. Silahkan input kembali: ");
	        }
		}
		return value;
	}

	public static double inputDouble(java.util.Scanner in) {
		double value;
		String input;
		while (true) {
			try {
	           	input = in.nextLine();
	           	value = Double.parseDouble(input);
	            break;
	        }
	        catch (Exception e) {
	            System.out.print("Invalid input type. Silahkan input kembali: ");
	        }
		}
		return value;
	}

	public static char inputChar(java.util.Scanner in) {
		char value;
		String input;
		while (true) {
			try {
	           	input = in.nextLine();
	            value = input.charAt(0);
	            break;
	        }
	        catch (Exception e) {
	            System.out.print("Invalid input type. Silahkan input kembali: ");
	        }
		}
		return value;
	}
}