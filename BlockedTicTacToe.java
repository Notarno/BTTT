


/**
 *
 * @author Martin Lang
 */
public class BlockedTicTacToe {
    private int boardSize;
    private int x;
    private int maxLevels;
    
    private char gameBoard[][];
    private TTTDictionary T;
    
    
    public BlockedTicTacToe(int board_size, int inline, int max_levels){
        boardSize = board_size;
        x = inline;
        maxLevels = max_levels;
        gameBoard = new char[board_size][board_size];
        
        //Allocating empty spaces in the gameBoard
        for (int i = 0; i < board_size; i++){
            for(int j = 0; j < board_size; j++){
                gameBoard[i][j] = ' ';
            }
        }            
    }
    
    public TTTDictionary createDictionary(){
        //5012 is the size of the dictionary
        T = new TTTDictionary(5021);
        return T;
    }
    
    public int repeatedConfig(TTTDictionary configurations){
        //Converting the gameBoard of type char[][] to string type
        String config = String.valueOf(gameBoard);
        TTTRecord temp;
        //Checks to see if specified config is in the dictonary
        //and if it is returns the score of that config
        if (configurations.get(config) != null){
            temp = configurations.get(config);
            return temp.getScore();
        }
        else{
            return -1;
        }
    }
    
    public void insertConfig(TTTDictionary configurations, int score, int level){
        //Converting the gameBoard of type char[][] to string type
        String config = String.valueOf(gameBoard);
        TTTRecord temp = new TTTRecord(config, score, level);
        //inserts the gameBoard configuration into the dictionary
        try{
            configurations.put(temp);
        }
        catch(DuplicatedKeyException e){
            System.out.println("Configuration is already stored");
        }
    }
    
    public void storePlay(int row, int col, char symbo){
        gameBoard[row][col] = symbo;
    }
    
    public boolean squareIsEmpty(int row, int col){
        if(gameBoard[row][col] == ' '){
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean wins(char symbol){
        //checks for row sequence
        int counter = 0;
        for (int i = 0; i < boardSize; i++){
            counter = 0;
            for (int j = 0; j < boardSize; j++){
                if (gameBoard[i][j] == symbol){
                    counter++;
                }
                if(counter == x){
                    return true;
                }
            }
        }
        
        counter = 0;
        //Checks for column sequence
        for (int i = 0; i < boardSize; i++){
            counter = 0;
            for (int j = 0; j < boardSize; j++){
                if (gameBoard[j][i] == symbol){
                    counter++;
                }
                if(counter == x){
                    return true;
                }
            }
         }
        
        counter = 0;
        //Checks for Diagonal sequence starting at top left
        for (int i = 0; i < boardSize; i++){
            if (gameBoard[i][i] == symbol){
                counter++;
            }
            if(counter == x){
                return true;
            }
        }
        
        counter = 0;
        //Checks for Diagonal starting at Top Right
        for (int i = 0, j = boardSize - 1; i < boardSize; i++, j--){
            if (gameBoard[i][j] == symbol){
                counter++;
            }
            if(counter == x){
                return true;
            }
        }      
        return false;
    }
    
    public boolean isDraw(){
        //checks if there is still a empty space on the board
        for (int i = 0; i < boardSize; i++){
                for (int j = 0; j < boardSize; j++){
                    if (gameBoard[i][j] == ' '){
                        return false;
                    }
                }
        }
        
        //checks to see if human or computer has won the game
        if(this.wins('x')){
            return false;
        }
        else if (this.wins('o')){
            return false;
        }
        else{
            return true;
        }
    }
    
    public int evalBoard(){
        //checks if computer has won
        if(this.wins('o')){
            return 3;
        }
        //checks if human has won
        else if(this.wins('x')){
            return 0;
        }
        //checks if a draw has occured
        else if(this.isDraw()){
            return 1;
        }
        else{
            return 2;
        }   
    }
}