
public class StringCheckerFunctions {
	
public static void main(String[] args) {
	// do nothing	
	
	}
/** removes "." for username portion of email **/
public String removeDotInEmailName(String temp){

	int atPos = temp.lastIndexOf("@");
	if (temp.substring(0,atPos).contains(".")){
	  return temp.substring(0,atPos).replace(".", "") + temp.substring(atPos+1);
	  }
	 else{
	  return temp;
	 }
}

/** removes "+" and all after part of user name **/
public String removeAllAfterPlusInEmailNAme(String temp){
	int atPos = temp.lastIndexOf("@");	
	if (temp.substring(0,atPos).contains("+")){
	    int plusPos =  temp.indexOf("+");
	    return temp.substring(0,plusPos) + temp.substring(atPos) ;	
	 }
	 else {
	  return temp;
	 }
}

}
