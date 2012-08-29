package wrimsv2.solver.Gurobi;
/* Copyright 2012, Gurobi Optimization, Inc. */

/* This example reads an LP model from a file and solves it.
   If the model is infeasible or unbounded, the example turns off
   presolve and solves the model again. If the model is infeasible,
   the example computes an Irreducible Infeasible Subsystem (IIS),
   and writes it to a file */


import gurobi.*;

public class LpTest {
  public static void main(String[] args) {

    if (args.length < 1) {
      System.out.println("Usage: java Lp filename");
      System.exit(1);
    }
    
    solveLP(args[0]);
    
  }

public static LpResult solveLP(String LpFilePath) {

	LpResult result = new LpResult();
	
    try {
      GRBEnv env = new GRBEnv();
      GRBModel model = new GRBModel(env, LpFilePath);

      model.optimize();

      int optimstatus = model.get(GRB.IntAttr.Status);
      result.status = model.get(GRB.IntAttr.Status);
      
      if (optimstatus == GRB.Status.INF_OR_UNBD) {
        model.getEnv().set(GRB.IntParam.Presolve, 0);
        model.optimize();
        optimstatus = model.get(GRB.IntAttr.Status);
        result.status = model.get(GRB.IntAttr.Status);
      }

      if (optimstatus == GRB.Status.OPTIMAL) {
        double objval = model.get(GRB.DoubleAttr.ObjVal);
        System.out.println("Optimal objective: " + objval);
        
        
        GRBVar[] allVars = model.getVars();
      //  String[] allVarNames = model.get(GRB.StringAttr.VarName,allVars);
      //  double[] allValues = model.get(GRB.DoubleAttr.X,allVars);

        result.varNames = model.get(GRB.StringAttr.VarName,allVars);
        result.varValues = model.get(GRB.DoubleAttr.X,allVars);
        
        
//        for (int i=0;i<allVars.length;i++){
//        	
//        	//double val = s.get(GRB.DoubleAttr.X);
//        	String varName = allVars[i].get(GRB.StringAttr.VarName);
//        	double val = allVars[i].get(GRB.DoubleAttr.X);
//        	//System.out.println(varName+":"+allVars[i].get(GRB.DoubleAttr.X)+":"+allValues[i]);
//        	System.out.println(result.varNames[i]+":"+result.varValues[i]);   	
//        	
//        }
        
        
        
        
      } else if (optimstatus == GRB.Status.INFEASIBLE) {
        System.out.println("Model is infeasible");

        // Compute and write out IIS
        model.computeIIS();
        model.write("D:\\cvwrsm\\trunk\\socketExample\\socketJava\\model.ilp");
      } else if (optimstatus == GRB.Status.UNBOUNDED) {
        System.out.println("Model is unbounded");
      } else {
        System.out.println("Optimization was stopped with status = "
                           + optimstatus);
      }

      // Dispose of model and environment
      model.dispose();
      env.dispose();

    } catch (GRBException e) {
      System.out.println("Error code: " + e.getErrorCode() + ". " +
          e.getMessage());
    }
	return result;
  }
}
