/* A mole */
public class Mole {
	Slot pos; //determine which slot position
	boolean scored; //determine if player hit
	int score; //how many points to award
	
	public Mole(Slot pos, boolean scored, int score) {
		super();
		this.pos = pos;
		this.scored = scored;
		this.score = score;
	}
	
	// add to player's score if hit
	void addScore() {
	 // to-do	
	}
	
	// delete the mole not sure if needed yet
	void delete() {
	// to-do
	}
	

}
