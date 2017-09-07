package pt.foundthat.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

import pt.foundthat.model.*;
import pt.foundthat.view.FrmImportacao;
import pt.foundthat.view.FrmLogin;


public class FoundThat {
	public static Instituicao prototypeInstituicao = new Instituicao();
	public static TipoObjeto prototypeTipoObjeto = new TipoObjeto();
	public static Sala prototypeSala = new Sala();
	public static TipoUser prototypeTipoUser = new TipoUser();

	public static ManagerActionStrategy managerAction = null;
	public static ManagerEntityStrategy managerEntity = null;
	//CRIA��O DE ARRAYS PARA AS CLASSES
	public static ArrayList<Registo> registos = new ArrayList<>();
	public static ArrayList<Registo> doacoes = new ArrayList<Registo>();
	public static ArrayList<Registo> importacoes;
	public static ArrayList<ModelPrototype> instituicoes = new ArrayList<>();
	public static ArrayList<TipoObjeto> tipoObjetos = new ArrayList<TipoObjeto>();
	public static ArrayList<ModelPrototype> salas = new ArrayList<>();
	public static ArrayList<ModelPrototype> tipoUsers = new ArrayList<>();
	public static ArrayList<User> users = new ArrayList<User>();
	public static ArrayList<Registo> registoDoacoes = new ArrayList<Registo>();
	//DATA SISTEMA
	public static SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
	public static String horaRegisto = formatoHora.format(new Date()).toString();

	public static SimpleDateFormat formatoDataRegistoNome = new SimpleDateFormat("MM-yyy");
	public static String dataRegistoNome = formatoDataRegistoNome.format(new Date()).toString();

	public static SimpleDateFormat formatoDataRegisto = new SimpleDateFormat("dd-MM-yyyy");
	public static String dataRegisto = formatoDataRegisto.format(new Date()).toString();

	//FICHEIROS
	static File registoFolder = new File("registos");
	static File registoFile = new File(registoFolder + "/" + dataRegistoNome + ".txt");
	static File[] listaRegistos = registoFolder.listFiles();
	static File instituicaoFile = new File("is.txt");
	static File isFolder = new File("institui��es");
	static File[] listaIS = isFolder.listFiles();
	static File salasFile = new File("salas.txt");
	static File objetosFile = new File("objetos.txt");
	static File tipoUsersFile = new File("tipoUser.txt");
	static File usersFile = new File("users.txt");
	static File doacoesFile = new File ("doacoes.txt");

	static Scanner in = new Scanner(System.in);
	static PrintWriter out;
	static boolean res;
	public static boolean check;

	//IMPORTACAO
	public static void carregarImportacao() throws Exception {
		File importacaoFile = new File(FrmImportacao.nomeFicheiroImportacao);

		@SuppressWarnings("resource")
		Scanner inFile = new Scanner(importacaoFile);

		importacoes = new ArrayList<Registo>();
		while (inFile.hasNextLine()) {

			String line = inFile.nextLine();
			String [] fields1 = line.split("#");
			String[] data = fields1[0].split(" ");
			String[] dados = fields1[1].split(";");

			Sala s = (Sala) FoundThat.prototypeSala.clone();
			s.setNome(dados[2]);
			for (TipoObjeto to : FoundThat.tipoObjetos) {
				if (to.getCodigo() == Integer.parseInt(dados[3])) {
				    Registo reg = new Registo(ManagerRegisto.getLastCode(), dados[0], dados[1], s, FoundThat.formatoDataRegisto.parse(data[0]), data[1], to, dados[4], dados[5], dados[6]);
					importacoes.add(reg);
					registos.add(reg);
				}

			}
			//ORDENA O ARRAYLIST DE REGISTOS PELA DATA (USANDO O COMPARABLE NA CLASSE REGISTO)
			Collections.sort(registos);
		}
	}



