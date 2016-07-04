import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

import java.util.Map;
import java.util.Optional;

/**
 * 拿客
 * www.coderknock.com
 * QQ群：213732117
 * 创建时间：2016年07月04日
 * 描述：
 */
public class Maven {
    public static String getLatestVersion(String groupId, String artifactId) {
        System.out.print(groupId + " " + artifactId + "------>");
        try {
            HttpResponse response = HttpRequest
                    .get("http://search.maven.org/solrsearch/select?g=" + groupId + "&q=" + artifactId + "&rows=20&wt=json")
                    .timeout(10000)
                    .send();
            Map<String, Map<String, Object>> obj = (Map<String, Map<String, Object>>) JSON.parse(response.bodyText());
            Map<String, Object> reponseObj = obj.get("response");
            JSONArray docs = (JSONArray) reponseObj.get("docs");

            Optional<Object> result = docs.stream().filter(x ->
                    (groupId.equals(((Map<String, String>) x).get("g")) && artifactId.equals(((Map<String, String>) x).get("a")))
            ).findFirst();
            Map<String, String> value = (Map<String, String>) result.get();
            System.out.println(value.get("latestVersion"));
            return value.get("latestVersion");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
