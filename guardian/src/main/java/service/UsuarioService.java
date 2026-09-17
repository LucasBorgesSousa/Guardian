package service;

import java.util.List;

import dao.UsuarioDAO;
import model.Usuario;

public class UsuarioService {

    UsuarioDAO dao = new UsuarioDAO();

    public void cadastrar(Usuario usuario) {
        dao.cadastrar(usuario);
    }

    public List<Usuario> listar() {
        return dao.listar();
    }

    public void atualizar(Usuario usuario) {
        dao.atualizar(usuario);
    }

    public void excluir(int id) {
        dao.excluir(id);
    }

    public Usuario login(String email, String senha) {
        return dao.login(email, senha);
    }

    public Usuario buscarPorId(int id) {
        return dao.buscarPorId(id);
    }
}