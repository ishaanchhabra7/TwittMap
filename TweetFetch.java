package tweetmap;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import twitter4j.*;
import twitter4j.auth.AccessToken;

public class TweetFetch {

	public static void main(String[] args) {

	    final Twitter twitter = new TwitterFactory().getInstance();
	    final  AccessToken accessToken = new AccessToken("XXX", "YYY");
	    //twitter.setOAuthConsumer("ZZZ", "QQQ");
	    twitter.setOAuthAccessToken(accessToken);
		
	    try {
	        Query query = new Query("london");
	        query.geoCode(new GeoLocation(37.1,-121.1),4000.0,"mi");
	        QueryResult result;
	        System.out.println("Searching...");
	        int Count=0;

	        do {
	            result = twitter.search(query);
	            List<Status> tweets = result.getTweets();
	           //System.out.println("tweets: "+tweets.size());
	            for (Status tweet : tweets) {
	                if(tweet.getGeoLocation()!=null)
	                {
	        	    	String s = tweet.getText();
	        	    	s = s.replaceAll(","," ");
	        	    	s = s.replaceAll("\n", " ");
	        	    	String s2 = tweet.getUser().getName();
	        	    	s2 = s2.replaceAll(","," ");
	        	    	s2 = s2.replaceAll("\n", " ");
	                    System.out.println(tweet.getCreatedAt()+" "+s+" "+tweet.getGeoLocation());
	                    System.out.println();
	                    Count++;
	                }
	            }
	        }
	        while ((query = result.nextQuery()) != null);
	        System.out.println(Count);
	        System.exit(0); 
	    } catch (TwitterException te) {
	        te.printStackTrace();
	        
	        System.out.println("Failed to search tweets: " + te.getMessage());
	        System.exit(-1);
	    }
	}
}

