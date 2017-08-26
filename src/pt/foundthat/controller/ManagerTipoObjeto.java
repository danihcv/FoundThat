package pt.foundthat.controller;

import pt.foundthat.model.Instituicao;
import pt.foundthat.model.ModelStrategy;
import pt.foundthat.model.Sala;
import pt.foundthat.model.TipoObjeto;

public class ManagerTipoObjeto extends ManagerEntityStrategy {

	public boolean isEntity(String nome) {
		this.entityList.addAll(FoundThat.tipoObjetos);
		boolean res = super.isEntity(nome);
		this.entityList.clear();
		return res;
	}

	public boolean alterarEntity(String novaEntity, String antigaEntity) {
		return false;
	}

	public boolean adicionarEntity(ModelStrategy entity) {
		boolean res = false;
		String isNome = ((TipoObjeto)entity).getCodigoIS().getNome();
		if (!isEntity(entity.getNome())) {
			for (ModelStrategy is : FoundThat.instituicoes) {
				if (is.getNome().equals(isNome)) {
					TipoObjeto to = new TipoObjeto(getLastCode(), entity.getNome(), (Instituicao) is);
					FoundThat.tipoObjetos.add(to);
					res = true;
				}
			}
		}
		return res;	
	}

	public boolean removerEntity(String nome) {
		boolean res = false;

		if (isEntity(nome)) {
			for (int i = 0; i < FoundThat.tipoObjetos.size(); i++) {
				TipoObjeto to = FoundThat.tipoObjetos.get(i);
				if (to.getNome().equals(nome)) {
					FoundThat.tipoObjetos.remove(i);	
			        res = true;
				}
			}
		}
		return res;
	}

	public boolean alterarEntity(ModelStrategy novoObjeto, ModelStrategy objetoAntigo) {
		boolean res = false;
		String isNova = ((TipoObjeto)novoObjeto).getCodigoIS().getNome();
		String isAntiga = ((TipoObjeto)objetoAntigo).getCodigoIS().getNome();
		if (!isEntity(novoObjeto.getNome())) {
			for (int i = 0; i < FoundThat.tipoObjetos.size(); i++) {
				TipoObjeto to = FoundThat.tipoObjetos.get(i);
				if (to.getNome().equals(objetoAntigo.getNome())) {
					for (ModelStrategy is : FoundThat.instituicoes) {
						if (is.getNome().equals(isNova)) {
							to.setNome(novoObjeto.getNome());
							to.setCodigoIS((Instituicao) is);
						}
					}

				}			
			}
			res = true;
		} 
		else {
			for (ModelStrategy is : FoundThat.instituicoes) {
				if (isNova.equals(isAntiga)) {
					res = false;
				}
				else {
					for (int i = 0; i < FoundThat.tipoObjetos.size(); i++) {
						TipoObjeto to = FoundThat.tipoObjetos.get(i);
						if (to.getNome().equals(objetoAntigo.getNome())) {
							if (is.getNome().equals(isNova)) {
								to.setNome(novoObjeto.getNome());
								to.setCodigoIS((Instituicao) is);
							}
						}			
					}
					res = true;
				}
			}
		}
		return res;	
	}
}
