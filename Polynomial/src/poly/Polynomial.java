package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @author runb-cs112
 *
 */
public class Polynomial {

	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
			throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}

	/**
	 * Returns the sum of two polynomials - DOES NOT change eithnur of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION

		Node outputFront = null;
		Node outputRear = null;

		Node p1 = poly1;
		Node p2 = poly2;

		while(p1!=null || p2!=null){
			Node n = null;
			if(p1!=null && p2==null){
				n = new Node(p1.term.coeff, p1.term.degree, null);
				p1 = p1.next;
			}
			else if(p1==null && p2!=null){
				n = new Node(p2.term.coeff, p2.term.degree, null);
				p2 = p2.next;
			}
			else{
				if(p1.term.degree == p2.term.degree && p1.term.coeff + p2.term.coeff != 0){
					n = new Node(p1.term.coeff + p2.term.coeff, p1.term.degree, null);
					p1 = p1.next;
					p2 = p2.next;
				}
				else if(p1.term.degree == p2.term.degree && p1.term.coeff + p2.term.coeff == 0){
					p1 = p1.next;
					p2 = p2.next;
				}
				else if(p1.term.degree > p2.term.degree){
					n = new Node(p2.term.coeff, p2.term.degree, null);
					p2 = p2.next;
				}
				else{
					n = new Node(p1.term.coeff, p1.term.degree, null);
					p1 = p1.next;
				}
			}
			if(n!=null){
				if(outputFront == null){
					outputFront = n;
					outputRear = n;
				}
				else{
					outputRear.next = n;
					outputRear = n;
				}
			}
		}
		return outputFront;
	}

	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION

		Node p1 = poly1;
		Node outputFront = null;

		while(p1!=null){
			Node p2 = poly2;
			Node tempFront = null;
			Node tempRear = null;
			Node temp = null;
			while(p2 != null){
				temp = new Node(p1.term.coeff*p2.term.coeff, p1.term.degree+p2.term.degree, null);
				if(tempFront == null){
					tempFront = temp;
					tempRear = temp;
					p2 = p2.next;
				}
				else{
					tempRear.next = temp;
					tempRear = temp;
					p2 = p2.next;
				}
			}
			outputFront = add(outputFront, tempFront);
			p1 = p1.next;
		}
		return outputFront;
	}

	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION

		float result = 0;
		Node p = poly;

		while(p != null){
			result = result + (float)(p.term.coeff*Math.pow(x, p.term.degree));
			p = p.next;
		}
		return result;
	}

	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 

		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
				current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}	
}
