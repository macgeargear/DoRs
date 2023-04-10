package buildings;

public class Place {
	private boolean isActive;
	public Place() {
		this.setActive(true);
	}
	
	public void handleIsActive() {
		this.setActive(!isActive);
	}
	

	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
