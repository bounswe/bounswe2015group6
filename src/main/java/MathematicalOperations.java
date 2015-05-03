public class MathematicalOperations {
    
    // performs addition
    public int binaryPlus(int x, int  y)
    {
        return x + y;
    }
    
    // performs subtraction (x minus y)
    public int binaryMinus(int x, int y) {
    	return x - y;
    }
    
    // performs multiplication
    public int times(int x, int y){
    	return x * y;
    }
    
    // performs multiplication
    public int divide(int x, int y){
        return x / y;
    }

    // raises x to the power of y
    public double power(double x, double y)
    {
        return Math.pow(x, y);
    }
    
    // takes the absolute value of x
    public int abs(int x){
    	if(x<0)
    		return -1*x;
    	else
    		return x;
    }
    //finds remainder
    public int remainder(int x, int y){
    	return x%y;
    }
}
