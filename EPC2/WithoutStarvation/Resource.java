public class Resource {
	
	private  int n;   
	
	public Resource(){ 	
		n = 0;
	} 
		
	public void incr(){
        try{
            n+= 1;
        }catch(ArithmeticException e) {
            n = 0;
            n++;
        }   
	} 	
	
	public int value() { 
        return n; 
    }
}
