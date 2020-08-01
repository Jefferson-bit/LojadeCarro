package com.crash.domain.enums;

public enum Perfil {
	
	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	private int code;
	private String descricao;

	private Perfil(int code, String descricao) {
		this.code = code;
		this.descricao = descricao;
	}

	public int getCode() {
		return code;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Perfil toEnums(Integer code) {
		if(code == null) {
			return null;
		}
		for(Perfil perfil: Perfil.values()) {
			if(code.equals(perfil.getCode())) {
				return perfil;
			}
		}
		throw new IllegalArgumentException("ID invalido: " + code);
	}
}