	//CARREGAR FICHEIRO
	@SuppressWarnings("resource")
	public static void carregarFicheiro() throws Exception {
		//Verifica se ficheiro existe. Se n�o existe cria-o vazio.
		if (registoFolder.isDirectory() == false) {
			registoFolder.mkdir();
		}
		if (registoFile.isFile() == false) {
			registoFile.createNewFile();
		}
		if (instituicaoFile.isFile() == false) {
			instituicaoFile.createNewFile();
		}
		if (isFolder.isDirectory() == false) {
			isFolder.mkdir();
		}
		if (salasFile.isFile() == false) {
			salasFile.createNewFile();
		}
		if (objetosFile.isFile() == false) {
			objetosFile.createNewFile();
		}
		if (usersFile.isFile() == false) {
			usersFile.createNewFile();
		}
		if (tipoUsersFile.isFile() == false) {
			tipoUsersFile.createNewFile();
		}
		if (doacoesFile.isFile() == false) {
			doacoesFile.createNewFile();
		}


		//Ler ficheiro de tipoUsers e gravar em array 
		Scanner inFile = new Scanner(tipoUsersFile);
		while (inFile.hasNextLine()) {
			String line = inFile.nextLine();
			String [] fields = line.split("#");
			TipoUser tu = (TipoUser) FoundThat.prototypeTipoUser.clone();
			tu.setCodigo(Integer.parseInt(fields[0]));
			tu.setNome(fields[1]);
			tu.setRegisto(Boolean.parseBoolean(fields[2]));
			tu.setReclamacao(Boolean.parseBoolean(fields[3]));
			tu.setImportacao(Boolean.parseBoolean(fields[4]));
			tu.setListagens(Boolean.parseBoolean(fields[5]));
			tu.setDoacoes(Boolean.parseBoolean(fields[6]));
			tu.setConfiguracoes(Boolean.parseBoolean(fields[7]));
			tipoUsers.add(tu);
		}


		//Ler ficheiro de utilizadores e gravar em array
		inFile = new Scanner(usersFile);
		while (inFile.hasNextLine()) {
			String line = inFile.nextLine();
			String [] fields = line.split("#");
			int tipo = Integer.parseInt(fields[2]);
			for (ModelPrototype tu : tipoUsers)
			{
				if (tu.getCodigo() == tipo) {
					User u = new User(fields[0], fields[1], (TipoUser) tu);
					users.add(u);
				}
			}

		}

		//Ler ficheiro de instituicoes e gravar em array 
		inFile = new Scanner(instituicaoFile);
		while (inFile.hasNextLine()) {
			String line = inFile.nextLine();
			String [] fields = line.split("#");
			Instituicao is = (Instituicao) FoundThat.prototypeInstituicao.clone();
			is.setCodigo(Integer.parseInt(fields[0]));
			is.setNome(fields[1].toLowerCase());
			instituicoes.add(is);
			File instituicaoFile = new File(isFolder + "/" + is.getNome()+".txt");
			if (!instituicaoFile.isFile()) {
				instituicaoFile.createNewFile();
			}
		}

		//Ler ficheiro de tipoobjetos e gravar em array 
		inFile = new Scanner(objetosFile);
		while (inFile.hasNextLine()) {
			String line = inFile.nextLine();
			String [] fields = line.split("#");
			//Adicionar codigo da instituicao(fields[2]) ao inteiro codigoIs e posteriormente associar ao TipoObjeto to 
			int codigoIs = Integer.parseInt(fields[2]);
			for (ModelPrototype is : instituicoes) {
				if (is.getCodigo() == codigoIs) {
					TipoObjeto to = (TipoObjeto) FoundThat.prototypeTipoObjeto.clone();
					to.setCodigo(Integer.parseInt(fields[0]));
					to.setNome(fields[1].toLowerCase());
					to.setCodigoIS((Instituicao) is);
					tipoObjetos.add(to);
				}
			}
		}


		//Ler ficheiro de salas e gravar em array 
		inFile = new Scanner(salasFile);
		while (inFile.hasNextLine()) {
            String line = inFile.nextLine();
			Sala s = (Sala) FoundThat.prototypeSala.clone();
			s.setNome(line);
			salas.add(s);
		}

		//Ler ficheiro de doacoes e gravar em array 
		inFile = new Scanner(doacoesFile);	
		while (inFile.hasNextLine()) {
			String line = inFile.nextLine();
			String[] fields = line.split("#");
			Sala tempSala = null;
			for (ModelPrototype s : salas)
			{
				if (s.getNome().equals(fields[3])) {
					tempSala = (Sala) s;
				}
			}

			//Adicionar codigo do tipo objeto(fields[6]) ao inteiro codigoIs e posteriormente associar ao Registo rec 
			int codigoObjeto = Integer.parseInt(fields[6]);


			for (TipoObjeto to : tipoObjetos) {
				if (to.getCodigo() == codigoObjeto) {
					Registo rec = new Registo(Integer.parseInt(fields[0]), fields[1], fields[2], tempSala, FoundThat.formatoDataRegisto.parse(fields[4]), fields[5], to, fields[7].toLowerCase(), fields[8].toLowerCase(), fields[9]);
					doacoes.add(rec);
				}
			}

		}



		//Ler ficheiro de registos e gravar em array 
		for (int i = 0; i < listaRegistos.length; i++) {
            if (listaRegistos[i].isFile()) {
                File registosFile = new File(registoFolder + "/" + listaRegistos[i].getName());

				inFile = new Scanner(registosFile);	
				while (inFile.hasNextLine()) {
					String line = inFile.nextLine();
					String[] fields = line.split("#");
					Sala tempSala = null;
                    System.out.println(fields[3]);
                    for (ModelPrototype s : salas)
					{
						if (s.getNome().equals(fields[3])) {
							tempSala = (Sala) s;
						}
					}

					//Adicionar codigo do tipo objeto(fields[6]) ao inteiro codigoIs e posteriormente associar ao Registo rec 
					int codigoObjeto = Integer.parseInt(fields[6]);


					for (TipoObjeto to : tipoObjetos) {
						if (to.getCodigo() == codigoObjeto) {
							Registo rec = new Registo(Integer.parseInt(fields[0]),fields[1], fields[2], tempSala, FoundThat.formatoDataRegisto.parse(fields[4]), fields[5], to, fields[7].toLowerCase(), fields[8].toLowerCase(), fields[9]);
							registos.add(rec);
						}
					}

				}
			}

		}
		inFile.close();
	}


