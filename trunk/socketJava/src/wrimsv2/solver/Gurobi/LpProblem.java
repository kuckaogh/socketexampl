package wrimsv2.solver.Gurobi;

public class LpProblem {
	
	public String lpFilePath = null;

	public LpProblem(){
		
		this.lpFilePath = null;
		
	}
	
	public LpProblem(String filePath){
		
		this.lpFilePath = filePath;
		
	}
	
}
