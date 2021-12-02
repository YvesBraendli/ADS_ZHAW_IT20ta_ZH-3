/**
 * MyVisitor -- Diese Klasse wird für die Tests verwendet
 *
*/

package ch.zhaw.ads;

class MyVisitor<T> implements Visitor<T> {
    StringBuilder output;


    MyVisitor() {
        output = new StringBuilder();
    }

    public void visit(T s) {
        output.append(s);
    }

    public String toString() {
        return output.toString();
    }


}