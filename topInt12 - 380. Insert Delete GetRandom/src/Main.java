import java.util.HashMap;

public class Main {

    class RandomizedSet {
        private HashMap<Integer, Integer> set;
        //index tracker (to make getRandom faster)
        private HashMap<Integer, Integer> idxTracker;
        private int count;

        public RandomizedSet() {
            set = new HashMap<>();
            idxTracker = new HashMap<>();
            count = 0;
        }

        public boolean insert(int val) {
            if(set.containsKey(val)){
                return false;
            }
            else{
                idxTracker.put(count, val);//reverse of the random set
                set.put(val, count++);//putting value with total element so far inserted
                return true;
            }
        }

        public boolean remove(int val) {
            if(set.containsKey(val)){
                int keyForIdx = set.get(val);//get the key from set to remove the element from idxTracker
                idxTracker.remove(keyForIdx);//not decrementing count so that the idxTracker doesnt get collided
                set.remove(val);
                return true;
            }
            else{
                return false;
            }
        }

        public int getRandom() {//worst performance when the amount of data is huge and many remove operations are done
            while(true){//generate a random key for idxTracker, run until a valid key found
                int randomNum = (int)(Math.random() * count);
                if(idxTracker.containsKey(randomNum)){
                    return idxTracker.get(randomNum);
                }
            }
        }
    }

    public static void main(String[] args) {
        //write code to test
    }
}