
public class PalavraReservada extends Atomo {

	@Override
	public boolean setAtomo(String atomo) {
		// TODO Auto-generated method stub
		atomo = atomo.toUpperCase();
		String palavrasReservadas[] = {"ALGORITMO", "VETOR", "ATE", "CADEIA", "CARACTER", "ENQUANTO", "ENTAO", "FACA", "FIM",
				"FUNCAO", "INICIO", "INTEIRO", "PARA", "PASSO", "PROCEDIMENTO", "REAL", "REF", "RETORNE", "SE", "SENAO",
				"VARIAVEIS"};
		for (int i = 0; i < palavrasReservadas.length; i++) {
			if (atomo.equals(palavrasReservadas[i])) {
				this.atomoArmazenado = palavrasReservadas[i];
				return true;
			}
		}
		return false;
	}

}
