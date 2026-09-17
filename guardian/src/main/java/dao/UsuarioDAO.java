package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;
import util.ConnectionFactory;

public class UsuarioDAO {

    public void cadastrar(Usuario usuario) {

        try {

            Connection conexao = ConnectionFactory.conectar();

            String sql =
                "insert into usuarios (nome, email, senha) " +
                "values (?, ?, ?)";

            PreparedStatement stmt =
                conexao.prepareStatement(sql);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());

            stmt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public List<Usuario> listar() {

        List<Usuario> usuarios = new ArrayList<>();

        String sql = "select * from usuarios";

        try {

            Connection conexao =
                ConnectionFactory.conectar();

            PreparedStatement stmt =
                conexao.prepareStatement(sql);

            ResultSet rs =
                stmt.executeQuery();

            while (rs.next()) {

                Usuario usuario = new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha")
                );

                usuarios.add(usuario);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return usuarios;
    }

    public Usuario buscarPorId(int id) {

        String sql =
            "select * from usuarios where id = ?";

        try {

            Connection conexao =
                ConnectionFactory.conectar();

            PreparedStatement stmt =
                conexao.prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet rs =
                stmt.executeQuery();

            if (rs.next()) {

                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha")
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    public void atualizar(Usuario usuario) {

        try {

            Connection conexao =
                ConnectionFactory.conectar();

            String sql =
                "update usuarios set " +
                "nome = ?, email = ?, senha = ? " +
                "where id = ?";

            PreparedStatement stmt =
                conexao.prepareStatement(sql);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setInt(4, usuario.getId());

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
                "delete from usuarios where id = ?";

            PreparedStatement stmt =
                conexao.prepareStatement(sql);

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // LOGIN

    public Usuario login(String email, String senha) {

        String sql =
            "select * from usuarios " +
            "where email = ? and senha = ?";

        try {

            Connection conexao =
                ConnectionFactory.conectar();

            PreparedStatement stmt =
                conexao.prepareStatement(sql);

            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs =
                stmt.executeQuery();

            if (rs.next()) {

                return new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha")
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
}