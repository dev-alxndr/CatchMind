package gui.play;

public class Word {

	public static void main(String[] args) {
		Word wd = new Word();
		wd.makeWordArray();
	}
	
	String wordList[] = {
			"사자", "비둘기","화광암", "고양이", "강아지", "얼룩말", "아지랑이", "기린", "물고기", "사과", 	// 0 ~ 10번
			"파인애플", "두리안", "상추", "나무", "이빨", "라면", "전화","탁구", "마이크", "높은음자리",		//11 ~ 20번
			"바퀴", "빵", "태양", "수영", "열쇠", "바지", "부메랑", "컴퓨터","소프라노", "정장",			//21 ~ 30번
			"원숭이", "공부", "선생님", "다이아몬드", "그물", "등급","호랑이", "사람", "축구", "파리",		//31 ~ 40번
			"소방관", "가수", "악기", "노래", "용지", "동아리", "코끼리", "광산", "완두콩", "산책"};		//41 ~ 50번
	String[] wordQuiz = new String[50];

	
	void makeWordArray() {
		int[] rNum = new int[50];
		
		//랜덤수 생성
		for(int i=0; i<rNum.length; i++) {
			rNum[i] = (int)(Math.random()*50)+0;
			//중복 제거
			for(int j=0; j<i; j++) {
				if(rNum[j] == rNum[i]) {
					i--;
					break;
				}
			}
			this.wordQuiz[i] = this.wordList[rNum[i]];
		}
		
		
		//확인 출력
		for(int i = 0; i<rNum.length; i++) {
			System.out.println("wordQuiz[" +i+"] = " + this.wordQuiz[i] + "\trNum[" + i + "] = " +rNum[i]);
		}
		
	}
}
