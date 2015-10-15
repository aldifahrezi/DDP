package melemparmeriam;

/*
 * Kelas ini merupakan handler setiap round yang dijalankan di dalam game.
 * 
 * @author Aldi Fahrezi
 * @version 1.0.0
 */

public class RoundHandler
{
	protected int mode;
	protected boolean isStarted;
	protected boolean isFinished;
	protected int turnCounter;
	protected int turnLimit;
	protected java.util.Scanner input;

	protected java.util.ArrayList<Meriam> meriamTeam;
	protected java.util.ArrayList<Manusia> manusiaTeam;

	public RoundHandler () {
		this.mode = 0;
		isStarted = false;
		isFinished = false;
		turnCounter = 0;
		turnLimit = 10;
		this.input = new java.util.Scanner(System.in);
		meriamTeam = new java.util.ArrayList<Meriam>();
		manusiaTeam = new java.util.ArrayList<Manusia>();
	}

	public RoundHandler (java.util.Scanner input) {
		this.mode = 0;
		isStarted = false;
		isFinished = false;
		turnCounter = 0;
		turnLimit = 10;
		this.input = input;
		meriamTeam = new java.util.ArrayList<Meriam>();
		manusiaTeam = new java.util.ArrayList<Manusia>();
	}

	public RoundHandler (java.util.Scanner input, int mode) {
		this.mode = mode;
		isStarted = false;
		isFinished = false;
		turnCounter = 0;
		turnLimit = 10;
		this.input = input;
		meriamTeam = new java.util.ArrayList<Meriam>();
		manusiaTeam = new java.util.ArrayList<Manusia>();
	}

	public void setTurnLimit(int turnLimit) {
		this.turnLimit = turnLimit;
	}

	public int getTurnLimit() {
		return turnLimit;
	}

