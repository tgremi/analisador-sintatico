
public class NumeroInteiro extends Atomo implements Numero {

	@Override
	public boolean setAtomo(String atomo) {
		// TODO Auto-generated method stub
		boolean verificador = false;
		for (int i = 0; i < atomo.length(); i++) {
			if(verificaAlfabeto(atomo.charAt(i)))
				return false;
			if(verificaCaractereEspecial(atomo.charAt(i)) || atomo.charAt(i) == 46)
				return false;
			if(verificaNumero(atomo.charAt(i)))
				verificador = true;
		}
		if(verificador)
			this.atomoArmazenado = atomo;
		return verificador;
	}
}
