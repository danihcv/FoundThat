package pt.foundthat.model;

import java.util.Date;

public class Registo implements Comparable<Registo>{

	private static int cod;
	private int codigo;
	private String nome;
	private String email;
	private Date data;
	private String hora;
	private String cor;
	private String estado;
	private String descricao; 
	private TipoObjeto objeto;
	private Sala sala;


	public Registo(int codigo, String nome, String email, Sala sala, Date data, String hora, TipoObjeto objeto, String cor, String estado, String descricao) {
		super();
		cod++;
		this.codigo = cod;
		this.nome = nome;
		this.email = email;
		this.data = data;
		this.hora = hora;
		this.cor = cor;
		this.estado = estado;
		this.descricao = descricao;
		this.objeto = objeto;
		this.sala = sala;
	}

	public static int getCod() {
		return cod;
	}

	public static void setCod(int cod) {
		Registo.cod = cod;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}


	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoObjeto getObjeto() {
		return objeto;
	}

	public void setObjeto(TipoObjeto objeto) {
		this.objeto = objeto;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	@Override
	public String toString() {
		return codigo + "#" + nome + "#" + email + "#" + sala + "#" + data.toString() + "#" + hora + "#" + objeto.getCodigo() + "#" + cor + "#" + estado + "#" + descricao;
	}

	@Override
	public int compareTo(Registo o) {
		return getObjeto().getNome().compareTo(o.getObjeto().getNome());
	}
}
