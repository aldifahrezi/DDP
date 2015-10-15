package melemparmeriam;

/**
 * Kelas ini merupakan kelas yang digunakan untuk menginput sekaligus memvalidasi input
 * 
 * @author Aldi Fahrezi
 * @version 1.0.0
 *
 */
public class InputValidator 
{	
	/**
	* Constructor ini merupakan Constructor default class InputValidator
	*/
	public InputValidator() {

	}

	/**
	* Method ini digunakan untuk menginput integer sekaligus memvalidasinya
	* 
	* @param in Scanner yang digunakan untuk mengambil input
	* @return value hasil input setelah lolos validasi
	*/
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

	/**
	* Method ini digunakan untuk menginput long sekaligus memvalidasinya
	* 
	* @param in Scanner yang digunakan untuk mengambil input
	* @return value hasil input setelah lolos validasi
	*/
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

	/**
	* Method ini digunakan untuk menginput double sekaligus memvalidasinya
	* 
	* @param in Scanner yang digunakan untuk mengambil input
	* @return value hasil input setelah lolos validasi
	*/
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

	/**
	* Method ini digunakan untuk menginput char sekaligus memvalidasinya
	* 
	* @param in Scanner yang digunakan untuk mengambil input
	* @return value hasil input setelah lolos validasi
	*/
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