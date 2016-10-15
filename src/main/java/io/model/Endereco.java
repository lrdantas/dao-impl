
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

    private String cep;

    public Endereco() { }

    public String getBairro() {

        return this.bairro;
    }

    public String getCep() {

        return this.cep;
    }

    public String getCidade() {

        return this.cidade;
    }

    public int getId() {

        return this.id;
    }

    public String getLogradouro() {

        return this.logradouro;
    }

    public String getNumero() {

        return this.numero;
    }

    public String getUf() {

        return this.uf;
    }

    public void setBairro(String bairro) {

        this.bairro = bairro;
    }

    public void setCep(String cep) {

        this.cep = cep;
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

    @Override
    public String toString() {

        return String.format(
            "%s, %s - %s : %s/ %s %s",

            this.getLogradouro(),
            this.getNumero(),
            this.getBairro(),

            this.getCidade(),
            this.getUf(),
            this.getCep());

    }

}
