package prob02;

public class SmartPhone extends MusicPhone {
	public void execute(String function) {
		if (function.equals("음악")) {
			playMusic();
			return;
		}else if(function.equals("앱")) {
			runApp();
			return;
		}
		super.execute(function);
	}
	public void playMusic() {
		System.out.println("다운로드해서 음악재생");
	}
	public void runApp() {
		System.out.println("\n앱실행");
	}
}
