package com.examenweb2021.model.entities;
// Generated 28/06/2021 08:59:35 AM by Hibernate Tools 4.3.5.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Connectiontoken generated by hbm2java
 */
@Entity
@Table(name = "connectiontoken", catalog = "conexiones")
public class Connectiontoken implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Typedb typedb;
	private Usuario usuario;
	private String host;
	private String userdb;
	private String pass;
	private String db;
	private String token;
	private Short port;
	private Short state;
	private Set<Reporte> reportes = new HashSet<Reporte>(0);

	public Connectiontoken() {
	}

	public Connectiontoken(Typedb typedb, Usuario usuario, String host, String userdb, String pass, String db,
			String token, Short port, Short state, Set<Reporte> reportes) {
		this.typedb = typedb;
		this.usuario = usuario;
		this.host = host;
		this.userdb = userdb;
		this.pass = pass;
		this.db = db;
		this.token = token;
		this.port = port;
		this.state = state;
		this.reportes = reportes;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type")
	public Typedb getTypedb() {
		return this.typedb;
	}

	public void setTypedb(Typedb typedb) {
		this.typedb = typedb;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Column(name = "host", length = 200)
	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Column(name = "userdb", length = 50)
	public String getUserdb() {
		return this.userdb;
	}

	public void setUserdb(String userdb) {
		this.userdb = userdb;
	}

	@Column(name = "pass", length = 100)
	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Column(name = "db", length = 50)
	public String getDb() {
		return this.db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	@Column(name = "token", length = 50)
	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Column(name = "port")
	public Short getPort() {
		return this.port;
	}

	public void setPort(Short port) {
		this.port = port;
	}

	@Column(name = "state")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "connectiontoken")
	public Set<Reporte> getReportes() {
		return this.reportes;
	}

	public void setReportes(Set<Reporte> reportes) {
		this.reportes = reportes;
	}

}
