/**
 * Para a classe abaixo implemente os dois métodos previamente iniciados. 
 */
public class ExercicioCalculoJuros {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	  A fórmula de júros compostos a ser aplicada será à abaixo.
	  
	  Empréstimos e Financiamentos na Tabela Price
	  A Tabela Price é o sistema de amortização mais utilizado nos empréstimos e financiamentos e sua principal característica são as parcelas de mesmo valor. 
	  Existe ainda o sistema Tabela SAC muito utilizado no financiamento de imóveis, onde os valores das parcelas são decrescentes.
	  
	  Referências: 
	  		https://brasilescola.uol.com.br/matematica/financiamentos-utilizando-tabela-price.htm
	  		https://fazaconta.com/financiamentos-pmt-rate-nper.htm
	  		https://www.youtube.com/watch?v=EAwTHxCWVtk
	  
	  PMT = PV * {[(1 + i)^n * i] / [(1 + i)^n - 1] } onde:
	 
	  PMT = prestação
	  PV = valor - presente
	  i = taxa
	  n = período em meses
	 */
	private void calcularTabelaPrice() {

		//Valor presente/atual
		//R$: 2.000,00");
		//Quantidade de parcelas
		//8
		//Taxa de juros mensal
		//4 %

	}

	/*CÁLCULO DE JUROS PRO-RATA DIE
	 
	Texto extraído de https://calculoexato.com.br/parprima.aspx?codMenu=FinanJurosSobreValor
	 
	Aplicação de juros sobre um valor
	
	Aplicação de juros compostos de 0,050% ao mês, pro-rata die, entre 29-Janeiro-2022 e 29-Julho-2022 sobre o valor de R$2.450,00
	
	 Valor original:   	R$2.450,00
	 Valor com juros:  	R$2.457,36
	
	Memória do Cálculo
	
	Juros
	Juros percentuais (JP)  =  0,30035 %
	Valor dos juros (VJ)  =  VA * JP  =  7,3587
	Valor total com juros  =  VA + VJ  =  R$2.457,36
	
	Exemplo:
	
	Fórmula dos juros compostos: Juros = ((1 + taxa / 100) ^ períodos) - 1
	períodos = 3/31 (prop. Janeiro-2022) + 5 (de Fevereiro-2022 a Junho-2022) + 28/31 (prop. Julho-2022) = 6
	Juros = ((1 + 0,05000 / 100) ^ 6) - 1 = 0,30035%
	
	Juros simples:
	No regime dos juros simples, a taxa de juros é aplicada sobre o valor inicial de forma linear em todos os períodos, ou seja, não considera que o valor sobre o qual incidem juros muda ao longo do tempo.
	
	Juros compostos:
	No regime de juros compostos, os juros corrigíveis de cada período são somados ao capital para o cálculo de novos juros nos períodos seguintes.
	Nesse caso, o valor da dívida é sempre corrigida e a taxa de juros é calculada sobre esse novo valor.
	
	Pro rata die:
	O pro rata die significa 'proporcional ao dia' e é usado na cobrança de juros em valores diários, quando há a cobrança de uma porcentagem ou taxa diária sobre o atraso no pagamento.
	*/
	private void calcularJurosCompostos() {

		//Valor inicial
		//R$ 2.450,00
		
		//Data inicial 
		//29/01/2022		
		//Data final 
		//29/07/2022
		
		//Taxa de juros mensal 0.050

	}

}
