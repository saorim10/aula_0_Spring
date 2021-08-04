package br.com.saorim.proj01.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pagamento Pendente"), 
	QUITADO(2, "Pagamento Quitado"),
	CANCELADO(3,"Pagamento Cancelado");
	
	private int cod;
	private String descr;
	
	private EstadoPagamento(int cod, String descr) {
		this.cod = cod;
		this.descr = descr;
	}

	public int getCod() {
		return cod;
	}

	public String getDescr() {
		return descr;
	}
	
	
	public static EstadoPagamento toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(EstadoPagamento x: EstadoPagamento.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: "+ cod);
	}
}
