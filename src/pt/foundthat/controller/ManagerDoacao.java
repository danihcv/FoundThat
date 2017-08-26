package pt.foundthat.controller;

import pt.foundthat.model.Registo;

public class ManagerDoacao extends ManagerActionStrategy{
    @Override
    public int execute(int codigo) {
        int idx = super.execute(codigo);
        if (idx != -1) {
            Registo registo = FoundThat.registos.get(idx);
            FoundThat.doacoes.add(registo);
        }
        return idx;
    }
}