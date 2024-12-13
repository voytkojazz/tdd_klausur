package de.szut.mylists;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class MyArrayList {

    private int[] values;

    private int size;

    static final int INIT_ARRAY_SIZE = 10;

    public MyArrayList() {
        this.values = new int[INIT_ARRAY_SIZE];
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public void add(int value) {
        if (size == values.length) {
            copyAndExtendInternalArray();
        }
        this.values[size] = value;
        this.size++;
    }

    public int get(int index) {
        if (index > this.size - 1) {
            throw new IndexOutOfBoundsException();
        }
        return this.values[index];
    }

    public void remove(int index) throws RuntimeException {
        if (!indexExists(index)) {
            throw new RuntimeException("Dieser Index existiert nicht!");
        }
        int[] firstSubSequence = Arrays.copyOfRange(values, 0, index);
        int[] secondSubSequence = Arrays.copyOfRange(values, index + 1, values.length);
        this.values = IntStream.concat(IntStream.of(firstSubSequence), IntStream.of(secondSubSequence)).toArray();
        this.size -= 1;
    }

    public boolean contains(int value) {
        for (int v : values) {
            if (Objects.equals(value, v)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Only visible for testing.
     *
     * @return the array that this class uses internally to store the values.
     * The array is returned without empty cells on the end (only the array of a size if this list is returned).
     *
     */
    int[] getInternalArrayForTest() {
        return Arrays.copyOf(this.values, this.size);
    }

    private void copyAndExtendInternalArray() {
        int newSize = size + 10;
        this.values = Arrays.copyOf(values, newSize);
    }

    private boolean indexExists(int index) {
        return index <= this.size - 1;
    }

}
