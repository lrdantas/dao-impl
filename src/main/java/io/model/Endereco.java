
package io.model;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class Endereco {

    private int id;

    private String logradouro;
    private String numero;
    private String bairro;

    private String cidade;
    private String uf;

    public Endereco() { }

    public String getBairro() {

        return bairro;
    }

    public String getCidade() {

        return cidade;
    }

    public int getId() {

        return id;
    }

    public String getLogradouro() {

        return logradouro;
    }

    public String getNumero() {

        return numero;
    }

    public String getUf() {

        return uf;
    }

    public void setBairro(String bairro) {

        this.bairro = bairro;
    }

    public void setCidade(String cidade) {

        this.cidade = cidade;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setLogradouro(String logradouro) {

        this.logradouro = logradouro;
    }

    public void setNumero(String numero) {

        this.numero = numero;
    }

    public void setUf(String uf) {

        this.uf = uf;
    }

}
