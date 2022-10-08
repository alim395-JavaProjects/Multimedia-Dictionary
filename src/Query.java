import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Query {
	public static void main(String[] args) {
		
		//Read command
		StringReader keyboard = new StringReader();
		String line = keyboard.read("Enter a command: ");
		
		//Initialize dictionary
		BSTOrderedDictionary dictionary = new BSTOrderedDictionary();
		BSTNode root = dictionary.getRoot();
		
		//Handle Input and create BSTNode
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(args[0]));
			String word = in.readLine();
		    String definition;
		    while (word != null) {
				try {
					definition = in.readLine();
					//Finding Type of file
					int typeVariable = 1;
					if(definition.contains(".txt")) {
						typeVariable = 1;
					}
					else if(definition.contains(".wav")) {
						typeVariable = 2;
					}
					else if(definition.contains(".gif")) {
						typeVariable = 3;
					}
					else if(definition.contains(".html")) {
						typeVariable = 4;
					}
					dictionary.put(root,word.toLowerCase(),definition,typeVariable); 
					word = in.readLine();
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
				}
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(!line.equals("end")){
			String[] lineArgs = line.split(" ");
			switch(lineArgs[0]) {
			    case("get"):
			    	/*
			    	 * If the BSTOrderedDictionary has a node p with key attribute k, then each one of the
			    	 * MultimediaItem objects in the ArrayList stored in that node will be processed in the following manner.
			    	 */
			    	try {
			    		ArrayList<MultimediaItem> a = dictionary.get(root, lineArgs[1]);
			    		if(a == null) {
			    			System.out.printf("Key %s is not in the ordered dictionary\n", lineArgs[1]);
			    			System.out.printf("Preceding word: %s", dictionary.predecessor(root, lineArgs[1]));
			    			System.out.printf("Following word: %s", dictionary.successor(root, lineArgs[1]));
			    		}
			    		else {
			    			for(int n = 0; n < a.size(); n++) {
			    				MultimediaItem item = a.get(n);
			    				if(item.getType() == 1) {
			    					/*
			    					 *  Print the text file.
			    					 */
			    					System.out.println(item.getContent());
			    				}
			    				else if(item.getType() == 2) {
			    					/*
			    					 *  Initialize a soundPlayer object, and play the audio file.
			    					 */
			    					SoundPlayer soundPlayer = new SoundPlayer();
									soundPlayer.play(item.getContent());
			    				}
			    				else if(item.getType() == 3) {
			    					/*
			    					 *  Initialize a pictureViewer object, and display the image file.
			    					 */
			    					PictureViewer pictureViewer = new PictureViewer();
									pictureViewer.show(item.getContent());
			    				}
			    				else if(item.getType() == 2) {
			    					/*
			    					 *  Initialize a showHTML object, and show the website file.
			    					 */
			    					ShowHTML showHTML = new ShowHTML();
									showHTML.show(item.getContent());
			    				}
			    			}
			    		}
			    	}catch(ArrayIndexOutOfBoundsException exception) {
					    System.out.println("Invalid number of arguments");
					} catch (MultimediaException e) {
						System.out.println("The named files cannot be found or cannot be processed");
					}
			    	break;
			    case("remove"):
			    	//Removes from the ordered dictionary the node with key attribute k
					try {
						dictionary.remove(root, lineArgs[1]);
					} catch (DictionaryException e) {
						System.out.printf("No record in the ordered dictionary has key %s.\n",lineArgs[1]);
					} catch(ArrayIndexOutOfBoundsException exception) {
					    System.out.println("Invalid number of arguments");
					}
			    	break;
			    case("delete"):
			    	//If a node p with key attribute k is in the tree, this command will delete from the ArrayList of p all the MultimediaItem objects with type t.
				try {
					dictionary.remove(root, lineArgs[1], Integer.valueOf(lineArgs[2]));
				} catch (NumberFormatException e) {
					System.out.println("Invalid type argument");
				} catch (DictionaryException e) {
					System.out.printf("No record in the ordered dictionary has key %s.\n",lineArgs[1]);
				} catch(ArrayIndexOutOfBoundsException exception) {
				    System.out.println("Invalid number of arguments");
				}
			    	break;
			    case("add"):
			    	/*
			    	 * If no node in the tree stores key k then a new node p with that key is added to the tree;
			    	 * otherwise your program finds the node p storing key k. 
			    	 * A MultimediaItem storing content c and type t is added to the ArrayList of p.
			    	 */
			    	try {
			    		dictionary.put(root, lineArgs[1], lineArgs[2], Integer.valueOf(lineArgs[3]));
			    	}catch(ArrayIndexOutOfBoundsException exception) {
					    System.out.println("Invalid number of arguments");
					}
			    	break;
			    case("next"):
			    	/*
		    		 * This command must find the node p with key attribute k, 
		    		 * if this node does not exist, the leaf node p where the key attribute k should have been stored is returned instead.
		    		 * Then your program must find the d successor nodes of p (if they exist) 
		    		 * and print the key attribute of p (if p is an internal node) and the key attributes of the d successors (if they exist) in lexicographic increasing order.
		    		 */
			    	try {
			    		ArrayList<MultimediaItem> p = dictionary.get(root,lineArgs[1]);
			    		int num_succ = Integer.valueOf(lineArgs[2]);
			    		if(p == null && dictionary.successor(root, lineArgs[1]) == null) {
			    			System.out.println("There are no keys larger than or equal to set");
			    		}
			    		else {
			    			String k = lineArgs[1];
			    			if(p == null) {
			    				for(int i = 0; i < num_succ; i++){
			    					NodeData s = dictionary.successor(root, k);
			    					if(s != null) {
			    						System.out.println(s.getName());
			    					}
			    					k = s.getName();
			    				}
			    			}
			    			else {
			    				NodeData s = dictionary.successor(root, k);
			    				k = s.getName();
			    				s = dictionary.predecessor(root, k);
			    				for(int i = 0; i < num_succ; i++){
			    					if(s != null) {
			    						System.out.println(s.getName());
			    					}
			    					s = dictionary.successor(root, k);
			    					k = s.getName();
			    				}
			    			}
			    		}
			    	}catch(ArrayIndexOutOfBoundsException exception) {
					    System.out.println("Invalid number of arguments");
					}
			    	break;
			    case("prev"):
			    	/*
		    		 * This command must find the node p with key attribute k, 
		    		 * if this node does not exist, the leaf node p where the key attribute k should have been stored is returned instead.
		    		 * Then your program must find the d predecessor nodes of p (if they exist) 
		    		 * and print the key attribute of p (if p is an internal node) and the key attributes of the d predecessors (if they exist) in lexicographic decreasing order.
		    		 */
			    	try {
			    		ArrayList<MultimediaItem> p = dictionary.get(dictionary.getRoot(),lineArgs[1]);
			    		int num_pre = Integer.valueOf(lineArgs[2]);
			    		if(p == null && dictionary.predecessor(root, lineArgs[1]) == null) {
			    			System.out.println("There are no keys larger than or equal to set");
			    		}
			    		else {
			    			String k = lineArgs[1];
			    			if(p == null) {
			    				for(int i = 0; i < num_pre; i++){
			    					NodeData s = dictionary.predecessor(root, k);
			    					if(s != null) {
			    						System.out.println(s.getName());
			    					}
			    					k = s.getName();
			    				}
			    			}
			    			else {
			    				NodeData s = dictionary.successor(root, k);
			    				k = s.getName();
			    				s = dictionary.predecessor(root, k);
			    				for(int i = 0; i < num_pre; i++){
			    					if(s != null) {
			    						System.out.println(s.getName());
			    					}
			    					s = dictionary.predecessor(root, k);
			    					k = s.getName();
			    				}
			    			}
			    		}
			    	}catch(ArrayIndexOutOfBoundsException exception) {
					    System.out.println("Invalid number of arguments");
					}
			    	break;
			    case("first"):
			    	/*
			    	 * Print the smallest key attribute in the ordered dictionary.
			    	 */
			    	if(dictionary.getNumInternalNodes() == 0) {
			    		System.out.println("The ordered dictionary is empty.");
			    	}
			    	else {
			    		NodeData s = dictionary.smallest(root);
			    		System.out.println(s.getName());
			    	}
			    	break;
			    case("last"):
			    	/*
			    	 * Print the largest key attribute in the ordered dictionary.
			    	 */
			    	if(dictionary.getNumInternalNodes() == 0) {
			    		System.out.println("The ordered dictionary is empty.");
			    	}
			    	else {
			    		NodeData s2 = dictionary.largest(root);
				    	System.out.println(s2.getName());
			    	}
			    	break;
				case("size"):
					/*
					 * Prints the number of internal nodes in the binary search tree implementing the ordered dictionary.
					 */
					int size = dictionary.getNumInternalNodes();
					System.out.printf("There are %d keys in the ordered dictionary.\n", size);
					break;
				default:
					System.out.println("Invalid command");
			}
			line = keyboard.read("Enter next command: ");
		}
	}
}
