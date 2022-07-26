package hello.core.lifecycle;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        conncect();
        call("초기화 연결 메세지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void conncect(){
        System.out.println("conncect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + ", meesage = " + message);
    }

    //서비스 종료시 호출
    public void disconnect(){
        System.out.println("close " + url);
    }



}
