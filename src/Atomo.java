
public abstract class Atomo {
	protected String atomoArmazenado = "";
	int ascii = 0;
	
	public String getAtomo() {
		return atomoArmazenado;
	}

	public Boolean detectarCaracteresEspeciais(String atomo) {
		resetAscii();
		for (int i = 0; i < atomo.length(); i++) {
			if (verificaCaractereEspecial(atomo.charAt(i)))
				return true;
		}
		return false;
	}
	public Boolean verificaCaracteres(String atomo) {
		resetAscii();
		for (int i = 0; i < atomo.length(); i++) {
			if (verificaAlfabeto(atomo.charAt(i)))
				return true;
		}
		return false;
	}
	public Boolean verificaCaractereEspecial(char atomo) {
		resetAscii();
		if ((!verificaAlfabeto(atomo)) && (!verificaNumero(atomo)))
			return true;
		return false;
	}
	public Boolean verificaAlfabeto(char atomo) {
		resetAscii();
		ascii = atomo;
		if ((ascii >= 65 && ascii <= 90) || (ascii >= 97 && ascii <= 122)){
			return true;
		}
		return false;
	}
	public Boolean verificaNumero(char atomo) {
		resetAscii();
		ascii = atomo;
		if ((ascii >= 48 && ascii <= 57))
			return true;
		return false;
	}
	public abstract boolean setAtomo(String atomo);
	protected void resetAscii(){
		this.ascii = 0;
	}
}
