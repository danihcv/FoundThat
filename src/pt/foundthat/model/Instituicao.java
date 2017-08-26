package pt.foundthat.model;


import pt.foundthat.controller.FoundThat;

public class Instituicao extends ModelStrategy implements Comparable <Instituicao> {

	public Instituicao(int codigo, String nome) {
		this.codigo = codigo;		
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public int compareTo(Instituicao other) {
		return getNome().compareTo(other.getNome()) ;
	}

}
