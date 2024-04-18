package ict.tag;

import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.util.ArrayList;

public class primeTag extends SimpleTagSupport {

    private int min, max;
    private String tagType;

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void doTag() {
        try {
            PageContext pageContext = (PageContext) getJspContext();
            JspWriter out = pageContext.getOut();
            ArrayList<Integer> primeList = new ArrayList<>();

            
            
            for (int i = min; i <= max; i++) {
                if (isPrime(i)) {
                    primeList.add(i);
                }
            }
            if ("simple".equalsIgnoreCase(tagType)) {
                int size = primeList.size();
                out.print("Primes number between "+min+" and "+max+" are ");
            for (int i = 0; i < size; i++) {
                out.print(primeList.get(i));
                if (i < size - 1) {
                    out.print(",");
                }
                }
            out.print(".");
            } else if ("table".equalsIgnoreCase(tagType)) {
              out.print("Primes number between "+min+" and "+max+" are follows:");
                out.print("<table><tr>");
                for (int i = 0; i < primeList.size(); i++) {
                out.print("<th>"+primeList.get(i)+"</th>");
                }
                        out.print("</tr></table>");
            } else {
                out.println("No such type");
            }
        } catch (IOException ioe) {
        } catch (IllegalArgumentException e) {
}
    }
}
