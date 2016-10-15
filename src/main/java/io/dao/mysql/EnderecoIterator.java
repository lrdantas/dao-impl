
package io.dao.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;

import io.model.Endereco;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class EnderecoIterator extends EntityIterator<Endereco> {

    public EnderecoIterator(ResultSet resultSet) {

        super(resultSet);

    }

    @Override
    protected Endereco fetchCurrent(ResultSet resultSet)
        throws SQLException {

        Endereco endereco = new Endereco();

        endereco.setLogradouro(resultSet.getString("logradouro"));
        endereco.setNumero(resultSet.getString("numero"));
        endereco.setBairro(resultSet.getString("bairro"));
        endereco.setCidade(resultSet.getString("cidade"));
        endereco.setUf(resultSet.getString("uf"));
        endereco.setCep(resultSet.getString("cep"));

        return endereco;

    }

}
