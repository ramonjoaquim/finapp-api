/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enums;

/**
 *
 * @author sistema
 */
public enum TipoLancamento {
   
    lancamento(1),
    despesa(0);
    
    private int value;
    
    private TipoLancamento(int value){
        this.value = value;
    }
    
}
