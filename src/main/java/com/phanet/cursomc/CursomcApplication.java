package com.phanet.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.phanet.cursomc.domain.Categoria;
import com.phanet.cursomc.domain.Cidade;
import com.phanet.cursomc.domain.Cliente;
import com.phanet.cursomc.domain.Endereco;
import com.phanet.cursomc.domain.Estado;
import com.phanet.cursomc.domain.Pagamento;
import com.phanet.cursomc.domain.PagamentoComBoleto;
import com.phanet.cursomc.domain.PagamentoComCartao;
import com.phanet.cursomc.domain.Pedido;
import com.phanet.cursomc.domain.Produto;
import com.phanet.cursomc.domain.enums.EstadoPagamento;
import com.phanet.cursomc.domain.enums.TipoCliente;
import com.phanet.cursomc.repositories.CategoriaRepository;
import com.phanet.cursomc.repositories.CidadeRepository;
import com.phanet.cursomc.repositories.ClienteRepository;
import com.phanet.cursomc.repositories.EnderecoRepository;
import com.phanet.cursomc.repositories.EstadoRepository;
import com.phanet.cursomc.repositories.PagamentoRepository;
import com.phanet.cursomc.repositories.PedidoRepository;
import com.phanet.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired 
	private EnderecoRepository enderecoRepository;
	
	@Autowired 
	private PedidoRepository pedidoRepository;
	
	@Autowired 
	private PagamentoRepository pagamentoRepository; 

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(java.util.Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(java.util.Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(java.util.Arrays.asList(est1, est2));
		cidadeRepository.saveAll(java.util.Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null,"Maria Silva","maria@gmail.com","36378912377",TipoCliente.PESSOAFISICA); 
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		Endereco e1 = new Endereco(null, "Rua FLores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(java.util.Arrays.asList(cli1));
		enderecoRepository.saveAll(java.util.Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy HH:MM");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6); 
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2); 
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(java.util.Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(java.util.Arrays.asList(pagto1,pagto2));
	}

}
