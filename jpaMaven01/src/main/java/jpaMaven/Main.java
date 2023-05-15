package jpaMaven;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		Programa principal = Programa.getInstance();

		// Cria uma nova pessoa
		Pessoa pessoa = new Pessoa();
		Pessoa pessoa2 = new Pessoa();
		Pessoa pessoa3 = new Pessoa();
		pessoa.setNome("João");
		pessoa.setIdade(30);
		pessoa.setEmail("joao@mail.com");
		pessoa2.setNome("Ana");
		pessoa2.setIdade(40);
		pessoa2.setEmail("ana@mail.com");
		pessoa3.setNome("Davi");
		pessoa3.setIdade(20);
		pessoa3.setEmail("davi@mail.com");
		

		// Insere a nova pessoa no banco de dados
		principal.inserir(pessoa);
		principal.inserir(pessoa2);
		principal.inserir(pessoa3);

		// Busca todas as pessoas cadastradas no banco de dados
		List<Pessoa> pessoas = principal.findAll();

		// Imprime o nome e a idade de todas as pessoas cadastradas 
		//estrutura de loop for-each em Java, que itera sobre uma coleção de objetos "pessoas" 
		//e atribui cada elemento da coleção à variável "p", um de cada vez
		for (Pessoa p : pessoas) {
			System.out.println(p.getNome() + " - " + p.getIdade()+ " - " + p.getEmail());
		}

		// Busca uma pessoa pelo seu id
		Pessoa pessoaEncontrada = principal.obterPorId(2);

		// Atualiza o nome e email da pessoa encontrada
		pessoaEncontrada.setNome("Maria");
		pessoaEncontrada.setEmail("maria@mail.com");

		// Atualiza a pessoa no banco de dados
		principal.atualizar(pessoaEncontrada);

		// Remove a pessoa encontrada pelo id
		principal.removerPeloId(3);
	}
}
