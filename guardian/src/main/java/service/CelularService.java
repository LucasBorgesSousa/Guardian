package service;

import java.util.List;

import dao.CelularDAO;
import model.Celular;

public class CelularService {

    CelularDAO dao = new CelularDAO();


    public void cadastrar(Celular celular) {

        if ("ATIVO".equalsIgnoreCase(
                celular.getStatusCelular())) {

            boolean possuiAtivo =
                dao.possuiCelularAtivo(
                    celular.getUsuarioId()
                );

            if (possuiAtivo) {

                throw new RuntimeException(
                    "O usuário já possui um celular ativo."
                );
            }
        }

        dao.cadastrar(celular);
    }


    public List<Celular> listar() {

        return dao.listar();
    }


    public Celular buscarPorId(int id) {

        return dao.buscarPorId(id);
    }


    public void atualizar(Celular celular) {

        if ("ATIVO".equalsIgnoreCase(
                celular.getStatusCelular())) {

            boolean possuiOutroAtivo =
                dao.possuiOutroCelularAtivo(
                    celular.getUsuarioId(),
                    celular.getId()
                );

            if (possuiOutroAtivo) {

                throw new RuntimeException(
                    "O usuário já possui outro celular ativo."
                );
            }
        }

        dao.atualizar(celular);
    }


    public void excluir(int id) {

        dao.excluir(id);
    }
}