import java.util.ArrayList;


public class Maze {
	
	public static void main(String[] args) {
		Maze maze = new Maze(10,30);
		System.out.println(maze);
	}
	
	Cell maze[][];
	
	public Maze() {
		maze = new Cell[0][0];
	}
	
	public Maze( int length , int height ) {
		maze = new Cell[height][length];
		for ( int i = 0 ; i < height ; ++i ) {
			for ( int k = 0 ; k < length ; ++k ) {
				maze[i][k] = new Cell(i,k);
				maze[i][k].setSet(i*length+k);
			}
		}
		generate();
	}
	
	
	private void generate() {
		int di[] = { -1 , 1 , 0 , 0 };
		int dk[] = {  0 , 0 , -1 , 1 };
		ArrayList<Integer> queue = new ArrayList<Integer>();
		for ( int i = 0 ; i < maze.length*maze[0].length ; ++i ) {
			queue.add(i);
		}
		for ( int q = 0 ; q < maze.length*maze[0].length-1 ; ++q ) {
			int pos = (int) (Math.random()*queue.size());
			int set = queue.get(pos);
			queue.remove(pos);
			ArrayList<Cell> stack = new ArrayList<Cell>();
			for ( int i = 0 ; i < maze.length ; ++i ) {
				for ( int k = 0 ; k < maze[0].length ; ++k ) {
					if ( maze[i][k].getSet() != set && check(maze[i][k],set,di,dk) ) {
						stack.add(maze[i][k]);
					}
				}
			}
			pos = (int) (Math.random()*stack.size());
			Cell cell = stack.get(pos);
			for ( int w = 0 ; w < di.length ; ++w ) {
				int ti = di[w]+cell.i;
				int tk = dk[w]+cell.k;
				try {
					if ( maze[ti][tk].getSet() == set ) {
						merge(cell,maze[ti][tk]);
						cell.connect(w, maze[ti][tk]);
						break;
					}
				}
				catch ( Exception e ) {}
			}
			
		}
	}
	
	//merges two cels together
	private void merge(Cell cell, Cell cell2) {
		int set = cell2.getSet();
		for ( int i = 0 ; i < maze.length ; ++i ) {
			for ( int k = 0 ; k < maze[0].length ; ++k ) {
				if ( maze[i][k].getSet() == set  ) {
					maze[i][k].setSet(cell.getSet());
				}
			}
		}
	}

	//checks to see if the new location is valid
	private boolean check ( Cell cell , int set, int[] di, int[] dk ) {
		for ( int w = 0 ; w < di.length ; ++w ) {
			int ti = di[w]+cell.i;
			int tk = dk[w]+cell.k;
			try {
				if ( maze[ti][tk].getSet() == set ) {
					return true;
				}
			}
			catch ( Exception e ) {}
		}
		return false;
	}
	
	
	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		for ( int k = 0 ; k < maze[0].length ; ++k ) {
			res.append("WW");
		}
		res.append("W");
		res.append("\n");
		for ( int i = 0 ; i < maze.length ; ++i ) {
			res.append("W");
			for ( int k = 0 ; k < maze[0].length ; ++k ) {
				if ( i == 0 && k == 0  )
					res.append(maze[i][k].toString(0).replace('R', 'S')); 
				else if ( i == maze.length-1 && k == maze[0].length-1  )
					res.append("EW");
				else
					res.append(maze[i][k].toString(0));
			}
			res.append("\n");
			res.append("W");
			for ( int k = 0 ; k < maze[0].length ; ++k ) {
				res.append(maze[i][k].toString(1));
			}
			res.append("\n");
		}
		return res.toString();
	}

}