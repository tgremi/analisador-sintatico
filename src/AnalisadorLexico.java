import java.util.ArrayList;

public class AnalisadorLexico {
	private int numeroLinha = 0;
	private int contador = 0;
	private String atomo = "";

	private boolean comentarioAberto = false;

	public ArrayList<Atomo> atomos = new ArrayList<Atomo>();

	public String linha(String linha) {
		String saida = "";
		for (int i = 0; i < linha.length(); i++) {
			if (comentarioAberto) {
				saida += linha.charAt(i);
				if (i > 0 && linha.charAt(i - 1) == '*' && linha.charAt(i) == '/') {
					comentarioAberto = false;
					saida += " ";
				}
			} else if (linha.charAt(i) != ' ') {
				atomo = "";
				contador = i;
				if (comentario(linha)) {
					try {
						// System.out.println(this.atomo + "<---- Nois aqui");
						// System.out.println("Retorno do addAtomo " + adicionaAtomo(this.atomo));
						atomos.add(adicionaAtomo(this.atomo));
						//
					} catch (AtomoInvalidoException e) {
						// TODO Auto-generated catch block
						char aspas = '"';
						System.out.println(
								"Syntax ERROR - linha " + this.numeroLinha + ", atomo = " + aspas + atomo + aspas);
						e.printStackTrace();
					}
					// contador++;
					i = contador;
					atomo = "";
				} else if (i < linha.length() && !comentarioAberto) {
					while (linha.charAt(i) != ' ') {
						atomo += linha.charAt(i);
						// System.out.println("atomo -> " + atomo);
						if (i + 1 == linha.length()) {
							break;
						}
						i++;
					}

					try {
						// System.out.println(this.atomo + "<---- Nois aqui");
						// System.out.println("Retorno do addAtomo " + adicionaAtomo(this.atomo));
						atomos.add(adicionaAtomo(this.atomo));

					} catch (AtomoInvalidoException e) {
						// TODO Auto-generated catch block
						char aspas = '"';
						System.out.println(
								"Syntax ERROR - linha " + this.numeroLinha + ", atomo = " + aspas + atomo + aspas);
						e.printStackTrace();
						break;
					}
				} else
					i--;
				// saida += atomo;
			}
			if (i == linha.length()) {
				break;
			}
		}
		numeroLinha++;
		return saida;
	}

	private String adicionaAtomo1() throws AtomoInvalidoException {
		String saida = "";
		boolean verifica = false;
		Identificador idendificador = new Identificador();
		Comentario comentario = new Comentario();
		NumeroInteiro inteiro = new NumeroInteiro();
		NumeroReal real = new NumeroReal();
		Frase frase = new Frase();
		OperadorLogico op_logico = new OperadorLogico();
		OperadorRelacional op_relacional = new OperadorRelacional();
		PalavraReservada palavraReservada = new PalavraReservada();
		AtomoSemAtributo atomoSemAtributo = new AtomoSemAtributo();
		Exponencial exponencial = new Exponencial();

		if (palavraReservada.setAtomo(atomo)) {
			saida += palavraReservada.getAtomo();
			saida += " ";
			System.out.println("Palavra Reservada = " + atomo);
			verifica = true;
		}

		if (atomoSemAtributo.setAtomo(atomo)) {
			saida += atomoSemAtributo.getAtomo();
			saida += " ";
			System.out.println("Atomo Sem Atributo = " + atomoSemAtributo.getAtomo());
			verifica = true;
		}

		if (idendificador.setAtomo(atomo)) {
			saida += idendificador.getAtomo();
			saida += " ";
			System.out.println("Indendificador = " + atomo);
			verifica = true;
		}

		if (comentario.setAtomo(atomo)) {
			saida += comentario.getAtomo();
			saida += " ";
			System.out.println("comentario = " + atomo);
			verifica = true;
		}
		if (frase.setAtomo(atomo)) {
			saida += frase.getAtomo();
			saida += " ";
			System.out.println("frase = " + atomo);
			verifica = true;
		}

		if (inteiro.setAtomo(atomo)) {
			saida += inteiro.getAtomo();
			saida += " ";
			System.out.println("inteiro = " + atomo);
			verifica = true;
		} else if (real.setAtomo(atomo)) {
			saida += real.getAtomo();
			saida += " ";
			System.out.println("real = " + atomo);
			verifica = true;
		}
		if (op_logico.setAtomo(atomo)) {
			saida += op_logico.getAtomo();
			saida += " ";
			System.out.println("operador logico = " + atomo);
			verifica = true;
		}
		if (op_relacional.setAtomo(atomo)) {
			saida += op_relacional.getAtomo();
			saida += " ";
			System.out.println("operador relacional = " + atomo);
			verifica = true;
		}
		if (exponencial.setAtomo(atomo)) {
			saida += exponencial.getAtomo();
			saida += " ";
			System.out.println("exponencial = " + atomo);
			verifica = true;
		}
		if (!verifica) {
			AtomoInvalidoException erro = new AtomoInvalidoException();
			throw erro;
		}

		return saida;
	}

