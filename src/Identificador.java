
public class Identificador extends Atomo {

	@Override
	public boolean setAtomo(String atomo) {
		// TODO Auto-generated method stub
		PalavraReservada palavra = new PalavraReservada();
		if (detectarCaracteresEspeciais(atomo)){// Verifica se o Idendificador
												// começa com alguma letra do
												// alfabeto
			return false;
		}
		if (verificar(atomo)) {// Verifica se o Idendificador começa com alguma
								// letra do alfabeto
			if (palavra.setAtomo(atomo)) {
				this.atomoArmazenado = palavra.getAtomo();
				return false;
			}
			this.atomoArmazenado = atomo;
			return true;
		}
		return false;
	}

	private boolean verificar(String atomo) {
		boolean verificador = false;
		for (int i = 0; i < atomo.length(); i++) {
			if (verificaAlfabeto(atomo.charAt(0)))
				verificador = true;
			if (verificaCaractereEspecial(atomo.charAt(i)))
				return false;
		}
		return verificador;
	}

}
