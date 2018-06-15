
public class Exponencial extends Atomo {
	public boolean setAtomo(String atomo) {
		// TODO Auto-generated method stub
		NumeroInteiro inteiro = new NumeroInteiro();
		NumeroReal real = new NumeroReal();
		String temp = "";
		for (int i = 0; i < atomo.length(); i++) {
			if (!verificaAlfabeto(atomo.charAt(i))){
				temp += atomo.charAt(i);
			}
			
			else if (inteiro.setAtomo(temp)) {
				atomoArmazenado += inteiro.getAtomo();
			}

			else if (real.setAtomo(temp)) {
				atomoArmazenado += real.getAtomo();
			}
			if(atomo.charAt(i) == 'e' || atomo.charAt(i) == 'E'){
				switch(atomo.charAt(i)){
					case 'e':
						atomoArmazenado += 'e';
						break;
					case 'E':
						atomoArmazenado += 'E';
						break;
				}
				String exponencial = "";
				while(i < atomo.length()-1){
					i++;
					if(verificaAlfabeto(atomo.charAt(i)))
						return false;
					exponencial += atomo.charAt(i);
				}
				if(inteiro.setAtomo(exponencial)){
					atomoArmazenado += inteiro.getAtomo();
					return true;
				}
			}
		}
		atomoArmazenado = "";
		return false;
	}
	public String getAtomo(){
		return atomoArmazenado;
	}
}
