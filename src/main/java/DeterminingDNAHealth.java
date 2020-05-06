import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.*;
        import java.util.function.Consumer;
        import java.util.function.Predicate;

public class DeterminingDNAHealth {


    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            int n = Integer.parseInt(in.readLine());
            String genes = in.readLine();
            String health = in.readLine();

            Dna dna = new Dna();
            parse(dna, n, genes, health);

            DnaHealth dnaHealth = new DnaHealth(dna);
            int s = Integer.parseInt(in.readLine());
            for (int i = 0; i < s; i++) {
                String str = in.readLine();
                LongParser longs = new LongParser(str);
                int first = (int) longs.next();
                int last = (int) longs.next();
                dnaHealth.processStrand(first, last, str, skipUntil(str, longs.pos, Character::isAlphabetic));
            }

            System.out.printf("%d %d\n", dnaHealth.minHealth(), dnaHealth.maxHealth());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void parse(Dna dna, int n, String geneStr, String healthStr) {
        int pos = 0;
        LongParser healthParser = new LongParser(healthStr);
        for (int i = 0; i < n; ++i)
            pos = dna.parseGene(i, geneStr, pos, healthParser.next());
        dna.setSuffixes();
    }

    static class LongParser {
        final String str;
        int pos;

        LongParser(String str) {
            this.str = str;
        }

        long next() {
            pos = skipUntil(str, pos, Character::isDigit);
            StringBuilder sb = new StringBuilder(32);
            char ch;
            for (; pos < str.length() && Character.isDigit(ch = str.charAt(pos)); ++pos)
                sb.append(ch);
            return Long.parseLong(sb.toString());
        }
    }

    private static int skipUntil(String str, int pos, Predicate<Character> predicate) {
        while (pos < str.length() && !predicate.test(str.charAt(pos)))
            ++pos;
        return pos;
    }
}

class DnaHealth {
    private final Dna dna;
    private long minHealth = Long.MAX_VALUE;
    private long maxHealth = Long.MIN_VALUE;

    long outputs;

    DnaHealth(Dna dna) {
        this.dna = dna;
    }

    long minHealth() {
        return minHealth;
    }

    long maxHealth() {
        return maxHealth;
    }

    void processStrand(int first, int last, String d) {
        processStrand(first, last, d, 0);
    }

    void processStrand(int first, int last, String d, int start) {
        long health = getHealth(first, last, d, start);
        if (health < minHealth) minHealth = health;
        if (health > maxHealth) maxHealth = health;
    }

    private long getHealth(int first, int last, String d, int start) {
        final long[] health = new long[]{0};
        dna.search(d, start, (GenesHealth genesHealth) -> {
            health[0] += genesHealth.health(first, last);
            outputs += 1;
        }, first, last);
        return health[0];
    }
}

class Dna {
    private final Node root;

    Dna() {
        this.root = new Node(null, '\0');
    }

    Dna(String[] genes, long[] health) {
        this();
        for (int i = 0; i < genes.length; ++i)
            addGene(i, genes[i], health[i]);
        setSuffixes();
    }

    private void addGene(int id, String str, long health) {
        parseGene(id, str, 0, health);
    }

    int parseGene(int id, String str, int start, long health) {
        int i = start;
        while (i < str.length() && !Character.isAlphabetic(str.charAt(i)))
            ++i;
        char letter;
        Node node = root;
        for (; i < str.length() && Character.isAlphabetic(letter = str.charAt(i)); ++i) {
            Node next = node.findChild(letter, null);
            if (next == null)
                node.addChild(next = new Node(node, letter));
            node = next;
        }
        if (node.output == null)
            node.output = new GenesHealth(id, health);
        else
            node.output.put(id, health);
        return i;
    }

