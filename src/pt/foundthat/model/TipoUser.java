package pt.foundthat.model;

public class TipoUser extends ModelStrategy implements Comparable <TipoUser>{

	private boolean registo;
	private boolean reclamacao;
	private boolean importacao;
	private boolean listagens;
	private boolean doacoes;
	private boolean configuracoes;

	public TipoUser(int codigo, String nome, boolean registo, boolean reclamacao,
			boolean importacao, boolean listagens, boolean doacoes,
			boolean configuracoes) {
		this.codigo = codigo;
		this.nome = nome;
		this.registo = registo;
		this.reclamacao = reclamacao;
		this.importacao = importacao;
		this.listagens = listagens;
		this.doacoes = doacoes;
		this.configuracoes = configuracoes;
	}

	public boolean isRegisto() {
		return registo;
	}

	public void setRegisto(boolean registo) {
		this.registo = registo;
	}

	public boolean isReclamacao() {
		return reclamacao;
	}

	public void setReclamacao(boolean reclamacao) {
		this.reclamacao = reclamacao;
	}

	public boolean isImportacao() {
		return importacao;
	}

	public void setImportacao(boolean importacao) {
		this.importacao = importacao;
	}

	public boolean isListagens() {
		return listagens;
	}

	public void setListagens(boolean listagens) {
		this.listagens = listagens;
	}

	public boolean isDoacoes() {
		return doacoes;
	}

	public void setDoacoes(boolean doacoes) {
		this.doacoes = doacoes;
	}

	public boolean isConfiguracoes() {
		return configuracoes;
	}

	public void setConfiguracoes(boolean configuracoes) {
		this.configuracoes = configuracoes;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome;
	}

	@Override
	public int compareTo(TipoUser other) {
		return this.getNome().compareTo(other.getNome());
	}

}
