package pbst;

import java.util.Collection;
import java.util.HashSet;

/*
 * The EmptyPBST class is used to create empty objects of the Polymorphic
 * Binary Search Tree class. It serves the purpose of polymorphism as it allows
 * objects of the PBST class to be of two types, either empty or non empty.
 * The methods in this class are the same as the abstract superclass and provide
 * functionality as it relates to an empty object of the PBST. Basic 
 * functionality includes adding new empty PBST objects to a PBS Tree, 
 * finding specific elements, removing specific elements, finding the number of 
 * key value pairs, etc. The two helper methods used are implemented in this 
 * class just because they were implemented in the abstract class, they 
 * provide no functionality here apart from the addKeyValuePair() method. 
 * The alternative of creating objects of type Empty PBST is that of subclass 
 * Non Empty PBST. Objects created in this class 
 * follow the same principles empty nodes in binary search tree do. 
 * Most functionality in this class is restricted to edge cases of being called
 * on a non data containing object of PBST. It also acts a class to end 
 * recursive calls from the Non Empty PBST class. This class also follows a 
 * Singleton structure where only one instance of objects created by this class 
 * are shared throughout the PBST class making it efficient in memory usage.
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class EmptyPBST<K extends Comparable<K>, V> extends PBST<K, V> {
	// Using Singleton structure, creates static instance of Empty PBST Object
	private static EmptyPBST uniqueEmpty = new EmptyPBST();

	// Empty constructor to ovveride public default constructor
	private EmptyPBST() {
	}

	// This method is used to get the static instance of this class outside of
	// this class. Helps this class follow singleton structure.
	public static EmptyPBST getInstance() {
		return uniqueEmpty;
	}

	// This method adds a Non Empty PBST object to an otherwise empty PBST
	// object by calling the Non Empty PBST class's constructor. The new Non
	// Empty PBST object is returned to help in future chained recursive calls.
	// The nullCheck helper method is used to make sure values in arguments are
	// non null.
	public NonemptyPBST<K, V> addKeyValuePair(K newKey, V newValue) {
		nullCheck(newKey);
		nullCheck(newValue);

		return new NonemptyPBST<K, V>(newKey, newValue, this);
	}

	// Method returns 0 as no pairs exist in a Empty PBST object
	public int numPairs() {
		return 0;
	}

	// Method returns null as no keys exist in Empty PBST object
	public V getValueByKey(K keyToLookUp) {
		return null;
	}

	// Method throws an EmptyPBSTException() which is a declared exception to
	// indicate that no keys exist in this empty PBST object
	public K largestKey() throws EmptyPBSTException {
		throw new EmptyPBSTException();
	}

	// Method throws an EmptyPBSTException() which is a declared exception to
	// indicate that no keys exist in this empty PBST object
	public K smallestKey() throws EmptyPBSTException {
		throw new EmptyPBSTException();
	}

	// Method throws an IllegalArgumentException as now branches of PBST exist
	// to track pathBalance funtionality otherwise present in Non Empty PBST
	// Class
	public int pathBalance(K keyToFind) {
		throw new IllegalArgumentException();
	}

	// Method returns empty HashSet collection to indicate absence of any keys
	public Collection<K> collectionOfKeys() {
		HashSet<K> hSet = new HashSet<K>();
		return hSet;
	}

	// Method returns untouched PBST object as there are no keys to remove in an
	// Empty PBST object.
	public PBST<K, V> removeKeyValuePair(K keyToRemove) {
		return this;
	}

	// This method returns an empty string as no data exists in an Empty PBST
	// object
	public String toString() {
		return "";
	}

	// Helper method from abstract PBST Class implemented, has no use in this
	// class
	public void helperMethod1(HashSet<K> hSet) {
	}

	// Helper method from abstract PBST Class implemented to help check for null
	// values passed in arguments
	public void nullCheck(Object O) {
		if (O == null) {
			throw new IllegalArgumentException();
		}
	}
}
