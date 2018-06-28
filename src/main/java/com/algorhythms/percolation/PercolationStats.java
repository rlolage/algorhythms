package com.algorhythms.percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * This is the statistics collector for the percolation problem.
 * It will run T trial experiments on nxn grid and collect the stats.
 *  
 * @author Raj Lolage
 */
public class PercolationStats {
	
	private double[] percolationThresholds;
	private int trials;
	
	public PercolationStats(int n, int trials) {
		if(n <= 0 || trials <= 0) {
			throw new IllegalArgumentException("n or trials should be > 0");
		}
		
		this.percolationThresholds = new double[trials];
		this.trials = trials;
		
		for(int i= 0; i < trials; i++) {
			Percolation p = new Percolation(n);
			boolean systemPercolates = false;
			while(true) {
				int row = StdRandom.uniform(n) + 1;
				int column = StdRandom.uniform(n) + 1;
				
				if(!p.isOpen(row, column)) {
					p.open(row, column);
					//System.out.println("Opened row = " + row + ", column = " + column);
					systemPercolates = p.percolates();
					//System.out.println("Trail#" + i + ", Sytem percolates = " + systemPercolates);
					
					if(systemPercolates) {
						break;
					}
				}
			}
			
			if(systemPercolates) {
				this.percolationThresholds[i] = (double)p.numberOfOpenSites()/(double)(n*n);
				//System.out.println("Trail#" + i + ", System Percolates after " + p.numberOfOpenSites() + " open sites");
				//System.out.println("Trail#" + i + ", Percolation probablility = " + this.percolationThresholds[i]);
			} else {
				//System.out.println("System does not percolates after " + p.numberOfOpenSites() + " open sites");
			}
		}
	}
	   
	public double mean() {
		return StdStats.mean(this.percolationThresholds);
	}

	public double stddev() {
		return StdStats.stddev(this.percolationThresholds);
	}
	
	public double confidenceLo() {
		double mean = mean();
		double stddev = stddev();
		return (mean - ((1.96) * stddev)/Math.sqrt(this.trials));
	}
	
	public double confidenceHi() {
		double mean = mean();
		double stddev = stddev();
		return (mean + ((1.96) * stddev)/Math.sqrt(this.trials));		
	}

	public static void main(String[] args) {
		
		if(args.length < 2 ) {
			throw new IllegalArgumentException("Please provide two args - n and trials");
		}
		
		int n = Integer.parseInt(args[0]);
		int trials = Integer.parseInt(args[1]);
		
		System.out.println("Please wait while we compute the stats......");
		PercolationStats percolationStats = new PercolationStats(n, trials);
		
		System.out.println("mean                    = " + percolationStats.mean());
		System.out.println("stddev                  = " + percolationStats.stddev());
		System.out.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
	}
}
