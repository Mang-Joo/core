package hello.core.singleton;

public class SingletonService {

    /*자바 영역의 static 영역(하나만 생성 가능하다)*/
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
