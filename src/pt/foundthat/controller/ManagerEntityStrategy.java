package pt.foundthat.controller;

import pt.foundthat.model.Instituicao;
import pt.foundthat.model.ModelStrategy;
import pt.foundthat.model.TipoObjeto;
import sun.awt.ModalExclude;
import sun.plugin2.main.server.ModalitySupport;

import java.util.ArrayList;

public abstract class ManagerEntityStrategy {
    ArrayList<ModelStrategy> entityList = new ArrayList<>();

    public boolean isEntity(String nome) {
        boolean res = false;

        for (ModelStrategy ent : entityList) {
            if (ent.getNome().equals(nome)) {
                res = true;
            }
        }
        return res;
    }

    public abstract boolean adicionarEntity(ModelStrategy entity);

    public abstract boolean alterarEntity(ModelStrategy novaEntity, ModelStrategy antigaEntity);

    protected boolean alterarEntity(ModelStrategy novaEntity, ModelStrategy antigaEntity, ArrayList<ModelStrategy> list) {
        boolean res = false;
        if (!isEntity(novaEntity.getNome())) {
            for (ModelStrategy ent : list) {
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
