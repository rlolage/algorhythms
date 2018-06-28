package com.algorhythms.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * This is the Percolation API specification that models the percolation problem
 * using a {@link WeightedQuickUnionUF} data structure.
 * 
 * @author Raj Lolage
 */
public class Percolation {
	
	private boolean grid[][];
	private int gridNodes[][];
	private int numberOfOpenSites;
	private WeightedQuickUnionUF weightedQuickUnionUF;
	private int size;
	
	public Percolation(int n) {
		
		if(n <= 0) {
			throw new IllegalArgumentException("n should be > 0");
		}
		
		//The first 2D array maintains the state of the site.
		this.grid = new boolean[n][n];
		
		//The second 2D array maintains the actual sites we want to connect together.
		this.gridNodes = new int[n][n];
		
		//Init the size
		this.size = n;
		
		//Init the number of open sites
		this.numberOfOpenSites = 0;

		// Create a WQUF data structure to hold the relationships
		this.weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2); 
		// + 2 because 1 for virtual top and 1 for virtual bottom site
		
		int count = 1;
		
		// Initialize the grid state with all blocked sites.
		// Initialize the grid nodes. 
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				this.grid[i][j] = false;
				this.gridNodes[i][j] = count;
				count++;
			}
		}
	}
	
	private void connectAdjacentSites(int row, int col) {
		// left adjacent site
		if(col > 0 && isOpen(row+1, col)) {
			this.weightedQuickUnionUF.union(this.gridNodes[row][col], this.gridNodes[row][col-1]);
		}
		
		// right adjacent site
		if(col < this.size - 1 && isOpen(row+1, col+2)) {
			this.weightedQuickUnionUF.union(this.gridNodes[row][col], this.gridNodes[row][col+1]);
		}
		
		//top adjacent site
		if(row > 0 && isOpen(row, col+1)) {
			this.weightedQuickUnionUF.union(this.gridNodes[row][col], this.gridNodes[row-1][col]);
		}
		
		// bottom adjacent site
		if(row < this.size - 1 && isOpen(row+2, col+1)) {
			this.weightedQuickUnionUF.union(this.gridNodes[row][col], this.gridNodes[row+1][col]);
		}
	}
	
	private void validateSite(int row, int col) {
		if(row <= 0 || col <= 0) {
			throw new IllegalArgumentException("Row or Column index should start with 1");
		}
	}
	
	public void open(int row, int col) {
		
		validateSite(row,col);
		
		//Open site
		this.grid[row-1][col-1] = true;
		
		//This is a top row, connect to top virtual site
		if((row-1) == 0) {
			this.weightedQuickUnionUF.union(this.gridNodes[row-1][col-1], 0);
		}
		
		//This is a bottom row, connect to bottom virtual site
		if((row-1) == this.size - 1) {
			this.weightedQuickUnionUF.union(this.gridNodes[row-1][col-1], this.size * this.size);
		}
		
		//connect adjacent sites
		connectAdjacentSites(row-1, col-1);
		
		//Increment numberOfOpenSites
		this.numberOfOpenSites++;
	}
	
	public boolean isOpen(int row, int col) {
		validateSite(row,col);
		return this.grid[row-1][col-1];
	}
	
	public boolean isFull(int row, int col) {
		validateSite(row,col);
		//This is a full site if the grid node is connected to 0 (which is the virtual top site)
		return this.weightedQuickUnionUF.connected(this.gridNodes[row-1][col-1], 0);
	}
	
	public int numberOfOpenSites() {
		return this.numberOfOpenSites;
	}
	
	public boolean percolates() {
		return this.weightedQuickUnionUF.connected(0, this.size * this.size);
	}
}
