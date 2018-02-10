/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
 
//Class node created for the chains
class Node<K, V>
{
    //Key and value objects
    K key;
    V value;
    //Reference to next node
    Node<K, V> next;
 
    public Node(K key, V value)
    {
        this.key = key;
        this.value = value;
    }
}
 
// Class to represent entire hash table
public class HashTable<K, V>
{
    // bucketArray is used to store array of chains
    private ArrayList<Node<K, V>> T;
    //Number of elements in the ArrayList
    private int numOfElements;
    //Max size of the ArrayList
    private int size;
 
    public HashTable(int s)
    {
        T = new ArrayList<>();
        size = s;
        numOfElements = 0;
 
        //HashTable is created with empty slots
        for (int i = 0; i < size; i++)
            T.add(null);
    }
    
    //returns the number of elements in the hash table
    public int size(){ 
        return numOfElements;
    }
    //checks to see if the hash table is empty
    public boolean isEmpty(){ 
        return size() == 0;
    } 
    //HashCode generater using polynomial accumulation
   @Override
   public int hashCode(){
       final int prime = 31;
       int result = 1;
       result = prime*result + ((T == null) ? 0 : T.hashCode());
       return result;
   }
   //Gets the index of the inserted key
    private int getIndex(K key)
    {
        int hashCode = key.hashCode();
        
        if (hashCode < 0){
            hashCode = hashCode*-1;
        }
        
        //Compressing Function, basic mod N
        int index = hashCode % size;
        return index;
    }
 
    //Removes the value at the key as well as the key itself
    public V remove(K key)
    {
        int index = getIndex(key);
        Node<K, V> header = T.get(index);
        Node<K, V> previous = null;
        
        //Loops until the key is found or it doesn't exist
        while (header != null)
        {
            if (header.key.equals(key))
                break;
 
            previous = header;
            header = header.next;
        }
 
        //Key was not in the hash table
        if (header == null){
            return null;
        }
        numOfElements--;
 
        //Key was in the hash table, so remove key
        if (previous != null){
            previous.next = header.next;
        }
        else{
            T.set(index, header.next);
        }
        
        return header.value;
    }
 
    //Gets the value for a certain key
    public V get(K key)
    {
        int index = getIndex(key);
        Node<K, V> header = T.get(index);
 
        //Search for the key
        while (header != null)
        {
            if (header.key.equals(key))
                return header.value;
            header = header.next;
        }
 
        //If key not found
        return null;
    }
 
    //Adds a key value pair to hash table
    public void add(K key, V value)
    {

        int index = getIndex(key);
        Node<K, V> header = T.get(index);
 
        // Check if key is already present
        while (header != null)
        {
            if (header.key.equals(key))
            {
                header.value = value;
                return;
            }
            header = header.next;
        }
 
        numOfElements++;
        header = T.get(index);
        Node<K, V> N = new Node<K, V>(key, value);
        N.next = header;
        T.set(index, N);
 
        //If load factor goes beyond threshold, then
        // double hash table size
        if ((1.0*numOfElements)/size >= 0.7)
        {
            ArrayList<Node<K, V>> temp = T;
            T = new ArrayList<>();
            size = 2 * size;
            numOfElements = 0;
            for (int i = 0; i < size; i++)
                T.add(null);
 
            for (Node<K, V> headNode : temp)
            {
                while (headNode != null)
                {
                    add(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }
}
 