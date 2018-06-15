import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Arquivo {
	public Arquivo(String caminho) throws IOException {
		AnalisadorLexico lexico = new AnalisadorLexico();
		// Leitura do arquivo externo
		BufferedReader br = new BufferedReader(new FileReader(caminho));
		while (br.ready()) {
			String linha = br.readLine();
			System.out.println("linha lida = " + linha);
			System.out.println("[ " + lexico.getNumeroLinha() + "]  " + " - " + lexico.linha(linha));
			for (int i = 0; i < lexico.atomos.size(); i++) {
//				System.out.println(lexico.atomos.get(i));	
			}
			
//			AnalisadorSintatico analisadorSintatico = new AnalisadorSintatico(lexico.atomos);

		}
		System.out.println("\n--------------------------------\n");
		br.close();
	}
}
