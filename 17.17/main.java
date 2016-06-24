import java.util.ArrayList;

public class main {
	HashMapList<String, Integer> searchAll(String big, String[] smalls){
		HashMapList<String, Integer> lookup = new HashMapList<String, Integer>();
		int maxLen = big.length();
		TrieNode root = createTreeFromStrings(smalls, maxLen).getRoot();
		
		for(int i = 0; i < big.length(); i++){
			ArrayList<String> strings = findStringAtLoc(root, big, i);
			//insertIntoHashMap(strings, lookup, i);
		}
		return lookup;
	}

	private ArrayList<String> findStringAtLoc(TrieNode root, String big, int start) {
		ArrayList<String> strings = new ArrayList<String>();
		int index = start;
		while(index < big.length()){
			root = root.getChild(big.charAt(index));
			if(root == null){
				break;
			}
			if(root.terminates()){
				strings.add(big.substring(start, index + 1));
			}
		}
		return strings;
	}

	private Trie createTreeFromStrings(String[] smalls, int maxLen) {
		Trie tree = new Trie("");
		for(String s : smalls){
			if(s.length() <= maxLen){
				tree.insertString(s, 0);
			}
		}
		return null;
	}
	
	
	
}