	public int getTurn() {
		return turnCounter;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public int getMode() {
		return mode;
	}

	public boolean isStarted () {
		return isStarted;
	}

	public boolean isFinished () {
		if (isFinished) {
			return true;
		}

		boolean ind = true;
		for (int i = 0; i < manusiaTeam.size(); ++i) {
			if (manusiaTeam.get(i).isAlive()) {
				ind = false;
			}
		}

		if (turnCounter >= turnLimit) {
			ind = true;
		}

		isFinished = ind;

		return isFinished;
	}

	public boolean start() {
		if (isStarted == true) {
			return false;
		}
		
		isStarted = true;

		System.out.println("|------------- START ROUND -------------|");

		System.out.printf("\n|---- Setup Tim Meriam ----|\n");
		System.out.print("Masukkan jumlah meriam: ");
		int meriamSize = InputValidator.inputInt(input);

		for (int i = 0; i < meriamSize; ++i) {
			Meriam meriam = new Meriam();
			double posX;
			
			System.out.print("Masukkan posisi meriam " + (i+1) + ": ");
			posX = InputValidator.inputDouble(input);
			
			meriam.setPosition(posX);
			meriamTeam.add(meriam);
		}

		System.out.printf("\n|---- Setup Tim Manusia ----|\n");
		System.out.print("Masukkan jumlah manusia: ");
		int manusiaSize = InputValidator.inputInt(input);
		System.out.println();

		for (int i = 0; i < manusiaSize; ++i) {
			Manusia manusia = new Manusia();
			double posX,healthPoint,maxMovement;
			
			System.out.print("Masukkan posisi manusia " + (i+1) + ": ");
			posX = InputValidator.inputDouble(input);
			manusia.setPosition(posX);
			
			System.out.print("Masukkan HP manusia " + (i+1) + ": ");
			healthPoint = InputValidator.inputDouble(input);
			manusia.setMaxHealth(healthPoint);
			manusia.setHealth(healthPoint);
			
			if (mode == 1) {
				System.out.print("Masukkan Kecepatan lari manusia " + (i+1) + ": ");
				maxMovement = InputValidator.inputDouble(input);
				manusia.setMaxMovement(maxMovement);
			}

			System.out.println();

			manusiaTeam.add(manusia);
		}

		return true;
	}

	public boolean nextTurn() {
		if (isStarted == false || isFinished == true) {
			return false;
		}

		System.out.printf("\n|-------------- TURN %d --------------|\n\n", turnCounter + 1);
		
		System.out.printf("STATUS TIM MERIAM:\n");
		for (int i = 0; i < meriamTeam.size(); ++i) {
			System.out.printf("Meriam %d berposisi di %.2f\n", i+1, meriamTeam.get(i).getPosition());
		}
		System.out.println();

		System.out.printf("STATUS TIM MANUSIA:\n");
		for (int i = 0; i < manusiaTeam.size(); ++i) {
			if (manusiaTeam.get(i).isAlive()) {
				System.out.printf("Manusia %d (HP: %.2f) berposisi di %.2f\n", i+1, manusiaTeam.get(i).getHealth(), manusiaTeam.get(i).getPosition());
			}
			else {
				System.out.printf("Manusia %d sudah tiada\n", i+1, manusiaTeam.get(i).getHealth(), manusiaTeam.get(i).getPosition());
			}
		}

		System.out.printf("\n|--- Start Turn ---|\n\n");
		int iMeriam = 0, iManusia = 0, counter = 0;
		
		if (mode == 0) {
			iManusia = manusiaTeam.size();
		}
		
		while (iMeriam < meriamTeam.size() || iManusia < manusiaTeam.size() ) {
			int turn;
			if (iMeriam >= meriamTeam.size()) {
				turn = 1;
			}
			else if (iManusia >= manusiaTeam.size()) {
				turn = 0;
			}
			else if (counter % 2 == 0) {
				turn = 0;
			}
			else {
				turn = 1;
			}

			if (turn == 0) {
				Meriam meriam = meriamTeam.get(iMeriam);

				System.out.printf("-- Turn Meriam %d (Pos: %.2f) --\n", iMeriam+1, meriam.getPosition());

				meriam.reload(new BolaMeriam());

				System.out.print("Masukkan kecepatan tembak meriam: ");
				double speed = InputValidator.inputDouble(input);
				meriam.setSpeed(speed);
				
				System.out.print("Masukkan sudut meriam (dalam derajat): ");
				double angle = InputValidator.inputDouble(input);
				meriam.setAngle(angle);
				
				System.out.printf("\nTembak!\n");
				BolaMeriam bola = meriam.fire();
				
				while (bola.isFlying()) {
					System.out.printf("t: %5.2f detik x: %5.2f m y: %5.2f m\n", bola.getTime(), bola.getPosX(), bola.getPosY());
					bola.fastForward(1.0);
				}

				System.out.printf("t: %5.2f detik x: %5.2f m y: %5.2f m\n\n", bola.getTime(), bola.getPosX(), bola.getPosY());	

				double damage = bola.getDamage();

				for (int j = 0; j < manusiaTeam.size(); ++j) {
					Manusia manusia = manusiaTeam.get(j);
					if (!manusia.isAlive()) {
						continue;
					}
					else {
						double healthBefore = manusia.getHealth();
						manusia.giveDamage(damage, bola.getPosX());
						double healthAfter = manusia.getHealth();

						if (Math.abs(healthBefore - healthAfter) < MeriamConstants.EPS) {
							continue;
						}

						System.out.printf("Meriam %d memberikan damage ke Manusia %d sebesar %.2f\n", iMeriam+1, j+1, Math.abs(healthAfter - healthBefore));
						
						if (Math.abs(healthAfter) < MeriamConstants.EPS) {
							System.out.printf("Manusia %d telah musnah\n", j+1);
						}
					}
					System.out.println();
				}
			}
			else if (manusiaTeam.get(iManusia).getMaxMovement() > 0) {
				Manusia manusia = manusiaTeam.get(iManusia);
				System.out.printf("-- Turn Manusia %d (HP: %.2f, Pos: %.2f) --\n", iManusia+1, manusia.getHealth(), manusia.getPosition());

				System.out.printf("Masukkan perpindahan manusia %d yang diinginkan (Gunakan angka negatif untuk bergerak ke kiri, nilai tidak lebih dari %.2f): ", iManusia+1, manusia.getMaxMovement());
				double movement = InputValidator.inputDouble(input);

				while (!manusia.move(movement)) {
					System.out.printf("Perpindahan diluar batas kecepatan yang dimiliki manusia %d, input lagi: ", iManusia+1);
					movement = InputValidator.inputDouble(input);
				}

				System.out.printf("\nManusia %d telah berpindah posisi ke %.2f\n\n", iManusia+1, manusia.getPosition());
			}

			if (isFinished()) break;
			
			if (iMeriam >= meriamTeam.size()) {
				++iManusia;
			}
			else if (iManusia >= manusiaTeam.size()) {
				++iMeriam;
			}
			else if (counter % 2 == 0) {
				++iMeriam;
			}
			else {
				++iManusia;
			}
			++counter;
		}


		System.out.println("|---------- END TURN ----------|");

		turnCounter++;

		return true;
	}

	public void reset() {
		mode = 0;
		isStarted = false;
		isFinished = false;
		meriamTeam.clear();
		manusiaTeam.clear();
		turnCounter = 0;
	}

	public int getWinner() {
		if (isFinished == false || isStarted == false) {
			return -1;
		}

		if (turnCounter >= turnLimit) {
			return 1;
		}
		else {
			return 0;
		}
	}
}