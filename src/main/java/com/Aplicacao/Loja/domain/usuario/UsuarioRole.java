package com.Aplicacao.Loja.domain.usuario;

public enum UsuarioRole {
    GERENTE("gerente"),
    SUPERVISOR("supervisor"),
    VENDERDOR("vendedor"),
    CLIENTE("cliente"),
    USUARIO("usuario");

    private String role;

    UsuarioRole(String role){
        this.role = role;
    }
    public String getRole(){
        return role;
    }
}
