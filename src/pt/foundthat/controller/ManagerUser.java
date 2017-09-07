package pt.foundthat.controller;

import pt.foundthat.model.ModelPrototype;
import pt.foundthat.model.TipoUser;
import pt.foundthat.model.User;

public class ManagerUser {

	public static boolean isUser(String username, String password) {
		boolean res = false;
		for (User u : FoundThat.users) {
			if (u.getUser().equals(username) && u.getPassword().equals(password)) {
				res = true;
			}
		}
		return res;
	}

	public static boolean isUserExiste(String username) {
		boolean res = false;
		for (User u : FoundThat.users) {
			if (u.getUser().equals(username)) {
				res = true;
			}
		}

		return res;
	}

	public static boolean isPerfil(String username, String perfil) {

		boolean res = false;
		for (User u : FoundThat.users) {
			if (u.getUser().equals(username)) {
				if (u.getTipo().getNome().equals(perfil)) {
					res = true;
				}
			}
		}

		return res;
	}

	public static boolean adicionarUser(String username, String password, String perfil) {
		boolean res = false;
		if (!isUserExiste(username)) {
			for (ModelPrototype tu : FoundThat.tipoUsers) {
				if (tu.getNome().equals(perfil)) {
					User user = new User(username, password, (TipoUser) tu);
					FoundThat.users.add(user);
					res = true;
				} 	
			}

		}
		return res;	
	}
	public static boolean removerUser(String username) {
		boolean res = false;
		if (isUserExiste(username)) {
			for (int i = 0; i < FoundThat.users.size(); i++) {
				User us = FoundThat.users.get(i);
				if (us.getUser().equals(username)) {
					FoundThat.users.remove(i);	
				}	
			}
			res = false;	
		}

		return res;
	}



	public static boolean alterarUser(String user, String userAntigo, String password, String perfil) {
		boolean res = false;
		if (!isUserExiste(user) || (isUserExiste(user) == true && isPerfil(user, perfil) == false)) {
			for (int i = 0; i < FoundThat.users.size(); i++) {
				User us = FoundThat.users.get(i);
				if (us.getUser().equals(userAntigo)) {
					for (ModelPrototype tu : FoundThat.tipoUsers) {
						if (tu.getNome().equals(perfil)) {
							us.setUser(user);
							us.setTipo((TipoUser) tu);
							us.setPassword(password);
						}
					}

				}			
			}
			res = true;
		} 

		return res;	
	}




}