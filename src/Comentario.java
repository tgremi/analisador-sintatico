
public class Comentario extends Atomo {

	@Override
	public boolean setAtomo(String atomo) {
		// TODO Auto-generated method stub
		boolean abreAspas = true;
		if (atomo.length() >= 4) {
			if (atomo.charAt(0) != '/')
				return false;
			if (atomo.charAt(1) != '*')
				return false;
			if (atomo.charAt(atomo.length() - 1) != '/')
				return false;
			if (atomo.charAt(atomo.length() - 2) != '*')
				return false;
			if (abreAspas)
				this.atomoArmazenado = atomo;
			return true;
		}
		return false;

	}

}
