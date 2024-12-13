package de.szut.mylists;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MyArrayListTest {

    private MyArrayList myArrayList;

    static int[] testElements = new int[] {461, 674, 1234, 768, 123566, 3242, 1, 454, 412, 3490, 0, 12, 54, 0};

    @BeforeEach
    void setUp() {
        myArrayList = new MyArrayList();
    }

    @Test
    void test_add_whenAddingElementsOfInitialArraySize() {
        // Arrange
        int[] toAdd = new int[MyArrayList.INIT_ARRAY_SIZE];
        for (int i = 0; i < MyArrayList.INIT_ARRAY_SIZE; i++) {
            toAdd[i] = i;
        }

        // Act
        for (int i : toAdd) {
            myArrayList.add(i);
        }

        // Assert
        assertThat(myArrayList.getInternalArrayForTest()).isEqualTo(toAdd);
    }

    @Test
    void test_add_whenAddingMoreElementsThenInitialArraySize() {
        // Arrange
        int amountToAdd = MyArrayList.INIT_ARRAY_SIZE + 5;
        int[] toAdd = new int[amountToAdd];
        for (int i = 0; i < amountToAdd; i++) {
            toAdd[i] = i;
        }

        // Act
        for (int i : toAdd) {
            myArrayList.add(i);
        }

        // Assert
        assertThat(myArrayList.getInternalArrayForTest()).isEqualTo(toAdd);
    }

    @ParameterizedTest
    @CsvSource({"1", "11", "21", "50", "101", "201"})
    void test_add_givenN_elements_thenAllAddedElementsArePresent(int elementsToAdd) {
        // Arrange
        int[] toAdd = new int[elementsToAdd];
        for (int i = 0; i < elementsToAdd; i++) {
            toAdd[i] = i;
        }

        // Act
        for (int i : toAdd) {
            myArrayList.add(i);
        }

        // Assert
        assertThat(myArrayList.getInternalArrayForTest()).isEqualTo(toAdd);
    }

    @Test
    void test_size_whenEmpty_thenReturn0() {
        // Arrange

        // Act
        int result = myArrayList.size();
        // Assert
        assertThat(result).isZero();
    }

    @Test
    void test_size_givenArrayToAdd_returnsSizeOfGivenArray() {
        // Arrange
        for (int testElement : testElements) {
            myArrayList.add(testElement);
        }

        // Act
        int resultSize = myArrayList.size();

        // Assert
        assertThat(resultSize).isEqualTo(testElements.length);
    }

    @ParameterizedTest
    @CsvSource({"1", "5", "11", "21"})
    void test_size_givenElementsToAdd_thenSizeEqualsGivenElements(int expectedSize) {
        for (int i = 0; i < expectedSize; i++) {
            myArrayList.add(i);
        }
        assertThat(myArrayList.size()).isEqualTo(expectedSize);
    }

    @Test
    void test_remove_whenGivenIndexExists_thenElementIsDeletedAndElementsAreMoved() {
        // Arrange
        for (int testElement : new int[] {2, 453, 756, 1024}) {
            myArrayList.add(testElement);
        }
        int[] expectedArray = new int[] {2, 453, 1024};

        // Act
        myArrayList.remove(2);

        // Assert
        assertThat(myArrayList.getInternalArrayForTest()).isEqualTo(expectedArray);
    }

    @Test
    void test_remove_whenRemovingFirstElement_FirstElementRemoved() {
        // Arrange
        for (int testElement : new int[] {2, 453, 756, 1024}) {
            myArrayList.add(testElement);
        }
        int[] expectedArray = new int[] {453, 756, 1024};

        // Act
        myArrayList.remove(0);

        // Assert
        assertThat(myArrayList.getInternalArrayForTest()).isEqualTo(expectedArray);
    }

    @Test
    void test_remove_whenRemovingLastElement_LastElementRemoved() {
        // Arrange
        for (int testElement : new int[] {2, 453, 756, 1024}) {
            myArrayList.add(testElement);
        }
        int[] expectedArray = new int[] {2, 453, 756};

        // Act
        myArrayList.remove(3);

        // Assert
        assertThat(myArrayList.getInternalArrayForTest()).isEqualTo(expectedArray);
    }

    @Test
    void test_remove_whenGivenIndexIfGreaterThenArrayListSize_thenThrowsRuntimeException() {
        // Arrange
        for (int testElement : new int[] {2, 453, 756, 1024}) {
            myArrayList.add(testElement);
        }

        // Act and Assert
        Exception exceptionLeererParameter = assertThrows(RuntimeException.class,
                () -> myArrayList.remove(4));
        assertEquals("Dieser Index existiert nicht!", exceptionLeererParameter.getMessage());
    }

    @Test
    void test_contains_whenGivenElementIsPresent_returnsTrue() {
        // Arrange
        myArrayList.add(2345);

        // Act
        boolean result = myArrayList.contains(2345);

        // Assert
        assertThat(result).isTrue();
    }

    @Test
    void test_contains_whenGivenElementIsNotPresent_returnsFalse() {
        // Arrange
        myArrayList.add(2345);

        // Act
        boolean result = myArrayList.contains(12345);

        // Assert
        assertThat(result).isFalse();
    }

    @Test
    void test_get_whenGetLastElement_returnsLastElement() {
        // Arrange
        for (int testElement : testElements) {
            myArrayList.add(testElement);
        }

        // Act
        int result = myArrayList.get(testElements.length - 1);

        // Assert
        assertThat(result).isEqualTo(testElements[testElements.length - 1]);
    }

    @Test
    void test_get_whenGetFirstElement_returnsFirstElement() {
        // Arrange
        for (int testElement : testElements) {
            myArrayList.add(testElement);
        }

        // Act
        int result = myArrayList.get(0);

        // Assert
        assertThat(result).isEqualTo(testElements[0]);
    }

    @Test
    void test_get_whenIndexIsPresent_returnsValuesOnThatIndex() {
        // Arrange
        for (int testElement : testElements) {
            myArrayList.add(testElement);
        }

        // Act
        int result = myArrayList.get(11);

        // Assert
        assertThat(result).isEqualTo(12);
    }

    @Test
    void test_get_whenIndexIsNotPresent_throwsIndexOutOfBoundException() {
        // Arrange
        for (int testElement : testElements) {
            myArrayList.add(testElement);
        }

        // Act
        assertThrows(IndexOutOfBoundsException.class, () -> myArrayList.get(testElements.length));
    }

}