	//GRAVAR FICHEIROS
	public static void gravarFicheiro() throws Exception {

		//TipoUsers
		out = new PrintWriter(tipoUsersFile);
		for (ModelPrototype tu : tipoUsers) {
			out.println(tu.getCodigo() + "#" + tu.getNome() + "#" + ((TipoUser)tu).isRegisto() + "#" + ((TipoUser)tu).isReclamacao() + "#" + ((TipoUser)tu).isImportacao() + "#" + ((TipoUser)tu).isListagens() + "#" + ((TipoUser)tu).isDoacoes() + "#" + ((TipoUser)tu).isConfiguracoes());
		}
		out.close();

		//Users
		out = new PrintWriter(usersFile);
		for (User us : users) {
			out.println(us.getUser() + "#" + us.getPassword()+ "#" + us.getTipo().getCodigo());
		}
		out.close();

		//Institui��es
		out = new PrintWriter (instituicaoFile);
		@SuppressWarnings("unused")
		PrintWriter out2;

		for (ModelPrototype is : instituicoes) {
			out.println(is.getCodigo() + "#" + is.getNome().toLowerCase());
		}
		out.close();

		//TipoObjetos
		out = new PrintWriter(objetosFile);
		for (TipoObjeto to : tipoObjetos) {
			out.println(to.getCodigo()+"#"+to.getNome().toLowerCase()+"#"+ to.getCodigoIS().getCodigo());
		}
		out.close();



		//Salas
		out = new PrintWriter(salasFile);
		for (ModelPrototype s : salas) {
			out.println(s.getNome().toUpperCase());
		}
		out.close();

		//Registos
		String[] fields = null;

		for (int i = 0; i < listaRegistos.length; i++) {
			out = new PrintWriter (listaRegistos[i]);

			for (Registo reg : FoundThat.registos) {
				fields = FoundThat.formatoDataRegisto.format(reg.getData()).split("-");
				String dataNome = fields[1] + "-" + fields[2];

				if (listaRegistos[i].getName().equals(dataNome + ".txt")) {
					out.println(reg.getCodigo() + "#" + reg.getNome() + "#" + reg.getEmail() + "#" + reg.getSala() + "#" + FoundThat.formatoDataRegisto.format(reg.getData()) + "#" + reg.getHora() + "#" + reg.getObjeto().getCodigo() + "#" + reg.getCor() + "#" + reg.getEstado() + "#" + reg.getDescricao());	
				}
			}
			out.close();

		}
	}

	//VERIFICAR SE � N�MERO (UTILIZADO NA FRMREGISTO, NO CAMPO NOME)
	public static boolean isNumber(String str) {
		boolean res = false;;
		char[] ch = str.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			if (Character.isDigit(ch[i])) {
				res = true;
			}
		}	
		return res;
	}

	public static void lerDoacoesIS() throws FileNotFoundException, Exception, Exception {
		//Ler ficheiro de doacoes e gravar em array 
		registoDoacoes.clear();
		for (Registo reg : doacoes) {
			if (reg.getObjeto().getCodigoIS().getNome().equals(FrmLogin.txtUser.getText())) {
				registoDoacoes.add(reg);
			}
		}
	}

	public static void gravarDoacoes() throws Exception {
		//DOACOES
		out = new PrintWriter (doacoesFile);

		for (Registo reg : FoundThat.doacoes) {
			String[] fields = FoundThat.formatoDataRegisto.format(reg.getData()).split("-");
			@SuppressWarnings("unused")
			String dataNome = fields[1] + "-" + fields[2];
			out.println(reg.getCodigo() + "#" + reg.getNome() + "#" + reg.getEmail() + "#" + reg.getSala() + "#" + FoundThat.formatoDataRegisto.format(reg.getData()) + "#" + reg.getHora() + "#" + reg.getObjeto().getCodigo() + "#" + reg.getCor() + "#" + reg.getEstado() + "#" + reg.getDescricao());	
		}
		out.close();
	}

	public static void gravarDoacoesIS() throws Exception {
		String nomeIS = null;

		for (int i = 0; i < listaIS.length; i++) {
			out = new PrintWriter (listaIS[i]);

			for (Registo reg : FoundThat.doacoes) {
				nomeIS = reg.getObjeto().getCodigoIS().getNome();
				if (listaIS[i].getName().equals(nomeIS + ".txt")) {
					out.println(reg.getCodigo() + "#" + reg.getNome() + "#" + reg.getEmail() + "#" + reg.getSala() + "#" + FoundThat.formatoDataRegisto.format(reg.getData()) + "#" + reg.getHora() + "#" + reg.getObjeto().getCodigo() + "#" + reg.getCor() + "#" + reg.getEstado() + "#" + reg.getDescricao());	
				}
			}
			out.close();

		}
	}

	public static boolean checkPerfil(String instituicao) {
		res = false;
		for (ModelPrototype is : instituicoes) {
			if (instituicao.equals(is.getNome())) {
				res = true;
			}
		}
		return res;
	}
}







