package pbst;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

/*
 * The NonemptyPBST class is used to create non empty objects of the Polymorphic
 * Binary Search Tree class. It serves the purpose of polymorphism as it allows
 * objects of the PBST class to be of two types, either empty or non empty.
 * The methods in this class are the same as the abstract superclass and provide
 * functionality as it relates to a non empty object of the PBST. Basic 
 * functionality includes adding new Non empty PBST objects to a PBS Tree, 
 * finding specific elements, removing specific elements, finding the number of 
 * key value pairs, etc. The premise a Non empty PBST object works on is having
 * a key, a value and two references to PBST objects, right and left. It also 
 * extends the comparable interface allowing like objects to be compared. Two
 * helper methods are used, one to check if any parameter is null and to throw
 * an IllegalArgumentException and the other is used in the collectionOfKeys()
 * method. The alternative of creating objects of type Non Empty PBST is that
 * of subclass Empty PBST. Objects created in this class follow the same 
 * principles nodes in binary search tree do. All methods follow some form of 
 * recursion to complete their functionality or use a helper method to do 
 * recursive calls.
 */
@SuppressWarnings("unchecked")
public class NonemptyPBST<K extends Comparable<K>, V> extends PBST<K, V> {

	// Creating required fields for a Non Empty PBST object to store data
	// as well references to the left and right PBST objects to follow tree
	// structure.
	private K key;
	private V value;
	private PBST<K, V> left, right;

	// Constructor for the Non Empty PBST objects, sets default value of
	// left and right references to a static Empty PBST object following
	// singleton structure.
	public NonemptyPBST(K keyIn, V valueIn, PBST<K, V> nextTree) {
		key = keyIn;
		value = valueIn;
		left = nextTree;
		right = nextTree;
	}

	// The addKeyValuePair method takes two parameters key and value of generic
	// types and adds them to the PBST object using the key. The method first
	// confirms the arguments are non null through nullCheck() helper method
	// and then proceeds to find the correct position of the Non Empty PBST
	// using the concept of Binary Search Tree (left is smaller number, right is
	// larger number). Once the appropriate location is found, the method
	// recursively calls the addKeyValuePair() method which at that point is
	// called on an Empty PBST object which creates a new (having null data)
	// Non Empty PBST. The method uses comparable interface to compare generic
	// key values.
	public NonemptyPBST<K, V> addKeyValuePair(K key, V value) {
		nullCheck(key);
		nullCheck(value);

		// If key to be added is smaller according to compareTo
		if (key.compareTo(this.key) < 0) {
			left = left.addKeyValuePair(key, value);
			// If key to be added is already exists, this reassigns value
		} else if (key.compareTo(this.key) == 0) {
			this.value = value;
			// If key to be added is larger according to compareTo
		} else {
			right = right.addKeyValuePair(key, value);
		}

		// returns a PBST object that contains all Non PBST and Empty PBST
		// objects
		return this;
	}

	// This method finds the number of key value pairs aka Non Empty PBST
	// objects currently in a PBST object
	public int numPairs() {
		return 1 + left.numPairs() + right.numPairs();
	}

	// This method finds the associated value of a given key passed as an
	// argument. The method first checks if argument is null using nullCheck()
	// helper method and then checks efficiently using concepts of binary search
	// tree what the associated value of the key its looking for is. At any
	// given search, at most only half the list will be traversed.
	public V getValueByKey(K keyToLookUp) {
		nullCheck(keyToLookUp);

		// If key is found, returns the associated value
		if (keyToLookUp.compareTo(key) == 0) {
			return value;
			// If key to be found is lesser according to compareTo
		} else if (keyToLookUp.compareTo(key) < 0) {
			return left.getValueByKey(keyToLookUp);
			// If key to be found is greater according to compareTo
		} else {
			return right.getValueByKey(keyToLookUp);
		}
	}

	// This method recursively finds the biggest key amongst all of the objects
	// that are not empty in PBST. It uses a try catch block to check if the
	// next elememnt in null in which case it stops recursing and returns the
	// that key. This method is efficient as it only traverses the right side
	// of the PBST as according to Binary Search tree concepts the largest key
	// would only ever be on the rightmost side.
	public K largestKey() throws EmptyPBSTException {
		try {
			return right.largestKey();
			// Catches thrown exception indicating next element is an Empty BST
			// object
		} catch (EmptyPBSTException e) {
			return key;
		}
	}

