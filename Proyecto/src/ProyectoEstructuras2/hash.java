/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProyectoEstructuras2;

/**
 *
 * @author ISAAC
 */


class hash{
    public boolean search(String P, String T){
        int q = 15485863;           //Numero primo
        int x = 256;                //Valor aleatorio entre 1 y p-1
        
        int pLength = P.length();   //Longitud del patron
        int tLength = T.length();   //Longitud del texto

        if(pLength > tLength){
            return false;
        }
        
        int pHash = PolyHash(P, q, x);
        
        int compHash = PolyHash(  T.substring(0, pLength), q, x);
        
        int y = 1;
        
        for(int i=0; i< pLength-1; i++){
            y = (y*x) % q;
        }     
        
        for(int i=0; i<= tLength-pLength; i++){
            if(pHash == compHash){
                if(P.equals( T.substring(i, i+pLength) )){
                    return true;
                }
            }
            
            
            if( i < tLength - pLength ){
                compHash = (x * (compHash - T.charAt(i)*y ) + T.charAt(i+pLength) ) % q;
                if(compHash<0){
                    compHash = (compHash + q);
                }
            }
            
        }
        
        return false;
        
    }
    
    public int PolyHash(String s, int q, int x){
        int hash = 0;
        int sLength = s.length();
        
        for(int i=0; i<sLength; i++){
            hash = (hash * x + s.charAt(i)) % q;
        }
        
        return hash;
    }
}