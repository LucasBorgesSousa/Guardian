package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Celular;
import util.ConnectionFactory;

public class CelularDAO {

    public void cadastrar(Celular celular) {

        try {

            Connection conexao = ConnectionFactory.conectar();

            String sql = "insert into celulares " +
                         "(marca, modelo, IMEI, status_celular, usuario_id) " +
                         "values (?, ?, ?, ?, ?)";

            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, celular.getMarca());
            stmt.setString(2, celular.getModelo());
            stmt.setString(3, celular.getIMEI());
            stmt.setString(4, celular.getStatusCelular());
            stmt.setInt(5, celular.getUsuarioId());

            stmt.executeUpdate();

        } catch (Exception e) {

            throw new RuntimeException(
                "Erro ao cadastrar celular: " + e.getMessage(),
                e
            );
        }
    }


    public List<Celular> listar() {

        List<Celular> celulares = new ArrayList<>();

        String sql = "select * from celulares";

        try {

            Connection conexao = ConnectionFactory.conectar();

            PreparedStatement stmt =
                conexao.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                String IMEI = rs.getString("IMEI");
                String statusCelular =
                    rs.getString("status_celular");
                int usuarioId =
                    rs.getInt("usuario_id");

                Celular celular = new Celular(
                    id,
                    marca,
                    modelo,
                    IMEI,
                    statusCelular,
                    usuarioId
                );

                celulares.add(celular);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return celulares;
    }


    // BUSCAR CELULAR PELO ID

    public Celular buscarPorId(int id) {

        String sql =
            "select * from celulares where id = ?";

        try {

            Connection conexao =
                ConnectionFactory.conectar();

            PreparedStatement stmt =
                conexao.prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet rs =
                stmt.executeQuery();

            if (rs.next()) {

                return new Celular(
                    rs.getInt("id"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getString("IMEI"),
                    rs.getString("status_celular"),
                    rs.getInt("usuario_id")
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }


    public void atualizar(Celular celular) {

        try {

            Connection conexao =
                ConnectionFactory.conectar();

            String sql =
                "update celulares set " +
                "marca = ?, modelo = ?, IMEI = ?, " +
                "status_celular = ?, usuario_id = ? " +
                "where id = ?";

            PreparedStatement stmt =
                conexao.prepareStatement(sql);

            stmt.setString(1, celular.getMarca());
            stmt.setString(2, celular.getModelo());
            stmt.setString(3, celular.getIMEI());
            stmt.setString(4, celular.getStatusCelular());
            stmt.setInt(5, celular.getUsuarioId());
            stmt.setInt(6, celular.getId());

            stmt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    public void excluir(int id) {

        try {

            Connection conexao =
                ConnectionFactory.conectar();

            String sql =
                "delete from celulares where id = ?";

            PreparedStatement stmt =
                conexao.prepareStatement(sql);

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // VERIFICA SE O USUÁRIO JÁ POSSUI CELULAR ATIVO

    public boolean possuiCelularAtivo(int usuarioId) {

        String sql =
            "select count(*) from celulares " +
            "where usuario_id = ? " +
            "and status_celular = 'ATIVO'";

        try {

            Connection conexao =
                ConnectionFactory.conectar();

            PreparedStatement stmt =
                conexao.prepareStatement(sql);

            stmt.setInt(1, usuarioId);

            ResultSet rs =
                stmt.executeQuery();

            if (rs.next()) {

                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }


    // VERIFICA SE EXISTE OUTRO CELULAR ATIVO
    // IGNORANDO O CELULAR QUE ESTÁ SENDO EDITADO

    public boolean possuiOutroCelularAtivo(
            int usuarioId,
            int idCelular) {

        String sql =
            "select count(*) from celulares " +
            "where usuario_id = ? " +
            "and status_celular = 'ATIVO' " +
            "and id <> ?";

        try {

            Connection conexao =
                ConnectionFactory.conectar();

            PreparedStatement stmt =
                conexao.prepareStatement(sql);

            stmt.setInt(1, usuarioId);
            stmt.setInt(2, idCelular);

            ResultSet rs =
                stmt.executeQuery();

            if (rs.next()) {

                return rs.getInt(1) > 0;
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }


    public void excluirPorUsuario(int usuarioId) {

        try {

            Connection conexao =
                ConnectionFactory.conectar();

            String sql =
                "delete from celulares where usuario_id = ?";

            PreparedStatement stmt =
                conexao.prepareStatement(sql);

            stmt.setInt(1, usuarioId);

            stmt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}