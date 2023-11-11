package testPackage;

public class SingleTon {

	
	private static SingleTon SingleTonObject=null;
	public String str;
	
	private SingleTon() {
		
		str= "singleton class/pattern practice";
		
	}
	
	public static SingleTon getInstance() {
		
		if(SingleTonObject == null)
			
			SingleTonObject = new SingleTon();
		return SingleTonObject;
		
		
	}
	
	public static void main(String[] args) {
		
		
		SingleTon x = SingleTon.getInstance();
		SingleTon y = SingleTon.getInstance();
		
		x.str =(x.str).toUpperCase();
		System.out.println(x.str);
		System.out.println(y.str);

	}

}