	public Atomo adicionaAtomo(String atomo) throws AtomoInvalidoException {

		Identificador idendificador = new Identificador();
		Comentario comentario = new Comentario();
		NumeroInteiro inteiro = new NumeroInteiro();
		NumeroReal real = new NumeroReal();
		Frase frase = new Frase();
		OperadorLogico op_logico = new OperadorLogico();
		OperadorRelacional op_relacional = new OperadorRelacional();
		PalavraReservada palavraReservada = new PalavraReservada();
		AtomoSemAtributo atomoSemAtributo = new AtomoSemAtributo();
		Exponencial exponencial = new Exponencial();
		atomo = atomo.replaceAll("\t", "");
		if (atomo.contains(";")) {
			atomo = atomo.replaceAll("\\;", "");
		}
		System.out.println("Atomo sem espaco:  " + atomo);

		if (palavraReservada.setAtomo(atomo)) {
			// System.out.println("Palavra reservada " + palavraReservada.getAtomo());
			// saida += palavraReservada.getAtomo();
			// saida += " ";
			// System.out.println("Palavra Reservada = " + atomo);

			return palavraReservada;
		}

		if (atomoSemAtributo.setAtomo(atomo)) {
			// System.out.println("sem atributo " + atomo);
			return atomoSemAtributo;
		}

		if (idendificador.setAtomo(atomo)) {
			// System.out.println("identificador" + atomo);
			return idendificador;
		}
		if (comentario.setAtomo(atomo)) {
			// System.out.println("comentario" + atomo);
			return comentario;
		}

		if (frase.setAtomo(atomo))
			return frase;
		if (inteiro.setAtomo(atomo))
			return inteiro;
		else if (real.setAtomo(atomo))
			return real;
		if (op_logico.setAtomo(atomo)) {
			System.out.println("logico " + atomo);
			return op_logico;
		}

		if (op_relacional.setAtomo(atomo)) {
			System.out.println("relacional " + atomo);
			return op_relacional;
		}

		if (exponencial.setAtomo(atomo))
			return exponencial;
		else {
			AtomoInvalidoException erro = new AtomoInvalidoException();
			throw erro;
		}
	}

	private boolean comentario(String linha) {
		if (linha.charAt(contador) == '/') {
			atomo += linha.charAt(contador);
			if (contador + 1 < linha.length()) {
				contador++;
			}
			if (linha.charAt(contador) == '*') {
				while (linha.charAt(contador) != '/') {
					atomo += linha.charAt(contador);
					if (linha.charAt(contador - 1) == '*' && linha.charAt(contador) == '/') {
						break;
					}
					if (contador + 1 == linha.length()) {
						break;
					}
					contador++;
				}
				comentarioAberto = true;
			}
			// System.out.println("atomo =" +atomo);
			if ((linha.charAt(contador) == '/')) {
				atomo += linha.charAt(contador);
				// System.out.println("saida ficou =" + atomo);
				// atomo += linha.charAt(j);
				return true;
			} else if ((linha.charAt(contador) == '"')) {
				atomo += linha.charAt(contador);
				return true;
			}
			atomo = "";

			// System.out.println("Ficou true");
		} else if (linha.charAt(contador) == '\\') {
			atomo += linha.charAt(contador);
			if (contador + 1 < linha.length()) {
				contador++;
			}
			atomo += linha.charAt(contador);
			if (linha.charAt(contador) == '"') {
				// atomo += linha.charAt(contador);
				if (contador + 1 < linha.length()) {
					contador++;
				}
				while (linha.charAt(contador) != '"') {
					atomo += linha.charAt(contador);
					if (contador + 1 == linha.length()) {
						break;
					}
					contador++;
				}
			}
			if ((linha.charAt(contador) == '"')) {
				atomo += linha.charAt(contador);
				// System.out.println("atomo = " +atomo);
				return true;
			}
		}
		atomo = "";
		return false;
	}

	public int getNumeroLinha() {
		return numeroLinha;
	}

}
