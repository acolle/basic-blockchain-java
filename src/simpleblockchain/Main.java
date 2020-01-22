package simpleblockchain;
import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class Main {

    // We use an ArrayList here as the blockchain
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 5;

    public static void main(String[] args) {

        // Add blocks to the blockchain
        blockchain.add(new Block("First block", "0"));
        blockchain.add(new Block("Second block", blockchain.get(blockchain.size()-1).hash));
        blockchain.add(new Block("Third block", blockchain.get(blockchain.size()-1).hash));
//        addBlock(new Block("First block", "0"));
//        addBlock(new Block("Second block", blockchain.get(blockchain.size()-1).hash));
//        addBlock(new Block("Third block", blockchain.get(blockchain.size()-1).hash));

//        System.out.println("\nBlockchain is Valid: " + isChainValid());
//
        String blockchainJson = StringUtil.getJson(blockchain);
        System.out.println(blockchainJson);
    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        //loop through blockchain to check hashes:
        for(int i=1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);
            //compare registered hash and calculated hash:
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            //check if hash is solved
            if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }

    public static void addBlock(Block newBlock) {
        newBlock.mineBlock(difficulty);
        blockchain.add(newBlock);
    }
}