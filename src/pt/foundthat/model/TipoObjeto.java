package pt.foundthat.model;


public class TipoObjeto extends ModelPrototype implements Comparable <TipoObjeto> {

	private static int cod;
	private Instituicao codigoIS;

	public TipoObjeto() {
		cod++;
		this.nome = "Novo Tipo Objeto";
		this.codigo = cod;
		this.codigoIS = null;
	}

	public TipoObjeto(TipoObjeto to) {
		this.nome = to.getNome();
		this.codigo = to.getCodigo();
		this.codigoIS = to.getCodigoIS();
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public ModelPrototype clone() {
		return new TipoObjeto(this);
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
