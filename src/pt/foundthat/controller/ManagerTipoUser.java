package pt.foundthat.controller;

import pt.foundthat.model.ModelStrategy;
import pt.foundthat.model.TipoObjeto;
import pt.foundthat.model.TipoUser;

public class ManagerTipoUser extends ManagerEntityStrategy {

	public boolean isPerfilUser(String nome) {
		boolean res = false;
		for (ModelStrategy tu : FoundThat.tipoUsers) {
			if (tu.getNome().equals(nome)) {
				res = true;
			}
		}
		return res;
	}

	//public boolean isEntity(String nome, Boolean registo, Boolean reclamacao, Boolean importacao, Boolean listagens, Boolean doacoes, Boolean configuracoes) {
	public boolean isEntity(ModelStrategy entity) {
        TipoUser ent = (TipoUser) entity;
		boolean res = false;

		for (ModelStrategy t : FoundThat.tipoUsers) {
		    TipoUser tu = ((TipoUser)t);
			if (tu.getNome().equals(entity.getNome())) {
				if (tu.isRegisto() == ent.isRegisto() &&  tu.isReclamacao() == ent.isReclamacao() && tu.isImportacao() == ent.isImportacao() && tu.isListagens() == ent.isListagens() && tu.isDoacoes() == ent.isDoacoes() && tu.isConfiguracoes() == ent.isConfiguracoes()) {
					res = true;
				}
			}
		}
		return res;
	}

	//public boolean adicionarEntity(String nome, Boolean registo, Boolean reclamacao, Boolean importacao, Boolean listagens, Boolean doacoes, Boolean configuracoes) {
	public boolean adicionarEntity(ModelStrategy entity) {
		boolean res = false;
		if (!isPerfilUser(entity.getNome())) {
			entity.setCodigo(getLastCode());
			FoundThat.tipoUsers.add(entity);
			res = true;
		} 
		return res;	
	}

	public boolean removerEntity(String nome) {
		boolean res = false;

		if (isPerfilUser(nome)) {
			for (int i = 0; i < FoundThat.tipoUsers.size(); i++) {
				TipoUser us = (TipoUser) FoundThat.tipoUsers.get(i);
				if (us.getNome().equals(nome)) {
					FoundThat.tipoUsers.remove(i);	
			        res = true;
				}
			}
		}
		return res;
	}

	public boolean alterarEntity(ModelStrategy tuNovo, ModelStrategy tuAntigo) {
		boolean res = false;

		if (!isPerfilUser(tuNovo.getNome()) || (isPerfilUser(tuNovo.getNome()) && !isEntity(tuAntigo))) {
			for (int i = 0; i < FoundThat.tipoUsers.size(); i++) {
				TipoUser tu = (TipoUser) FoundThat.tipoUsers.get(i);
				if (tu.getNome().equals(tuAntigo.getNome())) {
					tu.setNome(tuNovo.getNome());
					tu.setRegisto(((TipoUser)tuNovo).isRegisto());
					tu.setReclamacao(((TipoUser)tuNovo).isReclamacao());
					tu.setImportacao(((TipoUser)tuNovo).isImportacao());
					tu.setListagens(((TipoUser)tuNovo).isListagens());
					tu.setDoacoes(((TipoUser)tuNovo).isDoacoes());
					tu.setConfiguracoes(((TipoUser)tuNovo).isConfiguracoes());
			        res = true;
				}
			}
		}
		return res;	
	}

	public int getLastCode() {
		if (FoundThat.tipoUsers.size() == 0) {
			return 1;
		}
		else {
			return FoundThat.tipoUsers.get(FoundThat.tipoUsers.size() - 1).getCodigo()+1;
		}
	}

}
