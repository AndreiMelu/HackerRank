package OtherStuff;

//Compiler version 1.8.0_111

public class Ski {
    /**
     * @param {string[][]} travel_time
     * @param {string[][]} points
     * @param {string}     start_token
     * @return Result = {Int, []} = {total, path}
     */

//    public static Resul distance_path(String[][] travel_time, String[][] points, String start_token) {
//        Resul res = new Resul();
//        int total = 0;
//        String[] path = null;
//        // Put your code here to calculate total and path
//        int first = 0;
//        int second = 0;
//        int median = 0;
//        int pToAdd = 0;
//        String toAdd = travel_time[0][0];
//
//        for (int i = 0; i < travel_time.length - 1; i++) {
//            while (travel_time[i][0].equals(travel_time[i + 1][0])) {
//
//                for (int j = 0; j < points.length; j++) {
//                    if (points[j][0].equals(travel_time[i][0])) {
//                        first = Integer.parseInt(points[j][1]);
//                    } else if (points[j][0].equals(travel_time[i + 1][0]))
//                        second = Integer.parseInt(points[j][1]);
//                }
//                if (first / Integer.parseInt(travel_time[i][2]) > second / Integer.parseInt(travel_time[i + 1][2])) {
//                    toAdd = travel_time[i][1];
//                    pToAdd = first;
//                } else {
//                    pToAdd = second;
//                }
//                i++;
//            }
//            total += pToAdd;
//            path[i] = toAdd;
//
//            if (!travel_time[i][0].equals(travel_time[i + 1][0])) {
//                while (travel_time[i][0].equals(travel_time[i + 1][0])) {
//
//                    for (int j = 0; j < points.length; j++) {
//                        if (points[j][0].equals(travel_time[i][0])) {
//                            first = Integer.parseInt(points[j][1]);
//                        } else if (points[j][0].equals(travel_time[i + 1][0]))
//                            second = Integer.parseInt(points[j][1]);
//                    }
//                    if (first / Integer.parseInt(travel_time[i][2]) > second / Integer.parseInt(travel_time[i + 1][2])) {
//                        toAdd = travel_time[i][1];
//                        pToAdd = first;
//                    } else {
//                        pToAdd = second;
//                    }
//                    i++;
//                }
//                total += Integer.parseInt(points[i][1]);
//                path[i] = travel_time[1][0];
//            }
//            int b[]=new int[3];
//        }
//
//        // Return the result, do not change the structure
//        res.total = total;
//        if (path == null) {
//            path = new String[]{};
//        }
//        res.path = path;
//        return res;
//    }
}

//class Resul {
//    int total;
//    String[] path;
//}

//class Main {
//    public static void main(String args[]) {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
//        List<String> lines = br.lines().collect(Collectors.toList());
//        int time_section_count = 0;
//        int total_lines = lines.size();
//        while (lines.get(time_section_count) != null && !lines.get(time_section_count).isEmpty()) time_section_count++;
//        String[][] travel_time = new String[time_section_count][];
//        String[][] points = new String[total_lines - time_section_count - 1][];
//        for (int index = 0; index < time_section_count; index++) travel_time[index] = lines.get(index).split(" ");
//        for (int index = time_section_count + 1; index < total_lines; index++)
//            points[index - time_section_count - 1] = lines.get(index).split(" ");
//
//        String start_token = "START";
//        Resul res = Ski.distance_path(travel_time, points, start_token);
//        System.out.println(String.valueOf(res.total) + " " + String.join(",", res.path));
//    }
//}