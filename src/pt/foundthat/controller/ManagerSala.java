package pt.foundthat.controller;

import pt.foundthat.model.ModelPrototype;
import pt.foundthat.model.Sala;

public class ManagerSala extends ManagerEntityStrategy {

	public boolean isEntity(String nome) {
		this.entityList.addAll(FoundThat.salas);
		boolean res = super.isEntity(nome);
		this.entityList.clear();
		return res;
	}

	public boolean adicionarEntity(ModelPrototype entity) {
		boolean res = false;
		if (!isEntity(entity.getNome())) {
			Sala s = (Sala) FoundThat.prototypeSala.clone();
			s.setNome(entity.getNome());
			FoundThat.salas.add(s);
			res = true;
		} 
		return res;		
	}

	public boolean alterarEntity(ModelPrototype salaNova, ModelPrototype salaAntiga) {
        return super.alterarEntity(salaNova, salaAntiga, FoundThat.salas);
	}

	public boolean removerEntity(String sala) {
		boolean res = false;

		if (isEntity(sala)) {
			for (int i = 0; i < FoundThat.salas.size(); i++) {
				ModelPrototype s = FoundThat.salas.get(i);
				if (s.getNome().equals(sala)) {
					FoundThat.salas.remove(i);	
			        res = true;
				}
			}
		}
		return res;
	}
}