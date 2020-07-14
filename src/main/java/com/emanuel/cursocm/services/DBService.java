package com.emanuel.cursocm.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.emanuel.cursocm.domain.Categoria;
import com.emanuel.cursocm.domain.Cidade;
import com.emanuel.cursocm.domain.Cliente;
import com.emanuel.cursocm.domain.Endereco;
import com.emanuel.cursocm.domain.Estado;
import com.emanuel.cursocm.domain.ItemPedido;
import com.emanuel.cursocm.domain.Pagamento;
import com.emanuel.cursocm.domain.PagamentoComBoleto;
import com.emanuel.cursocm.domain.PagamentoComCartao;
import com.emanuel.cursocm.domain.Pedido;
import com.emanuel.cursocm.domain.Produto;
import com.emanuel.cursocm.enuns.EstadoPagamento;
import com.emanuel.cursocm.enuns.Perfil;
import com.emanuel.cursocm.enuns.TipoCliente;
import com.emanuel.cursocm.repositories.CategoriaRepository;
import com.emanuel.cursocm.repositories.CidadeRepository;
import com.emanuel.cursocm.repositories.ClienteRepository;
import com.emanuel.cursocm.repositories.EnderecoRepository;
import com.emanuel.cursocm.repositories.EstadoRepository;
import com.emanuel.cursocm.repositories.ItemPedidoRepository;
import com.emanuel.cursocm.repositories.PagamentoRepository;
import com.emanuel.cursocm.repositories.PedidoRepository;
import com.emanuel.cursocm.repositories.ProdutoRepository;

@Service
public class DBService {
	
	@Autowired
	private BCryptPasswordEncoder bCrypt;
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
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public void instantiateTestDataBase() throws ParseException {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "ACategoria");
		Categoria cat4 = new Categoria(null, "BCategoria");
		Categoria cat5 = new Categoria(null, "CCategoria3");
		Categoria cat6 = new Categoria(null, "DCategoria3");
		Categoria cat7 = new Categoria(null, "ECategoria3");
		Categoria cat8 = new Categoria(null, "FCategoria3");
		Categoria cat9 = new Categoria(null, "GCategoria3");
		Categoria cat10 = new Categoria(null, "HCategoria3");
		
		Produto p1 = new Produto(null, "Tecladoz", 48.50);
		Produto p2 = new Produto(null, "Cadeira", 150.00);
		Produto p3 = new Produto(null, "Mousez", 20.00);
		
		cat1.setProdutos(Arrays.asList(p1, p3));
		cat2.setProdutos(Arrays.asList(p2));
		
		p1.setCategorias(Arrays.asList(cat1));
		p2.setCategorias(Arrays.asList(cat2));
		p3.setCategorias(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2,cat3, cat4, cat5,cat6, cat7, cat8,cat9, cat10));
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
		
		Cliente cliente1 = new Cliente(null, "ze", "ze@gmail.com", "123456789", TipoCliente.PESSOAFISICA, bCrypt.encode("teste"));
		Cliente cliente2 = new Cliente(null, "ju", "ju@gmail.com", "987654321", TipoCliente.PESSOAFISICA, bCrypt.encode("teste"));
		Cliente cliente3 = new Cliente(null, "ju", "cliente3@gmail.com", "987654321", TipoCliente.PESSOAFISICA, bCrypt.encode("teste"));
		
		cliente3.addPerfil(Perfil.ADMIN);
		
		cliente1.getTelefones().addAll(Arrays.asList("88888888","55555555"));
		cliente2.getTelefones().addAll(Arrays.asList("44444444"));
		cliente3.getTelefones().addAll(Arrays.asList("44444444"));
		
		Endereco endereco1 = new Endereco(null, "rua", "654", "xica", "bancarios", "58055000", cidade1);
		Endereco endereco3 = new Endereco(null, "rua", "654", "xica", "bancarios", "58055000", cidade3);
		
		endereco1.setCliente(cliente1);
		endereco3.setCliente(cliente3);
		
		clienteRepository.saveAll(Arrays.asList(cliente1, cliente2, cliente3));
		
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy HH:mm");
		
		Pedido pedido1 = new Pedido(null, sdf.parse("01/07/2019 12:45"), endereco1, cliente1);
		Pedido pedido2 = new Pedido(null, sdf.parse("02/07/2019 11:27"), endereco1, cliente2);
		
		Pagamento pag1 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, pedido1, sdf.parse("04/07/2019 12:00"), null);
		pedido1.setPagamento(pag1);
		
		Pagamento pag2 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, pedido2, 6);
		pedido2.setPagamento(pag2);
		
		cliente1.getPedidos().addAll(Arrays.asList(pedido1));
		cliente2.getPedidos().addAll(Arrays.asList(pedido2));
		
		pedidoRepository.saveAll(Arrays.asList(pedido1,pedido2));
		pagamentoRepository.saveAll(Arrays.asList(pag1,pag2));
		
		ItemPedido ip1 = new ItemPedido(pedido1, p1, 0.00, 1, 48.50);
		ItemPedido ip2 = new ItemPedido(pedido2, p2, 2.00, 2, 200.00);
		ItemPedido ip3 = new ItemPedido(pedido1, p3, 3.00, 3, 60.00);
		
		pedido1.getItens().addAll(Arrays.asList(ip1, ip3));
		pedido2.getItens().addAll(Arrays.asList(ip2));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip2));
		p3.getItens().addAll(Arrays.asList(ip3));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}

}
