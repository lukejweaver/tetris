package tetris;

import java.util.ArrayList;

public interface BlockBlueprints {
	
	ArrayList<String> blueprints = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;

		{
			add(TBLOCK);
			add(LBLOCK);
			add(IBLOCK);
			add(JBLOCK);
			add(ZBLOCK);
			add(SBLOCK);
			add(OBLOCK);
//			add(ABLOCK);
//			add(CBLOCK);
//			add(FBLOCK);
		}
	}; 

	String TBLOCK = "p"
				  + "----/"
			  	  + "-+--/"
			  	  + "+*+-/"
			  	  + "----";

	String LBLOCK = "o"
				  + "----/"
		  	  	  + "-+--/"
		  	  	  + "-*--/"
		  	  	  + "-++-";
	
	String IBLOCK = "b"
				  + "-+--/"
		  	      + "-+--/"
		  	      + "-*--/"
		  	      + "-+--";
	
	String JBLOCK = "d"
				  + "----/"
		  	      + "-+--/"
		  	      + "-*--/"
		  	      + "++--";
	
	String ZBLOCK = "r"
				  + "----/"
		  	      + "----/"
		  	      + "+*--/"
		  	      + "-++-";
	
	String SBLOCK = "g"
				  + "----/"
		  	      + "----/"
		  	      + "-*+-/"
		  	      + "++--";
	
	String OBLOCK = "y"
				  + "----/"
		  	      + "-++-/"
		  	      + "-++-/"
		  	      + "----";
	
	String CBLOCK = "p"
				  + "---+-/"
		  	      + "--+--/"
		  	      + "--*--/"
		  	      + "-+++-";
	
	String FBLOCK = "y"
				  + "+--+/"
		  	      + "-++-/"
		  	      + "-++-/"
		  	      + "+--+";
	
	String ABLOCK = "y"
				  + "----/"
		  	      + "-++-/"
		  	      + "+--+/"
		  	      + "-++-";
}
