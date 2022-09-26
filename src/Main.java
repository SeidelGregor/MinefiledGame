
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	final int large = 9, height = 9, mine = 9;
	int minefield[][] = new int [large][height];
	char presentation[][] = new char [large][height];
	boolean visible[][] = new boolean [large][height], game;
	
	public static void main(String[] args) {
		
		Main god = new Main();
		god.game = true;
		
		god.generator();
		do{	
			god.showPlayer();
			god.player();
		}while(god.game == true); 
		god.showPlayer();
	}
	
	public void generator() {
		
		Random r = new Random();
		
		int cont = 0, aux1, aux2;
		
		for(int a = 0; a < height; a++) {
			for(int b = 0; b < large; b++) {
				minefield[b][a] = 0;
				visible[b][a] = false;
			}
		}
		
		while(cont < mine) {
			aux1 = r.nextInt(9);
			aux2 = r.nextInt(9);
			if(minefield[aux1][aux2] == 0) {
				minefield[aux1][aux2] = 9;
				cont++;
			}
		}
		
		for(int a = 0; a < height; a++) {
			for(int b = 0; b < large; b++) {
				if(minefield[b][a] != 9) {
					if(a - 1 >= 0) {
						if(b - 1 >= 0) {
							if(minefield[b - 1][a - 1] == 9) {
								minefield[b][a] += 1;
							}
						}
						if(b + 1 < large) {
							if(minefield[b + 1][a - 1] == 9) {
								minefield[b][a] += 1;
							}
						}
						if(minefield[b][a - 1] == 9) {
							minefield[b][a] += 1;	
						}
					}
					if(a + 1 < height) {
						if(b - 1 >= 0) {
							if(minefield[b - 1][a + 1] == 9) {
								minefield[b][a] += 1;
							}
						}
						if(b + 1 < large) {
							if(minefield[b + 1][a + 1] == 9) {
								minefield[b][a] += 1;
							}
						}
						if(minefield[b][a + 1] == 9) {
							minefield[b][a] += 1;
						}
					}
					if(b - 1 >= 0) {
						if(minefield[b - 1][a] == 9) {
							minefield[b][a] += 1;
						}
					}
					if(b + 1 < large) {
						if(minefield[b + 1][a] == 9) {
							minefield[b][a] += 1;
						}
					}
				}
			}
		}
		
	}
	
	public void showPlayer() {
			for(int a = 0; a < height; a++) {
				System.out.print("|");
				for(int b = 0; b < large; b++) {
					if(visible[b][a] == false) {
						System.out.print(presentation[b][a]+"|");
					}else {
						System.out.print(minefield[b][a]+"|");
					}
				}
				System.out.println();
				System.out.println("-------------------");
			}
	}
	
	public void player() {
		
		Scanner e = new Scanner(System.in);
		int a, b;
		do {
			do {
				System.out.println("Digite a posiçao na fila: ");
				b = e.nextInt();
			}while(b < 1 || b > 9);
			do {
				System.out.println("Digite a posiçao na coluna: ");
				a = e.nextInt();
			}while(a < 1 || a > 9);
		}while(visible[b-1][a-1]);
		bombVerify(a-1,b-1);
	}
	
	public void bombVerify(int a, int b) {
		int cont = 0;
		if(minefield[b][a] == 9) {
			System.out.println("Voce perdeu!");
			game = false;
			for(int x = 0; x < height; x++) {
				for(int y = 0; y < large; y++) {
					if(minefield[y][x] == 9) {
						presentation[y][x] = '!';
					}
				}
			}
			return;
		}
		visible[b][a] = true;
		if(a >= 0 && a < height && b >=0 && b < large) {
			if(a - 1 >= 0) {
				if(b - 1 >= 0) {
					if(minefield[b - 1][a - 1] != 9) {
						visible[b-1][a-1] = true;
					}
				}
				if(b + 1 < large) {
					if(minefield[b + 1][a - 1] != 9) {
						visible[b+1][a-1] = true;
					}
				}
				if(minefield[b][a - 1] != 9) {
					visible[b][a-1] = true;
				}	
			}
			if(a + 1 < height) {
				if(b - 1 >= 0) {
					if(minefield[b - 1][a + 1] != 9) {
						visible[b-1][a+1] = true;
					}
				}
				if(b + 1 < large) {
					if(minefield[b + 1][a + 1] != 9) {
						visible[b+1][a+1] = true;
					}
				}
				if(minefield[b][a + 1] != 9) {
					visible[b][a+1] = true;
				}	
			}
			if(b - 1 >= 0) {
				if(minefield[b - 1][a] != 9) {
					visible[b-1][a] = true;
				}
			}
			if(b + 1 < large) {
				if(minefield[b + 1][a] != 9) {
					visible[b+1][a] = true;
				}
			}
		}
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < large; j++) {
				if(visible[j][i]==false) {
					cont++;
				}
			}
		}
		if(cont <= mine) {
			System.out.println("Voce venceu!");
			game = false;
			return;
		}
		cont = 0;
	}	
}
