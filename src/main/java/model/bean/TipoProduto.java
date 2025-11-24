/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

import java.util.Objects;

/**
 *
 * @author gregory
 */
public class TipoProduto {
    private int id;
    private String descricao;
    
    // Métodos de encapsulamento
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
    /* Os dois próximos métodos servem para poder comparar o tipo de produto de 
       meuProduto (na TelaProduto) com os objetos de TipoProduto do combobox de TipoProduto */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TipoProduto tp = (TipoProduto) obj;
        return this.id == tp.id; // visto que id é um identificador único para o TipoProduto
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Utiliza o mesmo campo de comparação
    }
}
