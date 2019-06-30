package com.emanuel.cursocm;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.emanuel.cursocm.domain.Categoria;
import com.emanuel.cursocm.domain.Cidade;
import com.emanuel.cursocm.domain.Cliente;
import com.emanuel.cursocm.domain.Endereco;
import com.emanuel.cursocm.domain.Estado;
import com.emanuel.cursocm.domain.Produto;
import com.emanuel.cursocm.enuns.TipoCliente;
import com.emanuel.cursocm.repositories.CategoriaRepository;
import com.emanuel.cursocm.repositories.CidadeRepository;
import com.emanuel.cursocm.repositories.ClienteRepository;
import com.emanuel.cursocm.repositories.EnderecoRepository;
import com.emanuel.cursocm.repositories.EstadoRepository;
import com.emanuel.cursocm.repositories.ProdutoRepository;

@SpringBootApplication
public class CursocmApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CursocmApplication.class, args);
	}

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Teclado", 48.50);
		Produto p2 = new Produto(null, "Cadeira", 150.00);
		Produto p3 = new Produto(null, "Mouse", 20.79);
		
		cat1.setProdutos(Arrays.asList(p1, p3));
		cat2.setProdutos(Arrays.asList(p2));
		
		p1.setCategorias(Arrays.asList(cat1));
		p2.setCategorias(Arrays.asList(cat2));
		p3.setCategorias(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2 ,p3));
		
		Estado estado1 = new Estado(null, "Paraíba");
		Estado estado2 = new Estado(null, "Pernambuco");
		
		Cidade cidade1 = new Cidade(null, "João Pessoa", estado1);
		Cidade cidade2 = new Cidade(null, "Sousa", estado1);
		Cidade cidade3 = new Cidade(null, "Recife", estado2);
		
		estado1.getCidades().addAll(Arrays.asList(cidade1, cidade2));
		estado2.getCidades().addAll(Arrays.asList(cidade3));
		
		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));
		
		Cliente cliente1 = new Cliente(null, "ze", "ze@gmail.com", "123456789", TipoCliente.PESSOAFISICA);
		Cliente cliente2 = new Cliente(null, "ju", "ju@gmail.com", "987654321", TipoCliente.PESSOAFISICA);
		
		cliente1.getTelefones().addAll(Arrays.asList("88888888","55555555"));
		cliente2.getTelefones().addAll(Arrays.asList("44444444"));
		
		Endereco endereco1 = new Endereco(null, "rua", "654", "xica", "bancarios", "58055000", cidade1);
		
		endereco1.setCliente(cliente1);
		
		clienteRepository.saveAll(Arrays.asList(cliente1, cliente2));
		
		enderecoRepository.saveAll(Arrays.asList(endereco1));
	}

}

