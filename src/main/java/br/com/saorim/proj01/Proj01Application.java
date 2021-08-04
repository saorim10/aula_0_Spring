package br.com.saorim.proj01;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.saorim.proj01.domain.Categoria;
import br.com.saorim.proj01.domain.Cidade;
import br.com.saorim.proj01.domain.Cliente;
import br.com.saorim.proj01.domain.Endereco;
import br.com.saorim.proj01.domain.Estado;
import br.com.saorim.proj01.domain.Pagamento;
import br.com.saorim.proj01.domain.PagamentoComBoleto;
import br.com.saorim.proj01.domain.PagamentoComCartao;
import br.com.saorim.proj01.domain.Pedido;
import br.com.saorim.proj01.domain.Produto;
import br.com.saorim.proj01.domain.enums.EstadoPagamento;
import br.com.saorim.proj01.domain.enums.TipoCliente;
import br.com.saorim.proj01.repositories.CategoriaRepository;
import br.com.saorim.proj01.repositories.CidadeRepository;
import br.com.saorim.proj01.repositories.ClienteRepository;
import br.com.saorim.proj01.repositories.EnderecoRepository;
import br.com.saorim.proj01.repositories.EstadoRepository;
import br.com.saorim.proj01.repositories.PagamentoRepository;
import br.com.saorim.proj01.repositories.PedidoRepository;
import br.com.saorim.proj01.repositories.ProdutoRepository;

@SpringBootApplication
public class Proj01Application implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categR;
	
	@Autowired
	private ProdutoRepository prodR;
	
	@Autowired
	private EstadoRepository estR;
	
	@Autowired
	private CidadeRepository cidR;
	
	@Autowired
	private ClienteRepository cliR;
	
	@Autowired
	private EnderecoRepository endR;
	
	@Autowired
	private PagamentoRepository pagtoR;
	
	@Autowired
	private PedidoRepository pedR;
	
	
	

	public static void main(String[] args) {
		SpringApplication.run(Proj01Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "Maria@email.com", "123.456.789-00", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("(12)98250-5464", "(11)98100-8797"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38.220-834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38.777-012", cli1, c2);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		
		categR.saveAll(Arrays.asList(cat1, cat2));
		prodR.saveAll(Arrays.asList(p1, p2, p3));
		estR.saveAll(Arrays.asList(est1, est2));
		cidR.saveAll(Arrays.asList(c1, c2, c3));
		cliR.saveAll(Arrays.asList(cli1));
		endR.saveAll(Arrays.asList(e1, e2));
		pedR.saveAll(Arrays.asList(ped1, ped2));
		pagtoR.saveAll(Arrays.asList(pagto1, pagto2));
		
	}
}
