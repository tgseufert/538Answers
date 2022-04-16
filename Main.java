package WordSearch;

import java.io.*;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException {

     ArrayList<String> arr1=getWords(url_1);
     ArrayList<String> arr2=getWords(url_2);



     ArrayList<List<String>> matches = new ArrayList<List<String>>();
        for (String word:arr1) {
            for (String word2:arr2) {
                if(word.length()==(word2.length()-1)&&wordContains(word2,word)){
                    List<String> wordPair =new ArrayList<String>();
                    wordPair.add(word);
                    wordPair.add(word2);
                    matches.add(wordPair);
                }
            }
        }

        System.out.println(matches.size());
        for (List<String> words:matches
             ) {
            System.out.println(words);
        }


    }

    public static boolean wordContains(String word2, String word) {
        String vowels="aieou";
        int checker=0;
        char badChar='t';
        for (int i = 0; i < word.length(); i++) {
            if (checker > 1) {
                return false;
            }

            if (word.charAt(i) != word2.charAt(i+checker)) {

                badChar=word2.charAt(i);
                i--;
                checker++;
            }
        }

        return (checker<2&&vowels.indexOf(badChar)!=-1);
    }


    public static ArrayList<String> getWords(URL url) throws IOException {

        BufferedReader br =new BufferedReader(new InputStreamReader(url.openStream()));

        String st;
        ArrayList<String> words = new ArrayList<String>();

        while ((st=br.readLine())!=null){
            words.add(st);
        }
        Collections.sort(words);
        br.close();
        return words;
    };

    static URL url_1;
    static URL url_2;

    static {
        try {
            url_2 = new URL("https://raw.githubusercontent.com/nathanmerrill/wordsbysyllables/master/2-syllable-words.txt");
            url_1 = new URL(
                    "https://raw.githubusercontent.com/nathanmerrill/wordsbysyllables/master/1-syllable-words.txt"    );
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    
}