    void setSuffixes() {
        Queue<Node> queue = new ArrayDeque<>();

        // `root` and its immediate children point back to `root`
        root.suffix = root;
        for (Node gene : root.children()) {
            gene.suffix = root;
            queue.addAll(gene.children());
        }

        // everyone else discovers their suffix from their parent
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            queue.addAll(node.children());

            Node suffix = node.parent;
            do {
                suffix = suffix.suffix;
                node.suffix = suffix.findChild(node.letter, suffix == root ? root : null);
            } while (node.suffix == null);

            node.suffixOutput = node.suffix.output == null
                    ? node.suffix.suffixOutput
                    : node.suffix;
        }
    }

    void search(String str, Consumer<GenesHealth> output) {
        search(str, 0, output, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    void search(String str, int start, Consumer<GenesHealth> output, int first, int last) {
        Node node = root;
        for (int i = start; i < str.length(); ++i) {
            char letter = str.charAt(i);

            Node next;
            do {
                next = node.findChild(letter, null);
                if (next != null) node = next;
                else if (node == root) break;
                else node = node.suffix;
            } while (next == null);

            for (Node suffix = node.suffixOutput; suffix != null; suffix = suffix.suffixOutput)
                output(suffix.output, output, first, last);

            if (node.output != null)
                output(node.output, output, first, last);
        }
    }

    private void output(GenesHealth output, Consumer<GenesHealth> callback, int first, int last) {
        if (first > output.idMax) return;
        if (last < output.idMin) return;
        callback.accept(output);
    }
}

class Node {
    final Node parent;
    final char letter;
    private Node child; // optimization for single child
    private HashMap children;
    GenesHealth output;
    Node suffix;
    Node suffixOutput;

    Node(Node parent, char letter) {
        this.parent = parent;
        this.letter = letter;
    }

    Collection<Node> children() {
        if (children != null) return children.values();
        ArrayList<Node> r = new ArrayList<>(1);
        if (child != null) r.add(child);
        return r;
    }

    void addChild(Node child) {
        if (this.children == null) {
            if (this.child == null) {
                this.child = child;
                return;
            }
            this.children = new HashMap<>();
            this.children.put(this.child.letter, this.child);
            this.child = null;
        }
        this.children.put(child.letter, child);
    }

    Node findChild(char letter, Node defaultValue) {
        if (child != null)
            return child.letter == letter ? child : defaultValue;
        return children != null ? (Node) children.getOrDefault(letter, defaultValue) : defaultValue;
    }
}

class GenesHealth {
    private int id; // single value optimization
    int idMin;
    int idMax;
    private long health;
    private long healthTotal;
    private TreeMap values;

    GenesHealth(int id, long health) {
        this.id = this.idMin = this.idMax = id;
        this.health = this.healthTotal = health;
    }

    void put(int id, long health) {
        getMap().put(id, health);
        if (id < idMin) this.idMin = id;
        if (id > idMax) this.idMax = id;
        this.healthTotal += health;
    }

    TreeMap getMap() {
        if (values != null)
            return values;
        this.values = new TreeMap<>();
        this.values.put(this.id, this.health);
        return values;
    }

    long health(int first, int last) {
        if (values == null)
            return id >= first && id <= last ? health : 0;
        if (first <= idMin && last >= idMax)
            return healthTotal;
        else if (first > idMax || last < idMin)
            return 0;
        long r = 0;
        for (Object health : values.subMap(first, true, last, true).values())
            r += Long.parseLong(health.toString());
        return r;
    }

    Set keySet() {
        if (values != null)
            return values.keySet();
        HashSet<Integer> ks = new HashSet<>();
        ks.add(id);
        return ks;
    }
}


// class Test{
//    private static int firstLast(Map<String, Integer> map, String s) {
//        int result = 0;
//        for (int i = 0; i < s.length(); i++) {
//            for (int j = i + 1; j <= s.length(); j++) {
//                if (map.containsKey(s.substring(i, j))) {
//                    result += map.get(s.substring(i, j));
//                    break;
//                }
//            }
//        }
//        return result;
//    }
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        int size = Integer.parseInt(reader.readLine().trim());
//        String[] array = new String[size];
//        int index = 0;
//        for (String s : reader.readLine().trim().split(" ")) array[index++] = s;
//        index = 0;
//        int[] values = new int[size];
//        for (String s : reader.readLine().trim().split(" "))
//            values[index++] = Integer.parseInt(s);
//        int number = Integer.parseInt(reader.readLine().trim());
//        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
//        String[] buffer = new String[3];
//        Map<String, Integer> map;
//        for (int i = 0; i < number; i++) {
//            index = 0;
//            for (String s : reader.readLine().trim().split(" ")) buffer[index++] = s;
//            int first = Integer.parseInt(buffer[0]);
//            int second = Integer.parseInt(buffer[1]);
//            map = new HashMap<>(second - first + 1);
//            for (int j = first; j <= second; j++) {
//                if (map.containsKey(array[j]))
//                    map.replace(array[j], map.get(array[j]) + values[j]);
//                else
//                    map.put(array[j], values[j]);
//            }
//            int result = firstLast(map, buffer[2]);
//            map.clear();
//            max = Math.max(max, result);
//            min = Math.min(min, result);
//        }
//        reader.close();
//        System.out.print(min + " " + max);
//    }
//}



//--Optimized--
//        import java.io.BufferedReader;
//        import java.io.IOException;
//        import java.io.InputStreamReader;
//        import java.util.ArrayList;
//        import java.util.List;
//        import java.util.Scanner;
//        import java.util.stream.Collectors;
//
//class TrieNode
//{
//    public TrieNode[] children = new TrieNode[26];
//    public List<Integer> genWeighIndex = new ArrayList<>();
//
//    public boolean isGen = false;
//    public int genWeighIndexMin=-1;
//    public int genWeighIndexMax=-1;
//    public long sumWeight=0;
//    public boolean weightSorted = false;
//
//    public TrieNode(){};
//    public TrieNode[] getChildren()
//    {
//        return children;
//    }
//    public void setIsGen()
//    {
//        this.isGen = true;
//    }
//
//    public  void setGenWeighIndex(int index)
//    {
//        genWeighIndex.add(index);
//        if(genWeighIndexMax == -1 || genWeighIndexMax<index)
//            genWeighIndexMax = index;
//        if(genWeighIndexMin == -1 || genWeighIndexMin>index)
//            genWeighIndexMin = index;
//    }
//}
//
//class Trie
//{
//    private TrieNode root;
//    public int[][] weights;//[node index][index of weight]
//
//    public Trie()
//    {
//        this.root = new TrieNode();
//    }
//    public  void insert(StringBuilder word, int[] healthItems)
//    {
//        TrieNode current = this.root;
//        int count =0;
//        int last=0;
//        for(int i=0; i<word.length(); i++)
//        {
//            char tmp = word.charAt(i);
//            if(tmp != ' ')
//            {
//                int indexNode = tmp-'a';
//                if(current.children[indexNode] == null)
//                {
//                    current.children[indexNode] = new TrieNode();
//                }
//                current = current.children[indexNode];
//            }
//            else
//            {
//                current.setGenWeighIndex(count);
//                current.sumWeight+=healthItems[count];
//                current.setIsGen();
//                count++;
//                current = this.root;
//            }
//        }
//        current.sumWeight+=healthItems[count];
//        current.setGenWeighIndex(count);
//        current.setIsGen();
//    }
//
//
//
//    public long countWeight(String word, int start, int end, int[] weights)
//    {
//        TrieNode current = this.root;
//        long count =0;
//        int deep = 0;
//        int last =0;
//        for(int i=0; i<word.length(); i++)
//        {
//            current = this.root;
//            for(int j=i; j< word.length(); j++)
//            {
//                int tmp = word.charAt(j)-'a';
//                current = current.children[tmp];
//                if(current != null && current.isGen)
//                {
//                    if(!current.weightSorted)
//                    {
//                        current.genWeighIndex = current.genWeighIndex.stream().sorted().collect(Collectors.toList());
//                        current.weightSorted = true;
//                    }
//                    long tmpCount = current.sumWeight;
//                    //System.out.println(current.sumWeight);
//                    //System.out.println(start + " " + end  + " "+ current.genWeighIndexMin + " "+current.genWeighIndexMax);
//                    /*for(int k= 0; k<current.genWeighIndex.size(); k++)
//                    {
//                        int tmpIndex = current.genWeighIndex.get(k);
//                        if(tmpIndex <= end && tmpIndex >=start) {
//                            count += weights[tmpIndex];
//                        }
//                    }*/
//                    for(int k=current.genWeighIndex.size()-1; k>=0; k--)
//                    {
//                        int tmpIndex = current.genWeighIndex.get(k);
//                        if(tmpIndex >end) {
//                            tmpCount -= weights[tmpIndex];
//                        }
//                        else
//                            break;
//                    }
//
//                    for(int k=0; k<current.genWeighIndex.size(); k++)
//                    {
//                        int tmpIndex = current.genWeighIndex.get(k);
//                        if(tmpIndex <start) {
//                            tmpCount -= weights[tmpIndex];
//                        }
//                        else
//                            break;
//                    }
//
//                    count+=tmpCount;
//                }
//                else if(current == null)
//                    break;
//            }
//        }
//        return count;
//        //
//    }
//}
//
//public class Main {
//
//
//
//
//    private static final Scanner scanner = new Scanner(System.in);
//    private static final InputStreamReader isr=new InputStreamReader(System.in);
//
//
//    public static void main(String[] args) {
//        try{
//            BufferedReader br= new BufferedReader(isr);
//            int n = Integer.valueOf(br.readLine());
//            StringBuilder st= new StringBuilder(br.readLine());
//
//
//
//            StringBuilder tmpHealthItems = new StringBuilder(br.readLine());
//            int[] healthItems = new int[n];
//            int count =0;
//            int last =0;
//            for(int i =0; i< tmpHealthItems.length(); i++)
//            {
//                if(tmpHealthItems.charAt(i)==' ')
//                {
//                    healthItems[count]= Integer.valueOf(tmpHealthItems.substring(last, i));
//                    count++;
//                    last = i+1;
//                }
//            }
//            healthItems[count]= Integer.valueOf(tmpHealthItems.substring(last));
//            Trie trie = new Trie();
//            trie.insert(st, healthItems);
//
//            int s = Integer.valueOf(br.readLine());
//
//            long max =-1;
//            long min =-1;
//            for(int j=0; j<s; j++)
//            {
//                String[] inputSequens = br.readLine().split(" ");
//                long current = 0;
//
//                int node = 0;
//                String string = inputSequens[2];
//                current = trie.countWeight(string, Integer.valueOf(inputSequens[0]), Integer.valueOf(inputSequens[1]), healthItems);
//                if(min ==-1 || min>current)
//                    min = current;
//                if(max ==-1 || max< current) {
//                    max = current;
//                }
//
//            }
//
//            System.out.println(min+" "+max);
//        }
//        catch(IOException ex)
//        {}
//    }
//}