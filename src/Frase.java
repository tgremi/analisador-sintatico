
public class Frase extends Atomo {

	@Override
	public boolean setAtomo(String atomo) {
		// TODO Auto-generated method stub
		boolean abreAspas = true;
		if (atomo.length() >= 3) {
			if (atomo.charAt(0) != '\\')
				return false;
			if (atomo.charAt(1) != '"')
				return false;
			for (int i = 0; i < atomo.length(); i++) {
				if (atomo.charAt(i) == '"' && !(abreAspas)) {
					abreAspas = true;
					break;
				}
				if (atomo.charAt(i) == '"')
					abreAspas = false;
			}
			if (abreAspas)
				this.atomoArmazenado = atomo;
			return abreAspas;
		}
		return false;
	}
}
