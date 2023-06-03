/******************************************************************/
/* Main.java - this is the entry point for the Dijkstra Algorithm */
/******************************************************************/


/* declare name of new package for this class */
package dijkstra ;


/* make this class visible to itself */
import dijkstra.* ;


/* make the basic functions of Java visible to this class */
import java.lang.* ;
import java.util.ArrayList ;
import java.util.Comparator ;


/* this is the main entry point for the program, extends top Object class */
public class Main extends Object
{
	
	
	
	/***********************************************************************/
	
	/* Dijsktra algorithm fields, such as unvisited_nodes list */
	
	ArrayList<Node> unvisited_nodes = new ArrayList<Node> () ;
	ArrayList<Node> visited_nodes = new ArrayList<Node> () ;
	ArrayList<Node> reconstructed_path = new ArrayList<Node> () ;

	int num_nodes = 7 ;
	
	
	
	
	
	/***********************************************************************/

	/////////////////////////////////////////////////////////////////////////
	
	/* utility function to return the heap address of a node based on its self_id */
	Node find_node_in_arraylist(ArrayList<Node> arraylist, int self_id)
	{
		for ( Node node : arraylist )
		{
			if ( node.get_self_id() == self_id )
			{
				return node ;
			}
		}
		
		System.out.format( "Main.java:find_node_in_arraylist:%nCould not find node!!!!!%n%n" ) ;
		return null ;
	}
	
	
	/* utility function to initialize nodes */
	void initialize_nodes(int num_nodes)
	{
		/* create num_nodes node objects, name them, set their scores to -1, and add them to the unvisited_nodes ArrayList */
		Node node ;
		for ( int self_id = 0 ; self_id < num_nodes ; self_id++ )
		{
			node = new Node () ;
			node.set_self_id( self_id ) ;
			node.set_score( -1 ) ;
			
			this.unvisited_nodes.add( node ) ;
		}
	}


	/* utility function to add neighbors to nodes */
	void add_neighbors()
	{
		int[][][] neighbors = new int[][][]
		{
			//    1   3   5
			// 0
			//    2   4   6
			
			{ {1,1} , {2,1} } , // node0 -> node1, node2
			{ {0,1} , {3,3} } , // node1 -> node0, node3
			{ {0,1} , {4,1} } , // node2 -> node0, node4
			{ {1,3} , {4,1} , {5,1} } , // node3 -> node1, node4, node5
			{ {2,1} , {3,1} , {6,1} } , // node4 -> node2, node3, node6
			{ {3,1} , {6,1} } , // node5 -> node3, node6
			{ {4,1} , {5,1} }   // node6 -> node4, node5
		};
		
		
		for ( int node_id = 0 ; node_id < num_nodes ; node_id++ )
		{
			Node node_found = this.find_node_in_arraylist( this.unvisited_nodes , node_id ) ;
			node_found.add_neighbors( neighbors[node_id] ) ;
		}
		
	}


	/* utility function to find the first node in an arraylist with the lowest score that's not -1 */
	Node find_node_with_lowest_score(ArrayList<Node> arraylist)
	{
		// this method does NOT need to sort() the arraylist first, it looks at every node's score 
		// and records the lowest score that is not -1
		
		Node lowest_score_node = arraylist.get(0) ;
		int lowest_score = lowest_score_node.get_score() ;
		
		for ( Node node : arraylist )
		{
			if ( ( node.get_score() < lowest_score ) && ( node.get_score() != -1 ) )
			{
				lowest_score = lowest_score_node.get_score() ;
				lowest_score_node = node ;
			}
		}
		
		System.out.format( "Main.java:find_node_with_lowest_score:%nLowest score node is node_id %d%n%n" , lowest_score_node.get_self_id() ) ;
							
		return lowest_score_node ;
	}


