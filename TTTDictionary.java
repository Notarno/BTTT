/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Martin Lang
*/

public class TTTDictionary implements TTTDictionaryADT{
    private HashTable T;
    public TTTDictionary(int size){
        T = new HashTable(size);
    }
    
    @Override
    public int put(TTTRecord record)
            throws DuplicatedKeyException {
            
        String config = record.getConfiguration();
        //Checks if the configuration already has a TTTRecord stored in the dictionary
        if(T.get(config) == null){
            T.add(config, record);
            return 0;
        }
        else{
                TTTRecord temp;
                temp = (TTTRecord)T.get(config);
                //If the cnfiguration is a duplicate an exception is thrown
                if(temp.getConfiguration().equals(record.getConfiguration())){
                    throw new DuplicatedKeyException("This key is a Duplicate");
                }
                T.add(config, record);
                return 1;
        }
    }
    
    @Override
    public void remove(String config)
            throws InexistentKeyException{
        if(T.get(config) == null){
             throw new InexistentKeyException("The key doesn't exist in this dictionary");
        }
        else{
            T.remove(config);
        }
    }
    //Gets the TTTRecord at the certain configuration
    @Override
    public TTTRecord get(String config){
        TTTRecord temp;
        if(T.get(config) != null){
            temp = (TTTRecord)T.get(config);
            return temp;
        }
        else{
            return null;
        }
    }
    //Returns the size of the dictionary
    @Override
    public int numElements(){
        return T.size();
    }
    
}
