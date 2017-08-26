package pt.foundthat.controller;

import pt.foundthat.model.Registo;

public abstract class ManagerActionStrategy {

    public int execute(int codigo) {
        int res = -1;

        for (int i = 0; i < FoundThat.registos.size(); i++) {
            Registo reg = FoundThat.registos.get(i);
            if (reg.getCodigo() == codigo) {
                FoundThat.registos.remove(i);
                res = i;
            }
        }
        return res;
    }
}
