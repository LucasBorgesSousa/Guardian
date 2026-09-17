package controller;

import java.util.List;

import model.Usuario;
import service.UsuarioService;

public class UsuarioController {

    UsuarioService service = new UsuarioService();

    public void cadastrar(Usuario usuario) {
        service.cadastrar(usuario);
    }

    public List<Usuario> listar() {
        return service.listar();
    }

    public void atualizar(Usuario usuario) {
        service.atualizar(usuario);
    }

    public void excluir(int id) {
        service.excluir(id);
    }

    public Usuario login(String email, String senha) {
        return service.login(email, senha);
    }

    public Usuario buscarPorId(int id) {
        return service.buscarPorId(id);
    }
}