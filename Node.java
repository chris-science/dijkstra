/***************************************************/
/* Node.java - this is the code for the Node class */
/***************************************************/


/* declare name of new package for this class */
package dijkstra ;


/* make this class visible to itself */
import dijkstra.* ;


/* make the basic functions of Java visible to this class */
import java.lang.* ;
import java.util.ArrayList ;


/* this is the main entry point for the program, extends top Object class */
class Node extends Object
{
	
	/*****************************************************/
	
	/* create fields for a node's information */
	int self_id ;
	int score ;		// -1 used for infinite
	int parent_id ;
	
	// the int[] must be 2 entries, first is a node_id, and second is distance to that node
	ArrayList<int[]> neighbor_id_and_dist = new ArrayList<int[]> ();
	
	
	
	/*****************************************************/

	////////////////////////////////////////////////////
	
	/* setter: self_id */
	void set_self_id(int self_id)
	{
		this.self_id = self_id ;
	}
	
	
	void set_score(int score)
	{
		this.score = score ;
	}


	void set_parent_id(int parent_id)
	{
		this.parent_id = parent_id;
	}
	
	
	/* setter: neighbor_id_and_dist */
	void add_neighbor(int[] neighbor)
	{
		this.neighbor_id_and_dist.add(neighbor) ;
	}
	
	
	/* setter: set multiple neighbors */
	void add_neighbors(int[][] neighbors)
	{
		for ( int[] neighbor : neighbors )
		{
			this.add_neighbor( neighbor ) ;
		}
	}



	////////////////////////////////////////////////////
	
	/* getter: self_id */
	int get_self_id()
	{
		return this.self_id ;
	}
	
	
	/* getter: score */
	int get_score()
	{
		return this.score ;
	}
	
	
	/* getter: parent_id */
	int get_parent_id()
	{
		return this.parent_id ;
	}

	
	ArrayList<int[]> get_neighbor_id_and_dist_array()
	{
		return this.neighbor_id_and_dist ;
	}


	

	////////////////////////////////////////////////////

	/* printer: self_id */
	void print_self_id()
	{
		System.out.format( "Node %d%n" , self_id ) ;
	}


	/* printer: self_id */
	void print_score()
	{
		System.out.format( "Score %d%n" , score ) ;
	}
	
	
	/* printer: parent_id */
	void print_parent_id()
	{
		System.out.format( "Parent id %d%n" , parent_id ) ;
	}
	
	
	/* printer: print all neighbor_id_and_dist entries for this node */
	void print_neighbors()
	{
		for ( int[] entry : neighbor_id_and_dist )
		{
			System.out.format( "Neighbor %d is distance %d%n", entry[0], entry[1] ) ;
		}
		
		System.out.format( "%n" ) ;
	}
	
	
}