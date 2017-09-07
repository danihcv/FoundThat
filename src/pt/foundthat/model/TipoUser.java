package pt.foundthat.model;

public class TipoUser extends ModelPrototype implements Comparable <TipoUser>{
	private boolean registo;
	private boolean reclamacao;
	private boolean importacao;
	private boolean listagens;
	private boolean doacoes;
	private boolean configuracoes;

	public TipoUser() {
		this.codigo = -1;
		this.nome = "Novo Tipo Usuario";
		this.registo = false;
		this.reclamacao = false;
		this.importacao = false;
		this.listagens = false;
		this.doacoes = false;
		this.configuracoes = false;
	}

	TipoUser(TipoUser tipoUser) {
		this.codigo = tipoUser.getCodigo();
		this.nome = tipoUser.getNome();
		this.registo = tipoUser.isRegisto();
		this.reclamacao = tipoUser.isReclamacao();
		this.importacao = tipoUser.isImportacao();
		this.listagens = tipoUser.isListagens();
		this.doacoes = false;
		this.configuracoes = false;
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
	public ModelPrototype clone() {
		return new TipoUser(this);
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
