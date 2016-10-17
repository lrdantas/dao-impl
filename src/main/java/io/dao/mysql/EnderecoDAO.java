
package io.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Iterator;

import io.dao.common.DAOException;

import io.model.Endereco;
import io.model.Usuario;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class EnderecoDAO extends JdbcDAO<Endereco> {

    public EnderecoDAO(Connection connection) {

        super(connection);

    }

    @Override
    protected Iterator<Endereco> doFindAll(long page, long count)
        throws SQLException {

        String query = ""
            + "SELECT e.id, e.logradouro, e.numero, e.bairro, e.cidade, e.uf, e.cep "

            + "FROM endereco e "

            + "LIMIT ?, ? ";

        ResultSet resultSet = super.executeFind(query, page, count);

        return this.iterator(resultSet);

    }

    @Override
    protected Endereco doFindById(Object id)
        throws SQLException {

        String query = ""
            + "SELECT e.id, e.logradouro, e.numero, e.bairro, e.cidade, e.uf, e.cep "

            + "FROM endereco e "

            + "WHERE e.id = ? ";

        ResultSet resultSet = super.executeFind(query, id);

        for (Endereco endereco : this.iterable(resultSet)) {

            return endereco;

        }

        return null;

    }

    @Override
    protected void doRemove(int id)
        throws SQLException {

        String query = "DELETE FROM endereco WHERE id = ?";

        PreparedStatement stmt;

        stmt = this.prepare(query);

        stmt.setLong(1, id);

        int affectedRows = stmt.executeUpdate();

        System.out.println(String.format(
            "Affected rows: %d",

            affectedRows));

    }

    @Override
    protected void doSave(Endereco obj)
        throws SQLException {

        String query = ""
            + "INSERT INTO endereco (id, logradouro, numero, bairro, cidade, uf, cep) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?) "
            + "ON DUPLICATE KEY UPDATE "
            + "  logradouro = VALUES(logradouro), "
            + "  numero = VALUES(numero), "
            + "  bairro = VALUES(bairro), "
            + "  cidade = VALUES(cidade), "
            + "  uf = VALUES(uf), "
            + "  cep = VALUES(cep) ";

        PreparedStatement stmt = this.getStmtSave(query);

        if (obj.getId() == 0) {

            stmt.setNull(1, Types.BIGINT);

        } else {

            stmt.setLong(1, obj.getId());

        }

        stmt.setString(2, obj.getLogradouro());
        stmt.setString(3, obj.getNumero());
        stmt.setString(4, obj.getBairro());
        stmt.setString(5, obj.getCidade());
        stmt.setString(6, obj.getUf());
        stmt.setString(7, obj.getCep());

        int affectedRows = stmt.executeUpdate();

        ResultSet generatedKeys = stmt.getGeneratedKeys();

        if (obj.getId() == 0 && generatedKeys != null) {

            generatedKeys.next();

            obj.setId(generatedKeys.getInt(1));

        }

    }

    public Iterable<Endereco> findByUsuario(Usuario usuario) {

        String query = ""
            + "SELECT e.id, e.logradouro, e.numero, e.bairro, e.cidade, e.uf, e.cep "

            + "FROM endereco e "

            + "INNER JOIN usuario_endereco eu "
            + "ON eu.endereco_id = e.id "

            + "WHERE eu.usuario_id = ? ";

        ResultSet resultSet = super.executeFind(query, usuario.getId());

        return this.iterable(resultSet);

    }

    @Override
    protected Iterator<Endereco> iterator(ResultSet resultSet) {

        return new EnderecoIterator(resultSet);

    }

    @Override
    public void remove(Endereco obj)
        throws DAOException {

        super.removeById(obj.getId());

    }

}
