// зк

/** % https://www.cs.princeton.edu
 https://www.cs.princeton.edu
 http://www.w3.org
 http://drupal.org
 http://www.princeton.edu
 http://csguide.cs.princeton.edu
 https://www.facebook.com
 https://twitter.com
 https://www.linkedin.com
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCrawler {
    public static void main(String[] args) {

        // timeout connection after 500 miliseconds
        System.setProperty("sun.net.client.defaultConnectTimeout", "500");
        System.setProperty("sun.net.client.defaultReadTimeout",    "1000");

        // initial web page
        String s = args[0];

        // list of web pages to be examined
        Queue<String> queue = new LinkedList<>();
        queue.add(s);

        // Set of examined web pages
        Set<String> marked = new TreeSet<>();
        marked.add(s);

        // BFS crawl of web
        while (!queue.isEmpty()) {
            String v = queue.remove();
            StdOut.println(v);

            In in = new In(v);
            String input = in.readAll().toLowerCase();

            /*************************************************************
             *  Find links of the form: http://xxx.yyy.com
             *  \\w+ for one or more alpha-numeric characters
             *  \\. for dot
             *************************************************************/

            String regexp = "(http|https)://(\\w+\\.)+(edu|com|org|gov)";
            Pattern pattern = Pattern.compile(regexp);
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                String w = matcher.group();
                if (!marked.contains(w)) {
                    marked.add(w);
                    queue.add(w);
                }
            }
        }
    }
}
