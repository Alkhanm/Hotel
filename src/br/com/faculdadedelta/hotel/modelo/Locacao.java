package br.com.faculdadedelta.hotel.modelo;

import java.time.LocalDate;

public class Locacao {
	
	private Long id;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private boolean casal;
	private Cliente cliente;
	private Quarto quarto;
	private Double valorTotal;
	private final Integer VALOR_DIARIA = 100; // R$95 por dia
	

	
	
	public Locacao() {
	}
	
	public Locacao(Long id, LocalDate dataInicio, LocalDate dataFim, boolean casal, Cliente cliente, Quarto quarto) {
		this.id = id;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.casal = casal;
		this.cliente = cliente;
		this.quarto = quarto;
	}

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public boolean isCasal() {
		return casal;
	}

	public void setCasal(boolean casal) {
		this.casal = casal;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public Cliente getCliente() {
		
		return cliente;
	}
	

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}
	
	public Double getValorTotal() {
		if (casal) { // o aluguel aumenta em 50 para quartos para casal
			valorTotal = (double) (dataFim.compareTo(dataInicio) * (VALOR_DIARIA + 50));
		} else {
			valorTotal = (double) (dataFim.compareTo(dataInicio) * VALOR_DIARIA);
		}
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = prime * result + ((quarto == null) ? 0 : quarto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Locacao other = (Locacao) obj;
		if (dataInicio == null) {
			if (other.dataInicio != null)
				return false;
		} else if (!dataInicio.equals(other.dataInicio))
			return false;
		if (quarto == null) {
			if (other.quarto != null)
				return false;
		} else if (!quarto.equals(other.quarto))
			return false;
		return true;
	}


	
	
}
