package example.chat;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 게임에 접속한 유저들의 정보를 담고 있는 클래스
 * <p>
 * name : 유저가 접속할 때 유저가 입력한 닉네임
 * pw   : 유저가 접속할 때 연결한 printWriter
 * host : 유저가 방장일 경우 true (최초 접속한 유저가 방장, 최초 접속한 유저가 나가면 나머지 유저 중 무작위)
 * turn : 유저의 턴일 경우 true (문제를 내는 입장)
 */
public class UserInfo {
    public String name;
    public PrintWriter pw;
    public boolean host;    // 방장인지 아닌지
    public boolean turn;    // 자기 차례인지 아닌지

    /*    public boolean gameOn;    // 게임중인지 아닌지*/

    public UserInfo() {
        pw = null;
        host = false;
        turn = false;
    }

    /**
     * 게임 접속시 입력한 닉네임을 name으로, PrintWriter를 pw로 받아 객체 생성
     *
     * @param name
     * @param pw
     */
    public UserInfo(String name, PrintWriter pw) {
        this.name = name;
        this.pw = pw;
        host = false;
        turn = false;
    }
}

/**
 * UserInfo를 HashMap으로 저장하고 있는 클래스
 */
class UserInfoMap {
    HashMap<String, UserInfo> clients;

    /**
     * Clients라는 HashMap을 초기화한다.
     */
    UserInfoMap() {
        clients = new HashMap<String, UserInfo>();
        Collections.synchronizedMap(clients);
    }

    /**
     * 닉네임 name과 PrintWriter out을 인자로 받아
     * HashMap에 새 UserInfo 객체를 추가한다.
     *
     * @param name
     * @param out
     */
    void add(String name, PrintWriter out) {
        clients.put(name, new UserInfo(name, out));
    }

    /**
     * name 이름을 가진 객체를 map에서 삭제
     *
     * @param name
     */
    void remove(String name) {
        clients.remove(name);
    }

    /**
     * 맵 자체를 리턴 하는 함수
     *
     * @return
     */
    HashMap<String, UserInfo> get() {
        return clients;
    }

    /**
     * 멤버의 수를 리턴
     *
     * @return
     */
    int size() {
        return clients.size();
    }

    /**
     * 현재 방장인 UserInfo 리턴
     *
     * @return
     */
    UserInfo getHost() {
        Iterator it = clients.keySet().iterator();

        while (it.hasNext()) {
            try {
                UserInfo user = clients.get(it.next());
                if (user.host) {
                    return user;
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    /**
     * 현재 Turn인 UserInfo 리턴
     *
     * @return
     */
    UserInfo getTurn() {
        Iterator it = clients.keySet().iterator();

        while (it.hasNext()) {
            try {
                UserInfo user = clients.get(it.next());
                if (user.turn) {
                    return user;
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    /**
     * user를 Turn으로 설정
     *
     * @param user
     */
    void setTurn(UserInfo user) {
        Iterator it = clients.keySet().iterator();

        while (it.hasNext()) {
            try {
                UserInfo tmp = clients.get(it.next());
                if (tmp == user) {
                    tmp.turn = true;
                } else {
                    tmp.turn = false;
                }
            } catch (Exception e) {
            }
        }
    }

    /**
     * 해당하는 UserInfo 리턴
     *
     * @param name
     * @return
     */
    UserInfo getUser(String name) {
        if (clients.containsKey(name)) {
            return clients.get(name);
        } else {
            return null;
        }
    }

    /**
     * random User를 리턴
     *
     * @return
     */
    UserInfo getRandomUser() {
        Iterator it = clients.keySet().iterator();
        UserInfo user = null;
        int randomIndex = (int) (Math.random() * clients.size()) + 1;
        for (int i = 0; i < randomIndex; i++) {
            user = clients.get(it.next());
        }
        return user;
    }
}
