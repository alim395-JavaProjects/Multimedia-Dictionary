import java.util.ArrayList;

public class NodeData {
	
	private String name; // This is the key attribute for the data stored in a node.
	private ArrayList<MultimediaItem> media; // List of Multimedia objects associated with the key attribute of this node.
	
	public NodeData(String newName) {
		/*
		 * Creates a new NodeData object with the given key attribute and an empty media list.
		 */
		this.name = newName;
		this.media = new ArrayList<MultimediaItem>();
	}
	
	public void add(MultimediaItem newItem) {
		/*
		 * Adds newItem to the media list of this object
		 */
		this.media.add(newItem);
	}
	
	public String getName() {
		/*
		 * Returns the name attribute of this object.
		 */
		return this.name;
	}
	
	public ArrayList<MultimediaItem> getMedia(){
		/*
		 * Returns the media list stored in this object.
		 */
		return this.media;
	}
	
}
