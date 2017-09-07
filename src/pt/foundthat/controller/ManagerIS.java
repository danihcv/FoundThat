package pt.foundthat.controller;

import pt.foundthat.model.Instituicao;
import pt.foundthat.model.ModelPrototype;
import pt.foundthat.model.TipoObjeto;

public class ManagerIS extends ManagerEntityStrategy {

    public boolean isAtribuida(String nome) {
        boolean res = false;
        for (TipoObjeto to : FoundThat.tipoObjetos) {
            if (to.getCodigoIS().getNome().equals(nome)) {
                res = true;
            }
        }
        return res;
    }

    @Override
	public boolean isEntity(String nome) {
        this.entityList.addAll(FoundThat.instituicoes);
        boolean res = super.isEntity(nome);
	    this.entityList.clear();
	    return res;
	}

	public boolean adicionarEntity(ModelPrototype entity) {
		boolean res = false;
		if (!isEntity(entity.getNome())) {
            Instituicao is = (Instituicao) FoundThat.prototypeInstituicao.clone();
            is.setCodigo(getLastCode());
            is.setNome(entity.getNome());
			FoundThat.instituicoes.add(is);
			res = true;
		} 
		return res;		
	}

    public boolean alterarEntity(ModelPrototype novaIS, ModelPrototype isAntiga) {
        return super.alterarEntity(novaIS, isAntiga, FoundThat.instituicoes);
    }

	public boolean removerEntity(String nome) {
		boolean res = false;

		if (isEntity(nome)) {
			for (int i = 0; i < FoundThat.instituicoes.size(); i++) {
				ModelPrototype is = FoundThat.instituicoes.get(i);
				if (is.getNome().equals(nome)) {
					FoundThat.instituicoes.remove(i);	
			        res = true;
				}
			}
		}
		return res;
	}
}
