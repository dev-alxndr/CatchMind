package gui.play;

public class Word {

	public static void main(String[] args) {
		Word wd = new Word();
		wd.wordprint();
	}
	

	String wordList[] = {"소","얼룩말", "고양이", "강아지","호랑이","원숭이","비둘기","기린","코끼리","물고기"};
	String wordQuiz[];
	
//	//rnum 중복검사 하고 wordQuiz에 넣어주기
//	int rNum[]= {1,2};
////			this.rNum[i] = (int)(Math.random()*50)+0;
//	System.out.println(rNum[i]);

	void wordprint() {
		int[] rNum = new int[50];
		for(int i=0; i<rNum.length; i++) {
			rNum[i] = (int)(Math.random()*50)+0;
		}
		
		//확인 출력
		for(int i = 0; i<rNum.length; i++) {
			System.out.println(rNum[i]);
		}
	}
}
