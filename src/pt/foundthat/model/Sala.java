package pt.foundthat.model;

public class Sala extends ModelPrototype implements Comparable<Sala> {

	public Sala() {
		this.nome = "Nova Sala";
	}

	public Sala(Sala sala) {
		this.nome = sala.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public ModelPrototype clone() {
		return new Sala(this);
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
