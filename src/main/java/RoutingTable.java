import java.util.LinkedList;

public class RoutingTable {

    private int nodeId;
    private int k;
    private LinkedList<Integer>[] buckets;

    public RoutingTable(int nodeId, int k) {
        this.nodeId = nodeId;
        this.k = k;
        this.buckets = new LinkedList[32]; // Because we use 32 bits to store int (4 bytes)
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    public void update(int senderId) {
        int i = Integer.numberOfLeadingZeros(nodeId ^ senderId);

        if (buckets[i].contains(senderId)) {
            buckets[i].add(buckets[i].pollFirst());
        } else {
            if (buckets[i].size() < k) {
                buckets[i].add(senderId);

            } else {
                // TODO
                // if full then ping least-recently seen node (first in the list) to decide what to do
                // if fails to respond, remove it and insert new to the tail
                // if responds, move it to the tail, and discard new
            }
        }
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buckets.length; i++) {
            sb.append(i).append(": [");

            for (int j = 0; j < buckets[i].size(); j++) {
                sb.append(buckets[i].get(j));
                if (j != buckets[i].size()-1) sb.append(",");
            }
            sb.append("]").append("\n");
        }
        return sb.toString();
    }

}