	/* utility function to reconstruct the path from a target node back to the start */
	ArrayList<Node> find_parent_node(ArrayList<Node> existing_path, int current_node_id)
	{
		// retrieve current node object from visited_nodes arraylist via its self_id
		Node current_node = this.find_node_in_arraylist(this.visited_nodes, current_node_id) ;
		
		// if current node is the start node, just add its object to the existing_path
		if ( current_node.get_self_id() == 0 )
		{
			existing_path.add( current_node ) ;
			return existing_path ;
		}
		
		// otherwise, the current node is in the middle of the path,
		// so add the current node to the path, but then also call 
		// this method again - passing in the updated path and the
		// current node's parent_id
		else
		{
			existing_path.add(current_node) ;
			
			return find_parent_node( existing_path , current_node.get_parent_id() ) ;
		}
	}




	/////////////////////////////////////////////////////////////////////////
	
	/* utility function to print information of all nodes */
	void print_all_nodes()
	{
		/* put all nodes into one ArrayList object */
		ArrayList<Node> all_nodes = new ArrayList<Node> () ;
		all_nodes.addAll( visited_nodes ) ;
		all_nodes.addAll( unvisited_nodes ) ;
		
		/* sort all_nodes ascending based on their self_id fields */
		all_nodes.sort( Comparator.comparing( Node::get_self_id ) ) ;
		
		/* print info from all nodes */
		for ( Node node : all_nodes )
		{
			node.print_self_id() ;
			node.print_score() ;
			node.print_parent_id() ;
			node.print_neighbors() ;
		}
	}
	
	/* utility function to print nodes in unvisited arraylist */
	void print_unvisited_nodes()
	{
		System.out.format( "Unvisited Nodes ArrayList:%n" ) ;
		
		/* print info from all nodes */
		for ( Node node : this.unvisited_nodes )
		{
			node.print_self_id() ;
			node.print_score() ;
			node.print_parent_id() ;
			node.print_neighbors() ;
		}
	}
	
	/* utility function to print nodes in visited arraylist */
	void print_visited_nodes()
	{
		System.out.format( "Visited Nodes ArrayList:%n" ) ;

		/* print info from all nodes */
		for ( Node node : this.visited_nodes )
		{
			node.print_self_id() ;
			node.print_score() ;
			node.print_parent_id() ;
			node.print_neighbors() ;
		}
	}
	
	/* utility function to print nodes in arbitrary arraylist */
	void print_nodes_in_arraylist(ArrayList<Node> arraylist)
	{
		System.out.format( "Nodes in Arbitrary ArrayList:%n" ) ;

		/* print info from all nodes */
		for ( Node node : arraylist )
		{
			node.print_self_id() ;
		}
	}
	
	/* utility function to return isEmpty() status from visited arraylist */
	boolean visited_arraylist_is_empty()
	{
		return this.visited_nodes.isEmpty() ;
	}
	
	/* utility function to return isEmpty() status from unvisited arraylist */
	boolean unvisited_arraylist_is_empty()
	{
		return this.unvisited_nodes.isEmpty() ;
	}
	
	/* utility function to print isEmpty() status of both visisted and unvisited arraylists */
	void print_empty_status_of_arraylists()
	{
		/* print if visisted and unvisited arraylists are empty */
		System.out.format( "Visited ArrayList is Empty: %b%n" , this.visited_arraylist_is_empty() ) ;
		System.out.format( "Unvisited ArrayList is Empty: %b%n%n" , this.unvisited_arraylist_is_empty() ) ;
	}
	
	
	
	
	
	/////////////////////////////////////////////////////////////////////////
	
