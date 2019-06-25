package gui.play;

public class Word {

	public static void main(String[] args) {
		Word wd = new Word();
		for(int i=0; i<50; i++)
			System.out.println(wd.getStr());

	}
	
	int[] rNum = new int[50];
	String wordList[] = {
			"사자", "비둘기","김경호", "고양이", "강아지", "얼룩말", "카레이서", "기린", "물고기", "사과", 	// 0 ~ 10번
			"파인애플", "카카오나무", "상추", "나무", "가격표", "라면", "전화","탁구", "마이크", "공중전화",		//11 ~ 20번
			"샴푸", "빵", "태양", "수영", "열쇠", "바지", "부메랑", "컴퓨터","소프라노", "정장",			//21 ~ 30번
			"원숭이", "공부", "선생님", "다이아몬드", "그물", "창조물","호랑이", "사람", "축구", "파리",		//31 ~ 40번
			"소방관", "가수", "악기", "노래", "귓속말", "동아리", "코끼리", "게릴라", "완두콩", "산책"};		//41 ~ 50번
	String[] wordQuiz = new String[50];
	String[] alreadyWord = new String[50]; 
	int num = 0;
	int flag = 0;
	public Word() {
//		
//		랜덤수 생성
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
	
		num++;
	}
	
	public String getStr() {
		String a = wordList[rNum[flag]];
		flag++;
		return a;
	}

}

