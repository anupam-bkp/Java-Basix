/**
 * 
 */
package concurrentskiplistmap;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author Bhagvan Kommadi
 *This example demonstrates the usage of the ConcurrentSkipListMap 
 *
 */
public class ConcurrentSkipListMapExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConcurrentSkipListMap<String,String> concurrentSkipListMap = new ConcurrentSkipListMap<String,String>();
        concurrentSkipListMap.put("1111", "Tom Smith");
        concurrentSkipListMap.put("2222","David Jones");
        concurrentSkipListMap.put("3333", "Jim Anderson");
        concurrentSkipListMap.put("4444", "John Abraham");
        concurrentSkipListMap.put("5555", "Brad Pitt");
        
        System.out.println("The name associated with id 1111 is "+ concurrentSkipListMap.get("1111"));
        
        NavigableSet<String> navigableKeySet = concurrentSkipListMap.keySet();
	
	    
	    System.out.println("The keys associated with this map are ");
	    for(Iterator<String> iterator = navigableKeySet.iterator();iterator.hasNext();)
	    {
	    	System.out.println(iterator.next());
	    }
	    
	    ConcurrentNavigableMap<String,String> subMap = concurrentSkipListMap.subMap("1111", "3333");
	    
	    NavigableSet<String> navigableSubKeySet = subMap.keySet();
	    
	    System.out.println("The keys associated with the submap keys from 1111 to 3333 are");
	    
	    for(Iterator<String> subMapIterator = navigableSubKeySet.iterator(); subMapIterator.hasNext();)
	    {
	    	System.out.println(subMapIterator.next());
	    }
	    
	    ConcurrentNavigableMap<String,String> headerMap = concurrentSkipListMap.headMap("2222");
	    
	    System.out.println("The keys associated with the headMap less than 2222");
	    
	    NavigableSet<String> navigableHeadMapKeySet = headerMap.keySet();
	    
	    for(Iterator<String> headMapIterator = navigableHeadMapKeySet.iterator(); headMapIterator.hasNext();)
	    {
	    	System.out.println(headMapIterator.next());
	    }
	    
	    ConcurrentNavigableMap<String,String> tailMap = concurrentSkipListMap.tailMap("1111");
	    
	    System.out.println("The keys associated with the tailMap greater than 1111");
	    NavigableSet<String> navigableTailMapKeySet = tailMap.keySet();
	    
	    for(Iterator<String> tailMapIterator = navigableTailMapKeySet.iterator(); tailMapIterator.hasNext();)
	    {
	    	System.out.println(tailMapIterator.next());
	    }
	}

}
