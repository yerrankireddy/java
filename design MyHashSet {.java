class MyHashSet {

    private static final int SIZE = 769;
    private LinkedList<Integer>[] buckets;

    public MyHashSet() {
        buckets = new LinkedList[SIZE];
        for (int i = 0; i < SIZE; i++) {
            buckets[i] = new LinkedList<Integer>();
        }
    }

    private int hash(int key) {
        return key % SIZE;
    }

    public void add(int key) {
        LinkedList<Integer> bucket = buckets[hash(key)];
        if (!bucket.contains(key)) {
            bucket.add(key);
        }
    }

    public void remove(int key) {
        buckets[hash(key)].remove((Integer) key);
    }

    public boolean contains(int key) {
        return buckets[hash(key)].contains(key);
    }
}