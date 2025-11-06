import java.util.Scanner;

public class encoding {

    // Node for Huffman Tree
    static class Node {
        char ch;
        int freq;
        Node left, right;

        Node(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
    }

    // Recursive function to generate Huffman codes
    static void generateCodes(Node root, String code, String[] chars, String[] codes, int n) {
        if (root == null)
            return;

        // Leaf node → store code
        if (root.left == null && root.right == null) {
            for (int i = 0; i < n; i++) {
                if (chars[i].charAt(0) == root.ch) {
                    codes[i] = code;
                    break;
                }
            }
            return;
        }

        generateCodes(root.left, code + "0", chars, codes, n);
        generateCodes(root.right, code + "1", chars, codes, n);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the string to encode: ");
        String text = sc.nextLine();

        // Step 1: Count character frequency manually
        int[] freq = new int[256]; // ASCII size
        for (int i = 0; i < text.length(); i++) {
            freq[text.charAt(i)]++;
        }

        // Step 2: Store unique characters
        int n = 0;
        for (int i = 0; i < 256; i++) {
            if (freq[i] > 0) n++;
        }

        Node[] nodes = new Node[n];
        String[] chars = new String[n];
        int index = 0;
        for (int i = 0; i < 256; i++) {
            if (freq[i] > 0) {
                nodes[index] = new Node((char) i, freq[i]);
                chars[index] = Character.toString((char) i);
                index++;
            }
        }

        // Step 3: Build Huffman Tree manually (by sorting each time)
        while (n > 1) {
            // Sort nodes by frequency (simple bubble sort)
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (nodes[i].freq > nodes[j].freq) {
                        Node temp = nodes[i];
                        nodes[i] = nodes[j];
                        nodes[j] = temp;
                    }
                }
            }

            // Combine two smallest nodes
            Node left = nodes[0];
            Node right = nodes[1];
            Node parent = new Node('\0', left.freq + right.freq);
            parent.left = left;
            parent.right = right;

            // Replace first two with parent node
            nodes[0] = parent;
            for (int i = 1; i < n - 1; i++) {
                nodes[i] = nodes[i + 1];
            }
            n--; // Reduce size
        }

        Node root = nodes[0];

        // Step 4: Generate Huffman codes
        String[] codes = new String[chars.length];
        generateCodes(root, "", chars, codes, chars.length);

        // Step 5: Print codes
        System.out.println("\nHuffman Codes:");
        for (int i = 0; i < chars.length; i++) {
            System.out.println(chars[i] + " : " + codes[i]);
        }

        // Step 6: Encode the text
        System.out.println("\nEncoded string:");
        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            for (int j = 0; j < chars.length; j++) {
                if (chars[j].charAt(0) == ch) {
                    encoded.append(codes[j]);
                    break;
                }
            }
        }
        System.out.println(encoded);

        sc.close();
    }
}