	/* program starts in this method*/
	static public void main (String[] args)
	{
		
		/***************************************************************************/
		/* initialize graph */
		System.out.format ( "Initializing graph...%n%n" ) ;
		
		/* build Main object */
		Main main_loop = new Main () ;
		
		/* initialize some nodes */
		main_loop.initialize_nodes( main_loop.num_nodes ) ;
		
		/* add neighbors to the nodes */
		main_loop.add_neighbors() ;
		
		/* print those nodes */
		main_loop.print_all_nodes() ;
		
		/* check empty status of visited and unvisited arraylists */
		main_loop.print_empty_status_of_arraylists() ;	

		System.out.format( "%n%n%n%n%n" ) ;
		
		
		

		/***************************************************************************/
		/* entering dijkstra algorithm */
		
		/* first, make node0 start node*/
		// find node0 in unvisited_nodes list, set node 0's score to 0
		Node start = main_loop.find_node_in_arraylist( main_loop.unvisited_nodes , 0 ) ;
		start.set_score( 0 ) ;

		
		/* now enter central while loop of dijkstra algo*/
		// find a 'parent' node in unvisited_nodes list with the lowest score that's not -1,
		// update its neighbors' scores and parent node,
		// move 'parent' node to visited_nodes list,
		// repeat until unvisited nodes arraylist is empty
		while ( ! main_loop.unvisited_arraylist_is_empty() )
		{
			/* find node with lowest score in unvisited_nodes list, this is new parent */
			Node parent_node = main_loop.find_node_with_lowest_score(main_loop.unvisited_nodes) ;
			
			System.out.format( "Now operating with parent node %d%n" , parent_node.get_self_id() ) ;
			
			/* update the parent's neighbors' score and parent_id */
			for ( int[] neighbor_tuple : parent_node.get_neighbor_id_and_dist_array() )
			{
				// look for the neighbor node_id in unvisited_nodes arraylist, could be int or NULL
				Node neighbor_node = main_loop.find_node_in_arraylist( main_loop.unvisited_nodes , neighbor_tuple[0] ) ;
				
				// if NULL then continue to next for loop interation
				if ( neighbor_node == null ) { continue ; }
				
				// only update neighbor's score if it is lower than the existing score
				int new_score = parent_node.get_score() + neighbor_tuple[1] ;
				
				if ( neighbor_node.get_score() == -1 )
				{
					neighbor_node.set_score( new_score ) ;
					neighbor_node.set_parent_id( parent_node.get_self_id() ) ;
				}
				
				if ( ( neighbor_node.get_score() >= 0 ) && ( new_score < neighbor_node.get_score() ) )
				{
					neighbor_node.set_score( new_score ) ;
					neighbor_node.set_parent_id( parent_node.get_self_id() ) ;
				}
				
				System.out.format( "-> Neighbor %d set to score %d%n" , neighbor_node.get_self_id() , neighbor_node.get_score() ) ;
				// main_loop.print_all_nodes() ;
			}
			
			/* move parent node to visited_nodes arraylist */
			main_loop.visited_nodes.add( parent_node ) ;
			main_loop.unvisited_nodes.remove( parent_node ) ;
			
			System.out.format( "%nTransferred parent node from unvisited list to visited list%n%n" ) ;
			main_loop.print_unvisited_nodes() ;
			main_loop.print_visited_nodes() ;
			
			System.out.format( "Check if lists are empty%n" ) ;
			/* check empty status of visited and unvisited arraylists */
			main_loop.print_empty_status_of_arraylists() ;

			
			System.out.format( "%n%n%n%n%n" ) ;
			// Exit after first loop to test
			//break ;
		}
		
		/* while loop is done, nodes should now hold shortest path information */
		System.out.format( "Dijkstra while loop is complete, nodes should hold shortest path information%n%n%n%n" ) ;
		
		/* print the node info from both unvisited and visited array lists */
		main_loop.print_unvisited_nodes() ;
		main_loop.print_visited_nodes() ;
		
		
		
		
		/***********************************************************************/
		/* get the shortest path back to the starting node from an arbitrary node */
		System.out.format( "%nReconstructing path:%n" ) ;		
		
		// call recursive method
		main_loop.reconstructed_path = main_loop.find_parent_node( main_loop.reconstructed_path , 5 ) ;
		
		main_loop.print_nodes_in_arraylist( main_loop.reconstructed_path ) ;
		
	}

}