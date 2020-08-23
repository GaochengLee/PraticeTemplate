package algo;

import java.util.BitSet;

public class BloomFilter {

    private static final int DEFAULT_SIZE = 1 << 25;
    private static final int[] seeds = new int[]{5, 7, 11, 13, 31, 37, 61};
    private final BitSet bits = new BitSet(DEFAULT_SIZE);
    private final SimpleHash[] func = new SimpleHash[seeds.length];

    public BloomFilter() {
        for (int i = 0; i < seeds.length; i++)
            func[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);
    }

    public void add(String value) {
        for (SimpleHash f : func) bits.set(f.hash(value), true);
    }

    public boolean contains(String value) {
        if (value == null) return false;
        boolean ret = true;
        for (SimpleHash f : func) ret = ret && bits.get(f.hash(value));
        return ret;
    }

    private static class SimpleHash {
        private int capasity;
        private int seed;

        public SimpleHash(int capasity, int seed) {
            this.capasity = capasity;
            this.seed = seed;
        }

        public int hash(String value) {
            int result = 0;
            for (char c : value.toCharArray())
                result = seed * result + c;
            return (capasity - 1) & result;
        }
    }
}