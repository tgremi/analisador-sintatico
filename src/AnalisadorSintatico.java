import java.util.ArrayList;

public class AnalisadorSintatico {
	Identificador identificador = new Identificador();
	Comentario comentario = new Comentario();
	NumeroInteiro inteiro = new NumeroInteiro();
	NumeroReal real = new NumeroReal();
	Frase frase = new Frase();
	OperadorLogico op_logico = new OperadorLogico();
	OperadorRelacional op_relacional = new OperadorRelacional();
	PalavraReservada palavraReservada = new PalavraReservada();
	AtomoSemAtributo atomoSemAtributo = new AtomoSemAtributo();
	Exponencial exponencial = new Exponencial();
	private int index = 0;
	ArrayList<Atomo> atomos = new ArrayList<Atomo>();
	ArrayList<Atomo> identificadores = new ArrayList<Atomo>();

	public AnalisadorSintatico(ArrayList<Atomo> atomos) {
		this.atomos = atomos;
		for (Atomo atomo : atomos) {
			if (atomo instanceof Identificador) {
//				System.out.println(atomo + "<-- no analisador sintatico" + index + "<== index");
				identificadores.add(atomos.get(index));
			}
		}
		algoritmo();
	}

	public void algoritmo() {
		System.out.println("ALGORITMO");
		if (atomos.get(index).atomoArmazenado.equals("ALGORITMO")) {
			identificador();
			bloco();
		}
		encerrar();
	}

	public void encerrar() {
		System.out.println("ERRO");
		System.exit(0);
	}

	public void bloco() {
		System.out.println("BLOCO");
		declaracao_de_variaveis();
		declaracao_de_modulo();
	}

	public void declaracao_de_variaveis() {
		System.out.println("DECLARACAO DE VARIAVEIS");
		System.out.println("Atomo testado = " + atomos.get(index).atomoArmazenado);
		tipo_simples();
		lista_de_identificadores();
		return;
	}

	public void tipo_simples() {
		System.out.println("TIPO SIMPLES");
		System.out.println(atomos.get(index).atomoArmazenado.toUpperCase() + "<- index");
		 index++;
		System.out.println("Atomo testado = " + atomos.get(index).atomoArmazenado);

		if (atomos.get(index).atomoArmazenado.toUpperCase().equals("INTEIRO")
				|| atomos.get(index).atomoArmazenado.toUpperCase().equals("REAL")) {
			return;
		}
		encerrar();
	}

	public void lista_de_identificadores() {
		System.out.println("LISTA DE IDENTIFICADORES");
		index++;
		System.out.println("Atomo testado = " + atomos.get(index).atomoArmazenado);
		if (atomos.get(index) instanceof Identificador) {
			index++;
			if (atomos.get(index).atomoArmazenado.equals(",")) {
				declaracao_de_variaveis();
			}
			return;
		}
	}

	public void declaracao_de_modulo() {
		System.out.println("DECLARACAO DE MODULO");
		declaracao_de_funcao();
	}

	public void declaracao_de_funcao() {
		System.out.println("DECLARACAO DE FUNCAO");
		System.out.println("Atomo testado = " + atomos.get(index).atomoArmazenado);
		if (atomos.get(index).atomoArmazenado.toUpperCase().equals("FUNCAO")) {
			tipo_simples();
			System.out.println("DECLARACAO DE FUNCAO");
			identificador();
			System.out.println("DECLARACAO DE FUNCAO");
			parametros_formais();
			System.out.println("DECLARACAO DE FUNCAO");
			index++;
			inicio();
			System.out.println("DECLARACAO DE FUNCAO");
			comando_composto();
			System.out.println("DECLARACAO DE FUNCAO");
			index++;
			fim();
			declaracao_de_funcao();
		}
	}

	public void declaracao_de_procedimento() {
		System.out.println("DECLARACAO DE PROCEDIMENTO");
		index++;
		System.out.println("Atomo testado = " + atomos.get(index).atomoArmazenado);
		if (atomos.get(index).atomoArmazenado.equals("procedimento")) {
			identificador();
			System.out.println("DECLARACAO DE PROCEDIMENTO");
			parametros_formais();
			System.out.println("DECLARACAO DE PROCEDIMENTO");
			declaracao_de_variaveis();
			System.out.println("DECLARACAO DE PROCEDIMENTO");
			inicio();
			System.out.println("DECLARACAO DE PROCEDIMENTO");
			comando_composto();
			System.out.println("DECLARACAO DE PROCEDIMENTO");
			fim();
			declaracao_de_procedimento();
		}
	}

