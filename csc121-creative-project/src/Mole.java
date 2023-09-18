/* A mole */
public class Mole {
	Slot pos; //determine which slot position
	boolean active, scored; //used for game score
	int score; //how many points to award
	
	public Mole(Slot pos, boolean active, boolean scored, int score) {
		super();
		this.pos = pos;
		this.active = active;
		this.scored = scored;
		this.score = score;
	}
	
	
	

}
