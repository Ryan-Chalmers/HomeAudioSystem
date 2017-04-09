package ca.mcgill.ecse321.HomeAudioSystem.model;

public enum Genre 
{
	ROCK {
		public String toString(){
			return "Rock";
		}
	},

	HIPHOP {
		public String toString(){
			return "HipHop";
		}
	},

	POP {
		public String toString(){
			return "Pop";
		}
	},

	COUNTRY {
		public String toString(){
			return "Country";
		}
	},

	DANCE {
		public String toString(){
			return "Dance";
		}
	},

	ELECTRONIC {
		public String toString(){
			return "Electronic";
		}
	},

	JAZZ {
		public String toString(){
			return "Jazz";
		}
	},

	CLASSICAL {
		public String toString(){
			return "Classical";
		}
	},

	HOUSE {
		public String toString(){
			return "House";
		}
	},

	OTHERS {
		public String toString(){
			return "Others";
		}
	}
}