	public void comando_composto() {
		System.out.println("COMANDO COMPOSTO");
		comando();
		System.out.println("COMANDO COMPOSTO");
		index++;
		System.out.println("Atomo = " + atomos.get(index).atomoArmazenado);
		if (atomos.get(index).atomoArmazenado.equals(";")) {
			return;
		}
		comando_composto();
	}

	public void comando() {
		index += 2;
		System.out.println("COMANDO");
		if (atomos.get(index).atomoArmazenado.equals("=")) {
			index -= 2;
			comando_atribuicao();
			return;
		}
		index--;
		if (atomos.get(index) instanceof Identificador) {
			index--;
			chamada_modulo();
			return;
		}
		if (atomos.get(index).atomoArmazenado.toUpperCase().equals("ENTÃO")) {
			index--;
			chamando_se();
			return;
		}
		if (atomos.get(index).atomoArmazenado.toUpperCase().equals("ENQUANTO")) {
			index--;
			chamando_enquanto();
			return;
		} else {
			index--;
			chamando_retorne();
			return;
		}
	}

	public void chamando_retorne() {
		System.out.println("COMANDO RETORNE");
		expressao();
		System.out.println(atomos.get(index).atomoArmazenado);
		return;
	}

	public void chamando_se() {
		System.out.println("CHAMANDO SE");
		index++;
		System.out.println(atomos.get(index).atomoArmazenado);
		if (atomos.get(index).atomoArmazenado.toUpperCase().equals("(")) {
			expressao();
			index++;
			if (atomos.get(index).atomoArmazenado.toUpperCase().equals(")")) {
				comando_composto();
				index++;
				if (atomos.get(index).atomoArmazenado.toUpperCase().equals("ENTÃO")) {
					comando_composto();
					index++;
					if (atomos.get(index).atomoArmazenado.toUpperCase().equals("SENÃO"))
						comando_composto();
					else if (atomos.get(index).atomoArmazenado.toUpperCase().equals("FIM")) {
						index++;
						if (atomos.get(index).atomoArmazenado.equals("-")) {
							index++;
							if (atomos.get(index).atomoArmazenado.toUpperCase().equals("SE")) {
								return;
							}
						}
					}
				}
			}
		}
		encerrar();
	}

	public void chamando_enquanto() {
		System.out.println("CHAMANDO ENQUANTO");
		index++;
		System.out.println(atomos.get(index).atomoArmazenado);
		if (atomos.get(index).atomoArmazenado.equals("(")) {
			expressao();
			index++;
			if (atomos.get(index).atomoArmazenado.equals(")")) {
				index++;
				if (atomos.get(index).atomoArmazenado.toUpperCase().equals("FAÇA")) {
					comando_composto();
					if (atomos.get(index).atomoArmazenado.toUpperCase().equals("FIM"))
						return;
				}
			}
		}
		encerrar();
	}

	public void chamada_modulo() {
		System.out.println("CHAMADA MODULO");
		identificador();
		System.out.println("CHAMADA MODULO");
		index++;
		System.out.println("Atomo testado = " + atomos.get(index).atomoArmazenado);
		if (atomos.get(index).atomoArmazenado.equals("(")) {
			lista_expressao();
			System.out.println("CHAMADA MODULO");
			index++;
			if (atomos.get(index).atomoArmazenado.equals(")"))
				return;
		}
		encerrar();
	}

	private void lista_expressao() {
		System.out.println("LISTA EXPRESSAO");
		expressao();
		index++;
		System.out.println("Atomo testado = " + atomos.get(index).atomoArmazenado);
		if (atomos.get(index).atomoArmazenado.equals(",")) {
			expressao();
		}
		index--;
		return;
	}

	public void comando_atribuicao() {
		System.out.println("COMANDO ATRIBUICAO");
		identificador();
		System.out.println("COMANDO ATRIBUICAO");
		index++;
		System.out.println("Atomo testado = " + atomos.get(index).atomoArmazenado);
		if (atomos.get(index).atomoArmazenado.equals("=")) {
			expressao();
			System.out.println("COMANDO ATRIBUICAO");
			return;
		}
		index--;
		encerrar();
	}

	public void expressao() {
		System.out.println("EXPRESSAO");
		expressao_simples();
		System.out.println("EXPRESSAO");
		comando_logico();
		System.out.println("EXPRESSAO");
		return;
	}

	public void comando_logico() {
		System.out.println("COMANDO LOGICO");
		index++;
		System.out.println("Atomo testado = " + atomos.get(index).atomoArmazenado);
		if ((atomos.get(index).atomoArmazenado.equals("&")) && (atomos.get(index).atomoArmazenado.equals("$")))
			expressao();
		index--;
		return;
	}

