import java.util.ArrayList;

public class BSTOrderedDictionary implements BSTOrderedDictionaryADT{

	private BSTNode root; //root of tree
	private int numInternalNodes; //number of internal nodes
	
	public BSTOrderedDictionary() {
		this.root = new BSTNode();
		this.numInternalNodes = 0;
	}
	
	@Override
	public BSTNode getRoot() {
		/*
		 * Returns the root of this tree.
		 */
		return this.root;
	}

	@Override
	public int getNumInternalNodes() {
		/*
		 * Returns the value of numInternalNodes.
		 */
		return this.numInternalNodes;
	}

	@Override
	public ArrayList<MultimediaItem> get(BSTNode r, String k) {
		/*
		 * finds in the tree with root r the BSTNode with the given key attribute key and it returns the list of MultimediaItem objects stored in it; 
		 * it returns null if no node in the tree stores the given key.
		 */
		k = k.toLowerCase();
		BSTNode p = getNode(r,k);
		if(p == null) {
			return null;
		}
		else {
			return p.getData().getMedia();
		}
	}

	@Override
	public void put(BSTNode r, String name, String content, int type) {
		/*
		 * Adds the given information to the tree with root r.
		 */
		name = name.toLowerCase();
		NodeData newNodeData = new NodeData(name);
		MultimediaItem newMultimediaItem = new MultimediaItem(content,type);
		
		//Empty Tree
		if(numInternalNodes == 0) {
			this.root.setData(newNodeData);
			this.root.getData().add(newMultimediaItem);
			this.numInternalNodes++;
		}
		else {
			/*
			 * If the tree already has a node p with key attribute equal to key then a new MultimediaItem object storing the given content and type is added to the ArrayList stored in p.
			 */
			if(name.compareTo(r.getData().getName()) == 0) {
				//If a node found matches current key...
				newNodeData = r.getData();
				newNodeData.add(newMultimediaItem);
				r.setData(newNodeData);
			}
			else if(name.compareTo(r.getData().getName()) < 0) {
				//If a node found is less than the current key...
				if(r.getLeftChild() == null) {
					//If it is a leaf create a new node and replace the leaf
					BSTNode c = new BSTNode();
					newNodeData.add(newMultimediaItem);
					r.setLeftChild(c);
					c.setParent(r);
					c.setData(newNodeData);
					this.numInternalNodes++;
				}
				else {
					put(r.getLeftChild(),name,content,type);
				}
			}
			else if(name.compareTo(r.getData().getName()) > 0) {
				//If a node found is more than the current key...
				if(r.getRightChild() == null) {
					BSTNode c = new BSTNode();
					newNodeData.add(newMultimediaItem);
					r.setRightChild(c);
					c.setParent(r);
					c.setData(newNodeData);
					this.numInternalNodes++;
				}
				else {
					put(r.getRightChild(),name,content,type);
				}
			}
		}
	}

	@Override
	public void remove(BSTNode r, String k) throws DictionaryException {
		/*
		 * Removes from the binary search tree with root r the BSTNode storing the given key attribute key.
		 * The method throws a DictionaryException if no node stores the given key.
		 */
		BSTNode p = getNode(r,k);
		if(p == null) {
			throw new DictionaryException("Node does not exist.");
		}
		else {
			BSTNode p2 = p.getParent();
			BSTNode c1 = p.getLeftChild();
			BSTNode c2 = p.getRightChild();
			if(c1 == null) {
				if(p2 == null) {
					this.root = c2;
				}
				else {
					if(p2.getLeftChild() == p) {
						p2.setLeftChild(c2);
						c2.setParent(p2);
					}
					else {
						p2.setRightChild(c2);
						c2.setParent(p2);
					}
				}
				this.numInternalNodes--;
			}
			if(c2 == null) {
				if(p2 == null) {
					this.root = c1;
				}
				else {
					if(p2.getLeftChild() == p) {
						p2.setLeftChild(c1);
						c1.setParent(p2);
					}
					else {
						p2.setRightChild(c1);
						c1.setParent(p2);
					}
				}
				this.numInternalNodes--;
			}
			else {
				BSTNode s = smallestNode(p.getRightChild());
				p.setData(s.getData());
				remove(s,s.getData().getName());
			}
		}
	}

