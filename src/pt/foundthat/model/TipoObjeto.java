package pt.foundthat.model;


public class TipoObjeto extends ModelStrategy implements Comparable <TipoObjeto> {

	private static int cod;
	private Instituicao codigoIS;

	public TipoObjeto(int codigo, String nome, Instituicao codigoIS) {
		cod++;
		this.nome = nome;
		this.codigo = cod;
		this.codigoIS = codigoIS;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Instituicao getCodigoIS() {
		return codigoIS;
	}

	public void setCodigoIS(Instituicao codigoIS) {
		this.codigoIS = codigoIS;
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public int compareTo(TipoObjeto other) {
		return getNome().compareTo(other.getNome()) ;
	}



}
