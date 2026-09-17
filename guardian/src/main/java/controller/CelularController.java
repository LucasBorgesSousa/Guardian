package controller;

import java.util.List;

import model.Celular;
import service.CelularService;

public class CelularController {

    CelularService service = new CelularService();


    public void cadastrar(Celular celular) {

        service.cadastrar(celular);
    }


    public List<Celular> listar() {

        return service.listar();
    }


    public Celular buscarPorId(int id) {

        return service.buscarPorId(id);
    }


    public void atualizar(Celular celular) {

        service.atualizar(celular);
    }


    public void excluir(int id) {

        service.excluir(id);
    }
}