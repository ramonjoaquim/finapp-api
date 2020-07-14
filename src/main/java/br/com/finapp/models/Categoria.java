/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.finapp.models;

/**
 *
 * @author sistema
 */


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name= "categoria", schema = "public")
public class Categoria implements Serializable{
    
    @Id
    @GeneratedValue
    private UUID id;
    
    @Column(nullable = false)
    private String descricao;
    
    @JsonIgnore
    @OneToMany(mappedBy = "categoria")
    private List<Lancamento> lancamentos;
    
    @JsonIgnore
    @OneToMany(mappedBy = "categoria")
    private List<Boleto> boletos;

    public Categoria() {
        this.lancamentos = new ArrayList<>();
        this.boletos = new ArrayList<>();
    }
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }

    public void setLancamentos(List<Lancamento> lancamentos) {
        this.lancamentos = lancamentos;
    }

    public List<Boleto> getBoletos() {
        return boletos;
    }

    public void setBoletos(List<Boleto> boletos) {
        this.boletos = boletos;
    }
       
}
