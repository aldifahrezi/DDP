import java.util.Scanner; 
import melemparmeriam.*;

/**
 * Kelas ini merupakan kelas utama (penjalan) dalam game "Melempar Meriam"
 * 
 * @author Aldi Fahrezi
 * @version 1.0.0
 */
public class MelemparMeriam 
{
	public static void main(String[] args) 
	{
		//Cetak Intro
		System.out.println("|-------- SELAMAT DATANG DI GAME MELEMPAR MERIAM (MERIAM VS MANUSIA) --------|\n");
		Scanner in = new Scanner(System.in);
		
		// Loop Round selama startGame == 1
		int startGame = 1;
		while (startGame == 1) {

			// Cetak Main Guide
			System.out.println("Di game ini terdapat 2 tim, meriam dan manusia");
			System.out.println("Tujuan tim meriam adalah mengeliminasi seluruh manusia dalam batas turn yang ditentukan");
			System.out.println("Tujuan tim manusia adalah survive selama batas turn yang ditentukan (mode 1 only)");
			System.out.println();
			
			// Cetak Guide mode
			System.out.println("Terdapat 2 jenis mode:");
			System.out.println("0: Mode dimana manusia tidak bisa gerak (Single Player)");
			System.out.println("1: Mode dimana manusia digerakkan oleh player lain (Turn based)");
			System.out.print("Ketik nomor mode yang diinginkan: ");
			
			// Input mode untuk digunakan pada RoundHandler
			int mode = InputValidator.inputInt(in);
			while (mode < 0 || mode > 1) {
				System.out.print("Mode yang anda masukkan tidak terdaftar di pilihan!\nSilahkan input lagi: ");
				mode = InputValidator.inputInt(in);
			}
			System.out.println();
			
			// Input turnLimit untuk digunakan pada RoundHandler
			System.out.print("Masukkan limit turn bagi tim meriam: ");
			int turnLimit = InputValidator.inputInt(in);
			System.out.println();

			// Deklarasi RoundHandler sekaligus set states-nya
			RoundHandler round = new RoundHandler(in);
			round.setMode(mode);
			round.setTurnLimit(turnLimit);

			// Menjalankan round hingga selesai
			round.start();
			while (!round.isFinished()) {
				round.nextTurn();
			}
			System.out.println("\n|------ Round ini telah selesai ------|");

			// Output pemenang menggunakan data dari round.getWinner() (Case 1: pemenang tim Manusia, Case 0: pemenang tim Meriam)
			int pemenang = round.getWinner();

			if (pemenang == 1) {
				System.out.println("|--- Pemenangnya adalah tim Manusia --|");
			}
			else if (pemenang == 0) {
				System.out.println("|--- Pemenangnya adalah tim Meriam ---|");
			}
			System.out.println();
			
			// Mulai round baru?
			System.out.print("Mulai Round baru? (1/0): ");
			startGame = InputValidator.inputInt(in);
			while (startGame < 0 || startGame > 1) {
				System.out.printf("Pilihan hanya (1/0) (1: untuk mengulang, 0: untuk berhenti). Silahkan input ulang: ");
				startGame = InputValidator.inputInt(in);
			}
			System.out.println();
		}
	}
}
