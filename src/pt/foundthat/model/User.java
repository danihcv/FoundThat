package pt.foundthat.model;



public class User  implements Comparable <User>{

	private String user;
	private String password;
	private TipoUser tipo;

	public User(String user, String password, TipoUser tipo) {
		super();
		this.user = user;
		this.password = password;
		this.tipo = tipo;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public TipoUser getTipo() {
		return tipo;
	}

	public void setTipo(TipoUser tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "User [user=" + user + ", password=" + password + "]";
	}

	@Override
	public int compareTo(User other) {
		return this.getUser().compareTo(other.getUser());
	}

}