	// This method recursively finds the smallest key amongst all of the objects
	// that are not empty in PBST. It uses a try catch block to check if the
	// next elememnt in null in which case it stops recursing and returns the
	// that key. This method is efficient as it only traverses the left side
	// of the PBST as according to Binary Search tree concepts the smallest key
	// would only ever be on the leftmost side.
	public K smallestKey() throws EmptyPBSTException {
		try {
			return left.smallestKey();
			// Catches thrown exception indicating next element is an Empty BST
			// object
		} catch (EmptyPBSTException e) {
			return key;
		}
	}

	// This method recursively checks for a specified key and calculates +1 for
	// every right path taken and -1 for every left path taken. The resulting
	// integer is returned with 0 being returned if the current key is
	// key to be found. The argument is passed through nullCheck helper method
	// to ensure it is not null.
	public int pathBalance(K keyToFind) {
		nullCheck(keyToFind);

		// If key to be found is current key
		if (keyToFind.compareTo(key) == 0) {
			return 0;
			// If key to be found is on left side
		} else if (keyToFind.compareTo(key) < 0) {
			return -1 + left.pathBalance(keyToFind);
			// If key to be found is on right side
		} else {
			return 1 + right.pathBalance(keyToFind);
		}
	}

	// This method returns a HashSet collection of all the keys present in all
	// the Non empty PBST objects in a PBST. They are in no particular order and
	// contain no duplicates. The implementing of Collection interface allows
	// HashSet to be returned as it is a class that implements said interface.
	// This method uses a helper method to recursively call each Non Empty PBST
	// and add the key value to a hash set. The final hash set is returned.
	public Collection<K> collectionOfKeys() {
		HashSet<K> hSet = new HashSet<K>();
		helperMethod1(hSet);
		return hSet;
	}

	// Helper method that takes in HashSet from collectionOfKeys() method and
	// adds keys from every Non Empty PBST object in PBST. It starts from left
	// side and ends on right side of the PBST.
	public void helperMethod1(HashSet<K> hSet) {
		left.helperMethod1(hSet);
		hSet.add(key);
		right.helperMethod1(hSet);
	}

	// This method checks and removes a specified Key Value pair (Non Empty PBST
	// Object) by identifying it through its key. Firstly the argument is
	// checked for being non null and then the associated Non Empty PBST Node is
	// located using the compareTo method (from Comparable Interface). Once the
	// Non Empty PBST Node is found the method uses a specific way to remove it
	// from the PBST while maintaining the rest of the structure. It uses the
	// Binary Search Tree concept of taking the largest value from the left side
	// and assigning its value to the node to be removed. The largest node on
	// the left side is then removed through a process of recursive calls. A try
	// catch block is used to remove the leaf of the tree as once a Non Empty
	// PBST object is located without any left or right references it means it
	// is a leaf and must be cut off. This is when a EmptyPBSTException is
	// thrown to indicate references being Empty PBST objects. This allows this
	// leaf to be cut off by assigning the reference of the previous Non Empty
	// PBST object to being null.
	public PBST<K, V> removeKeyValuePair(K keyToRemove) {
		nullCheck(keyToRemove);

		// Locates key to remove using compareTo
		if (keyToRemove.compareTo(key) == 0) {
			// Uses try to keep changing current Non Empty PBST object's data to
			// left side's largest Non Empty PBST object's data
			try {
				value = getValueByKey(left.largestKey());
				key = left.largestKey();
				left = left.removeKeyValuePair(key);
				// Exception indicates presence of leaf and so reference to
				// static Empty PBST object is returned effectively cutting off
				// leaf
			} catch (EmptyPBSTException e) {
				return EmptyPBST.getInstance();
			}

			// If key to be removed is smaller according to compareTo
		} else if (keyToRemove.compareTo(key) < 0) {
			left = left.removeKeyValuePair(keyToRemove);
			// If key to be removed is larger according to compareTo
		} else {
			right = right.removeKeyValuePair(keyToRemove);
		}

		// A reference to the resulting PBST object is returned which may or may
		// not have been altered according to how the method was used
		return this;
	}

	// This method overrides the default toString method by recursively calling
	// every element in increasing order from left most element to rightmost
	// element. It automatically uses recursion in left and right calls as they
	// automatically call the toString method again creating a loop that
	// concludes when each Non Empty PBST node has been iterated over and is
	// concatenate into a singular string representing the PBST object.
	public String toString() {
		return left + "" + key + "/" + value + " " + right;
	}

	// Helper method from abstract PBST Class implemented to check if arguments
	// passed are null. This helper method is used throughout the Non Empty PBST
	// class
	public void nullCheck(Object O) {
		if (O == null) {
			throw new IllegalArgumentException();
		}
	}
}
