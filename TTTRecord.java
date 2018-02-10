/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Martin Lang
 */
public class TTTRecord {
    private String c;
    private int s;
    private int l;
    
    public TTTRecord(String config, int score, int level){
        c = config;
        s = score;
        l = level;
    }
    
    public String getConfiguration(){
        return c;
    }
    
    public int getScore(){
        return s;
    }
    
    public int getLevel(){
        return l;
    }
}
