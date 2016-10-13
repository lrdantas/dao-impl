/**
 *
 */
package io.model;

/**
 * @author José Nascimento <joseaugustodearaujonascimento@gmail.com>
 *
 */
public class Usuario {

    private int id;
    private String nome;

    public Usuario() { }

    public Usuario(String nome) {

        this.nome = nome;
    }

    public int getId() {

        return id;
    }

    public String getNome() {

        return nome;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

}
