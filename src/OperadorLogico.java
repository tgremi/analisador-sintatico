
public class OperadorLogico extends Atomo {

	@Override
	public boolean setAtomo(String atomo) {
		// TODO Auto-generated method stub
		//& = AND, $ = OR e ! = NOT
		String operadores[] = { "&", "$", "!" };
		for (int i = 0; i < operadores.length; i++) {
			if (atomo.equals(operadores[i])) {
				this.atomoArmazenado = operadores[i];
				return true;
			}
		}
		return false;
	}

}
