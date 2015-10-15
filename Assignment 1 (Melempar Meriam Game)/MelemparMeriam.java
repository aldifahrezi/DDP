import java.util.Scanner;
import melemparmeriam.*;

/**
 * Kelas ini merupakan kelas utama (penjalan) dalam game "Melempar Meriam"
 * 
 * @author Aldi Fahrezi
 * @version 1.0.0
 *
 */
 
public class MelemparMeriam 
{
	public static void main(String[] args) 
	{

		System.out.println("|-------- SELAMAT DATANG DI GAME MELEMPAR MERIAM (MERIAM VS MANUSIA) --------|\n");
		Scanner in = new Scanner(System.in);
		
		int startGame = 1;

		while (startGame == 1) {
			System.out.println("Di game ini terdapat 2 tim, meriam dan manusia");
			System.out.println("Tujuan tim meriam adalah mengeliminasi seluruh manusia dalam batas turn yang ditentukan");
			System.out.println("Tujuan tim manusia adalah survive selama batas turn yang ditentukan (mode 1 only)");
			System.out.println();
			System.out.println("Terdapat 2 jenis mode:");
			System.out.println("0: Mode dimana manusia tidak bisa gerak (Single Player)");
			System.out.println("1: Mode dimana manusia digerakkan oleh player lain (Turn based)");
			System.out.printf("Ketik nomor mode yang diinginkan: ");
			
			int mode = InputValidator.inputInt(in);
			while (mode < 0 || mode > 1) {
				System.out.print("Mode yang anda masukkan tidak terdaftar di pilihan!\nSilahkan input lagi: ");
				mode = InputValidator.inputInt(in);
			}

			System.out.println();
			System.out.print("Masukkan limit turn bagi tim meriam: ");
			int turnLimit = InputValidator.inputInt(in);
			System.out.println();

			RoundHandler round = new RoundHandler(in);
			
			round.setMode(mode);
			round.setTurnLimit(turnLimit);

			round.start();
			while (!round.isFinished()) {
				round.nextTurn();
			}
			int pemenang = round.getWinner();

			System.out.println("\n|------ Round ini telah selesai ------|");

			if (pemenang == 1) {
				System.out.println("|--- Pemenangnya adalah tim Manusia --|");
			}
			else {
				System.out.println("|--- Pemenangnya adalah tim Meriam ---|");
			}

			System.out.println();
			System.out.print("Mulai Round baru? (1/0): ");
			
			startGame = -1;
			while (startGame < 0 && startGame > 1) {
				startGame = InputValidator.inputInt(in);
			}

			System.out.println();
		}
	}
}
