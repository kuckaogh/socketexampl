package wrimsv2.solver.Gurobi;

public class LpResult {
	
	public int status = 0;
	
	//public int numberOfData;
	public String[] varNames = null;
	public double[] varValues = null;

	public LpResult(){
		
		this.status = 0;
		this.varNames = null;
		this.varValues = null;
		
	}
	
//	public LpResult(String[] varNameArray, double[] varValueArray){
//		
//		varNames = varNameArray;
//		varValues = varValueArray;
//		
//	}
	
}
