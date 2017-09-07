package pt.foundthat.model;


public class Instituicao extends ModelPrototype implements Comparable <Instituicao> {

	public Instituicao() {
		this.codigo = -1;
		this.nome = "Nova Instituição";
	}

	Instituicao(Instituicao is) {
		this.codigo = is.getCodigo();
		this.nome = is.getNome();
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public int compareTo(Instituicao other) {
		return getNome().compareTo(other.getNome()) ;
	}

	@Override
	public ModelPrototype clone() {
		return new Instituicao(this);
	}
}