	public void expressao_simples() {
		System.out.println("EXPRESSAO SIMPLES");
		termo_relacional();
		System.out.println("EXPRESSAO SIMPLES");
		operador_relacional();
		System.out.println("EXPRESSAO SIMPLES");
		return;
	}

	public void operador_relacional() {

		System.out.println("OPERADOR RELACIONAL");
		index++;
		System.out.println("Atomo testado = " + atomos.get(index).atomoArmazenado);
		if ((atomos.get(index).atomoArmazenado.equals("<>")) && (atomos.get(index).atomoArmazenado.equals("<"))
				&& (atomos.get(index).atomoArmazenado.equals(">")) && (atomos.get(index).atomoArmazenado.equals("<="))
				&& (atomos.get(index).atomoArmazenado.equals(">="))
				&& (atomos.get(index).atomoArmazenado.equals("="))) {
			expressao_simples();
		}
		return;

	}

	public void inicio() {
		System.out.println("INICIO");
		System.out.println("Atomo testado = " + atomos.get(index).atomoArmazenado);
		if (atomos.get(index).atomoArmazenado.toUpperCase().equals("INICIO"))
			return;
		encerrar();
	}

	public void fim() {
		System.out.println("FIM");
		System.out.println("Atomo testado = " + atomos.get(index).atomoArmazenado);
		if (atomos.get(index).atomoArmazenado.toUpperCase().equals("FIM")) {
			System.out.println("FIM DE EXECUÇÃO");
			System.exit(0);
		}
		return;
	}

	public void parametros_formais() {
		System.out.println("PARAMETROS FORMAIS");
		index++;
		System.out.println(atomos.get(index).atomoArmazenado);
		if (atomos.get(index).atomoArmazenado.equals("(")) {
			sessao_parametros();
			System.out.println("PARAMETROS FORMAIS");
			System.out.println(atomos.get(index).atomoArmazenado);
			if (atomos.get(index).atomoArmazenado.equals(")"))
				return;
		}
		encerrar();
	}

	public void sessao_parametros() {
		System.out.println("SESSAO PARAMETROS");
		tipo_simples();
		System.out.println("SESSAO PARAMETROS");
		lista_de_identificadores();
		return;
	}

	public void identificador() {
		System.out.println("IDENTIFICADOR");
		index++;
		System.out.println("Atomo testado = " + atomos.get(index).atomoArmazenado);
		if (atomos.get(index) instanceof Identificador)
			return;
		encerrar();
	}

	public void termo_relacional() {
		System.out.println("TERMO RELACIONAL");
		termo();
		System.out.println("TERMO RELACIONAL");
		operadorAdicao();
		System.out.println("TERMO RELACIONAL");
		return;
	}

	public void operadorAdicao() {
		System.out.println("OPERADOR ADICAO");
		index++;
		System.out.println("Atomo testado = " + atomos.get(index).atomoArmazenado);
		if ((atomos.get(index).atomoArmazenado.equals("+")) || (atomos.get(index).atomoArmazenado.equals("+")))
			termo_relacional();
		index--;
		return;
	}

	public void termo() {
		System.out.println("TERMO");
		fator();
		System.out.println("TERMO");
		operador_multiplicacao();
		System.out.println("TERMO");
		return;
	}

	public void operador_multiplicacao() {
		System.out.println("OPERADOR MULTIPLICACAO");
		index++;
		System.out.println("Atomo testado = " + atomos.get(index).atomoArmazenado);
		if ((atomos.get(index).atomoArmazenado.equals("*")) || (atomos.get(index).atomoArmazenado.equals("/"))
				|| (atomos.get(index).atomoArmazenado.equals("%")))
			termo();
		index--;
		return;
	}

	public void fator() {
		System.out.println("FATOR");
		index++;
		System.out.println("Atomo testado = " + atomos.get(index).atomoArmazenado);
		if (atomos.get(index) instanceof Identificador) {
			index++;
			if (atomos.get(index).atomoArmazenado.equals("(")) {
				lista_expressao();
				index++;
				if (atomos.get(index).atomoArmazenado.equals(")")) {
					encerrar();
				}
				encerrar();
			}
			index--;
			return;
		} else if (atomos.get(index) instanceof NumeroInteiro) {
			return;
		} else if (atomos.get(index) instanceof NumeroReal) {
			return;
		} else if (atomos.get(index) instanceof Frase) {
			return;
		} else if (atomos.get(index).atomoArmazenado.equals("!")) {
			fator();
		} else if (atomos.get(index).atomoArmazenado.equals("-")) {
			fator();
		} else if (atomos.get(index).atomoArmazenado.equals("(")) {
			expressao();
			index++;
			if (atomos.get(index).atomoArmazenado.equals(")")) {
				return;
			}
			encerrar();
		}
		encerrar();
	}
}
