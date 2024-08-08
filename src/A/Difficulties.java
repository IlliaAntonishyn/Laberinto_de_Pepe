package A;

public enum Difficulties {
	
	TUTORIAL("Templates/TutorialTemplate.txt"),
	EASY("Templates/EasyTemplate.txt"),
	MEDIUM("Templates/MediumTemplate.txt"),
	HARD("Templates/HardTemplate.txt"),
	EXTREME("Templates/ExtremeTemplate.txt");
	
	public String url;
	
	private Difficulties(String url) {
		this.url = url;
	}
	
}
