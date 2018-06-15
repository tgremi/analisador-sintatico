
public class NumeroReal extends Atomo implements Numero {

	@Override
	public boolean setAtomo(String atomo) {
		// TODO Auto-generated method stub
		boolean verificador = false;
		for (int i = 0; i < atomo.length(); i++) {
			if(verificaAlfabeto(atomo.charAt(i)))
				return false;
			if(verificaCaractereEspecial(atomo.charAt(i)) && atomo.charAt(i) != 46)
				return false;
			if(verificaNumeroReal(atomo))
				verificador = true;
		}
		if(verificador)
			this.atomoArmazenado = atomo;
		return verificador;
	}
	public boolean verificaNumeroReal(String atomo) {
		resetAscii();
		boolean verificador = false,  ponto = false;
		for (int i = 0; i < atomo.length(); i++) {
			ascii = atomo.charAt(i);
			if ((ascii == 46 && ponto) || ((ascii == 46) && (i == atomo.length()-1))){
				return false;
			}
			if (ascii == 46)
				ponto = true;
			if ((ascii >= 48 && ascii <= 57))
				verificador = true;
		}
		return verificador;
	}
}
