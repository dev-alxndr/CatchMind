package gui.play;

public class Word {

	public static void main(String[] args) {
		Word wd = new Word();
		System.out.println(wd.getWord());
		
	}
	
	
	String wordList[] = {
			"사자", "비둘기","화광암", "고양이", "강아지", "얼룩말", "아지랑이", "기린", "물고기", "사과", 	// 0 ~ 10번
			"파인애플", "두리안", "상추", "나무", "이빨", "라면", "전화","탁구", "마이크", "높은음자리",		//11 ~ 20번
			"바퀴", "빵", "태양", "수영", "열쇠", "바지", "부메랑", "컴퓨터","소프라노", "정장",			//21 ~ 30번
			"원숭이", "공부", "선생님", "다이아몬드", "그물", "등급","호랑이", "사람", "축구", "파리",		//31 ~ 40번
			"소방관", "가수", "악기", "노래", "용지", "동아리", "코끼리", "광산", "완두콩", "산책"};		//41 ~ 50번
	String[] wordQuiz = new String[50];
	String[] alreadyWord = new String[50]; 
	
	Word() {
		
	}
	
	public String getWord(){
		String word = null;
		int rnum, i;
		
		//문자 넣기
		rnum = (int)(Math.random()*50)+0;
		word = wordList[rnum];
		
		
		
		return word;
	}

}
