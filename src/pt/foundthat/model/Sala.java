package pt.foundthat.model;

public class Sala extends ModelStrategy implements Comparable<Sala> {

	public Sala(String nome) {
		this.nome = nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public int compareTo(Sala other) {		

		return getNome().compareTo(other.getNome()) ;
	}

}
