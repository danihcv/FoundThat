package pt.foundthat.controller;

import pt.foundthat.model.ModelPrototype;

import java.util.ArrayList;

public abstract class ManagerEntityStrategy {
    ArrayList<ModelPrototype> entityList = new ArrayList<>();

    public boolean isEntity(String nome) {
        boolean res = false;

        for (ModelPrototype ent : entityList) {
            if (ent.getNome().equals(nome)) {
                res = true;
            }
        }
        return res;
    }

    public abstract boolean adicionarEntity(ModelPrototype entity);

    public abstract boolean alterarEntity(ModelPrototype novaEntity, ModelPrototype antigaEntity);

    protected boolean alterarEntity(ModelPrototype novaEntity, ModelPrototype antigaEntity, ArrayList<ModelPrototype> list) {
        boolean res = false;
        if (!isEntity(novaEntity.getNome())) {
            for (ModelPrototype ent : list) {
                if (ent.getNome().equals(antigaEntity.getNome())) {
                    ent.setNome(novaEntity.getNome());
                    res = true;
                }
            }
        }
        return res;
    }

    public abstract boolean removerEntity(String nome);

    public int getLastCode() {
        if (FoundThat.registos.size() == 0) {
            return 1;
        }
        else {
            return FoundThat.registos.get(FoundThat.registos.size() - 1).getCodigo()+1;
        }
    }
}
