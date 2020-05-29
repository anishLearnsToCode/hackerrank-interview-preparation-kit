package graphs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MatrixUnionJoin {
    static int minTime(int[][] roads, int[] machines) {
        Arrays.sort(roads, (x, y) -> Integer.compare(y[2], x[2]));

        int n = roads.length + 1;
        Map<Integer,Integer> parent = new HashMap<>();
        boolean[] red = new boolean[n];
        for(int machine: machines) red[machine]=true;

        // Find by Path Splitting
        java.util.function.IntFunction<Integer> find = x -> {
            while(x != parent.getOrDefault(x, x)) {
                x = parent.put(x, parent.get(x));
            }
            return x;
        };

        // Union by Size
        int[] size = new int[n];
        for(int i = 0; i < n; i++) size[i] = 1;
        java.util.function.Function<int[],Integer> union = roadArray -> {
            int root1 = find.apply(roadArray[0]);
            int root2 = find.apply(roadArray[1]);
            if(red[root1] && red[root2]) return roadArray[2];

            if(root1 != root2) {
                if(size[root1] > size[root2]) {
                    int r = root1;
                    root1 = root2;
                    root2 = r;
                }
                parent.put(root1, root2);
                size[root2] += size[root1];
            }

            red[root1] |= red[root2];
            red[root2] |= red[root1];
            return 0;
        };

        return Arrays.stream(roads)
                .map(roadArray -> union.apply(roadArray))
                .reduce(0, Integer::sum);
    }
}
