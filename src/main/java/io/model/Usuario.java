
package io.model;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 */
public class Usuario {

    private int id;

    private String nome;

    private Collection<Endereco> enderecos;

    public Usuario() { }

    public Usuario(String nome) {

        this.nome = nome;

    }

    public Iterable<Endereco> getEnderecos() {

        return this.enderecos;
    }

    public int getId() {

        return this.id;
    }

    public String getNome() {

        return this.nome;
    }

    public void setEnderecos(Collection<Endereco> enderecos) {

        this.enderecos = enderecos;
    }

    public void addEndereco(Endereco endereco) {

        if (this.enderecos == null)
            this.enderecos = new LinkedList<Endereco>();

        this.enderecos.add(endereco);

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
