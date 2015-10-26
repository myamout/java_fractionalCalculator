import java.util.Scanner;

public class fracCalc {
	
	public static boolean error = false;
	public static int num1;
	public static int num2;
	public static int den1;
	public static int den2;
	public static int whole1;
	public static int whole2;
	public static int numFinal;
	public static int denFinal;
	public static int wholeFinal;
	public static int nNum1;
	public static int nNum2;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		welcomeMessage();
		Scanner console = new Scanner(System.in);
		System.out.println("Enter an expression below: ");
		boolean quit = true;
		do {
			String expression = console.nextLine();
			if (expression.equalsIgnoreCase("quit")) {
				System.out.println("Thank you for using the calculator!");
				quit = false;
			}
			else if (expression.length() < 4) {
				System.out.println("Error!");
			}
			else {
				if (runChecks(expression)) {
					parseExpression(expression);
					resetGlobals();
				}
				else {
					System.out.println("Error!");
					resetGlobals();
				}
			}
			
		}while (quit);

	}
	
	public static void welcomeMessage() {
		System.out.println("Welcome to Matt's Fractional Calculator!"); //Welcomes the User.
		System.out.println("This calculator allows the user to input an experssion and solve fractions or whole numbers."); //tells the user the purpose
		System.out.println("Instructions: All inputed numbers must be an integer, anything other than an integer will result in an error.");
		System.out.println("              You must put one space between the fraction and the operator or an error will occur.");
		System.out.println("              The \"/\" symbol represents the fraction bar.");
		System.out.println("              To enter in a mixed number you must enter in \"_\" between the whole number and the fraction.");
		System.out.println("Examples of acceptable expressions: 3/4 + 2/5");
		System.out.println("                                    1_3/4 + 2/5");
		System.out.println("If the user enters in any non-integer value, the calculator will reset and allow the user to enter in their equation again.");
		System.out.println("If the user would like to check what the values of the faction are, type in \"detect\"");
		System.out.println("When detecting where your values are simply use the \"+\" sign in your equation.");
		System.out.println("Entering in very large number will crash the program... Sorry...");
		System.out.println("To quit at anytime type in \"quit\"");
		System.out.println("=============================================================================================================");
	}

	public static boolean letterCheck(String unChecked) {
		String [] test = unChecked.split(" ");
		boolean x = false;
		boolean y = false;
		for (int i = 0; i < test[0].length(); i++)
		{
			if (Character.isLetter(test[0].charAt(i)))
			{
				x = true;
			}

		}
		for (int i = 0; i < test[2].length(); i++)
		{
			if (Character.isLetter(test[2].charAt(i)))
			{
				y = true;
			}

		}

		if (x == true || y == true)
		{
			error = true;
		}
		return error;
	}

	public static boolean checkOperator(String unChecked) {
		
		boolean x = false;
		boolean y = false;
		String [] test = unChecked.split(" ");
		if (test[1].length() != 1)
		{
			x = true;
		}
		if (!(test[1].contains("+") || test[1].contains("-") || test[1].contains("*") || test[1].contains("/")))
		{
			y = true;
		}
		if (x == true || y == true)
		{
			error = true;
		}
		return error;
	}

	public static boolean countSlashAndUnderScore(String unChecked) {
		
		String [] test = unChecked.split(" ");
		int subCounter = 0;
		int subCounter1 = 0;
		int barCounter = 0;
		int barCounter1 = 0;
		boolean x = false;
		boolean y = false;
		boolean z = false;
		boolean e = false;
		for (int i = 0; i < test[0].length(); i++)
		{
			if (test[0].charAt(i) == '_')
			{
				subCounter++;
			}
			if (test[0].charAt(i) == '/')
			{
				barCounter++;
			}
		}
		for (int i = 0; i < test[2].length(); i++)
		{
			if (test[2].charAt(i) == '_')
			{
				subCounter1++;
			}
			if (test[2].charAt(i) == '/')
			{
				barCounter1++;
			}
		}
		if (subCounter > 1)
		{
			x = true;
		}
		if (barCounter > 1)
		{
			y = true;
		}
		if (subCounter1 > 1)
		{
			z = true;
		}
		if (barCounter1 > 1)
		{
			e = true;
		}
		if (x == true || y == true || z == true || e == true)
		{
			error = true;
		}
		return error;
	}
	
	public static boolean checkMiddleString(String unChecked) {
		
		String [] test = unChecked.split(" ");
		if (test[0].contains("_/") || test[0].contains("/_"))
		{
			error = true;
		}
		if (test[2].contains("_/") || test[2].contains("/_"))
		{
			error = true;
		}
		return error;
	}

	public static boolean checkFirstLastNumber(String unChecked) {
		
		String [] test = unChecked.split(" ");
		if (test[0].startsWith("/") || test[0].startsWith("_"))
		{
			error = true;
		}
		if (test[0].endsWith("/") || test[0].endsWith("_") || test[0].endsWith("-"))
		{
			error = true;
		}
		if (test[2].startsWith("/") || test[2].startsWith("_"))
		{
			error = true;
		}
		if (test[2].endsWith("/") || test[2].endsWith("_") || test[2].endsWith("-"))
		{
			error = true;
		}
		return error;
	}
	
	public static boolean checkSpacesAndDecimals(String unChecked) {
		
		int spaceCounter = 0;
		for (int i = 0; i < unChecked.length(); i++)
		{
			if (unChecked.charAt(i) == ' ')
			{
				spaceCounter++;
			}
		}
		if (spaceCounter != 2)
		{
			error = true;
		}
		if (unChecked.contains("."))
		{
			error = true;
		}
		return error;
	}
	
	public static boolean runChecks(String expression) {
		
		letterCheck(expression);
		checkOperator(expression);
		countSlashAndUnderScore(expression);
		checkMiddleString(expression);
		checkFirstLastNumber(expression);
		checkSpacesAndDecimals(expression);
		
		if (error == true) {
			return false;
		}
		
		else {
			return true;
		}
	}
	
	public static void resetGlobals()
	{
		num1 = 0;
		num2 = 0;
		den1 = 0;
		den2 = 0;
		whole1 = 0;
		whole2 = 0;
		numFinal = 0;
		denFinal = 0;
		wholeFinal = 0;
		nNum1 = 0;
		nNum2 = 0;
		error = false;
	}

	public static void parseExpression(String expression) {
		String [] ops = expression.split(" "); // splits string at every space
		if (ops[0].contains("_")) // mixed number
		{
			String [] ops0 = ops[0].split("_"); // splits the first number at the "_"
			String [] ops1 = ops0[1].split("/"); // then splits at "/"
			whole1 = Integer.parseInt(ops0[0]); // sets and parses numeric values
			num1 = Integer.parseInt(ops1[0]); 	   // ""    ""   ""    ""       ""
			den1 = Integer.parseInt(ops1[1]);	  //  ""    ""    ""    ""      ""
		}
		else if (!(ops[0].contains("_")) && ops[0].contains("/")) // normal fraction
		{
			String [] ops0 = ops[0].split("/"); // splits the first number at the "/"s
			//System.out.println(ops0[0]); first numerator
			//System.out.println(ops0[1]); second numerator
			whole1 = 0; // whole number is set to zero to keep the setup method working
			num1 = Integer.parseInt(ops0[0]); 
			den1 = Integer.parseInt(ops0[1]);
		}
		else if (!(ops[0].contains("_")) && !(ops[0].contains("/"))) // whole number
		{
			whole1 = 0;
			num1 = Integer.parseInt(ops[0]);
			den1 = 1;
		}
		// runs through the same split process for ops[2], or the second number


		if (ops[2].contains("_")) 
		{
			String [] ops3 = ops[2].split("_");
			String [] ops4 = ops3[1].split("/");
			//System.out.println(ops3[0]); second whole number
			//System.out.println(ops4[0]); second numerator
			//System.out.println(ops4[1]); second denominator
			whole2 = Integer.parseInt(ops3[0]);
			num2 = Integer.parseInt(ops4[0]);
			den2 = Integer.parseInt(ops4[1]);
		}
		else if (!(ops[2].contains("_")) && ops[2].contains("/"))
		{
			String [] ops3 = ops[2].split("/");
			whole2 = 0;
			num2 = Integer.parseInt(ops3[0]);
			den2 = Integer.parseInt(ops3[1]);
		}
		else if (!(ops[2].contains("_")) && !(ops[2].contains("/")))
		{

			whole2 = 0;
			num2 = Integer.parseInt(ops[2]);
			den2 = 1;
		}
		if (ops[1].equals("+")) // if the operator string equals "+" then the add method is called
		{
			add();
		}
		else if (ops[1].equals("-")) // if the operator string equals "-" then the subtract method is called
		{
			subtract();
		}
		else if (ops[1].contains("*")) // if the operator string equals "*" then the multiply method is called
		{
			multiply();
		}
		else if (ops[1].contains("/")) // if the operator string equals "/" then the divide method is called
		{
			divide();
		}
	}

	public static void add() {
		convert();
		if (den1 == den2)
		{
			numFinal = nNum1 + nNum2;
			denFinal = den1;
			startReducing();
		}
		else if (den1 != den2)
		{
			numFinal = (nNum1 * den2) + (nNum2 * den1);
			denFinal = den1 * den2;
			startReducing();
		}
	}

	public static void subtract() {
		
		convert();
		if (den1 == den2)
		{
			numFinal = nNum1 - nNum2;
			denFinal = den1;
			startReducing();
		}
		else if (den1 != den2)
		{
			numFinal = (nNum1 * den2) - (nNum2 * den1);
			denFinal = den1 * den2;
			startReducing();
		}
	}

	public static void multiply() {
		
		convert();
		numFinal = nNum1 * nNum2;
		denFinal = den1 * den2;
		startReducing();
	}

	public static void divide() {
		convert();
		numFinal = nNum1 * den2;
		denFinal = nNum2 * den1;
		startReducing();
	}

	public static void convert() {
		if (den1 == 0 || den2 == 0)
		{
			System.out.println("Error!");
		}
		else
		{
			nNum1 = (whole1 * den1) + num1;
			nNum2 = (whole2 * den2) + num2;
		}
	}

	public static void startReducing() {
		if (denFinal == 1)
		{
			System.out.println(numFinal);
		}
		else if (numFinal == 0)
		{
			System.out.println("0");
		}
		else if (numFinal == denFinal)
		{
			System.out.println("1");
		}
		else if (denFinal == 0)
		{
			System.out.println("Error!");
		}
		else if (den1 == 0 || den2 == 0)
		{
			System.out.println("Error!");
		}
		else
		{
			regReducing();
		}
	}

	public static void regReducing() {
		int x = Math.abs(numFinal);
		int y = Math.abs(denFinal);
		int gcd = 1;
		if (x < y)
		{
			for (int i = 1; i <= y; i++)
			{
				if (y % i == 0 && x % i == 0)
				{
					gcd = i;
				}
			}
			x = x / gcd;
			y = y / gcd;

			if (numFinal < 0 || denFinal < 0)
			{
				System.out.println("-"+x+"/"+y);
			}
			else
			{
				System.out.println(x+"/"+y);
			}
		}
		else if (x > y)
		{
			int whole = x / y;
			int num = x % y;
			int den = y;
			for (int i = 1; i <= den; i++)
			{
				if (den % i == 0 && x % i == 0)
				{
					gcd = i;
				}
			}
			num = num / gcd;
			den = den / gcd;
			if (num == 0)
			{
				System.out.println(whole);
			}
			else if (numFinal < 0 && num == 0)
			{
				System.out.println("-"+whole);
			}
			else if (numFinal < 0 || denFinal < 0)
			{
				System.out.println("-"+whole+"_"+num+"/"+den);
			}
			else
			{
				System.out.println(whole+"_"+num+"/"+den);
			}
		}
	}
	
}


