
package io.model;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class Usuario {

    private int id;

    private String nome;
    private Iterable<Endereco> enderecos;

    public Usuario() { }

    public Usuario(String nome) {

        this.nome = nome;
    }

    public Iterable<Endereco> getEnderecos() {

        return enderecos;
    }

    public int getId() {

        return this.id;
    }

    public String getNome() {

        return nome;
    }

    public void setEnderecos(Iterable<Endereco> enderecos) {

        this.enderecos = enderecos;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    @Override
    public String toString() {

        return String.format(
            "#%d: %s",

            this.getId(),
            this.getNome());

    }

}
