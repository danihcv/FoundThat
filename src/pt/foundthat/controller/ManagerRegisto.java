package pt.foundthat.controller;

import pt.foundthat.model.Registo;
import pt.foundthat.model.Sala;
import pt.foundthat.model.TipoObjeto;

public class ManagerRegisto {

	public static boolean isRegisto(String nome, String email, Object sala, Object objeto, String cor, String estado, String descricao) {
		boolean res = false;
		for (Registo reg : FoundThat.registos) {
			if (reg.getData().equals(FoundThat.dataRegisto+FoundThat.horaRegisto)) {
				res = true;
			}

		}

		return res;	
	}

	public static boolean adicionarRegisto(String nome, String email, Object sala, Object objeto, String cor, String estado, String descricao) throws Exception {
		boolean res = false;
		if (!isRegisto(nome, email, sala, objeto, cor, estado, descricao)) {
			Sala s = (Sala) sala;
			TipoObjeto to = (TipoObjeto) objeto;
			Registo reg = new Registo(getLastCode(), nome, email, s, FoundThat.formatoDataRegisto.parse(FoundThat.dataRegisto), FoundThat.horaRegisto, to, cor.toLowerCase(), estado.toLowerCase(), descricao);
			FoundThat.registos.add(reg);
			res = true;
		} 
		return res;		
	}

	public static int getLastCode() {
		if (FoundThat.registos.size() == 0) {
			return 1;
		}
		else {
			return FoundThat.registos.get(FoundThat.registos.size() - 1).getCodigo()+1;
		}
	}
}