	@Override
	public void remove(BSTNode r, String k, int type) throws DictionaryException {
		/*
		 * Removes from the tree with root r all MultimediaItem objects of the type specified by the third parameter stored in the ArrayList of the node with key attribute key. 
		 * If after removing these MultimediaItem objects the ArrayList becomes empty, the BSTNode with key attribute key must be removed from the tree.
		 */
		ArrayList<MultimediaItem> p = get(r,k);
		if(p == null) {
			throw new DictionaryException("Data not found.");
		}
		else {
			for(int n = 0; n < p.size();n++) {
				if(p.get(n).getType() == type) {
					p.remove(n);
				}
			}
			if(p.size() == 0) {
				remove(r,k);
			}
		}
	}

	@Override
	public NodeData successor(BSTNode r, String k) {
		/*
		 * returns the NodeData objects stored in the successor of the node storing key attribute key in the tree with root r; \
		 * returns null if the successor does not exist.
		 */
	    BSTNode p = getNode(r, k); 
	    if(p.getRightChild()!=null) {
	    	System.out.println("If you are a B*");
	    	NodeData s = smallest(p.getRightChild());
	        return smallest(p.getRightChild());
	    }
	    else {
	    	System.out.println("Else you are a B*");
	    	BSTNode pp = p.getParent();
	        while((p!=null) && (p==pp.getRightChild())){
	            p = pp;
	            pp = pp.getParent();
	        }
	        return pp.getData();
	    }
	}
	
	@Override
	public NodeData predecessor(BSTNode r, String k) {
		/*
		 * returns the NodeData objects stored in the predecessor of the node storing key attribute key in the tree with root r; 
		 * returns null if the predecessor does not exist.
		 */
		BSTNode p = getNode(r,k);
		if(p.getLeftChild() != null) {
			System.out.println("If you are a B*");
			return largest(p.getLeftChild());
		}
		else {
			System.out.println("Else you are a B*");
			BSTNode pa = p.getParent();
			while((p != null) && (p == pa.getLeftChild())) {
				p = pa;
				pa = pa.getParent();
				System.out.println("PRE");
			}
			return pa.getData();
		}
	}

	@Override
	public NodeData smallest(BSTNode r) {
		/*
		 * Returns the NodeData object storing the smallest key in the tree with root r; returns null if the tree is empty.
		 */
		BSTNode p = smallestNode(r);
		if(p.isLeaf()) {
			return p.getParent().getData();
		}
		return p.getData();
	}

	@Override
	public NodeData largest(BSTNode r) {
		/*
		 * Returns the NodeData object storing the largest key in the tree with root r; returns null if the tree is empty.
		 */
		BSTNode p = largestNode(r);
		if(p.isLeaf()) {
			return p.getParent().getData();
		}
		return p.getData();
	}

	private BSTNode getNode(BSTNode r, String k) {
		if(r == null) {
			return null;
		}
		else {
			String k2 = r.getData().getName();
			if(k.compareTo(k2) < 0) {
				return getNode(r.getLeftChild(),k);
			}
			else if(k.compareTo(k2) > 0) {
				return getNode(r.getRightChild(), k);
			}
			else {
				return r;
			}
		}
	}
	
	private BSTNode smallestNode(BSTNode r) {
		while(r.getLeftChild() != null) {
			r = r.getLeftChild();
		}
		return r.getParent();
	}
	
	private BSTNode largestNode(BSTNode r) {
		while(r.getRightChild() != null) {
			r = r.getRightChild();
		}
		return r.getParent();
	}
}
